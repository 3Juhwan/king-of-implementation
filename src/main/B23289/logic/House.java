package main.B23289.logic;

import main.B23289.logic.utils.AlreadyExistException;

public class House {

    private Cell[][] house;

    public House(int sizeOfRow, int sizeOfLength) {
        house = new Cell[sizeOfRow][sizeOfLength];
    }

    public Cell getCellByCoordinate(int x, int y) {
        return house[x][y];
    }

    public void setSingleCell(Cell cell) {
        if (cell != null) {
            throw new AlreadyExistException("이미 존재하는 셀입니다. ");
        }
        int posX = cell.getPosX(), posY = cell.getPosY();
        house[posX][posY] = cell;
    }


}
