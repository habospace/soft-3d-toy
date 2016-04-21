import  java.lang.Math;

public class Vec3 implements Vec <Vec3>, MultipliableByMatrix<Vec3, Matrix3X3> {

    private final static double index = 1;
    private final double X;
    private final double Y;
    private final double Z;
    private final double W;

    public Vec3(double x,
                double y,
                double z){
        this.X = x;
        this.Y = y;
        this.Z = z;
        this.W = index;
    }

    public Vec3(double x,
                double y,
                double z,
                double w){
        this.X = x;
        this.Y = y;
        this.Z = z;
        this.W = w;
    }

    public Vec3 crossProduct(Vec3 vec){
        return new Vec3(Y * vec.getZ() - Z * vec.getY(),
                        Z * vec.getX() - X * vec.getZ(),
                        X * vec.getY() - Y * vec.getX());
    }

    @Override
    public Vec3 addVector(Vec3 vec){
        return new Vec3(X + vec.getX(),
                        Y + vec.getY(),
                        Z + vec.getZ());
    }

    @Override
    public Vec3 normalize(){
        double magnitude = Math.sqrt((X*X) + (Y*Y) + (Z*Z));
        return new Vec3(X/magnitude,
                        Y/magnitude,
                        Z/magnitude);
    }

    @Override
    public double dotProduct (Vec3 vec){
        return (X*vec.getX() + Y*vec.getY() + Z*vec.getZ());
    }

    @Override
    public Vec3 multiplyByMatrix(Matrix3X3 transmat){
        double[][] matrix = transmat.getMatrix();
        double[] multipliedvector = new double[Matrix3X3.matrixheight];
        for (int i = 0; i < Matrix3X3.matrixheight; i++){
            double v0 = matrix[i][0] * X;
            double v1 = matrix[i][1] * Y;
            double v2 = matrix[i][2] * Z;
            double v3 = matrix[i][3] * W;
            multipliedvector[i] = v0 + v1 + v2 + v3;
        }
        return new Vec3(multipliedvector[0],
                        multipliedvector[1],
                        multipliedvector[2],
                        multipliedvector[3]);
    }

    public static Vec3 getrSurfaceNormalVector(Vec3 vertex1,
                                               Vec3 vertex2,
                                               Vec3 vertex3){
        Vec3 side1 = new Vec3(vertex2.getX() - vertex1.getX(),
                              vertex2.getY() - vertex1.getY(),
                              vertex2.getZ() - vertex1.getZ());
        Vec3 side2 = new Vec3(vertex3.getX() - vertex1.getX(),
                              vertex3.getY() - vertex1.getY(),
                              vertex3.getZ() - vertex1.getZ());

        return new Vec3((side1.getY()*side2.getZ()) - (side1.getZ()*side2.getY()),
                        (side1.getZ()*side2.getX()) - (side1.getX()*side2.getZ()),
                        (side1.getX()*side2.getY()) - (side1.getY()*side2.getX()));
    }

    public double getX(){
        return X;
    }

    public double getY(){
        return Y;
    }

    public double getZ(){
        return Z;
    }

    public double getW(){
        return W;
    }

    public void print(){
        System.out.println("X = "+X);
        System.out.println("Y = "+Y);
        System.out.println("Z = "+Z);
        System.out.println("W = "+W);
    }
}