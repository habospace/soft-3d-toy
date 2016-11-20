import java.util.ArrayList;

class Mesh {

    private final int verticesCount;
    private final Vec3[] vertices;
    private final ArrayList<Pair<Triple<Integer>, Triple<Vec2>>> faces;
    private final Texture texture;

    Mesh (int verticesCount, Texture texture) {
        this.verticesCount = verticesCount;
        this.vertices = new Vec3[verticesCount];
        this.faces = new ArrayList<>();
        this.texture = texture;
    }

    Mesh () {
        this.verticesCount = 8;
        this.vertices = new Vec3[verticesCount];
        this.faces = new ArrayList<>();
        this.texture = new Texture("resources\\SELMECZI-GABRIELLA.jpg");
        addVertex(new Vec3(5, -5, -50), 0);
        addVertex(new Vec3(-5, -5, -50), 1);
        addVertex(new Vec3(-5, 5, -50), 2);
        addVertex(new Vec3(5, 5, -50), 3);
        addVertex(new Vec3(5, -5, -60), 4);
        addVertex(new Vec3(-5, -5, -60), 5);
        addVertex(new Vec3(-5, 5, -60), 6);
        addVertex(new Vec3(5, 5, -60), 7);
        addFace(new Pair<>(new Triple<>(1, 0, 2),
                new Triple<>(new Vec2(1, 0),  new Vec2(0, 0),  new Vec2(1, 1))), 0);
        addFace(new Pair<>(new Triple<>(0, 3, 2),
                new Triple<>(new Vec2(0, 0),  new Vec2(0, 1),  new Vec2(1, 1))), 1);
        addFace(new Pair<>(new Triple<>(4, 5, 6),
                new Triple<>(new Vec2(1, 0),  new Vec2(0, 0),  new Vec2(0, 1))), 2);
        addFace(new Pair<>(new Triple<>(4, 6, 7),
                new Triple<>(new Vec2(1, 0),  new Vec2(0, 1),  new Vec2(1, 1))), 3);
        addFace(new Pair<>(new Triple<>(3, 7, 2),
                new Triple<>(new Vec2(0, 0),  new Vec2(0, 1),  new Vec2(1, 0))), 4);
        addFace(new Pair<>(new Triple<>(2, 7, 6),
                new Triple<>(new Vec2(1, 0),  new Vec2(0, 1),  new Vec2(1, 1))), 5);
        addFace(new Pair<>(new Triple<>(0, 1, 4),
                new Triple<>(new Vec2(0, 1),  new Vec2(1, 1),  new Vec2(0, 0))), 6);
        addFace(new Pair<>(new Triple<>(1, 5, 4),
                new Triple<>(new Vec2(1, 1),  new Vec2(1, 0),  new Vec2(0, 0))), 7);
        addFace(new Pair<>(new Triple<>(4, 7, 0),
                new Triple<>(new Vec2(0, 0),  new Vec2(0, 1),  new Vec2(1, 0))), 8);
        addFace(new Pair<>(new Triple<>(0, 7, 3),
                new Triple<>(new Vec2(1, 0),  new Vec2(0, 1),  new Vec2(1, 1))), 9);
        addFace(new Pair<>(new Triple<>(1, 2, 6),
                new Triple<>(new Vec2(0, 0),  new Vec2(0, 1),  new Vec2(1, 1))), 10);
        addFace(new Pair<>(new Triple<>(5, 1, 6),
                new Triple<>(new Vec2(1, 0),  new Vec2(0, 0),  new Vec2(1, 1))), 11);
    }

    Texture getTexture () {
        return texture;
    }

    void addVertex (Vec3 vertex, int index) {
        vertices[index] = vertex;
    }

    void addFace (Pair<Triple<Integer>, Triple<Vec2>> face, int index) {
        faces.add(index, face);
    }

    void move(Matrix3X3 matrix) {
        for(int i = 0; i < verticesCount; i++){
            vertices[i] = vertices[i].multiplyByMatrix(matrix);
        }
    }

    Vec3[] getVertices () {
        return vertices;
    }

    Vec3 getVertex (int index) {
        return vertices[index];
    }

    int getVerticesCount () {
        return verticesCount;
    }

    ArrayList<Pair<Triple<Integer>, Triple<Vec2>>> getFaces () {
        return faces;
    }
}