package B23289.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Input {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int n, m, k, r;
    private int[][] houseInput;
    private int[][] wallInput;

    public int getK() {
        return k;
    }

    public int[][] readHouseInput() throws IOException {
        houseInput = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                houseInput[i][j] = Integer.parseInt(row[j]);
            }
        }
        return houseInput;
    }

    public int[][] readWallInput() throws IOException {
        wallInput = new int[r][3];
        for (int i = 0; i < r; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                wallInput[i][j] = Integer.parseInt(row[j]);
            }
        }
        return wallInput;
    }

    public void readVariableR() throws IOException {
        r = Integer.parseInt(br.readLine());
    }

    public void readVariableNMK() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }
}
