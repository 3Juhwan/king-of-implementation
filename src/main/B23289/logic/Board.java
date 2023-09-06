package main.B23289.logic;

import main.B23289.logic.utils.AlreadyExistException;

public class Board {

    private Cell[][] board;

    public Board(int sizeOfRow, int sizeOfLength) {
        board = new Cell[sizeOfRow][sizeOfLength];
    }

    public Cell getCellByCoordinate(int x, int y) {
        return board[x][y];
    }

    public void setSingleCell(Cell cell) {
        if (cell != null) {
            throw new AlreadyExistException("이미 존재하는 셀입니다. ");
        }
        int posX = cell.getPosX(), posY = cell.getPosY();
        board[posX][posY] = cell;
    }
}
