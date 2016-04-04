import java.awt.event.*;
/**
 * Created by habospace on 03/04/16.
 */
public class Camera implements KeyListener, MouseListener, MouseMotionListener{

    private Vec3 position;
    private Vec3 orientation;
    private Vec3 updirection;
    private Vec3 Xorientation;
    private Vec2 previousMousePos;

    public Camera(){
        this.position = new Vec3(0, 0, 0);
        this.orientation = new Vec3(0, 0, 1);
        this.updirection = new Vec3(0, 1, 0);
        this.Xorientation = new Vec3(1, 0, 0);
    }

    public Camera(Vec3 position,
                  Vec3 orientation,
                  Vec3 updirection){
        this.position = position;
        this.orientation = orientation;
        this.updirection = updirection;
    }

    private void rotateCamera(Vec2 currentPos) {
        double rotationX = (currentPos.getX() - previousMousePos.getX()) * 0.3;
        double rotationY = (currentPos.getY() - previousMousePos.getY()) * 0.3;
        RotationMatrix rotmatX
                = new RotationMatrix(position.getX(),
                position.getY(), position.getY(),
                Xorientation.getX(), Xorientation.getY(),
                Xorientation.getZ(), rotationY);
        RotationMatrix rotmatY
                = new RotationMatrix(position.getX(),
                position.getY(), position.getY(),
                updirection.getX(), updirection.getY(),
                updirection.getZ(), rotationX);
        TransformationMatrix transmat = rotmatX.multiplyByMatrix(rotmatY);
        orientation = transmat.multiplyByVector(orientation);
        updirection = transmat.multiplyByVector(updirection);
        Xorientation = transmat.multiplyByVector(Xorientation);
    }

    public Vec3 getPosition(){
        return position;
    }

    public Vec3 getOrientation(){
        return orientation;
    }

    public Vec3 getUpDirection(){
        return updirection;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double x = e.getX(), y = e.getY();
        rotateCamera(new Vec2(x, y));
        previousMousePos.updateAbsLocation(x, y);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        previousMousePos = new Vec2(e.getX(), e.getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT){
            position.updateRelLocation(-(Xorientation.getX()),
                                       -(Xorientation.getY()),
                                       -(Xorientation.getZ()));
        }
        if (key == KeyEvent.VK_RIGHT){
            position.updateRelLocation(Xorientation.getX(),
                                       Xorientation.getY(),
                                       Xorientation.getZ());
        }
        if (key == KeyEvent.VK_UP){
            position.updateRelLocation(orientation.getX(),
                                       orientation.getY(),
                                       orientation.getZ());
        }
        if (key == KeyEvent.VK_DOWN){
            position.updateRelLocation(-(orientation.getX()),
                                       -(orientation.getY()),
                                       -(orientation.getZ()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
