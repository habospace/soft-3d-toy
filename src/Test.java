import java.lang.Math;

public class Test {

    public static void main (String[] args) {

        Mesh cube = new Mesh(8);
        cube.addVertex(new Vec3(-1, 1, 1), 0);
        cube.addVertex(new Vec3(1, 1, 1), 1);
        cube.addVertex(new Vec3(-1, -1, 1), 2);
        cube.addVertex(new Vec3(-1, -1, -1), 3);
        cube.addVertex(new Vec3(-1, 1, -1), 4);
        cube.addVertex(new Vec3(1, 1, -1), 5);
        cube.addVertex(new Vec3(1, -1, 1), 6);
        cube.addVertex(new Vec3(1, -1, -1), 7);

        TranslationMatrix matrix1 = new TranslationMatrix(1, 2, 3);
        matrix1.printMatrix();
        System.out.println();
        System.out.println();
        ProjectionMatrix matrix3 = new ProjectionMatrix();
        matrix3.printMatrix();
        System.out.println();
        System.out.println();

        //Vec3 vector1 = new Vec3(0, 0, 0);
        //vector1.print();
        System.out.println();
        System.out.println();
        System.out.println(Math.toRadians(45));
        MultipliableByMatrix matrix4 = new TranslationMatrix(1, 1, 1);
        Vec3 vector5 = new Vec3(2, 2, -3);
        Vec3 vector6 = new Vec3(2, -1, -6);
        //MultipliableByMatrix lookmat = new LookAtMatrix(new Vec3(0, 0, 0), new Vec3(-2, 1, 3), new Vec3(0, 1, 0));
        //Vec3 vector7 = lookmat.multiplyByVector(vector5);
        //vector7.print();
        //Vec3 vector8 = lookmat.multiplyByVector(vector6);
        //vector8.print();
        //Vec3 vector1 = new Vec3(0, 15, 0);
        //Vec3 vector2 = new Vec3(0, 0, 132);
        //Vec3 vector3 = vector1.crossProduct(vector2);
        //vector3.print();
        //Vec3 vector4 = vector2.crossProduct(vector1);
        //vector4.print();
        Vec3 vector9 = new Vec3(0, 0, 1);
        Vec3 vector10 = new Vec3(0, 0, -1);
        Matrix3X3 projectionmatrix = new ProjectionMatrix();
        Vec3 projvector1 = projectionmatrix.multiplyByVector(vector9);
        Vec3 projvector2 = projectionmatrix.multiplyByVector(vector10);
        double projx1 = (projvector1.getX() / projvector2.getW()*300/2)+300/2;
        double projy1 = (projvector1.getY() / projvector2.getW()*300/2)+300/2;
        double projx2 = (projvector2.getX() / projvector2.getW()*300/2)+300/2;
        double projy2 = (projvector2.getY() / projvector2.getW()*300/2)+300/2;
        System.out.println("X1 = "+projx1+" Y1 = "+projy1);
        projvector1.print();
        System.out.println("X2 = "+projx2+" Y2 = "+projy2);
        projvector2.print();
        System.out.println();
        Vec3 vecc1 = new Vec3(5, 5, 5).normalize();
        Vec3 vecc2 = new Vec3(5, -0, -2).normalize();
        double num = vecc1.dotProduct(vecc2);
        System.out.println(num);
        System.out.println(Math.toDegrees(Math.cosh(num)));
        Vec3 vecc3 = new Vec3(5, 5, 5);
        Vec3 vecc4 = new Vec3(-5, 5, 5);
        Vec3 vecc5 = new Vec3(-5, -5, 5);
        Vec3 vecc6 = new Vec3(5, -5, 5);
        Vec3 normal1 = Vec3.surfaceNormalVector(vecc3, vecc4, vecc6);
        Vec3 normal2 = Vec3.surfaceNormalVector(vecc4, vecc5, vecc6);
        normal1.print();
        normal2.print();
        Mesh mesh = new Mesh();
        Vec3[] vertices = mesh.getVertices();
        Triangle[] faces = mesh.getFaces();
        int i = 0;
        for (Triangle face : faces){
            i += 1;
            Vec3 p1 = vertices[(int)face.getVertex1()];
            Vec3 p2 = vertices[(int)face.getVertex2()];
            Vec3 p3 = vertices[(int)face.getVertex3()];
            Vec3 surfacenormal = Vec3.surfaceNormalVector(p1, p2, p3);
            System.out.println(i+"  "+surfacenormal.length());
        }
    }
}