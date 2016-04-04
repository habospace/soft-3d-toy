import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.lang.Math;
import java.util.HashMap;

public class Engine extends JPanel implements ActionListener{

    private Camera camera;
    private HashMap<Mesh, Vec2[]> meshes = new HashMap<>();
    private Multipliable projectionmatrix = new ProjectionMatrix();
    private int frameheight;
    private int framewidth;
    Timer timer;

    public Engine(Camera camera,
                  int delay,
                  int framewidth,
                  int frameheight){
        this.camera = camera;
        this.framewidth = framewidth;
        this.frameheight = frameheight;
        this.timer = new Timer(delay, this);
        MouseListener cameraAlias1 = this.camera;
        MouseMotionListener cameraAlias2 = this.camera;
        KeyListener cameraAlias3 = this.camera;
        addMouseListener(cameraAlias1);
        addMouseMotionListener(cameraAlias2);
        addKeyListener(cameraAlias3);
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
                drawLine(point1, point2, graphics);
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

    private void render(){
        Vec3 campos = camera.getPosition();
        Multipliable lookatmatrix
                = new LookAtMatrix(campos,
                camera.getOrientation(),
                camera.getUpDirection());
        Multipliable worldmatrix
                = new TranslationMatrix(-campos.getX(),
                -campos.getY(), -campos.getZ());

        for (Mesh mesh : meshes.keySet()){
            Vec3[] vertices = mesh.getVertices();
            for (int i = 0; i < mesh.getVerticesCount(); i++){
                Vec3 projvector = projectionmatrix.multiplyByMatrix(lookatmatrix.multiplyByMatrix(worldmatrix)).multiplyByVector(vertices[i]);
                double projx = (projvector.getX()/projvector.getW()*framewidth/2)+framewidth/2;
                double projy = (projvector.getY()/projvector.getW()*frameheight/2)+frameheight/2;
                meshes.get(mesh)[i] = new Vec2(projx, projy);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        render();
        repaint();
    }

    public static void main(String[] args){
        Camera camera = new Camera();
        Engine engine = new Engine(camera, 20, 300, 300);
        Mesh mesh1 = new Mesh();
        engine.addMesh(mesh1);
        JFrame frame = new JFrame();
        frame.setTitle("3d Engine");
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
        engine.startEngine();
    }
}