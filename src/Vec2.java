import java.lang.Math;

class Vec2 implements Vec<Vec2> {

    private final static double index = 1;
    private final double x;
    private final double y;
    private final double w;

    Vec2(double x,
         double y) {
        this.x = x;
        this.y = y;
        this.w = index;
    }

    Vec2(double x,
         double y,
         double w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    @Override
    public Vec2 addVector(Vec2 vec) {
        return new Vec2(x + vec.getX(),
                y + vec.getY());
    }

    @Override
    public Vec2 normalize() {
        double magnitude = length();
        return new Vec2(x /magnitude, y /magnitude);
    }

    @Override
    public double dotProduct(Vec2 vec) {
        return (x *vec.getX() + y *vec.getY());
    }

    @Override
    public double length() {
        return Math.sqrt((y * y) + (x * x));
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getW() {
        return w;
    }

    public void print() {
        System.out.println("x = "+ x);
        System.out.println("y = "+ y);
        System.out.println("w = "+ w);
    }
}