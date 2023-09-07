package B23289.logic;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {

    UP(3, -1, 0),
    RIGHT(1, 0, 1),
    DOWN(4, 1, 0),
    LEFT(2, 0, -1);

    private static final Map<Integer, Direction> label = Stream.of(values()).collect(Collectors.toMap(Direction::getNum, e -> e));
    private final int num, dx, dy;

    Direction(int num, int dx, int dy) {
        this.num = num;
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction valueOfLabel(int num) {
        return label.get(num);
    }

    public int getNum() {
        return this.num;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

}
