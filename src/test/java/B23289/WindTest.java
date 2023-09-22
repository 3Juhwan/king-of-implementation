package B23289;

import B23289.TestData.HouseInput;
import B23289.TestData.HouseOutput;
import B23289.object.Cell;
import B23289.object.Heater;
import B23289.object.House;
import B23289.object.Wind;
import B23289.utils.Direction;
import B23289.utils.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static B23289.simulater.HouseMaker.setHouse;
import static B23289.simulater.HouseMaker.setWall;
import static B23289.simulater.WindShifter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WindTest {

    @Test
    void wind_propagation_test() {
        // given
        int[][] houseInput = HouseInput.getHouseInput();
        int[][] wallInput = HouseInput.getWallInput();
        List<Tuple> investigators = new ArrayList<>();
        List<Heater> heaters = new ArrayList<>();
        House house = setHouse(houseInput, investigators, heaters);
        setWall(wallInput, house);

        // when
        propagateWind(house, heaters);

        // then
        int[][] houseOutput = HouseOutput.getHouseOutput();
        for (int i = 0; i < house.getRowLength(); i++) {
            for (int j = 0; j < house.getColumnLength(); j++) {
                Cell cell = house.getCell(i, j);
                assertThat(cell.getTemperature()).isEqualTo(houseOutput[i][j]);
            }
        }
    }

    @DisplayName("바람 전파 방향 검증")
    @Nested
    class WindPropagateTest {
        int[][] houseInput = HouseInput.getHouseInput();
        House house = setHouse(houseInput, new ArrayList<>(), new ArrayList<>());

        Wind downWind = new Wind(1, 3, 3, Direction.DOWN, 1);
        Wind UpWind = new Wind(2, 3, 3, Direction.UP, 1);
        Wind RightWind = new Wind(3, 3, 3, Direction.RIGHT, 1);
        Wind LeftWind = new Wind(4, 3, 3, Direction.LEFT, 1);


        @DisplayName("Forward wind propagation test")
        @Test
        void forwardWindPropagate() {
            // when
            Wind forwardDownWind = getForwardWind(downWind, house);
            Wind forwardUpWind = getForwardWind(UpWind, house);
            Wind forwardRightWind = getForwardWind(RightWind, house);
            Wind forwardLeftWind = getForwardWind(LeftWind, house);

            // then
            assertThat(forwardDownWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(4, 3, Direction.DOWN);

            assertThat(forwardUpWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(2, 3, Direction.UP);

            assertThat(forwardLeftWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(3, 2, Direction.LEFT);

            assertThat(forwardRightWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(3, 4, Direction.RIGHT);
        }

        @DisplayName("Diagonal left wind propagation test")
        @Test
        void diagonalLeftWindPropagate() {
            // when
            Wind diagonalLeftDownWind = getDiagonalLeftWind(downWind, house);
            Wind diagonalLeftUpWind = getDiagonalLeftWind(UpWind, house);
            Wind diagonalLeftRightWind = getDiagonalLeftWind(RightWind, house);
            Wind diagonalLeftLeftWind = getDiagonalLeftWind(LeftWind, house);

            // then
            assertThat(diagonalLeftDownWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(4, 4, Direction.DOWN);

            assertThat(diagonalLeftUpWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(2, 2, Direction.UP);

            assertThat(diagonalLeftLeftWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(4, 2, Direction.LEFT);

            assertThat(diagonalLeftRightWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(2, 4, Direction.RIGHT);
        }

        @DisplayName("Diagonal right wind propagation test")
        @Test
        void diagonalRightWindPropagate() {
            // when
            Wind diagonalRightDownWind = getDiagonalRightWind(downWind, house);
            Wind diagonalRightUpWind = getDiagonalRightWind(UpWind, house);
            Wind diagonalRightRightWind = getDiagonalRightWind(RightWind, house);
            Wind diagonalRightLeftWind = getDiagonalRightWind(LeftWind, house);

            // then
            assertThat(diagonalRightDownWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(4, 2, Direction.DOWN);

            assertThat(diagonalRightUpWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(2, 4, Direction.UP);

            assertThat(diagonalRightLeftWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(2, 2, Direction.LEFT);

            assertThat(diagonalRightRightWind).isNotNull()
                    .extracting("x", "y", "direction")
                    .containsExactly(4, 4, Direction.RIGHT);
        }
    }
}