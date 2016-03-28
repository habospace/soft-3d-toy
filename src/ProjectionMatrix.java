import java.lang.Math;
/**
 * Created by habospace on 28/03/16.
 */
public class ProjectionMatrix extends TransformationMatrix {

    private static final double fovy = 0.785398;
    private static final double aspect = 1;
    private static final double nearZ = 10;
    private static final double farZ = 100;

    public ProjectionMatrix (){
        MakeMatrix();
    }

    private void MakeMatrix(){
        for (int i = 0; i < matrixheight; i++){
            for (int j = 0; j < matrixwidth; j++){
                matrix[i][j] = 0;
            }
        }
        double frustumdepth = farZ - nearZ;
        double recdepth = 1 / frustumdepth;

        matrix[1][1] = 1 / Math.tan(fovy / 2);
        matrix[0][0] = matrix[1][1] / aspect;
        matrix[2][2] = -(farZ + nearZ) / (farZ - nearZ);
        matrix[2][3] = (2 * farZ * nearZ) / (farZ - nearZ);
        matrix[3][2] = 1;
    }
}
