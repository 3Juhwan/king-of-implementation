package B23289.simulater;

import B23289.object.Cell;
import B23289.object.House;
import B23289.utils.Direction;

public class Thermostat {

    public static void controlTemperature(House house) {
        int[][] controlAmount = new int[house.getRowLength()][house.getColumnLength()];
        for (int i = 0; i < house.getRowLength() - 1; i++) {
            for (int j = 0; j < house.getColumnLength(); j++) {
                Cell cell1 = house.getCell(i, j), cell2 = house.getCell(i + 1, j);
                if (cell1.getWallExist(Direction.DOWN)) {
                    continue;
                }
                compareCell(controlAmount, cell1, cell2);
            }
        }

        for (int i = 0; i < house.getRowLength(); i++) {
            for (int j = 0; j < house.getColumnLength() - 1; j++) {
                Cell cell1 = house.getCell(i, j), cell2 = house.getCell(i, j + 1);
                if (cell1.getWallExist(Direction.RIGHT)) {
                    continue;
                }
                compareCell(controlAmount, cell1, cell2);
            }
        }

        for (int i = 0; i < house.getRowLength(); i++) {
            for (int j = 0; j < house.getColumnLength(); j++) {
                house.getCell(i, j).changeTemperature(controlAmount[i][j]);
            }
        }
    }

    private static void compareCell(int[][] controlAmount, Cell highCell, Cell lowCell) {
        if (lowCell.getTemperature() > highCell.getTemperature()) {
            Cell tmpCell = lowCell;
            lowCell = highCell;
            highCell = tmpCell;
        }

        int diff = (highCell.getTemperature() - lowCell.getTemperature()) / 4;
        controlAmount[highCell.getPosX()][highCell.getPosY()] -= diff;
        controlAmount[lowCell.getPosX()][lowCell.getPosY()] += diff;
    }

    public static void decreaseOuterCell(House house) {
        for (int i = 0; i < house.getRowLength(); i++) {
            for (int j = 0; j < house.getColumnLength(); j++) {
                if (i == 0 || j == 0 || i == house.getRowLength() - 1 || j == house.getColumnLength() - 1) {
                    Cell cell = house.getCell(i, j);
                    if (cell.getTemperature() > 0) {
                        cell.changeTemperature(-1);
                    }
                }
            }
        }
    }

}
