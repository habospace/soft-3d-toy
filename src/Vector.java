/**
 * Created by habospace on 25/03/16.
 */
public class Vector {

    private static double index = 1;
    private static final int vectorsize = 4;
    private double[] vector = new double[vectorsize];

    public Vector (double x, double y, double z){
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
        vector[3] = index;
    }

    public double[] GetVector(){
        return vector;
    }

    public void PrintVector(){
        for(int i = 0; i < vectorsize; i++){
            System.out.print(vector[i]+" ");
        }
    }
}