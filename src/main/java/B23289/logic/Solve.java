package B23289.logic;

import B23289.logic.utils.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solve {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static List<Heater> heaters = new ArrayList<>();
    private static List<Tuple> investigators = new ArrayList<>();
    private static List<List<Integer>> twoDimensionIntegerInput = new ArrayList<>();
    private static int n, m, k, r;

    public static void run() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            input.add(Arrays.stream(br.readLine().split(" "))
                    .map(s -> Integer.parseInt(s)).toList());
        }

        House house = setHouse(input, investigators, heaters);

        r = Integer.parseInt(br.readLine());
        input = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            input.add(Arrays.stream(br.readLine().split(" "))
                    .map(s -> Integer.parseInt(s)).toList());
        }

        setWall(input, house);
    }

    /**
     * Setting cells, inspect investigators(5), find heaters(1~4)
     * @param lines
     * @param investigators
     * @param heaters
     * @return
     */
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
            int x = lines.get(i).get(0), y = lines.get(i).get(1), t = lines.get(i).get(2);
            Direction wallDirection = t == 0 ? Direction.UP : Direction.RIGHT;
            house.getCellByCoordinate(x, y).setWallDirection(wallDirection);
        }
    }


}
