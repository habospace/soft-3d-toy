/**
 * Created by habospace on 25/03/16.
 */
public class Vector {

    private int index = 1;
    private double[] vector;

    public Vector (double x, double y, double z){
        vector = new double[4];
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
        vector[3] = index;
    }

    public double[] GetVector(){
        return vector;
    }



}
