/**
 * Created by habospace on 28/03/16.
 */
public class TransformationMatrix implements VectorMultipliable{

    protected static final int matrixheight = 4;
    protected static final int matrixwidth = 4;
    protected double[][] matrix = new double[matrixheight][matrixwidth];

    public double[][] GetMatrix (){
        return matrix;
    }

    public void PrintMatrix (){
        for(int i = 0; i < matrixheight; i++){
            System.out.println();
            for (int j = 0; j < matrixwidth; j++){
                System.out.print(matrix[i][j]+" ");
            }
        }
    }
}
