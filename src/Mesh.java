/**
 * Created by habospace on 25/03/16.
 */
public class Mesh {

    private int verticescount;
    private Vector[] vertices;
    private int maxlinescount;
    private int[][] lines;

    public Mesh(int verticescount){
        this.verticescount = verticescount;
        this.vertices = new Vector [verticescount];
        this.maxlinescount = getMaximumLines(verticescount);
        this.lines = new int[maxlinescount][2];
    }

    public Mesh(){
        this.verticescount = 8;
        this.vertices = new Vector[verticescount];
        this.maxlinescount = getMaximumLines(verticescount);
        this.lines = new int[maxlinescount][2];
        AddVertex(new Vector (5, -5, 50), 0);
        AddVertex(new Vector (-5, -5, 50), 1);
        AddVertex(new Vector (-5, 5, 50), 2);
        AddVertex(new Vector (5, 5, 50), 3);
        AddVertex(new Vector (5, -5, 60), 4);
        AddVertex(new Vector (-5, -5, 60), 5);
        AddVertex(new Vector (-5, 5, 60), 6);
        AddVertex(new Vector (5, 5, 60), 7);
    }

    private int getMaximumLines(int verticescount){
        return (verticescount*(verticescount -1))/2;
    }

    public void AddVertex(Vector vertex, int index){
        try {
            vertices[index] = vertex;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown  :" + error);
        }
    }

    public void addLine(int vertex1, int vertex2, int index){
        try{
            lines[index][0] = vertex1;
            lines[index][1] = vertex2;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown :" + error);
        }
    }

    public void Move(VectorMultipliable matrix){
        for(int i = 0; i < verticescount; i++){
            vertices[i].UpdateVector(matrix.Multiply(vertices[i]));
        }
    }

    public int getVerticescount(){
        return verticescount;
    }

    public Vector getVertex(int index){
        return vertices[index];
    }
}
