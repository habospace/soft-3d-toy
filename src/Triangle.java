public class Triangle <T> implements Comparable<Triangle> {

    private final T vertex1;
    private final T vertex2;
    private final T vertex3;
    private double centreZDepth;
    private int colorCode;

    public Triangle(T vertex1,
                    T vertex2,
                    T vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public T getVertex1() {
        return vertex1;
    }

    public T getVertex2() {
        return vertex2;
    }

    public T getVertex3() {
        return vertex3;
    }

    public void setCentreZDepth(double z) {
        centreZDepth = z;
    }

    public double getCentreZDepth() {
        return  centreZDepth;
    }

    public void setColorCode(int code) {
        colorCode = code;
    }

    public int getColorCode() {
        return colorCode;
    }

    @Override
    public int compareTo(Triangle other) {
        double compareDepth = other.getCentreZDepth();
        if (compareDepth == centreZDepth){
            return 1;
        }
        return (int) (centreZDepth - compareDepth);
    }
}
