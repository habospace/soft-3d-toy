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

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        drawLines(graphics);
    }

    public void rasterize(Vec2 point1,
                          Vec2 point2,
                          Vec2 point3,
                          Graphics graphics){
        double invSlope1 = (point2.getX()-point1.getX())
                         / (point2.getY()-point1.getY());
        double invSlope2 = (point3.getX()-point1.getX())
                         / (point3.getY()-point1.getY());
        double curx1 = point1.getX();
        double curx2 = point1.getX();
        for (int Y = (int) point1.getY(); Y > point2.getY(); Y-=1){
            Vec2 pixel1 = new Vec2(curx1, Y);
            Vec2 pixel2 = new Vec2(curx2, Y);
            drawLine(pixel1, pixel2, graphics);
            curx1 += invSlope1;
            curx2 += invSlope2;
        }

    }

    private void drawPoints(Graphics graphics){
        for (Mesh mesh : meshes.keySet()){
            for (Vec2 point : meshes.get(mesh)){
                putPixel(point, graphics);
            }
        }
    }

    private void drawLines(Graphics graphics){
        for (Mesh mesh : meshes.keySet()){
            Vec2[] projectedVertices = meshes.get(mesh);
            for (Edge edge : mesh.getEdges()){
                Vec2 point1 = projectedVertices[edge.getVertex1()];
                Vec2 point2 = projectedVertices[edge.getVertex2()];
                if (isOnScreen(point1.getW()) && isOnScreen(point2.getW())){
                    drawLine(point1, point2, graphics);
                }
            }
        }
    }

    private void drawLine(Vec2 point1,
                          Vec2 point2,
                          Graphics graphics){
        int x1 = (int) point1.getX();
        int y1 = (int) point1.getY();
        int x2 = (int) point2.getX();
        int y2 = (int) point2.getY();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;
        while (true) {
            putPixel(new Vec2(x1, y1), graphics);
            if ((x1 == x2) && (y1 == y2)){
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy){
                err -= dy; x1 += sx;
            }
            if (e2 < dx){
                err += dx; y1 += sy;
            }
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