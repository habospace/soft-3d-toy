public interface Vec<V>{

    public V add(V other);

    public double dotProduct(V other);

    public V normalize();
}
