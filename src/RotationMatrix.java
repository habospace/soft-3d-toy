import java.lang.Math;

/**
 * Created by habospace on 27/03/16.
 */

public class RotationMatrix extends TransformationMatrix{

    public RotationMatrix(Vec3 vec1,
                          Vec3 vec2,
                          double theta){
        arbitraryAxis1(vec1, vec2, theta);
    }

    public RotationMatrix(int choice,
                          double theta){
        if (choice == 0){
            Xaxis(theta);
        }
        else if (choice == 1){
            Yaxis(theta);
        }
        else if (choice == 2){
            Zaxis(theta);
        }
        else{
            System.out.println("Out of Range (0-2) where 0 = X / 1 = Y / 2 = Z");
        }

    }

    private void arbitraryAxis1(Vec3 vec1,
                                Vec3 vec2,
                                double theta){
        double a = vec1.getX();
        double b = vec1.getY();
        double c = vec1.getZ();
        Vec3 normvec = vec2.normalize();
        double u = normvec.getX();
        double v = normvec.getY();
        double w = normvec.getZ();
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

    private void arbitraryAxe2(double theta, Vec3 vec){
        makeIdentityMatrix();
        Vec3 axe = vec.normalize();
        double X = axe.getX();
        double Y = axe.getY();
        double Z = axe.getZ();
        double thetaRad = Math.toRadians(theta);
        double c = Math.cos(thetaRad);
        double s = Math.sin(thetaRad);
        double t = 1 - c;
        matrix[0][0] = (t*X*X)+c;
        matrix[0][1] = (t*X*Y) + (s*Z);
        matrix[0][2] = (t*X*Z) - (s*Y);
        matrix[1][0] = (t*X*Y) - (s*Z);
        matrix[1][1] = (t*Y*Y) + c;
        matrix[1][2] = (t*Y*Z) + (s*Z);
        matrix[2][0] = (t*X*Y) + (s*Y);
        matrix[2][1] = (t*Y*Z) - (s*X);
        matrix[2][2] = (t*Z*Z) + c;
    }

    private void Xaxis(double theta){
        makeIdentityMatrix();
        double thetaRad = Math.toRadians(theta);
        matrix[1][1] = Math.cos(thetaRad);
        matrix[1][2] = -Math.sin(thetaRad);
        matrix[2][1] = Math.sin(thetaRad);
        matrix[2][2] = Math.cos(thetaRad);
    }

    private void Yaxis(double theta){
        makeIdentityMatrix();
        double thetaRad  = Math.toRadians(theta);
        matrix[0][0] = Math.cos(thetaRad);
        matrix[0][2] = Math.sin(thetaRad);
        matrix[2][0] = -Math.sin(thetaRad);
        matrix[2][2] = Math.cos(thetaRad);
    }

    private void Zaxis(double theta){
        makeIdentityMatrix();
        double thetaRad = Math.toRadians(theta);
        matrix[0][0] = Math.cos(thetaRad);
        matrix[0][1] = -Math.sin(thetaRad);
        matrix[1][0] = Math.sin(thetaRad);
        matrix[1][1] = Math.cos(thetaRad);
    }
}