package B23289.utils;

public class Tuple<F, S> {

    private final F first;
    private final S second;

    public Tuple(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <F, S> Tuple<F, S> of(final F first, final S second) {
        return new Tuple<>(first, second);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

}
