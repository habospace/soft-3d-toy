import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Engine extends JPanel implements ActionListener {

    private Body cube = new Body();
    private RotationMatrix rotmat = new RotationMatrix(0, -5, 55, 0, 10, 0, 5);
    private ProjectionMatrix projmat = new ProjectionMatrix();
    private static final int FrameHeight = 300;
    private static final int FrameWidth = 300;
    private static final int delay = 300;
    Timer timer = new Timer(delay, this);

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        int[][] projection= cube.GetProjection();
        graphics.drawLine(projection[0][0], projection[0][1],
                          projection[1][0], projection[1][1]);
        graphics.drawLine(projection[1][0], projection[1][1],
                          projection[2][0], projection[2][1]);
        graphics.drawLine(projection[2][0], projection[2][1],
                          projection[3][0], projection[3][1]);
        graphics.drawLine(projection[3][0], projection[3][1],
                          projection[0][0], projection[0][1]);
        graphics.drawLine(projection[4][0], projection[4][1],
                          projection[5][0], projection[5][1]);
        graphics.drawLine(projection[5][0], projection[5][1],
                          projection[6][0], projection[6][1]);
        graphics.drawLine(projection[6][0], projection[6][1],
                          projection[7][0], projection[7][1]);
        graphics.drawLine(projection[7][0], projection[7][1],
                          projection[4][0], projection[4][1]);
        graphics.drawLine(projection[0][0], projection[0][1],
                          projection[4][0], projection[4][1]);
        graphics.drawLine(projection[1][0], projection[1][1],
                          projection[5][0], projection[5][1]);
        graphics.drawLine(projection[2][0], projection[2][1],
                          projection[6][0], projection[6][1]);
        graphics.drawLine(projection[3][0], projection[3][1],
                          projection[7][0], projection[7][1]);

        timer.start();
    }

    public void actionPerformed(ActionEvent e){
        moveBody();
        projectBody();
        repaint();
    }

    private void moveBody(){
        cube.Move(rotmat);
    }

    private void projectBody(){
        cube.Project(projmat, FrameHeight, FrameWidth);
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        JFrame frame = new JFrame();
        frame.setTitle("3d Engine");
        frame.setSize(FrameWidth, FrameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
    }
}