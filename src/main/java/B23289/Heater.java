package main.java.B23289;

public class Heater {

    private final int posX, posY;
    private final Direction direction;

    Heater(int x, int y, Direction direction) {
        this.posX = x;
        this.posY = y;
        this.direction = direction;
    }

    /**
     * Generate wind in front of heater.
     *
     * @return New wind
     */
    public Wind generateWind() {
        int x = posX + direction.getDx(), y = posY + direction.getDy();


        return null;
    }

}
