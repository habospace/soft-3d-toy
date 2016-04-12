import java.lang.Math;

public class Vec2 implements Vec<Vec2> {

    private final static double index = 1;
    private final double X;
    private final double Y;
    private final double W;

    public Vec2(double x,
                double y){
        this.X = x;
        this.Y = y;
        this.W = index;
    }

    public Vec2(double x,
                double y,
                double w){
        this.X = x;
        this.Y = y;
        this.W = w;
    }

    @Override
    public Vec2 addVector(Vec2 vec){
        return new Vec2(X + vec.getX(),
                        Y + vec.getY());
    }

    @Override
    public Vec2 normalize(){
        double magnitude = Math.sqrt((X*X) + (Y*Y));
        return new Vec2(X/magnitude, Y/magnitude);
    }

    @Override
    public double dotProduct(Vec2 vec){
        return (X*vec.getX() + Y*vec.getY());
    }

    public double getX(){
        return X;
    }

    public double getY(){
        return Y;
    }

    public double getW(){
        return W;
    }

    public void print(){
        System.out.println("X = "+X);
        System.out.println("Y = "+Y);
    }
}