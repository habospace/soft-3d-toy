import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Engine extends JPanel implements ActionListener {

    private Mesh[] meshes;
    public int[] pixels;
    private RotationMatrix rotmat = new RotationMatrix(0, -5, 55, 0, 10, 0, 5);
    private ProjectionMatrix projmat = new ProjectionMatrix();
    private int frameheight;
    private int framewidth;
    private int delay;
    Timer timer = new Timer(delay, this);

    public Engine (int framewidth, int frameheight, int delay,
                   int meshescount, int verticescount){
        this.framewidth = framewidth;
        this.frameheight = frameheight;
        this.delay = delay;
        this.meshes = new Mesh[meshescount];
        this.pixels = new int[verticescount*2];
    }

    public void startEngine(){
        timer.start();
    }

    public void addMesh(Mesh mesh, int index){
        try {
            meshes[index] = mesh;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown  :" + error);
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < pixels.length; i+=2){
            graphics.drawLine(pixels[i], pixels[i+1], pixels[i], pixels[i+1]);
        }
    }

    public void actionPerformed(ActionEvent e){
        moveAll(rotmat);
        projectMeshes(projmat);
        repaint();
    }

    private void moveBody(int meshindex, VectorMultipliable matrix){
        meshes[meshindex].Move(matrix);
    }

    private void moveAll(VectorMultipliable matrix){
        for (Mesh mesh : meshes){
            mesh.Move(matrix);
        }
    }

    private void projectMeshes(VectorMultipliable projectionmatrix){
        for (Mesh mesh : meshes){
            for (int i = 0; i < mesh.getVerticescount(); i++){
                double[] projvector = projectionmatrix.Multiply(mesh.getVertex(i));
                double projx = (projvector[0]/projvector[3] *framewidth/2)+framewidth/2;
                double projy = (projvector[1]/projvector[3]*frameheight/2)+frameheight/2;
                pixels[i*2] = (int) projx;
                pixels[i*2+1] = (int) projy;
            }
        }
    }

    public static void main(String[] args){
        Engine engine = new Engine(400, 400, 100, 1, 8);
        engine.addMesh(new Mesh(), 0);
        JFrame frame = new JFrame();
        frame.setTitle("3d Engine");
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
        engine.startEngine();
    }
}