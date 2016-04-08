import java.lang.Math;
/**
 * Created by habospace on 25/03/16.
 */
public class Test {

    public static void main (String[] args){

        Mesh cube = new Mesh(8, new Vec3(0, 0, 0));
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
        RotationMatrix matrix2 = new RotationMatrix(0, 0, 0, 0, 0, 1, 360);
        matrix2.printMatrix();
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
        Multipliable matrix4 = new TranslationMatrix(1, 1, 1);
        Vec3 vector5 = new Vec3(2, 2, -3);
        Vec3 vector6 = new Vec3(2, -1, -6);
        Multipliable lookmat = new LookAtMatrix(new Vec3(0, 0, 0), new Vec3(-2, 1, 3), new Vec3(0, 1, 0));
        Vec3 vector7 = lookmat.multiplyByVector(vector5);
        vector7.print();
        Vec3 vector8 = lookmat.multiplyByVector(vector6);
        vector8.print();
        //Vec3 vector1 = new Vec3(0, 15, 0);
        //Vec3 vector2 = new Vec3(0, 0, 132);
        //Vec3 vector3 = vector1.crossProduct(vector2);
        //vector3.print();
        //Vec3 vector4 = vector2.crossProduct(vector1);
        //vector4.print();


    }
}