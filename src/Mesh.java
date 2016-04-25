public class Mesh {

    private final int verticescount;
    private final Vec3[] vertices;
    private final Triangle[] faces;

    public Mesh(int verticescount){
        this.verticescount = verticescount;
        this.vertices = new Vec3[verticescount];
        this.faces = new Triangle[calculateMaximumLines(verticescount)];
    }

    public Mesh(){
        this.verticescount = 8;
        this.vertices = new Vec3[verticescount];
        this.faces = new Triangle[12];
        addVertex(new Vec3(5, -5, -50), 0);
        addVertex(new Vec3(-5, -5, -50), 1);
        addVertex(new Vec3(-5, 5, -50), 2);
        addVertex(new Vec3(5, 5, -50), 3);
        addVertex(new Vec3(5, -5, -60), 4);
        addVertex(new Vec3(-5, -5, -60), 5);
        addVertex(new Vec3(-5, 5, -60), 6);
        addVertex(new Vec3(5, 5, -60), 7);
        addFace(new Triangle(0, 2, 1), 0);
        addFace(new Triangle(0, 3, 2), 1);
        addFace(new Triangle(4, 5, 6), 2);
        addFace(new Triangle(4, 6, 7), 3);
        addFace(new Triangle(3, 7, 2), 4);
        addFace(new Triangle(2, 7, 6), 5);
        addFace(new Triangle(0, 1, 4), 6);
        addFace(new Triangle(1, 5, 4), 7);
        addFace(new Triangle(4, 7, 0), 8);
        addFace(new Triangle(0, 7, 3), 9);
        addFace(new Triangle(1, 2, 6), 10);
        addFace(new Triangle(5, 1, 6), 11);
    }

    private int calculateMaximumLines(int verticescount){
        return (verticescount*(verticescount -1))/2;
    }

    public void addVertex(Vec3 vertex, int index){
        try {
            vertices[index] = vertex;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown at: "+error);
        }
    }

    public void addFace(Triangle face, int index){
        try {
            faces[index] = face;
        }
        catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Exception thrown at: "+error);
        }
    }

    public void move(Matrix3X3 matrix){
        for(int i = 0; i < verticescount; i++){
           vertices[i] = vertices[i].multiplyByMatrix(matrix);
        }
    }

    public Vec3[] getVertices(){
        return vertices;
    }

    public Vec3 getVertex(int index){
        return vertices[index];
    }

    public int getVerticesCount(){
        return verticescount;
    }

    public Triangle[] getFaces(){
        return  faces;
    }
}