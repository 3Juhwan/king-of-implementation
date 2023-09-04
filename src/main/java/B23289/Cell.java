package main.java.B23289;

public class Cell {

    private final int posX, posY;
    private final int wallDirection;
    private int temperature;

    Cell(int x, int y, int wallDirection) {
        this.posX = x;
        this.posY = y;
        this.wallDirection = wallDirection;
    }


}
