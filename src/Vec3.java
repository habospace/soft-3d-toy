/**
 * Created by habospace on 25/03/16.
 */
public class Vec3 {

    private static double index = 1;
    private double X;
    private double Y;
    private double Z;
    private  double w;

    public Vec3(double x,
                double y,
                double z){
        this.X = x;
        this.Y = y;
        this.Z = z;
        this.w = index;
    }

    public Vec3(double x,
                double y,
                double z,
                double w){
        this.X = x;
        this.Y = y;
        this.Z = z;
        this.w = w;

    }

    public Vec3(){
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        this.w = index;
    }

    public Vec3 add(Vec3 vec){
        return new Vec3(X + vec.getX(),
                        Y + vec.getY(),
                        Z + vec.getZ());
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

    public  double getX(){
        return X;
    }

    public double getY(){
        return Y;
    }

    public double getZ(){
        return Z;
    }

    public double getW(){
        return w;
    }

    public void print(){
        System.out.println("X = "+X);
        System.out.println("Y = "+Y);
        System.out.println("Z = "+Z);
        System.out.println("w = "+w);
    }
}