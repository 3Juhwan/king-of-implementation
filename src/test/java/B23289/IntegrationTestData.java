package B23289;

public class IntegrationTestData implements TestData {
    int[][] inputData1 = {
            {7, 8, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 5, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 5, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 0},
            {3},
            {4, 4, 1},
            {5, 4, 0},
            {5, 6, 0}
    };

    @Override
    public int[][] getInputData(int number) {
        return new int[0][];
    }

    @Override
    public int[][] getOutputData(int number) {
        return new int[0][];
    }


}
