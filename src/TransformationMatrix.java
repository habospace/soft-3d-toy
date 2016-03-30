/**
 * Created by habospace on 28/03/16.
 */
public class TransformationMatrix implements VectorMultipliable{

    protected static final int matrixheight = 4;
    protected static final int matrixwidth = 4;
    protected double[][] matrix = new double[matrixheight][matrixwidth];

    public double[][] getMatrix(){
        return matrix;
    }

    private void makeMatrix(){
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

    public void printMatrix(){
        for(int i = 0; i < matrixheight; i++){
            System.out.println();
            for (int j = 0; j < matrixwidth; j++){
                System.out.print(matrix[i][j]+" ");
            }
        }
    }

    public double[] multiply(Vector vec){
        double[] vector = vec.getDimensions();
        double[] multipliedvector = new double[matrixheight];
        for (int i = 0; i < matrixheight; i++){
            double v0 = matrix[i][0] * vector[0];
            double v1 = matrix[i][1] * vector[1];
            double v2 = matrix[i][2] * vector[2];
            double v3 = matrix[i][3] * vector[3];
            multipliedvector[i] = v0 + v1 + v2 + v3;
        }
        return multipliedvector;
    }
}