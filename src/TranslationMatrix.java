/**
 * Created by habospace on 27/03/16.
 */
public class TranslationMatrix extends TransformationMatrix {


    public TranslationMatrix(double deltaX, double deltaY, double deltaZ){
        MakeMatrix(deltaX, deltaY, deltaZ);
    }

    private void MakeMatrix (double deltaX,
                             double deltaY,
                             double deltaZ){
        for(int i = 0; i < matrixheight; i++){
            for (int j = 0; j < matrixwidth; j++){
                if (i == j){
                    matrix[i][j] = 1;
                }
                else {
                    matrix[i][j] = 0;
                }
            }
        }
        matrix[0][3] = deltaX;
        matrix[1][3] = deltaY;
        matrix[2][3] = deltaZ;
    }
}
