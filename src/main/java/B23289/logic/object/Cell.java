package B23289.logic.object;

import B23289.logic.utils.Direction;

public class Cell {

    private final int posX, posY;
    private boolean[] wall = new boolean[5];
    private int temperature = 0;
    private int lastWind = 0;

    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    Cell(int x, int y, int temperature) {
        this(x, y);
        this.temperature = temperature;
    }

    public boolean wallExist(Direction direction) {
        return wall[direction.getNum()];
    }

    public void increaseTemperature(int diff, int windId) {
        this.temperature += diff;
        this.lastWind = windId;
    }


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setWallDirection(Direction direction) {
        int directionNum = direction.getNum();
        wall[directionNum] = true;
    }

    public int getLastWind() {
        return this.lastWind;
    }

    public int getTemperature() {
        return this.temperature;
    }
}
