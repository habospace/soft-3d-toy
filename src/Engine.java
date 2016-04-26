import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Engine extends JPanel implements ActionListener, KeyListener {

    private double drawFPS;
    private double prevTime;
    private int FPSchecks = 0;
    private final Camera camera;
    private final HashMap<Mesh, Vec2[]> meshes = new HashMap<>();
    private final Matrix3X3 projectionmatrix = new ProjectionMatrix();
    private final int frameheight;
    private final int framewidth;
    private final Vec3 lightSource = new Vec3(0, 0, 5);
    Timer timer;

    public Engine(Camera camera,
                  int delay,
                  int framewidth,
                  int frameheight) {
        this.camera = camera;
        this.framewidth = framewidth;
        this.frameheight = frameheight;
        this.timer = new Timer(delay, this);
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
    }

    public void startEngine() {
        timer.start();
    }

    public void stopEngine() {
        timer.stop();
    }

    public void addMesh(Mesh mesh) {
        meshes.put(mesh, new Vec2[mesh.getVerticesCount()]);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTriangles(g);
        drawFPS(g);
    }

    private void drawFPS(Graphics g) {
        calculateFPS();
        g.setColor(Color.black);
        g.setFont(new Font("default", Font.BOLD, 12));
        g.drawString("FPS: "+(int)drawFPS, 140, 20);
    }

    private void drawTriangles(Graphics g) {
        ArrayList<Triangle> triangles = new ArrayList<>();

        for (Mesh mesh : meshes.keySet()) {
            Vec2[] projectedVertices = meshes.get(mesh);
            for (Triangle face : mesh.getFaces()){

                Vec2 projPoint1 = projectedVertices[(int)face.getVertex1()];
                Vec2 projPoint2 = projectedVertices[(int)face.getVertex2()];
                Vec2 projPoint3 = projectedVertices[(int)face.getVertex3()];

                boolean onScreen = (isOnScreen(projPoint1.getW()) &&
                                    isOnScreen(projPoint2.getW()) &&
                                    isOnScreen(projPoint3.getW()));
                boolean onCanvas = (isOnCanvas(projPoint1.getX(), projPoint1.getY()) &&
                                    isOnCanvas(projPoint2.getX(), projPoint2.getY()) &&
                                    isOnCanvas(projPoint3.getX(), projPoint3.getY()));
                boolean inFront = isInFront(projPoint1, projPoint2, projPoint3);

                if (onScreen && onCanvas && inFront){
                    Triangle<Vec2> triangle = new Triangle<>(projPoint1,
                                                             projPoint2,
                                                             projPoint3);
                    Vec3 unProjP1 = mesh.getVertex((int)face.getVertex1());
                    Vec3 unProjP2 = mesh.getVertex((int)face.getVertex2());
                    Vec3 unProjP3 = mesh.getVertex((int)face.getVertex3());
                    int greenness = calculateShade(unProjP1, unProjP2, unProjP3);

                    triangle.setColorCode(greenness);
                    triangle.setCentreZDepth((unProjP1.getZ() +
                                              unProjP2.getZ() +
                                              unProjP3.getZ())/ 3);
                    triangles.add(triangle);
                }
            }
        }
        Collections.sort(triangles);
        for (Triangle face : triangles){
            drawTriangle(face, g);
        }
    }

    private boolean isOnScreen(double w) {
        if (0 <= w){
            return true;
        }
        return false;
    }

    private boolean isOnCanvas(double x, double y) {
        if ((0 < x && x < framewidth) && (0 < y && y < frameheight)){
            return true;
        }
        return false;
    }

    private boolean isInFront(Vec2 p1, Vec2 p2, Vec2 p3) {

        double lace1 = (p1.getX() * p2.getY())
                     + (p2.getX() * p3.getY())
                     + (p3.getX() * p1.getY());

        double lace2 = (p1.getY() * p2.getX())
                     + (p2.getY() * p3.getX())
                     + (p3.getY() * p1.getX());

        if (lace1 - lace2 < 0){
            return false;
        }
        return true;
    }

    private int calculateShade(Vec3 p1, Vec3 p2, Vec3 p3) {

        Vec3 centre = new Vec3((p1.getX()+p2.getX()+p3.getX()) / 3,
                               (p1.getY()+p2.getY()+p3.getY()) / 3,
                               (p1.getZ()+p2.getZ()+p3.getZ()) / 3);

        Vec3 surfNorm = Vec3.surfaceNormalVector(p1, p2, p3).normalize();

        Vec3 lightDirection = new Vec3(lightSource.getX() - centre.getX(),
                                       lightSource.getY() - centre.getY(),
                                       lightSource.getZ() - centre.getZ()).normalize();

        return (int) (Math.abs(lightDirection.dotProduct(surfNorm)) * 255);
    }

    private void drawTriangle(Triangle<Vec2> T, Graphics g) {
        Vec2 p1 = T.getVertex1();
        Vec2 p2 = T.getVertex2();
        Vec2 p3 = T.getVertex3();
        g.setColor(new Color(0, T.getColorCode(), 0));

        if (p1.getY() > p2.getY()){
            Vec2 temp = p2;
            p2 = p1;
            p1 = temp;
        }
        if (p2.getY() > p3.getY()){
            Vec2 temp = p2;
            p2 = p3;
            p3 = temp;
        }
        if (p1.getY() > p2.getY()){
            Vec2 temp = p2;
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
        if (right || (!left && dP1P2 > dP1P3)){
            for (int y = (int)p1.getY(); y <= (int)p3.getY(); y++){
                if (y < p2.getY()){
                    processScanLine(y, p1, p3, p1, p2, g);
                }
                else{
                    processScanLine(y, p1, p3, p2, p3, g);
                }
            }
        }
        else{
            for (int y = (int)p1.getY(); y <= (int)p3.getY(); y++){
                if (y < p2.getY()){
                    processScanLine(y, p1, p2, p1, p3, g);
                }
                else{
                    processScanLine(y, p2, p3, p1, p3, g);
                }
            }
        }
    }

    private double clamp(double value) {
        return Math.max(0, Math.min(value, 1));
    }

    private double interpolate(double min, double max,
                               double gradient) {
        return min + (max - min) * clamp(gradient);
    }

    private void processScanLine(int y, Vec2 pa, Vec2 pb,
                                 Vec2 pc, Vec2 pd, Graphics g) {

        double gradient1 = pa.getY() != pb.getY()
                         ? (y - pa.getY()) / (pb.getY() - pa.getY()) : 1;
        double gradient2 = pc.getY() != pd.getY()
                         ? (y - pc.getY()) / (pd.getY() - pc.getY()) : 1;

        int sx = (int) interpolate(pa.getX(), pb.getX(), gradient1);
        int ex = (int) interpolate(pc.getX(), pd.getX(), gradient2);

        for (int x = sx; x < ex; x++){
                putPixel(x, y, g);
        }
    }

    private void putPixel(int x, int y, Graphics g) {
        g.drawLine(x, y, x, y);
    }

    private void render(){
        Matrix3X3 Xrotation = new XaxisRotationMatrix(camera.getXrotation());
        Matrix3X3 Yrotation = new YaxisRotationMatrix(camera.getYrotation());
        Matrix3X3 translation = new TranslationMatrix(-camera.getSidewardMovement(), 0,
                                                      -camera.getForwardMovement());
        Matrix3X3 transformationMatrix = Xrotation.multiplyByMatrix(Yrotation).multiplyByMatrix(translation);

        for (Mesh mesh : meshes.keySet()){
            Vec3[] vertices = mesh.getVertices();
            for (int i = 0; i < mesh.getVerticesCount(); i++){

                vertices[i] = transformationMatrix.multiplyByVector(vertices[i]);
                Vec3 projvector = projectionmatrix.multiplyByVector(vertices[i]);

                double projx = (projvector.getX() / projvector.getW()*framewidth/2) + framewidth/2;
                double projy = (projvector.getY() / projvector.getW()*frameheight/2) + frameheight/2;

                Vec2 projectedVertex = new Vec2(projx, projy, projvector.getW());
                meshes.get(mesh)[i] = projectedVertex;
            }
        }
    }

    private void calculateFPS() {
        FPSchecks++;
        double currentTime = System.currentTimeMillis();
        double newFPS = (1000 / (currentTime - prevTime));
        prevTime = currentTime;
        if (FPSchecks > 15){
            drawFPS = newFPS;
            FPSchecks = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        render();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            camera.rotateYaxisAtPositive();
        }
        if (key == KeyEvent.VK_J){
            camera.rotateXaxisAtPositive();
        }
        if (key == KeyEvent.VK_K){
            camera.rotateXaxisAtNegative();
        }
        if (key == KeyEvent.VK_H){
            camera.rotateYaxisAtNegative();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W){
            camera.setBackForwardMovement();
        }
        if (key == KeyEvent.VK_S){
            camera.setBackForwardMovement();
        }
        if (key == KeyEvent.VK_D){
            camera.setBackSidewardMovement();
        }
        if (key == KeyEvent.VK_A){
            camera.setBackSidewardMovement();
        }
        if (key == KeyEvent.VK_L){
            camera.setBackYRotation();
        }
        if (key == KeyEvent.VK_J){
            camera.setBackXrotation();
        }
        if (key == KeyEvent.VK_K){
            camera.setBackXrotation();
        }
        if (key == KeyEvent.VK_H){
            camera.setBackYRotation();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE){
            stopEngine();
        }
    }

    public static void main(String[] args) {
        Camera camera = new Camera();
        Engine engine = new Engine(camera, 20, 300, 300);
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
        JFrame frame = new JFrame();
        frame.setTitle("3d Engine");
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
        engine.startEngine();
    }
}//1221997523 07-OCT-95