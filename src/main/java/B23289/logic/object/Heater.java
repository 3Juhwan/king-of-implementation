package B23289.logic.object;

import B23289.logic.utils.Direction;

public class Heater {

    private static int id = 1;

    private final int posX, posY;
    private final Direction direction;

    public Heater(int x, int y, Direction direction) {
        this.posX = x;
        this.posY = y;
        this.direction = direction;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Wind generateWind() {
        int nx = posX + direction.getDx(), ny = posY + direction.getDy();
        return new Wind(id++, nx, ny, direction, 5);
    }

}
