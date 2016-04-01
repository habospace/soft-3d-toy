import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.lang.Math;

public class Camera extends JPanel implements ActionListener, MouseMotionListener, MouseListener{

    private HashMap<Mesh, int[][]> meshes = new HashMap<>();
    private RotationMatrix rotmat = new RotationMatrix(0, -5, 55, 0, 10, 0, 5);
    private ProjectionMatrix projmat = new ProjectionMatrix();
    private int frameheight;
    private int framewidth;
    private static final int x = 0;
    private static final int y = 1;
    private boolean clickedOnMesh = false;
    private int[][] mouseMotion = new int[2][2];
    private boolean screenUntouched = true;
    Timer timer;

    public Camera(int framewidth, int frameheight, int delay){
        this.framewidth = framewidth;
        this.frameheight = frameheight;
        this.timer = new Timer(delay, this);
    }

    public void startEngine(){
        timer.start();
    }

    public void stopEngine(){
        timer.stop();
    }

    public void addMesh(Mesh mesh){
            meshes.put(mesh, new int[mesh.getVerticesCount()][2]);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        drawLines(graphics);
        //drawPoints(graphics);
    }

    private void drawPoints(Graphics graphics){
        for (Mesh mesh : meshes.keySet()){
            int[][] vertices = meshes.get(mesh);
            for (int[] vertex : vertices){
                putPixel(vertex[x], vertex[y], graphics);
            }
        }
    }

    private void drawLines(Graphics graphics){
        for (Mesh mesh : meshes.keySet()){
            int[][] verticescoords = meshes.get(mesh);
            for (int[] vertexpair : mesh.getLines()){
                int point1 = vertexpair[0];
                int point2 = vertexpair[1];
                drawLine(verticescoords[point1][x],
                         verticescoords[point1][y],
                         verticescoords[point2][x],
                         verticescoords[point2][y],
                         graphics);
            }
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2,
                          Graphics graphics){
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x1, y1, graphics);
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

    public void putPixel(int x, int y,
                         Graphics graphics){
        graphics.drawLine(x, y, x, y);
    }

    private void moveAll(VectorMultipliable matrix){
        for (Mesh mesh : meshes.keySet()){
            mesh.move(matrix);
        }
    }

    private void projectMeshes(VectorMultipliable projectionmatrix){
        for (Mesh mesh : meshes.keySet()){
            for (int i = 0; i < mesh.getVerticesCount(); i++){
                double[] projvector = projectionmatrix.multiply(mesh.getVertex(i));
                double projx = (projvector[x]/projvector[3] *framewidth/2)+framewidth/2;
                double projy = (projvector[y]/projvector[3]*frameheight/2)+frameheight/2;
                meshes.get(mesh)[i][x] = (int) projx;
                meshes.get(mesh)[i][y] = (int) projy;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        moveAll(rotmat);
        projectMeshes(projmat);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (screenUntouched){
            mouseMotion[0][x] = e.getX();
            mouseMotion[0][y] = e.getY();
        }
        else{
            mouseMotion[1][x] = mouseMotion[0][x];
            mouseMotion[1][y] = mouseMotion[0][y];
            mouseMotion[0][x] = e.getX();
            mouseMotion[0][y] = e.getY();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args){
        Camera engine = new Camera(300, 300, 100);
        JFrame frame = new JFrame();
        frame.setTitle("3d Camera");
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
        Mesh mesh1 = new Mesh();
        mesh1.addLine(0, 1, 0);
        mesh1.addLine(1, 2, 1);
        mesh1.addLine(2, 3, 2);
        mesh1.addLine(3, 0, 3);
        mesh1.addLine(4, 5, 4);
        mesh1.addLine(5, 6, 5);
        mesh1.addLine(6, 7, 6);
        mesh1.addLine(7, 4, 7);
        mesh1.addLine(0, 4, 8);
        mesh1.addLine(1, 5, 9);
        mesh1.addLine(2, 6, 10);
        mesh1.addLine(3, 7, 11);
        engine.addMesh(mesh1);
        engine.startEngine();
    }
}