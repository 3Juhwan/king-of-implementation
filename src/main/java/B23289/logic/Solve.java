package B23289.logic;

import B23289.logic.object.Heater;
import B23289.logic.object.House;
import B23289.logic.utils.Input;
import B23289.logic.utils.Tuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static B23289.logic.simulater.HouseMaker.setHouse;
import static B23289.logic.simulater.HouseMaker.setWall;
import static B23289.logic.simulater.Inspector.inspect;
import static B23289.logic.simulater.Thermostat.controlTemperature;
import static B23289.logic.simulater.Thermostat.decreaseOuterCell;
import static B23289.logic.simulater.WindShifter.propagateWind;

public class Solve {

    private static List<Heater> heaters = new ArrayList<>();
    private static List<Tuple> targets = new ArrayList<>();
    private static Input input = new Input();


    public static void run() throws IOException {
        int chocolate = 1;

        input.readVariableNMK();
        int[][] inputHouse = input.readHouseInput();
        House house = setHouse(inputHouse, targets, heaters);

        input.readVariableR();
        int[][] inputMatrix = input.readWallInput();
        setWall(inputMatrix, house);

        while (simulate(house, heaters) && chocolate < 101) {
            chocolate++;
        }

        System.out.println(chocolate);
    }

    public static boolean simulate(House house, List<Heater> heaters) {
        propagateWind(house, heaters);
        controlTemperature(house);
        decreaseOuterCell(house);
        return inspect(targets, house, input.getK());
    }
}