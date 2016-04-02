/**
 * Created by habospace on 02/04/16.
 */
public class Edge {

    private int vertex1;
    private int vertex2;

    public Edge(int vertex1, int vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Edge (int[] vertices){
        this.vertex1 = vertices[0];
        this.vertex2 = vertices[1];
    }

    public int[] getVertices(){
        int[] vertices = {vertex1, vertex2};
        return vertices;
    }

    public int getVertex1(){
        return vertex1;
    }

    public int getVertex2(){
        return vertex2;
    }
}
