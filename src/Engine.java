import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Engine extends JPanel implements ActionListener {
    
    private Body cube= new Body();
    private static final int FrameHeight = 300;
    private static final int FrameWidth = 300;
    private static final int delay = 200;
    Timer timer = new Timer(delay, this);

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);

    }

    public void actionPerformed(ActionEvent e){
        UpdateCanvas();
    }

    private void UpdateCanvas(){

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