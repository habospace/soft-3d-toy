/**
 * Created by habospace on 25/03/16.
 */
public class Mesh {

    private int verticescount;
    private Vec3[] vertices;
    private Edge[] edges;

    public Mesh(int verticescount, Vec3 centre){
        this.verticescount = verticescount;
        this.vertices = new Vec3[verticescount];
        this.edges = new Edge[calculateMaximumLines(verticescount)];
    }

    public Mesh(){
        this.verticescount = 8;
        this.vertices = new Vec3[verticescount];
        this.edges = new Edge[12];
        addVertex(new Vec3(5, -5, -50), 0);
        addVertex(new Vec3(-5, -5, -50), 1);
        addVertex(new Vec3(-5, 5, -50), 2);
        addVertex(new Vec3(5, 5, -50), 3);
        addVertex(new Vec3(5, -5, -60), 4);
        addVertex(new Vec3(-5, -5, -60), 5);
        addVertex(new Vec3(-5, 5, -60), 6);
        addVertex(new Vec3(5, 5, -60), 7);
        addEdge(0, 1, 0);
        addEdge(1, 2, 1);
        addEdge(2, 3, 2);
        addEdge(3, 0, 3);
        addEdge(4, 5, 4);
        addEdge(5, 6, 5);
        addEdge(6, 7, 6);
        addEdge(7, 4, 7);
        addEdge(0, 4, 8);
        addEdge(1, 5, 9);
        addEdge(2, 6, 10);
        addEdge(3, 7, 11);
    }

    private int calculateMaximumLines(int verticescount){
        return (verticescount*(verticescount -1))/2;
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

    public void move(Multipliable matrix){
        for(int i = 0; i < verticescount; i++){
           vertices[i] = matrix.multiplyByVector(vertices[i]);
        }
    }

    public Vec3[] getVertices(){
        return vertices;
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

}