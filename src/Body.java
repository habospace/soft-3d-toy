/**
 * Created by habospace on 25/03/16.
 */
public class Body {

    private Vector[] vertices;
    private int verticesCount;

    public Body(int verticesCount){
        this.verticesCount = verticesCount;
        this.vertices = new Vector[verticesCount];
    }

    public void AddVertex(Vector vertex, int index){
        try {
            vertices[index] = vertex;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown  :" + error);
        }
    }

    public void Move(VectorMultipliable matrix){
        for(int i = 0; i < verticesCount; i++){
            vertices[i].UpdateVector(matrix.Multiply(vertices[i]));
        }
    }
}
