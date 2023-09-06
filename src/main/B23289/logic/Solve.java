package main.B23289.logic;

import main.B23289.logic.utils.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solve {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static List<Heater> heaters = new ArrayList<>();
    private static List<Tuple> investigators = new ArrayList<>();


    public static void run() throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = nextIntToken(st), m = nextIntToken(st), k = nextIntToken(st);

        // Set house
        House house = new House(n, m);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = nextIntToken(st);
                if (num == 5) {
                    investigators.add(Tuple.of(i, j));
                } else if (num > 0) {
                    heaters.add(new Heater(i, j, Direction.valueOfLabel(num)));
                }
                house.setSingleCell(new Cell(i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = nextIntToken(st);

        // Set walls
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int x = nextIntToken(st), y = nextIntToken(st), t = nextIntToken(st);
            Direction wallDirection = t == 0 ? Direction.UP : Direction.RIGHT;
            house.getCellByCoordinate(x, y).setWallDirection(wallDirection);
        }



    }

    private static int nextIntToken(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }


}
