/**
 * Created by habospace on 25/03/16.
 */
public class Vec3 extends Vec2{

    private static double index = 1;
    private double Z;
    private static final int vectorSize = 3;

    public Vec3(double x, double y, double z){
        super(x, y);
        this.Z = z;
    }

    public Vec3(double [] position){
        super(position);
        this.Z = position[2];
    }

    public Vec3(){
        super();
        this.Z = 0;
    }

    public void updateAbsLocation(double x, double y, double z){
        super.updateAbsLocation(x, y);
        this.Z = z;
    }

    public void updateAbsLocation(double[] newloc){
        super.updateAbsLocation(newloc);
        this.Z = newloc[2];
    }

    public double[] getVector(){
        double[] vector = {X, Y, Z, index};
        return vector;
    }

    public double getZ(){
        return Z;
    }

    public void print(){
        super.print();
        System.out.println("Z = "+Z);
    }
}
