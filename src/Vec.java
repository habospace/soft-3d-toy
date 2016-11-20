interface Vec<V>{

    V addVector(V other);

    double dotProduct(V other);

    V normalize();

    double length();
}