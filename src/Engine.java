import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.lang.Math;
import java.util.HashMap;

public class Engine extends JPanel implements ActionListener, KeyListener{

    private final Camera camera;
    private final HashMap<Mesh, Vec2[]> meshes = new HashMap<>();
    private final Matrix3X3 projectionmatrix = new ProjectionMatrix();
    private final int frameheight;
    private final int framewidth;
    Timer timer;

    public Engine(Camera camera,
                  int delay,
                  int framewidth,
                  int frameheight){
        this.camera = camera;
        this.framewidth = framewidth;
        this.frameheight = frameheight;
        this.timer = new Timer(delay, this);
        addKeyListener(this);
        setFocusable(true);
    }

    public void startEngine(){
        timer.start();
    }

    public void stopEngine(){
        timer.stop();
    }

    public void addMesh(Mesh mesh){
        meshes.put(mesh, new Vec2[mesh.getVerticesCount()]);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        drawTriangles(g);
    }

    private void drawTriangles(Graphics g){
        for (Mesh mesh : meshes.keySet()){
            Vec2[] projectedVertices = meshes.get(mesh);
            for (Triangle face : mesh.getFaces()){
                Vec2 point1 = projectedVertices[face.getVertex1()];
                Vec2 point2 = projectedVertices[face.getVertex2()];
                Vec2 point3 = projectedVertices[face.getVertex3()];
                if (isOnScreen(point1.getW()) &&
                        isOnScreen(point2.getW()) &&
                        isOnScreen(point3.getW())){
                    drawTriangle(point1, point2, point3, g);
                }
            }
        }
    }

    private void drawTriangle(Vec2 p1, Vec2 p2,
                              Vec2 p3, Graphics g){

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

        double dP1P2, dP1P3;

        if (p2.getY() - p1.getY() > 0){
            dP1P2 = (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
        }
        else {
            dP1P2 = 0;
        }
        if (p3.getY() - p1.getY() > 0){
            dP1P3 = (p3.getX() - p1.getX()) / (p3.getY() - p1.getY());
        }
        else{
            dP1P3 = 0;
        }
        if (dP1P2 > dP1P3){
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

    private double clamp(double value){
        return Math.max(0, Math.min(value, 1));
    }

    private double interpolate(double min, double max,
                               double gradient){
        return min + (max - min) * clamp(gradient);
    }

    private void processScanLine(int y, Vec2 pa,
                                 Vec2 pb, Vec2 pc,
                                 Vec2 pd, Graphics g){
        double gradient1 = pa.getY() != pb.getY()
                         ? (y - pa.getY()) / (pb.getY() - pa.getY()) : 1;
        double gradient2 = pc.getY() != pd.getY()
                         ? (y - pc.getY()) / (pd.getY() - pc.getY()) : 1;

        int sx = (int) interpolate(pa.getX(), pb.getX(), gradient1);
        int ex = (int) interpolate(pc.getX(), pd.getX(), gradient2);

        for (int x = sx; x < ex; x++) {
            putPixel(new Vec2(x, y), g);
        }
    }

    private void putPixel(Vec2 point,
                          Graphics graphics){
        int x1 = (int) point.getX(), y1 = (int) point.getY();
        graphics.drawLine(x1, y1, x1, y1);
    }

    private boolean isOnScreen(double w){
        if (0 <= w){
            return true;
        }
        return false;
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

                Vec2 pixel = new Vec2(projx, projy, projvector.getW());
                meshes.get(mesh)[i] = pixel;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
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

    public static void main(String[] args){
        Camera camera = new Camera();
        Engine engine = new Engine(camera, 20, 500, 300);
        Mesh mesh1 = new Mesh();
        engine.addMesh(mesh1);
        JFrame frame = new JFrame();
        frame.setTitle("3d Engine");
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
        engine.startEngine();
    }
}