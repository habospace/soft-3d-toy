import java.lang.Math;
/**
 * Created by habospace on 01/04/16.
 */
public class Vec2 {

    protected double X;
    protected double Y;

    public Vec2(double x,
                double y){
        this.X = x;
        this.Y = y;
    }

    public Vec2(){
        this.X = 0;
        this.Y = 0;
    }

    public void updateAbsLocation(double x,
                                  double y){
        X = x;
        Y = y;
    }

    public void updateRelLocation(double deltaX,
                                  double deltaY){
        X += deltaX;
        Y += deltaY;
    }

    public Vec2 normalize(){
        double magnitude = Math.sqrt((X*X) + (Y*Y));
        return new Vec2(X/magnitude, Y/magnitude);
    }

    public double crossProduct(Vec2 vec){
        return (X * vec.getY() - Y * vec.getX());
    }

    public double getX(){
        return X;
    }

    public double getY(){
        return Y;
    }

    public void print(){
        System.out.println("X = "+X);
        System.out.println("Y = "+Y);
    }
}