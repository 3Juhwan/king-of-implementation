package B23289.logic.simulater;

import B23289.logic.object.House;
import B23289.logic.utils.Tuple;

import java.util.List;

public class Inspector {

    public static boolean inspect(List<Tuple> targets, House house, int k) {
        for (Tuple target : targets) {
            int x = (int) target.getFirst(), y = (int) target.getSecond();
            if (house.getCell(x, y).getTemperature() < k) {
                return true;
            }
        }
        return false;
    }
}
