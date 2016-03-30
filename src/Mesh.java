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
        addVertex(new Vector (5, -5, 40), 0);
        addVertex(new Vector (-5, -5, 40), 1);
        addVertex(new Vector (-5, 5, 40), 2);
        addVertex(new Vector (5, 5, 40), 3);
        addVertex(new Vector (5, -5, 50), 4);
        addVertex(new Vector (-5, -5, 50), 5);
        addVertex(new Vector (-5, 5, 50), 6);
        addVertex(new Vector (5, 5, 50), 7);
    }

    private int getMaximumLines(int verticescount){
        return (verticescount*(verticescount -1))/2;
    }

    public void addVertex(Vector vertex, int index){
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

    public void move(VectorMultipliable matrix){
        for(int i = 0; i < verticescount; i++){
            vertices[i].updateVector(matrix.multiply(vertices[i]));
        }
    }

    public int getVerticesCount(){
        return verticescount;
    }

    public Vector getVertex(int index){
        return vertices[index];
    }

    public int[][] getLines(){
        return lines;
    }
}