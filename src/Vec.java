public interface Vec<V>{

    public V addVector(V other);

    public double dotProduct(V other);

    public V normalize();

    public double length();
}