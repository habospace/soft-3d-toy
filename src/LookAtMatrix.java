/**
 * Created by habospace on 04/04/16.
 */
public class LookAtMatrix extends TransformationMatrix{

    public LookAtMatrix(Vec3 eyepoint,
                        Vec3 lookatpoint,
                        Vec3 up){
        makeMatrix(eyepoint,
                   lookatpoint,
                   up);
    }

    private void makeMatrix(Vec3 eye,
                            Vec3 lookat,
                            Vec3 up){
        Vec3 Zaxis = new Vec3(lookat.getX() - eye.getX(),
                              lookat.getY() - eye.getX(),
                              lookat.getZ() - eye.getZ()).normalize();
        Vec3 Xaxis = Zaxis.crossProduct(up).normalize();
        Vec3 Yaxis = Xaxis.crossProduct(Zaxis).normalize();

        matrix[0][0] = Xaxis.getX();
        matrix[0][1] = Xaxis.getY();
        matrix[0][2] = Xaxis.getZ();
        matrix[0][3] = 0;

        matrix[1][0] = Yaxis.getX();
        matrix[1][1] = Yaxis.getY();
        matrix[1][2] = Yaxis.getZ();
        matrix[1][3] = 0;

        matrix[2][0] = -Zaxis.getX();
        matrix[2][1] = -Zaxis.getY();
        matrix[2][2] = -Zaxis.getZ();
        matrix[2][3] = 0;

        matrix[3][0] = 0;
        matrix[3][1] = 0;
        matrix[3][2] = 0;
        matrix[3][3] = 1;
    }
}