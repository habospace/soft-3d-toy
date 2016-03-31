import java.lang.Math;
/**
 * Created by habospace on 25/03/16.
 */
public class Test {

    public static void main (String[] args){

        Mesh cube = new Mesh(8);
        cube.addVertex(new Vector(-1, 1, 1), 0);
        cube.addVertex(new Vector(1, 1, 1), 1);
        cube.addVertex(new Vector(-1, -1, 1), 2);
        cube.addVertex(new Vector(-1, -1, -1), 3);
        cube.addVertex(new Vector(-1, 1, -1), 4);
        cube.addVertex(new Vector(1, 1, -1), 5);
        cube.addVertex(new Vector(1, -1, 1), 6);
        cube.addVertex(new Vector(1, -1, -1), 7);

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

        Vector vector1 = new Vector(0, 0, 0);
        vector1.printVector();
        System.out.println();
        System.out.println();

        double[] multipliedvector1 = matrix1.multiply(vector1);
        System.out.println();
        System.out.println();
        System.out.print(multipliedvector1[0]+"; ");
        System.out.print(multipliedvector1[1]+"; ");
        System.out.print(multipliedvector1[2]+"; ");
        System.out.print(multipliedvector1[3]+"; ");
        System.out.println();
        System.out.println();

        Vector vector2 = new Vector(5, 5, 30);
        double[] multipliedvector2 = matrix3.multiply(vector2);
        System.out.println();
        System.out.println();
        System.out.print(multipliedvector2[0]+"; ");
        System.out.print(multipliedvector2[1]+"; ");
        System.out.print(multipliedvector2[2]+"; ");
        System.out.print(multipliedvector2[3]+"; ");
        System.out.println();
        System.out.println();

        System.out.println(Math.toRadians(45));
    }
}