/**
 * Created by habospace on 25/03/16.
 */
public class Test {

    public static void main (String[] args){
        Body cube = new Body("Cube1", 8);
        cube.AddVertex(new Vector(-1, 1, 1), 0);
        cube.AddVertex(new Vector(1, 1, 1), 1);
        cube.AddVertex(new Vector(-1, -1, 1), 2);
        cube.AddVertex(new Vector(-1, -1, -1), 3);
        cube.AddVertex(new Vector(-1, 1, -1), 4);
        cube.AddVertex(new Vector(1, 1, -1), 5);
        cube.AddVertex(new Vector(1, -1, 1), 6);
        cube.AddVertex(new Vector(1, -1, -1), 7);

        TranslationMatrix matrix1 = new TranslationMatrix(1, 2, 3);
        matrix1.PrintMatrix();
        System.out.println();
        RotationMatrix matrix2 = new RotationMatrix(0, 0, 0, 0, 0, 1, 360);
        matrix2.PrintMatrix();
        System.out.println();
        ProjectionMatrix matrix3 = new ProjectionMatrix();
        matrix3.PrintMatrix();
        System.out.println();
    }

}
