import java.lang.Math;

/**
 * Created by habospace on 27/03/16.
 */

public class RotationMatrix extends TransformationMatrix  {

    public RotationMatrix(double a, double b,
                          double c, double uUn,
                          double vUn, double wUn,
                          double theta){
        makeMatrix(a, b, c, uUn, vUn, wUn, theta);
    }

    private void makeMatrix(double a, double b,
                            double c, double uUn,
                            double vUn, double wUn,
                            double theta){
        double l = getLength(uUn, vUn, wUn);
        double u = uUn/l;
        double v = vUn/l;
        double w = wUn/l;
        double u2 = u*u;
        double v2 = v*v;
        double w2 = w*w;
        double cosT = Math.cos(Math.toRadians(theta));
        double oneMinusCosT = 1-cosT;
        double sinT = Math.sin(Math.toRadians(theta));

        matrix[0][0] = u2 + (v2 + w2) * cosT;
        matrix[0][1] = u*v * oneMinusCosT - w*sinT;
        matrix[0][2] = u*w * oneMinusCosT + v*sinT;
        matrix[0][3] = (a*(v2 + w2) - u*(b*v + c*w))*oneMinusCosT
                + (b*w - c*v)*sinT;
        matrix[1][0] = u*v * oneMinusCosT + w*sinT;
        matrix[1][1] = v2 + (u2 + w2) * cosT;
        matrix[1][2] = v*w * oneMinusCosT - u*sinT;
        matrix[1][3] = (b*(u2 + w2) - v*(a*u + c*w))*oneMinusCosT
                + (c*u - a*w)*sinT;
        matrix[2][0] = u*w * oneMinusCosT - v*sinT;
        matrix[2][1] = v*w * oneMinusCosT + u*sinT;
        matrix[2][2] = w2 + (u2 + v2) * cosT;
        matrix[2][3] = (c*(u2 + v2) - w*(a*u + b*v))*oneMinusCosT
                + (a*v - b*u)*sinT;
        matrix[3][0] = 0;
        matrix[3][1] = 0;
        matrix[3][2] = 0;
        matrix[3][3] = 1;
    }

    private double getLength(double x,
                             double y,
                             double z){
        double length = Math.sqrt(x*x + y*y + z*z);
        return length;
    }
}