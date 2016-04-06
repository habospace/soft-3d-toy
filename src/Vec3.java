/**
 * Created by habospace on 25/03/16.
 */
public class Vec3 extends Vec2{

    private static double index = 1;
    private double Z;
    private  double w;

    public Vec3(double x,
                double y,
                double z){
        super(x, y);
        this.Z = z;
        this.w = index;
    }

    public Vec3(double x,
                double y,
                double z,
                double w){
        super(x, y);
        this.Z = z;
        this.w = w;

    }

    public Vec3(){
        super();
        this.Z = 0;
        this.w = index;
    }

    public void updateAbsLocation(double x,
                                  double y,
                                  double z){
        super.updateAbsLocation(x, y);
        Z = z;
    }

    public void updateRelLocation(double deltaX,
                                  double deltaY,
                                  double deltaZ){
        super.updateRelLocation(deltaX, deltaY);
        Z += deltaZ;
    }

    public Vec3 normalize(){
        double magnitude = Math.sqrt((X*X) + (Y*Y) + (Z*Z));
        return new Vec3(X/magnitude, Y/magnitude, Z/magnitude);
    }

    public Vec3 crossProduct(Vec3 vec){
        return new Vec3(Y * vec.getZ() - Z * vec.getY(),
                        Z * vec.getX() - X * vec.getZ(),
                        X * vec.getY() - Y * vec.getX());
    }

    public double dotProduct (Vec3 vec){
        return (X * vec.getX() + Y * vec.getY() + Z * vec.getZ());
    }

    public double getZ(){
        return Z;
    }

    public double getW(){
        return w;
    }

    public void print(){
        super.print();
        System.out.println("Z = "+Z);
        System.out.println("w = "+w);
    }
}