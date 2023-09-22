package B23289.utils;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {

    RIGHT(1, 0, 1),
    LEFT(2, 0, -1),
    UP(3, -1, 0),
    DOWN(4, 1, 0);

    /*
    RIGHT (3, 4) -> self
    UP(3) -> RIGHT, DOWN(4) -> RIGHT

    LEFT (3, 4) -> self
    UP(3) -> LEFT, DOWN(4) -> LEFT

    UP (1, 2) -> self
    LEFT(2) -> UP, RIGHT(1) -> UP

    DOWN (1, 2) -> self
    LEFT(2) -> DOWN, RIGHT(1) -> DOWN
     */

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

    public Direction relativeLeftDirection() {
        switch (this.num) {
            case 1:
                return Direction.UP;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.LEFT;
            case 4:
                return Direction.RIGHT;
        }
        return null;
    }

    public Direction relativeRightDirection() {
        switch (this.num) {
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.RIGHT;
            case 4:
                return Direction.LEFT;
        }
        return null;
    }

}
