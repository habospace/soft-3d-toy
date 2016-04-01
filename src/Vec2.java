/**
 * Created by habospace on 01/04/16.
 */
public class Vec2 {

    protected double X;
    protected double Y;
    private static final int vectorSize = 2;

    public Vec2(double x, double y){
        this.X = x;
        this.Y = y;
    }

    public Vec2(double[] position){
        this.X = position[0];
        this.Y = position[1];
    }

    public Vec2(){
        this.X = 0;
        this.Y = 0;
    }

    public void updateAbsLocation(double x, double y){
        this.X = x;
        this.Y = y;
    }

    public void updateAbsLocation(double[] newloc){
        this.X = newloc[0];
        this.Y = newloc[1];
    }

    public void updateRelLocation(double x, double y){
        this.X += x;
        this.Y += y;
    }

    public void updateRelLocation(double[] newloc){
        this.X += newloc[0];
        this.Y = newloc[1];
    }

    public double[] getVector(){
        double[] vector = {X, Y};
        return vector;
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
