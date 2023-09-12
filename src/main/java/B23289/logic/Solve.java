package B23289.logic;

import B23289.logic.object.Cell;
import B23289.logic.object.Heater;
import B23289.logic.object.House;
import B23289.logic.utils.Direction;
import B23289.logic.utils.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static B23289.logic.simulater.Inspector.inspect;
import static B23289.logic.simulater.Thermostat.controlTemperature;
import static B23289.logic.simulater.Thermostat.decreaseOuterCell;
import static B23289.logic.simulater.WindShifter.propagateWind;

public class Solve {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static List<Heater> heaters = new ArrayList<>();
    private static List<Tuple> targets = new ArrayList<>();
    private static List<List<Integer>> twoDimensionIntegerInput = new ArrayList<>();
    private static int houseRowLength, houseColumnLength, k, r;


    public static void run() throws IOException {
        int chocolate = 1;

        readVariableNMK();
        int[][] input = readMatrix(houseRowLength, houseColumnLength);
        House house = setHouse(input, targets, heaters);

        readVariableR();
        input = readMatrix(r, 3);
        setWall(input, house);

        while (simulate(house, heaters) && chocolate < 101) {
            chocolate++;
        }

        System.out.println(chocolate);
    }

    public static boolean simulate(House house, List<Heater> heaters) {
        propagateWind(house, heaters);
        controlTemperature(house);
        decreaseOuterCell(house);
        return inspect(targets, house, k);
    }

    private static int[][] readMatrix(int n, int m) throws IOException {
        int[][] input = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                input[i][j] = Integer.parseInt(row[j]);
            }
        }
        return input;
    }

    private static void readVariableR() throws IOException {
        r = Integer.parseInt(br.readLine());
    }

    private static void readVariableNMK() throws IOException {
        st = new StringTokenizer(br.readLine());
        houseRowLength = Integer.parseInt(st.nextToken());
        houseColumnLength = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

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