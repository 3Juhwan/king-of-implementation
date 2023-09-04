package main.java.B23289;

public class House {

    private Cell[][] board;

    House(int[][] board, int n, int m) {
        this.board = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.board[i][j] = new Cell(i, j, -1);
            }
        }
    }


}
