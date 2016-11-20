
class Pair <T1, T2> {

    private final T1 e1;
    private final T2 e2;

    Pair (T1 e1, T2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    T1 getE1 () {
        return e1;
    }

    T2 getE2 () {
        return e2;
    }
}
