import java.lang.Math;

class xAxisRotationMatrix extends Matrix3X3 {

    xAxisRotationMatrix(double theta) {
        makeMatrix(theta);
    }

    private void makeMatrix(double theta) {
        makeIdentityMatrix();
        double thetaRad = Math.toRadians(theta);
        matrix[1][1] = Math.cos(thetaRad);
        matrix[1][2] = -Math.sin(thetaRad);
        matrix[2][1] = Math.sin(thetaRad);
        matrix[2][2] = Math.cos(thetaRad);
    }
}