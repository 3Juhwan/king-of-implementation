package B23289.logic.simulater;

import B23289.logic.object.Cell;
import B23289.logic.object.Heater;
import B23289.logic.object.House;
import B23289.logic.object.Wind;
import B23289.logic.utils.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WindShifter {


    /**
     * Generate winds from heaters, and propagate them
     *
     * @param house
     * @param heaters
     */
    public static void propagateWind(House house, List<Heater> heaters) {
        for (Heater heater : heaters) {
            Queue<Wind> windQueue = new LinkedList<>();
            Wind wind = heater.generateWind(house);
            windQueue.add(wind);
            wind.increaseTemperature(house);

            while (!windQueue.isEmpty()) {
                Wind currendWind = windQueue.poll();
                if (currendWind.getIncreaseAmount() == 1) {
                    continue;
                }

                Wind forwardWind = getForwardWind(currendWind, house);
                if (forwardWind != null && windValidation(forwardWind, house)) {
                    forwardWind.increaseTemperature(house);
                    windQueue.add(forwardWind);
                }

                Wind diagonalLeftWind = getDiagonalLeftWind(currendWind, house);
                if (diagonalLeftWind != null && windValidation(diagonalLeftWind, house)) {
                    diagonalLeftWind.increaseTemperature(house);
                    windQueue.add(diagonalLeftWind);
                }

                Wind diagonalRightWind = getDiagonalRightWind(currendWind, house);
                if (diagonalRightWind != null && windValidation(diagonalRightWind, house)) {
                    diagonalRightWind.increaseTemperature(house);
                    windQueue.add(diagonalRightWind);
                }
            }
        }
    }

    public static Wind getForwardWind(Wind wind, House house) {
        Direction direction = wind.getDirection();
        Cell cell = house.getCellByCoordinate(wind.getX(), wind.getY());

        if (!cell.wallExist(direction)) {
            int nx = cell.getPosX() + direction.getDx(), ny = cell.getPosY() + direction.getDy();
            return new Wind(wind, nx, ny);
        }
        return null;
    }

    public static Wind getDiagonalLeftWind(Wind wind, House house) {
        Direction leftDirection = wind.getDirection().relativeLeftDirection();
        Cell cell = house.getCellByCoordinate(wind.getX(), wind.getY());

        if (!cell.wallExist(leftDirection)) {
            int nx = cell.getPosX() + leftDirection.getDx(), ny = cell.getPosY() + leftDirection.getDy();
            if (outOfBound(nx, ny, house.getRowLength(), house.getColumnLength())) {
                return null;
            }
            Cell leftCell = house.getCellByCoordinate(nx, ny);
            Direction rightDirection = leftDirection.relativeRightDirection();
            if (!leftCell.wallExist(rightDirection)) {
                nx = leftCell.getPosX() + rightDirection.getDx();
                ny = leftCell.getPosY() + rightDirection.getDy();
                return new Wind(wind, nx, ny);
            }
        }
        return null;
    }

    public static Wind getDiagonalRightWind(Wind wind, House house) {
        Direction rightDirection = wind.getDirection().relativeRightDirection();
        Cell cell = house.getCellByCoordinate(wind.getX(), wind.getY());

        if (!cell.wallExist(rightDirection)) {
            int nx = cell.getPosX() + rightDirection.getDx(), ny = cell.getPosY() + rightDirection.getDy();
            if (outOfBound(nx, ny, house.getRowLength(), house.getColumnLength())) {
                return null;
            }
            Cell rightCell = house.getCellByCoordinate(nx, ny);
            rightDirection = rightDirection.relativeLeftDirection();
            if (!rightCell.wallExist(rightDirection)) {
                nx = rightCell.getPosX() + rightDirection.getDx();
                ny = rightCell.getPosY() + rightDirection.getDy();
                return new Wind(wind, nx, ny);
            }
        }
        return null;
    }


    /**
     * Validate wind
     * condition(2): not-out-of-bound, not-visited
     *
     * @param wind
     * @param house
     * @return validation result
     */
    private static boolean windValidation(Wind wind, House house) {
        if (outOfBound(wind.getX(), wind.getY(), house.getRowLength(), house.getColumnLength())) {
            return false;
        } else if (visited(wind, house)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean outOfBound(int x, int y, int boundX, int boundY) {
        return x < 0 || x >= boundX || y < 0 || y >= boundY;
    }

    private static boolean visited(Wind wind, House house) {
        int x = wind.getX(), y = wind.getY();
        Cell cell = house.getCellByCoordinate(x, y);
        return cell.getLastWind() >= wind.getId();
    }

}
