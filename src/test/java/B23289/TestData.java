package B23289;

public interface TestData {

    public int[][] getInputData(int number);
    public int[][] getOutputData(int number);




    static class HouseInput {
        private static final int[][] emptyHouseInput = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        private static final int[][] houseInput = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 5, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0}
        };
        private static final int[][] wallInput = new int[][]{
                {4, 4, 1},
                {5, 4, 0},
                {5, 6, 0}
        };

        static int[][] getHouseInput() {
            return houseInput;
        }

        static int[][] getWallInput() {
            return wallInput;
        }

        static int[][] getEmptyHouseInput() {
            return emptyHouseInput;
        }
    }

    static class HouseOutput {

        static final int[][] houseOutput = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 2, 2, 2, 7, 2, 2, 2},
                {0, 0, 3, 4, 7, 4, 3, 0},
                {0, 0, 3, 4, 7, 4, 3, 0},
                {0, 2, 2, 2, 7, 2, 2, 2},
                {1, 1, 1, 1, 1, 1, 1, 1},
        };

        static int[][] getHouseOutput() {
            return houseOutput;
        }
    }
}
