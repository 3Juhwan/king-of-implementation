package B23289;

import B23289.object.Heater;
import B23289.object.House;
import B23289.utils.Tuple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static B23289.simulater.HouseMaker.setHouse;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseTest {

    @Test
    void cell_wall_test() {
        // given
        int[][] houseInput = HouseInput.getHouseInput();
        List<Tuple> investigators = new ArrayList<>();
        List<Heater> heaters = new ArrayList<>();

        // when
        House house = setHouse(houseInput, investigators, heaters);

        for (int i = 0; i < house.getRowLength(); i++) {
            for (int j = 0; j < house.getColumnLength(); j++) {
                assertThat(house.getCell(i, j)).isNotNull();
            }
        }
    }


    /**
     * Test data
     */
    private static class HouseInput {
        static final int[][] houseInput = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 5, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0}
        };

        static final int[][] emptyHouseInput = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        static final int[][] getHouseInput() {
            return houseInput;
        }

        static final int[][] getEmptyHouseInput() {
            return emptyHouseInput;
        }
    }
}
