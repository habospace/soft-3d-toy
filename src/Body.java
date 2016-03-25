/**
 * Created by habospace on 25/03/16.
 */
public class Body {

    private String name;
    private Vector[] vertices;
    private Vector position;
    private Vector rotation;

    public Body(String name, int verticesCount){
        this.vertices = new Vector[verticesCount];
        this.name = name;
    }
}
