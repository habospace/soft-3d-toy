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

    public Vector (double [] position){
        vector[0] = position[0];
        vector[1] = position[1];
        vector[2] = position[2];
        vector[3] = index;
    }

    public void UpdateVector(double x, double y, double z){
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
    }

    public void UpdateVector(double[] newposition){
        vector[0] = newposition[0];
        vector[1] = newposition[1];
        vector[2] = newposition[2];
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