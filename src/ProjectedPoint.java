class ProjectedPoint {

    private final double x;
    private final double y;
    private final Vec2 uvPosition;


    ProjectedPoint (double x, double y,
                   Vec2 uvPosition) {
        this.x = x;
        this.y = y;
        this.uvPosition = uvPosition;
    }

    double getX () {
        return x;
    }

    double getY () {
        return y;
    }

    Vec2 getUvPosition () {
        return uvPosition;
    }

    void print () {
        System.out.println("X = "+x);
        System.out.println("Y = "+y);
    }
}
