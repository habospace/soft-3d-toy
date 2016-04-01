/**
 * Created by habospace on 01/04/16.
 */
public class Point {

    private int X;
    private int Y;

    public Point(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public Point(int[] point){
        this.X = point[0];
        this.Y = point[1];
    }

    public Point(){
        this.X = 0;
        this.Y = 0;
    }

    public void updateAbsLocation(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public void updateAbsLocatio(int[] newloc){
        this.X = newloc[0];
        this.Y = newloc[1];
    }

    public void updateRelLocation(int x, int y){
        this.X += x;
        this.Y += y;
    }

    public void updateRelLocation(int[] newloc){
        this.X += newloc[0];
        this.Y = newloc[1];
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }
}
