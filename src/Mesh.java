import java.util.Collections;
import java.util.Arrays;

/**
 * Created by habospace on 25/03/16.
 */
public class Mesh {

    private int verticescount;
    private int maxlinescount;
    private Vec3[] vertices;
    private Vec3 centre;
    private Edge[] edges;

    public Mesh(int verticescount, Vec3 centre){
        this.verticescount = verticescount;
        this.vertices = new Vec3[verticescount];
        this.maxlinescount = calculateMaximumLines(verticescount);
        this.edges = new Edge[maxlinescount];
        this.centre = centre;
    }

    public Mesh(){
        this.verticescount = 8;
        this.vertices = new Vec3[verticescount];
        this.maxlinescount = calculateMaximumLines(verticescount);
        this.edges = new Edge[maxlinescount];
        this.centre = new Vec3(0, 0, 55);
        addVertex(new Vec3(5, -5, 50), 0);
        addVertex(new Vec3(-5, -5, 50), 1);
        addVertex(new Vec3(-5, 5, 50), 2);
        addVertex(new Vec3(5, 5, 50), 3);
        addVertex(new Vec3(5, -5, 60), 4);
        addVertex(new Vec3(-5, -5, 60), 5);
        addVertex(new Vec3(-5, 5, 60), 6);
        addVertex(new Vec3(5, 5, 60), 7);
    }

    private int calculateMaximumLines(int verticescount){
        return (verticescount*(verticescount -1))/2;
    }

    public double[] calculateMaximumArea(VectorMultipliable projmatrix,
                                          int framewidth,
                                          int frameheight){
        Double[] tempXcont = new Double[verticescount];
        Double[] tempYcont = new Double[verticescount];
        for (int i = 0; i < verticescount; i++) {
            double[] projvector = projmatrix.multiply(vertices[i]);
            tempXcont[i] = (projvector[0]/projvector[3]*framewidth/2)+framewidth/2;
            tempYcont[i] = (projvector[1]/projvector[3]*frameheight/2)+frameheight/2;
        }
        double[] margins = {Collections.min(Arrays.asList(tempXcont)),
                            Collections.min(Arrays.asList(tempYcont)),
                            Collections.max(Arrays.asList(tempXcont)),
                            Collections.max(Arrays.asList(tempYcont))};
        return margins;
    }

    public void addVertex(Vec3 vertex, int index){
        try {
            vertices[index] = vertex;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown  :" + error);
        }
    }

    public void addEdge(int vertex1, int vertex2, int index){
        try{
            edges[index] = new Edge(vertex1, vertex2);
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown :" + error);
        }
    }

    public void move(VectorMultipliable matrix){
        for(int i = 0; i < verticescount; i++){
            vertices[i].updateAbsLocation(matrix.multiply(vertices[i]));
        }
    }

    public int getVerticesCount(){
        return verticescount;
    }

    public Vec3 getVertex(int index){
        return vertices[index];
    }

    public Edge[] getEdges(){
        return edges;
    }

    public Vec3 getCentre(){
        return centre;
    }
}