class Triangle implements Comparable<Triangle> {

    private final ProjectedPoint point1;
    private final ProjectedPoint point2;
    private final ProjectedPoint point3;
    private final double centreZDepth;
    private final double shading;
    private final Texture texture;

    Triangle (ProjectedPoint p1,
              ProjectedPoint p2,
              ProjectedPoint p3,
              double centreZDepth,
              double shading,
              Texture texture) {
        this.point1 = p1;
        this.point2 = p2;
        this.point3 = p3;
        this.centreZDepth = centreZDepth;
        this.shading = shading;
        this.texture = texture;
    }

    ProjectedPoint getPoint1 () {
        return point1;
    }

    ProjectedPoint getPoint2 () {
        return point2;
    }

    ProjectedPoint getPoint3 () {
        return point3;
    }

    private double getCentreZDepth () {
        return  centreZDepth;
    }

    double getShading () {
        return shading;
    }

    Texture getTexture () {
        return texture;
    }

    @Override
    public int compareTo (Triangle other) {
        double compareDepth = other.getCentreZDepth();
        if (compareDepth == centreZDepth){
            return 1;
        }
        return (int) (centreZDepth - compareDepth);
    }
}
