class Matrix3X3 implements MultipliableByVector<Vec3, Vec3>, MultipliableByMatrix<Matrix3X3, Matrix3X3> {

    static final int matrixHeight = 4;
    static final int matrixWidth = 4;
    final double[][] matrix = new double[matrixHeight][matrixWidth];

    Matrix3X3 () {
        makeIdentityMatrix();
    }

    private Matrix3X3 (double[][] array) {
        makeMatrixFromArray(array);
    }

    public static xAxisRotationMatrix constructXaxisRotationMatrix (double theta) {
        return new xAxisRotationMatrix(theta);
    }

    public static yAxisRotationMatrix constructYaxisRotationMatrix (double theta) {
        return new yAxisRotationMatrix(theta);
    }

    public static TranslationMatrix constructTranslationMatrix (double x,
                                                                double y,
                                                                double z) {
        return new TranslationMatrix (x, y, z);
    }

    public static ProjectionMatrix constructProjectionMatrix () {
        return new ProjectionMatrix ();
    }

    public static Matrix3X3 constructIdentityMatrix () {
        return new Matrix3X3();
    }

    public static Matrix3X3 constructMatrixFromArray (double[][] array) {
        return new Matrix3X3(array);
    }

    void makeIdentityMatrix () {
        for(int i = 0; i < matrixHeight; i++){
            for (int j = 0; j < matrixWidth; j++){
                if (i == j){
                    matrix[i][j] = 1;
                }
                else {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void makeMatrixFromArray (double[][] argMatrix) {
        int limit1 = 0;
        int limit2 = 0;
        if (argMatrix.length >= matrixHeight){
            limit1 += matrixHeight;
        }
        else{
            limit1 += argMatrix.length;
        }
        if (argMatrix[0].length >= matrixWidth){
            limit2 += matrixWidth;
        }
        else{
            limit2 += argMatrix[0].length;
        }
        for (int i = 0; i < limit1; i++){
            System.arraycopy(argMatrix[i], 0, matrix[i], 0, limit2);
        }
        if (limit1 < matrixHeight){
            for (int i = matrixHeight -1; i > argMatrix.length-1; i--){
                for (int j = 0; j < matrixWidth; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        if (limit2 < matrixWidth){
            for (int i = 0; i < matrixHeight; i++){
                for (int j = matrixWidth -1; j > argMatrix[0].length-1; j--){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    @Override
    public Matrix3X3 multiplyByMatrix (Matrix3X3 otherMatrix) {
        double[][] transMatrix = otherMatrix.getMatrix();
        double[][] tempMatrix = new double[matrixHeight][matrixWidth];
        for (int i = 0; i < matrixHeight; i++){
            for (int j = 0; j < matrixWidth; j++){
                tempMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < matrixHeight; i++){
            for (int j = 0; j < matrixWidth; j++){
                for (int k = 0; k < matrixHeight; k++){
                    tempMatrix[i][j] += (matrix[i][k]*transMatrix[k][j]);
                }
            }
        }
        return new Matrix3X3(tempMatrix);
    }

    @Override
    public Vec3 multiplyByVector (Vec3 vec) {
        double[] multipliedVector = new double[matrixHeight];
        for (int i = 0; i < matrixHeight; i++){
            double v0 = matrix[i][0] * vec.getX();
            double v1 = matrix[i][1] * vec.getY();
            double v2 = matrix[i][2] * vec.getZ();
            double v3 = matrix[i][3] * vec.getW();
            multipliedVector[i] = v0 + v1 + v2 + v3;
        }
        return new Vec3(multipliedVector[0],
                multipliedVector[1],
                multipliedVector[2],
                multipliedVector[3]);
    }

    void printMatrix () {
        for(int i = 0; i < matrixHeight; i++){
            System.out.println();
            for (int j = 0; j < matrixWidth; j++){
                System.out.print(matrix[i][j]+" ");
            }
        }
    }

    double[][] getMatrix () {
        return matrix;
    }
}