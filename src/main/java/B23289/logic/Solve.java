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
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static B23289.logic.simulater.WindShifter.propagateWind;

public class Solve {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static List<Heater> heaters = new ArrayList<>();
    private static List<Tuple> investigators = new ArrayList<>();
    private static List<List<Integer>> twoDimensionIntegerInput = new ArrayList<>();
    private static int houseRowLength, houseColumnLength, k, r;


    public static void run() throws IOException {
        readVariableNMK();
        List<List<Integer>> input = readMatrix(houseRowLength);
        House house = setHouse(input, investigators, heaters);

        readVariableR();
        input = readMatrix(r);
        setWall(input, house);

        simulate(house, heaters);
    }

    public static void simulate(House house, List<Heater> heaters) {
        propagateWind(house, heaters);
    }


    private static List<List<Integer>> readMatrix(int x) throws IOException {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            input.add(Arrays.stream(br.readLine().split(" "))
                    .map(s -> Integer.parseInt(s)).toList());
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

    public static House setHouse(List<List<Integer>> lines, List<Tuple> investigators, List<Heater> heaters) {
        int n = lines.size(), m = lines.get(0).size();

        House house = new House(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = lines.get(i).get(j);
                if (num == 5) {
                    investigators.add(Tuple.of(i, j));
                } else if (num > 0) {
                    heaters.add(new Heater(i, j, Direction.valueOfLabel(num)));
                }
                house.setSingleCell(new Cell(i, j));
            }
        }
        return house;
    }

    public static void setWall(List<List<Integer>> lines, House house) {
        for (int i = 0; i < lines.size(); i++) {
            int x = lines.get(i).get(0) - 1, y = lines.get(i).get(1) - 1, t = lines.get(i).get(2);
            if (t == 0) {
                house.getCellByCoordinate(x, y).setWallDirection(Direction.UP);
                house.getCellByCoordinate(x - 1, y).setWallDirection(Direction.DOWN);
            } else {
                house.getCellByCoordinate(x, y).setWallDirection(Direction.RIGHT);
                house.getCellByCoordinate(x, y + 1).setWallDirection(Direction.LEFT);
            }
        }
    }


}
