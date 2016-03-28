/**
 * Created by habospace on 25/03/16.
 */
public class Body {

    private String name;
    private Vector[] vertices;
    private Vector position;
    private Vector rotation;
    private int verticesCount;

    public Body(String name, int verticesCount){
        this.verticesCount = verticesCount;
        this.vertices = new Vector[verticesCount];
        this.name = name;
    }

    public void AddVertex(Vector vertex, int index){
        try {
            this.vertices[index] = vertex;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown  :" + error);
        }
    }

    public void MoveBody (VectorMultipliable matrix){
        for(int i = 0; i < vertices.length; i++){
            double[] currentvertex = vertices[i].GetVector();
        }
    }
}
