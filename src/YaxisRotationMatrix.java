import java.lang.Math;

public class YaxisRotationMatrix extends Matrix3X3 {

    public YaxisRotationMatrix(double theta) {
        makeMatrix(theta);
    }

    private void makeMatrix(double theta) {
        makeIdentityMatrix();
        double thetaRad = Math.toRadians(theta);
        matrix[0][0] = Math.cos(thetaRad);
        matrix[0][2] = Math.sin(thetaRad);
        matrix[2][0] = -Math.sin(thetaRad);
        matrix[2][2] = Math.cos(thetaRad);
    }
}