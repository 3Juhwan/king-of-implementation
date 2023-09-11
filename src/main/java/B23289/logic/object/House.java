package B23289.logic.object;

import B23289.logic.utils.AlreadyExistException;

public class House {

    private Cell[][] house;

    public House(int sizeOfRow, int sizeOfLength) {
        house = new Cell[sizeOfRow][sizeOfLength];
    }

    public Cell getCellByCoordinate(int x, int y) {
        return house[x][y];
    }

    public void setSingleCell(Cell cell) {
        int posX = cell.getPosX(), posY = cell.getPosY();
        if (house[posX][posY] != null) {
            throw new AlreadyExistException("이미 존재하는 셀입니다. ");
        }
        house[posX][posY] = cell;
    }

    public int getRowLength() {
        return house.length;
    }

    public int getColumnLength() {
        return house[0].length;
    }
}
