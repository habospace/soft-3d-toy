import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class EventHandler extends JPanel implements MouseMotionListener, MouseListener, ActionListener {

    private int mX, mY;
    private boolean onclick;
    Timer timer;

    public EventHandler() {
        addMouseMotionListener(this);
        addMouseListener(this);
        setVisible(true);
        onclick = false;
        this.timer = new Timer(100, this);
        timer.start();
    }

    public void mouseMoved(MouseEvent me) {
    }

    public void mouseDragged(MouseEvent me) {
        mX = (int) me.getPoint().getX();
        mY = (int) me.getPoint().getY();
        System.out.println("X: "+mX+" - Y: "+mY);
        System.out.println(me.getPoint());
        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(mX, mY, 5, 5);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICKED1");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        onclick = true;
        System.out.println("CLICKED2");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        onclick = false;
        System.out.println("CLICKED3");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("CLICKED4");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("CLICKED5");
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.getContentPane().add(new EventHandler());
        f.setSize(200, 200);
        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (onclick){
            System.out.println(onclick);
        }
    }
}