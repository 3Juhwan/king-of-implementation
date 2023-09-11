package B23289.logic.object;

import B23289.logic.utils.Direction;

public class Wind {

    private final Direction direction;
    private int id;
    private int x, y;
    private int increaseAmount;

    public Wind(int sequence, int x, int y, Direction direction, int increaseAmount) {
        this.id = sequence;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.increaseAmount = increaseAmount;
    }

    public Wind(Wind wind, int x, int y) {
        this(wind.id, x, y, wind.direction, wind.increaseAmount - 1);
    }

    public void increaseTemperature(House house) {
        Cell cell = house.getCellByCoordinate(x, y);
        cell.increaseTemperature(increaseAmount, id);
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getIncreaseAmount() {
        return this.increaseAmount;
    }
}
