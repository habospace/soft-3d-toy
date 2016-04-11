public class Matrix3X3 implements MultipliableByVector<Vec3, Vec3>, MultipliableByMatrix<Matrix3X3, Matrix3X3> {

    protected static final int matrixheight = 4;
    protected static final int matrixwidth = 4;
    protected final double[][] matrix = new double[matrixheight][matrixwidth];

    public Matrix3X3(){
        makeIdentityMatrix();
    }

    public Matrix3X3(double[][] array){
        makeMatrixFromArray(array);
    }

    public static XaxisRotationMatrix constructXaxisRotationMatrix(double theta){
        return new XaxisRotationMatrix(theta);
    }

    public static YaxisRotationMatrix constructYaxisRotationMatrix(double theta){
        return new YaxisRotationMatrix(theta);
    }

    public static TranslationMatrix constructTranslationMatrix(double x,
                                                               double y,
                                                               double z){
        return new TranslationMatrix(x, y, z);
    }

    public static ProjectionMatrix constructProjectionMatrix(){
        return new ProjectionMatrix();
    }

    public static Matrix3X3 constructIdentityMatrix(){
        return new Matrix3X3();
    }

    public static Matrix3X3 constructMatrixFromArray(double[][] array){
        return new Matrix3X3(array);
    }

    protected void makeIdentityMatrix(){
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
    }

    private void makeMatrixFromArray(double[][] argmatrix){
        int limit1 = 0;
        int limit2 = 0;
        if (argmatrix.length >= matrixheight){
            limit1 += matrixheight;
        }
        else{
            limit1 += argmatrix.length;
        }
        if (argmatrix[0].length >= matrixwidth){
            limit2 += matrixwidth;
        }
        else{
            limit2 += argmatrix[0].length;
        }
        for (int i = 0; i < limit1; i++){
            for (int j = 0; j < limit2; j++){
                matrix[i][j] = argmatrix[i][j];
            }
        }
        if (limit1 < matrixheight){
            for (int i = matrixheight-1; i > argmatrix.length-1; i--){
                for (int j = 0; j < matrixwidth; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        if (limit2 < matrixwidth){
            for (int i = 0; i < matrixheight; i++){
                for (int j = matrixwidth-1; j > argmatrix[0].length-1; j--){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    @Override
    public Matrix3X3 multiplyByMatrix(Matrix3X3 othermatrix) {
        double[][] transmatrix = othermatrix.getMatrix();
        double[][] tempmatrix = new double[matrixheight][matrixwidth];
        for (int i = 0; i < matrixheight; i++){
            for (int j = 0; j < matrixwidth; j++){
                tempmatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < matrixheight; i++){
            for (int j = 0; j < matrixwidth; j++){
                for (int k = 0; k < matrixheight; k++){
                    tempmatrix[i][j] += (matrix[i][k]*transmatrix[k][j]);
                }
            }
        }
        return new Matrix3X3(tempmatrix);
    }

    @Override
    public Vec3 multiplyByVector(Vec3 vec){
        double[] multipliedvector = new double[matrixheight];
        for (int i = 0; i < matrixheight; i++){
            double v0 = matrix[i][0] * vec.getX();
            double v1 = matrix[i][1] * vec.getY();
            double v2 = matrix[i][2] * vec.getZ();
            double v3 = matrix[i][3] * vec.getW();
            multipliedvector[i] = v0 + v1 + v2 + v3;
        }
        return new Vec3(multipliedvector[0],
                        multipliedvector[1],
                        multipliedvector[2],
                        multipliedvector[3]);
    }

    public void printMatrix(){
        for(int i = 0; i < matrixheight; i++){
            System.out.println();
            for (int j = 0; j < matrixwidth; j++){
                System.out.print(matrix[i][j]+" ");
            }
        }
    }

    public double[][] getMatrix(){
        return matrix;
    }
}