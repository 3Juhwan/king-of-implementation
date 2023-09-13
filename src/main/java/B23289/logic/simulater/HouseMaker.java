package B23289.logic.simulater;

import B23289.logic.object.Cell;
import B23289.logic.object.Heater;
import B23289.logic.object.House;
import B23289.logic.utils.Direction;
import B23289.logic.utils.Tuple;

import java.util.List;

public class HouseMaker {
    public static House setHouse(int[][] input, List<Tuple> investigators, List<Heater> heaters) {
        int n = input.length, m = input[0].length;

        Cell[][] house = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = input[i][j];

                if (num == 5) {
                    investigators.add(Tuple.of(i, j));
                } else if (num > 0) {
                    heaters.add(new Heater(i, j, Direction.valueOfLabel(num)));
                }

                house[i][j] = new Cell(i, j);
            }
        }
        return new House(house);
    }

    public static void setWall(int[][] input, House house) {
        for (int[] line : input) {
            int x = line[0] - 1, y = line[1] - 1, t = line[2];
            if (t == 0) {
                house.getCell(x, y).setWallExist(Direction.UP);
                house.getCell(x - 1, y).setWallExist(Direction.DOWN);
            } else {
                house.getCell(x, y).setWallExist(Direction.RIGHT);
                house.getCell(x, y + 1).setWallExist(Direction.LEFT);
            }
        }
    }
}
