import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class  Engine extends JPanel implements ActionListener, KeyListener {

    private double drawFPS;
    private double prevTime;
    private int fpsChecks = 0;
    private final Camera camera;
    private final HashMap<Mesh, Vec2[]> meshes = new HashMap<>();
    private final Matrix3X3 projectionMatrix = new ProjectionMatrix();
    private final int frameHeight;
    private final int frameWidth;
    private final Vec3 lightSource = new Vec3(0, 0, 5);
    private Timer timer;

    private Engine (Camera camera,
                    int delay,
                    int frameWidth,
                    int frameHeight) {
        this.camera = camera;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.timer = new Timer(delay, this);
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
    }

    private void startEngine () {
        timer.start();
    }

    private void stopEngine () {
        timer.stop();
    }

    private void addMesh (Mesh mesh) {
        meshes.put(mesh, new Vec2[mesh.getVerticesCount()]);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        drawTriangles(g);
        drawFPS(g);
    }

    private void drawFPS (Graphics g) {
        calculateFPS();
        g.setColor(Color.black);
        g.setFont(new Font("default", Font.BOLD, 12));
        g.drawString("FPS: "+(int)drawFPS, 140, 20);
    }

    private void drawTriangles (Graphics g) {

        ArrayList<Triangle> triangles = new ArrayList<>();

        for (Mesh mesh : meshes.keySet()) {

            Vec2[] projectedVertices = meshes.get(mesh);
            for (Pair<Triple<Integer>, Triple<Vec2>> face : mesh.getFaces()){

                final int iVertex1 = face.getE1().getE1();
                final int iVertex2 = face.getE1().getE2();
                final int iVertex3 = face.getE1().getE3();

                Vec2 projectedPoint1 = projectedVertices[iVertex1];
                Vec2 projectedPoint2 = projectedVertices[iVertex2];
                Vec2 projectedPoint3 = projectedVertices[iVertex3];

                final boolean onScreen = (isOnScreen(projectedPoint1.getW()) &&
                                          isOnScreen(projectedPoint2.getW()) &&
                                          isOnScreen(projectedPoint3.getW()));

                final boolean inFront = isInFront(projectedPoint1,
                                                  projectedPoint2,
                                                  projectedPoint3);

                if (onScreen && inFront){

                    Vec3 unProjectedP1 = mesh.getVertex(iVertex1);
                    Vec3 unProjectedP2 = mesh.getVertex(iVertex2);
                    Vec3 unProjectedP3 = mesh.getVertex(iVertex3);

                    final double shade = calculateShade(unProjectedP1,
                                                        unProjectedP2,
                                                        unProjectedP3);
                    final double zDepth = (unProjectedP1.getZ() +
                                           unProjectedP2.getZ() +
                                           unProjectedP3.getZ())/ 3;

                    final ProjectedPoint p1 = new ProjectedPoint(projectedPoint1.getX(),
                                                                 projectedPoint1.getY(),
                                                                 face.getE2().getE1());
                    final ProjectedPoint p2 = new ProjectedPoint(projectedPoint2.getX(),
                                                                 projectedPoint2.getY(),
                                                                 face.getE2().getE2());
                    final ProjectedPoint p3 = new ProjectedPoint(projectedPoint3.getX(),
                                                                 projectedPoint3.getY(),
                                                                 face.getE2().getE3());

                    Triangle triangle = new Triangle(p1, p2, p3,
                                                     zDepth, shade,
                                                     mesh.getTexture());

                    triangles.add(triangle);
                }
            }
        }
        Collections.sort(triangles);
        for (Triangle face : triangles){
            drawTriangle(face, g);
        }
    }

    private boolean isOnScreen (double w) {
        return 0 <= w;
    }

    private boolean isInFront (Vec2 p1, Vec2 p2, Vec2 p3) {

        final double lace1 = (p1.getX() * p2.getY())
                           + (p2.getX() * p3.getY())
                           + (p3.getX() * p1.getY());

        final double lace2 = (p1.getY() * p2.getX())
                           + (p2.getY() * p3.getX())
                           + (p3.getY() * p1.getX());

        return lace1 - lace2 >= 0;
    }

    private double calculateShade (Vec3 p1, Vec3 p2, Vec3 p3) {

        Vec3 centre = new Vec3((p1.getX()+p2.getX()+p3.getX()) / 3,
                               (p1.getY()+p2.getY()+p3.getY()) / 3,
                               (p1.getZ()+p2.getZ()+p3.getZ()) / 3);

        Vec3 surfNorm = Vec3.surfaceNormalVector(p1, p2, p3).normalize();

        Vec3 lightDirection = new Vec3(lightSource.getX() - centre.getX(),
                                       lightSource.getY() - centre.getY(),
                                       lightSource.getZ() - centre.getZ()).normalize();

        return Math.abs(lightDirection.dotProduct(surfNorm));
    }

    private void drawTriangle (Triangle T, Graphics g) {

        ProjectedPoint p1 = T.getPoint1();
        ProjectedPoint p2 = T.getPoint2();
        ProjectedPoint p3 = T.getPoint3();

        if (p1.getY() > p2.getY()){
            ProjectedPoint temp = p2;
            p2 = p1;
            p1 = temp;
        }
        if (p2.getY() > p3.getY()){
            ProjectedPoint temp = p2;
            p2 = p3;
            p3 = temp;
        }
        if (p1.getY() > p2.getY()){
            ProjectedPoint temp = p2;
            p2 = p1;
            p1 = temp;
        }

        double dP1P2 = 0;
        double dP1P3;
        boolean right = false;
        boolean left = false;

        if (p2.getY() - p1.getY() > 0){
            dP1P2 = (p2.getX()-p1.getX()) / (p2.getY()-p1.getY());
        }
        else if (p2.getX() > p1.getX()){
            right = true;
        }
        else {
            left = true;
        }
        if (p3.getY() - p1.getY() > 0){
            dP1P3 = (p3.getX()-p1.getX()) / (p3.getY()-p1.getY());
        }
        else{
            dP1P3 = 0;
        }

        final int yStart = Math.max((int) (p1.getY()), 0);
        final int yEnd = Math.min((int) (p3.getY()), frameHeight);

        if (right || (!left && dP1P2 > dP1P3)){
            for (int y = yStart; y <= yEnd; y++){
                if (y < p2.getY()){
                    processScanLine(y, T.getTexture(), T.getShading(),
                            p1, p3, p1, p2, g);
                }
                else{
                    processScanLine(y, T.getTexture(), T.getShading(),
                            p1, p3, p2, p3, g);
                }
            }
        }
        else{
            for (int y = yStart; y <= yEnd; y++){
                if (y < p2.getY()){
                    processScanLine(y, T.getTexture(), T.getShading(),
                            p1, p2, p1, p3, g);
                }
                else{
                    processScanLine(y, T.getTexture(), T.getShading(),
                            p2, p3, p1, p3, g);
                }
            }
        }
    }

    private double clamp (double value) {
        return Math.max(0, Math.min(value, 1));
    }

    private double interpolate (double min, double max,
                                double gradient) {
        return min + (max - min) * clamp(gradient);
    }

    private void processScanLine (int y, Texture texture, double shading,
                                  ProjectedPoint pa, ProjectedPoint pb,
                                  ProjectedPoint pc, ProjectedPoint pd,
                                  Graphics g) {

        final double gradient1 = pa.getY() != pb.getY()
                               ? (y - pa.getY()) / (pb.getY() - pa.getY()) : 1;
        final double gradient2 = pc.getY() != pd.getY()
                               ? (y - pc.getY()) / (pd.getY() - pc.getY()) : 1;

        final double su = interpolate(pa.getUvPosition().getX(), pb.getUvPosition().getX(), gradient1);
        final double eu = interpolate(pc.getUvPosition().getX(), pd.getUvPosition().getX(), gradient2);
        final double sv = interpolate(pa.getUvPosition().getY(), pb.getUvPosition().getY(), gradient1);
        final double ev = interpolate(pc.getUvPosition().getY(), pd.getUvPosition().getY(), gradient2);

        final int sx = (int) interpolate(pa.getX(), pb.getX(), gradient1);
        final int ex = (int) interpolate(pc.getX(), pd.getX(), gradient2);

        final int xStart = Math.max(sx, 0);
        final int xEnd = Math.min(ex, frameWidth);

        for (int x = xStart; x < xEnd; x++){

            double gradient = (x - sx) / (double) (ex - sx);
            double u = interpolate(su, eu, gradient);
            double v = interpolate(sv, ev, gradient);
            Color c = texture.map(u, v);
            Color shadedColor = new Color((int) (c.getRed() * shading),
                                          (int) (c.getGreen() * shading),
                                          (int) (c.getBlue() * shading));

            putPixel(x, y, shadedColor, g);
        }
    }

    private void putPixel (int x, int y, Color c, Graphics g) {
        g.setColor(c);
        g.drawLine(x, y, x, y);
    }

    private void render (){

        Matrix3X3 xRotation = new xAxisRotationMatrix(camera.getXRotation());
        Matrix3X3 yRotation = new yAxisRotationMatrix(camera.getYRotation());
        Matrix3X3 translation = new TranslationMatrix(-camera.getSideWardMovement(), 0,
                                                      -camera.getForwardMovement());
        Matrix3X3 transformationMatrix = xRotation.multiplyByMatrix(yRotation).multiplyByMatrix(translation);

        for (Mesh mesh : meshes.keySet()){
            Vec3[] vertices = mesh.getVertices();
            for (int i = 0; i < mesh.getVerticesCount(); i++){

                vertices[i] = transformationMatrix.multiplyByVector(vertices[i]);
                Vec3 projectionVector = projectionMatrix.multiplyByVector(vertices[i]);

                double xProjection = (projectionVector.getX() / projectionVector.getW()
                                   * frameWidth /2) + frameWidth /2;
                double yProjection = (projectionVector.getY() / projectionVector.getW()
                                   * frameHeight /2) + frameHeight /2;

                Vec2 projectedVertex = new Vec2(xProjection, yProjection, projectionVector.getW());
                meshes.get(mesh)[i] = projectedVertex;
            }
        }
    }

    private void calculateFPS () {
        fpsChecks++;
        double currentTime = System.currentTimeMillis();
        double newFPS = (1000 / (currentTime - prevTime));
        prevTime = currentTime;
        if (fpsChecks > 15) {
            drawFPS = newFPS;
            fpsChecks = 0;
        }
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        render();
        repaint();
    }

    @Override
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W){
            camera.moveForward();
        }
        if (key == KeyEvent.VK_S){
            camera.moveBackward();
        }
        if (key == KeyEvent.VK_D){
            camera.moveRight();
        }
        if (key == KeyEvent.VK_A){
            camera.moveLeft();
        }
        if (key == KeyEvent.VK_L){
            camera.rotateYAxisAtPositive();
        }
        if (key == KeyEvent.VK_J){
            camera.rotateXAxisAtPositive();
        }
        if (key == KeyEvent.VK_K){
            camera.rotateXAxisAtNegative();
        }
        if (key == KeyEvent.VK_H){
            camera.rotateYAxisAtNegative();
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W){
            camera.setBackForwardMovement();
        }
        if (key == KeyEvent.VK_S){
            camera.setBackForwardMovement();
        }
        if (key == KeyEvent.VK_D){
            camera.setBackSideWardMovement();
        }
        if (key == KeyEvent.VK_A){
            camera.setBackSideWardMovement();
        }
        if (key == KeyEvent.VK_L){
            camera.setBackYRotation();
        }
        if (key == KeyEvent.VK_J){
            camera.setBackXRotation();
        }
        if (key == KeyEvent.VK_K){
            camera.setBackXRotation();
        }
        if (key == KeyEvent.VK_H){
            camera.setBackYRotation();
        }
    }

    @Override
    public void keyTyped (KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE){
            stopEngine();
        }
    }

    public static void main (String[] args) {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        Camera camera = new Camera();
        Engine engine = new Engine(camera, 20, 500, 500);
        Mesh mesh1 = new Mesh();
        engine.addMesh(mesh1);


        Mesh mesh2 = new Mesh();
        mesh2.move(new TranslationMatrix(15, 0, 0));
        engine.addMesh(mesh2);
        Mesh mesh3 = new Mesh();
        mesh3.move(new TranslationMatrix(-15, 0, 0));
        engine.addMesh(mesh3);
        Mesh mesh4 = new Mesh();
        mesh4.move(new TranslationMatrix(0, 15, 0));
        engine.addMesh(mesh4);
        Mesh mesh5 = new Mesh();
        mesh5.move(new TranslationMatrix(0, -15, 0));
        engine.addMesh(mesh5);
        Mesh mesh6 = new Mesh();
        mesh6.move(new TranslationMatrix(0, 0, 15));
        engine.addMesh(mesh6);
        Mesh mesh7 = new Mesh();
        mesh7.move(new TranslationMatrix(0, 0, -15));
        engine.addMesh(mesh7);

        class Frame extends JFrame {

            Frame (int width, int height, String title, Engine engine) {
                setTitle(title);
                setSize(width, height);
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                add(engine);
            }

        }
        SwingUtilities.invokeLater(() -> {
            new Frame (500, 500, "soft 3D engine", engine) {

            };  // Let the constructor do the job
        });
        engine.startEngine();
    }
}
//1221997523 07-OCT-95