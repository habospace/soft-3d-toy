import java.lang.Math;
/**
 * Created by habospace on 01/04/16.
 */
public class Vec2 {

    private final double X;
    private final double Y;
    private boolean onscreen;

    public Vec2(double x,
                double y){
        this.X = x;
        this.Y = y;
    }

    public Vec2(){
        this.X = 0;
        this.Y = 0;
    }

    public void checkOnScreen(double w){
        if(0 < w){
            this.onscreen = true;
        }
        else{
            this.onscreen = false;
        }
    }

    public Vec2 add(Vec2 vec){
        return new Vec2(X + vec.getX(),
                        Y + vec.getY());
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

    public boolean isOnScreen(){
        return onscreen;
    }

    public void print(){
        System.out.println("X = "+X);
        System.out.println("Y = "+Y);
    }
}