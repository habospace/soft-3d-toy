class Camera {
    //
    private double xRotation;
    private double yRotation;
    private double forward;
    private double sideWard;

    Camera(double xRot,
           double yRot,
           double forward,
           double sideWard) {
        this.xRotation = xRot;
        this.yRotation = yRot;
        this.forward = forward;
        this.sideWard = sideWard;
    }

    Camera() {
        this.xRotation = 0;
        this.yRotation = 0;
        this.forward = 0;
        this.sideWard = 0;
    }

    void rotateXAxisAtPositive() {
        xRotation = 1;
    }

    void rotateXAxisAtNegative() {
        xRotation = -1;
    }

    void rotateYAxisAtPositive() {
        yRotation = 1;
    }

    void rotateYAxisAtNegative() {
        yRotation = -1;
    }

    void moveForward() {
        forward = -1;
    }

    void moveBackward() {
        forward = 1;
    }

    void moveRight() {
        sideWard = 1;
    }

    void moveLeft() {
        sideWard = -1;
    }

    void setBackXRotation() {
        xRotation = 0;
    }

    void setBackYRotation() {
        yRotation = 0;
    }

    void setBackForwardMovement() {
        forward = 0;
    }

    void setBackSideWardMovement() {
        sideWard = 0;
    }

    double getXRotation() {
        return xRotation;
    }

    double getYRotation() {
        return yRotation;
    }

    double getForwardMovement() {
        return forward;
    }

    double getSideWardMovement() {
        return sideWard;
    }
}