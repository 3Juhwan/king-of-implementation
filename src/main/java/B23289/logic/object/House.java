package B23289.logic.object;

public class House {

    private Cell[][] house;

    public House(Cell[][] house) {
        this.house = house;
    }

    public Cell getCell(int x, int y) {
        return house[x][y];
    }

    public int getRowLength() {
        return house.length;
    }

    public int getColumnLength() {
        return house[0].length;
    }
}
