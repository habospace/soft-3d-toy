import  java.lang.Math;

class Vec3 implements Vec <Vec3>, MultipliableByMatrix<Vec3, Matrix3X3> {

    private final static double index = 1;
    private final double x;
    private final double y;
    private final double z;
    private final double w;

    Vec3 (double x,
          double y,
          double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = index;
    }

    Vec3 (double x,
          double y,
          double z,
          double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    private Vec3 crossProduct (Vec3 vec) {
        return new Vec3(y * vec.getZ() - z * vec.getY(),
                        z * vec.getX() - x * vec.getZ(),
                        x * vec.getY() - y * vec.getX());
    }

    @Override
    public Vec3 addVector (Vec3 vec) {
        return new Vec3(x + vec.getX(),
                        y + vec.getY(),
                        z + vec.getZ());
    }

    @Override
    public Vec3 normalize () {
        double magnitude = length();
        return new Vec3(x /magnitude,
                        y /magnitude,
                        z /magnitude);
    }

    @Override
    public double dotProduct (Vec3 vec) {
        return (x *vec.getX() + y *vec.getY() + z *vec.getZ());
    }

    @Override
    public double length () {
        return Math.sqrt((x * x)+(y * y)+(z * z));
    }

    @Override
    public Vec3 multiplyByMatrix (Matrix3X3 transMatrix) {
        double[][] matrix = transMatrix.getMatrix();
        double[] multipliedVector = new double[Matrix3X3.matrixHeight];
        for (int i = 0; i < Matrix3X3.matrixHeight; i++){
            double v0 = matrix[i][0] * x;
            double v1 = matrix[i][1] * y;
            double v2 = matrix[i][2] * z;
            double v3 = matrix[i][3] * w;
            multipliedVector[i] = v0 + v1 + v2 + v3;
        }
        return new Vec3(multipliedVector[0],
                        multipliedVector[1],
                        multipliedVector[2],
                        multipliedVector[3]);
    }

    static Vec3 surfaceNormalVector (Vec3 vertex1,
                                     Vec3 vertex2,
                                     Vec3 vertex3) {
        Vec3 side1 = new Vec3(vertex2.getX() - vertex1.getX(),
                              vertex2.getY() - vertex1.getY(),
                              vertex2.getZ() - vertex1.getZ());
        Vec3 side2 = new Vec3(vertex3.getX() - vertex1.getX(),
                              vertex3.getY() - vertex1.getY(),
                              vertex3.getZ() - vertex1.getZ());

        return side1.crossProduct(side2);
    }

    double getX () {
        return x;
    }

    double getY () {
        return y;
    }

    double getZ () {
        return z;
    }

    double getW () {
        return w;
    }

    void print (){
        System.out.println("x = "+ x);
        System.out.println("y = "+ y);
        System.out.println("z = "+ z);
        System.out.println("w = "+ w);
    }
}