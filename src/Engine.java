import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Engine extends JPanel {

    Timer timer;


    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        graphic.setColor(Color.BLACK);
    }

}