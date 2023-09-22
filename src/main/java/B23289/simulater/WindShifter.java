package B23289.simulater;

import B23289.object.Cell;
import B23289.object.Heater;
import B23289.object.House;
import B23289.object.Wind;
import B23289.utils.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WindShifter {
    public static void propagateWind(House house, List<Heater> heaters) {
        for (Heater heater : heaters) {
            Queue<Wind> windQueue = new LinkedList<>();
            Wind wind = heater.generateWind();
            windQueue.add(wind);
            wind.changeTemperature(house);

            while (!windQueue.isEmpty()) {
                Wind currendWind = windQueue.poll();
                if (currendWind.getIncreaseAmount() == 1) {
                    continue;
                }

                Wind forwardWind = getForwardWind(currendWind, house);
                if (forwardWind != null && windValidation(forwardWind, house)) {
                    forwardWind.changeTemperature(house);
                    windQueue.add(forwardWind);
                }

                Wind diagonalLeftWind = getDiagonalLeftWind(currendWind, house);
                if (diagonalLeftWind != null && windValidation(diagonalLeftWind, house)) {
                    diagonalLeftWind.changeTemperature(house);
                    windQueue.add(diagonalLeftWind);
                }

                Wind diagonalRightWind = getDiagonalRightWind(currendWind, house);
                if (diagonalRightWind != null && windValidation(diagonalRightWind, house)) {
                    diagonalRightWind.changeTemperature(house);
                    windQueue.add(diagonalRightWind);
                }
            }
        }
    }

    public static Wind getForwardWind(Wind wind, House house) {
        Direction direction = wind.getDirection();
        Cell cell = house.getCell(wind.getX(), wind.getY());

        if (!cell.getWallExist(direction)) {
            int nx = cell.getPosX() + direction.getDx(), ny = cell.getPosY() + direction.getDy();
            return new Wind(wind, nx, ny);
        }
        return null;
    }

    public static Wind getDiagonalLeftWind(Wind wind, House house) {
        Direction leftDirection = wind.getDirection().relativeLeftDirection();
        Cell cell = house.getCell(wind.getX(), wind.getY());

        if (!cell.getWallExist(leftDirection)) {
            int nx = cell.getPosX() + leftDirection.getDx(), ny = cell.getPosY() + leftDirection.getDy();
            if (outOfBounds(nx, ny, house.getRowLength(), house.getColumnLength())) {
                return null;
            }
            Cell leftCell = house.getCell(nx, ny);
            Direction rightDirection = leftDirection.relativeRightDirection();
            if (!leftCell.getWallExist(rightDirection)) {
                nx = leftCell.getPosX() + rightDirection.getDx();
                ny = leftCell.getPosY() + rightDirection.getDy();
                return new Wind(wind, nx, ny);
            }
        }
        return null;
    }

    public static Wind getDiagonalRightWind(Wind wind, House house) {
        Direction rightDirection = wind.getDirection().relativeRightDirection();
        Cell cell = house.getCell(wind.getX(), wind.getY());

        if (!cell.getWallExist(rightDirection)) {
            int nx = cell.getPosX() + rightDirection.getDx(), ny = cell.getPosY() + rightDirection.getDy();
            if (outOfBounds(nx, ny, house.getRowLength(), house.getColumnLength())) {
                return null;
            }
            Cell rightCell = house.getCell(nx, ny);
            rightDirection = rightDirection.relativeLeftDirection();
            if (!rightCell.getWallExist(rightDirection)) {
                nx = rightCell.getPosX() + rightDirection.getDx();
                ny = rightCell.getPosY() + rightDirection.getDy();
                return new Wind(wind, nx, ny);
            }
        }
        return null;
    }

    private static boolean windValidation(Wind wind, House house) {
        if (outOfBounds(wind.getX(), wind.getY(), house.getRowLength(), house.getColumnLength())) {
            return false;
        } else {
            return !visited(wind, house);
        }
    }

    private static boolean outOfBounds(int x, int y, int boundX, int boundY) {
        return x < 0 || x >= boundX || y < 0 || y >= boundY;
    }

    private static boolean visited(Wind wind, House house) {
        Cell cell = house.getCell(wind.getX(), wind.getY());
        return cell.getLastWind() >= wind.getId();
    }

}
