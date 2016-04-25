public class Camera {

    private double Xrotation;
    private double Yrotation;
    private double forward;
    private double sideward;

    Camera(double xrot,
           double yrot,
           double forward,
           double sideward) {
        this.Xrotation = xrot;
        this.Yrotation = yrot;
        this.forward = forward;
        this.sideward = sideward;
    }

    Camera() {
        this.Xrotation = 0;
        this.Yrotation = 0;
        this.forward = 0;
        this.sideward = 0;
    }

    public void rotateXaxisAtPositive() {
        Xrotation = 1;
    }

    public  void rotateXaxisAtNegative() {
        Xrotation = -1;
    }

    public void rotateYaxisAtPositive() {
        Yrotation = 1;
    }

    public void rotateYaxisAtNegative () {
        Yrotation = -1;
    }

    public void moveForward() {
        forward = -1;
    }

    public  void moveBackward() {
        forward = 1;
    }

    public void moveRight() {
        sideward = 1;
    }

    public void moveLeft() {
        sideward = -1;
    }

    public void setBackXrotation() {
        Xrotation = 0;
    }

    public void setBackYRotation() {
        Yrotation = 0;
    }

    public void setBackForwardMovement() {
        forward = 0;
    }

    public void setBackSidewardMovement() {
        sideward = 0;
    }

    public double getXrotation() {
        return Xrotation;
    }

    public double getYrotation() {
        return Yrotation;
    }

    public double getForwardMovement() {
        return forward;
    }

    public double getSidewardMovement() {
        return sideward;
    }
}