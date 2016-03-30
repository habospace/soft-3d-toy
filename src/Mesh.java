/**
 * Created by habospace on 25/03/16.
 */
public class Mesh {

    private int verticescount;
    private Vector[] vertices;
    private int[][] projection;

    public Mesh(int verticescount){
        this.verticescount = verticescount;
        this.vertices = new Vector [verticescount];
        this.projection = new int[verticescount][2];
    }

    public Mesh(){
        this.verticescount = 8;
        this.vertices = new Vector[verticescount];
        this.projection = new int[verticescount][2];
        AddVertex(new Vector (5, -5, 50), 0);
        AddVertex(new Vector (-5, -5, 50), 1);
        AddVertex(new Vector (-5, 5, 50), 2);
        AddVertex(new Vector (5, 5, 50), 3);
        AddVertex(new Vector (5, -5, 60), 4);
        AddVertex(new Vector (-5, -5, 60), 5);
        AddVertex(new Vector (-5, 5, 60), 6);
        AddVertex(new Vector (5, 5, 60), 7);
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
        for(int i = 0; i < verticescount; i++){
            vertices[i].UpdateVector(matrix.Multiply(vertices[i]));
        }
    }

    public void Project(VectorMultipliable projectionmatrix,
                        int framewidth, int frameheight){
        for (int i = 0; i < verticescount; i++){
            double[] projvector = projectionmatrix.Multiply(vertices[i]);
            double projx = (projvector[0]/projvector[3] *framewidth/2)+framewidth/2;
            projection[i][0] = (int) projx;
            double projy = (projvector[1]/projvector[3]*frameheight/2)+frameheight/2;
            projection[i][1] = (int) projy;
        }
    }

    public int[][] GetProjection(){
        return projection;
    }
}
