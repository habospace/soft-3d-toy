public class TranslationMatrix extends Matrix3X3 {


    public TranslationMatrix(double deltaX,
                             double deltaY,
                             double deltaZ){
        makeMatrix(deltaX,
                   deltaY,
                   deltaZ);
    }

    private void makeMatrix(double deltaX,
                            double deltaY,
                            double deltaZ){
        makeIdentityMatrix();
        matrix[0][3] = deltaX;
        matrix[1][3] = deltaY;
        matrix[2][3] = deltaZ;
    }
}