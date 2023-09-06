package main.B23289.logic;

public class Cell {

    private final int posX, posY;
    private boolean[] wall = new boolean[5];
    private int temperature;

    Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    Cell(int x, int y, int temperature) {
        this(x, y);
        this.temperature = temperature;
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
}
