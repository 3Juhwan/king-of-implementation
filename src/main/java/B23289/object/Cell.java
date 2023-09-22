package B23289.object;

import B23289.utils.Direction;

public class Cell {

    private final int posX, posY;
    private boolean[] wallExist = new boolean[5];
    private int temperature = 0;
    private int lastWind = -1;

    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getLastWind() {
        return this.lastWind;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public boolean getWallExist(Direction direction) {
        return wallExist[direction.getNum()];
    }

    public void setWallExist(Direction direction) {
        wallExist[direction.getNum()] = true;
    }

    public void changeTemperature(int diff, int windId) {
        this.temperature += diff;
        this.lastWind = windId;
    }

    public void changeTemperature(int diff) {
        this.temperature += diff;
    }
}
