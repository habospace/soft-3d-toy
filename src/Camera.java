import java.awt.event.*;
/**
 * Created by habospace on 03/04/16.
 */
public class Camera implements KeyListener{

    private Vec3 viewfrom;
    private Vec3 viewto;
    private Vec3 updirection;

    public Camera(){
        this.viewfrom = new Vec3(0, 0, 0);
        this.viewto = new Vec3(0, 0, -1);
        this.updirection = new Vec3(0, 1, 0);
    }

    public Camera(Vec3 position,
                  Vec3 orientation,
                  Vec3 updirection){
        this.viewfrom = position;
        this.viewto = orientation;
        this.updirection = updirection;
    }

    private void rotateCamera(Multipliable rotationmatrix) {
        viewto = rotationmatrix.multiplyByVector(viewto);
        updirection = rotationmatrix.multiplyByVector(updirection);
        System.out.println("POSITION:");
        viewfrom.print();
        System.out.println("LOOKAT:");
        viewto.print();
        System.out.println("UP:");
        updirection.print();
    }

    public Vec3 getViewfrom(){
        return viewfrom;
    }

    public Vec3 getViewto(){
        return viewto;
    }

    public Vec3 getUpDirection(){
        return updirection;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Vec3 viewvector = new Vec3(viewto.getX() - viewfrom.getX(),
                                   viewto.getY() - viewfrom.getY(),
                                   viewto.getZ() - viewfrom.getZ());
        Vec3 sidevector = viewvector.crossProduct(updirection);
        if (key == KeyEvent.VK_W){
            viewfrom.updateRelLocation(viewvector.getX(),
                                       viewvector.getY(),
                                       viewvector.getZ());
            viewto.updateRelLocation(viewvector.getX(),
                                     viewvector.getY(),
                                     viewvector.getZ());
            System.out.println("Forward__'W'____________");
            System.out.println("POSITION:");
            viewfrom.print();
            System.out.println("LOOKAT:");
            viewto.print();
            System.out.println("UP:");
            updirection.print();
        }
        if (key == KeyEvent.VK_S){
            viewfrom.updateRelLocation(-(viewvector.getX()),
                                       -(viewvector.getY()),
                                       -(viewvector.getZ()));
            viewto.updateRelLocation(-(viewvector.getX()),
                                     -(viewvector.getY()),
                                     -(viewvector.getZ()));
            System.out.println("Back__'S'____________");
            System.out.println("POSITION:");
            viewfrom.print();
            System.out.println("LOOKAT:");
            viewto.print();
            System.out.println("UP:");
            updirection.print();
        }
        if (key == KeyEvent.VK_D){
            viewfrom.updateRelLocation(sidevector.getX(),
                                       sidevector.getY(),
                                       sidevector.getZ());
            viewto.updateRelLocation(sidevector.getX(),
                                     sidevector.getY(),
                                     sidevector.getZ());
            System.out.println("Right__'D'_______________");
            System.out.println("POSITION:");
            viewfrom.print();
            System.out.println("LOOKAT:");
            viewto.print();
            System.out.println("UP:");
            updirection.print();
        }
        if (key == KeyEvent.VK_A){
            viewfrom.updateRelLocation(-(sidevector.getX()),
                                       -(sidevector.getY()),
                                       -(sidevector.getZ()));
            viewto.updateRelLocation(-(sidevector.getX()),
                                     -(sidevector.getY()),
                                     -(sidevector.getZ()));
            System.out.println("Left__'A'______________");
            System.out.println("POSITION:");
            viewfrom.print();
            System.out.println("LOOKAT:");
            viewto.print();
            System.out.println("UP:");
            updirection.print();
        }
        if (key == KeyEvent.VK_L){

            Multipliable rotationmatrix
                    = new RotationMatrix(viewfrom.getX(),
                                         viewfrom.getY(),
                                         viewfrom.getZ(),
                                         updirection.getX(),
                                         updirection.getY(),
                                         updirection.getZ(),
                                         1);
            rotateCamera(rotationmatrix);
            System.out.println("ROTATE_LEFT________________");
        }
        if (key == KeyEvent.VK_J){
            Multipliable rotationmatrix
                    = new RotationMatrix(viewfrom.getX(),
                                         viewfrom.getY(),
                                         viewfrom.getZ(),
                                         sidevector.getX(),
                                         sidevector.getY(),
                                         sidevector.getZ(),
                                         1);
            System.out.println("ROTATE_UP________________");
            rotateCamera(rotationmatrix);
        }
        if (key == KeyEvent.VK_K){
            Multipliable rotationmatrix
                    = new RotationMatrix(viewfrom.getX(),
                                         viewfrom.getY(),
                                         viewfrom.getZ(),
                                         sidevector.getX(),
                                         sidevector.getY(),
                                         sidevector.getZ(),
                                         -1);
            System.out.println("ROTATE_DOWN________________");
            rotateCamera(rotationmatrix);
        }
        if (key == KeyEvent.VK_H){
            Multipliable rotationmatrix
                    = new RotationMatrix(viewfrom.getX(),
                                         viewfrom.getY(),
                                         viewfrom.getZ(),
                                         updirection.getX(),
                                         updirection.getY(),
                                         updirection.getZ(),
                                         -1);
            System.out.println("ROTATE_RIGHT________________");
            rotateCamera(rotationmatrix);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}