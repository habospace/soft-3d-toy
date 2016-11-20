
class Triple <T> {

    private final T e1;
    private final T e2;
    private final T e3;

    Triple(T e1, T e2, T e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    T getE1() {
        return e1;
    }

    T getE2() {
        return e2;
    }

    T getE3() {
        return e3;
    }
}
