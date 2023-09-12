package B23289.logic.object;

import B23289.logic.utils.Direction;

public class Wind {

    private final Direction direction;
    private final int id;
    private final int x, y;
    private final int increaseAmount;

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

    public int getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getIncreaseAmount() {
        return this.increaseAmount;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void changeTemperature(House house) {
        house.getCell(x, y).changeTemperature(increaseAmount, id);
    }
}
