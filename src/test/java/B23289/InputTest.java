package B23289;

import B23289.object.Cell;
import B23289.object.Heater;
import B23289.object.House;
import B23289.utils.Direction;
import B23289.utils.Tuple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static B23289.simulater.HouseMaker.setHouse;
import static B23289.simulater.HouseMaker.setWall;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


public class InputTest {

    @Test
    void setHouseTest() {
        // given
        int[][] houseInput = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 5, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0}
        };

        int[][] wallInput = new int[][]{
                {4, 4, 1},
                {5, 4, 0},
                {5, 6, 0}
        };


        List<Tuple> investigators = new ArrayList<>();
        List<Heater> heaters = new ArrayList<>();

        // when
        House house = setHouse(houseInput, investigators, heaters);
        setWall(wallInput, house);

        // then
        assertThat(investigators).extracting(Tuple::getFirst, Tuple::getSecond)
                .contains(tuple(4, 5),
                        tuple(3, 2),
                        tuple(3, 3));

        assertThat(heaters).extracting(Heater::getPosX, Heater::getPosY, Heater::getDirection)
                .contains(tuple(1, 4, Direction.DOWN),
                        tuple(6, 4, Direction.UP));

        for (int i = 0; i < houseInput.length; i++) {
            for (int j = 0; j < houseInput[0].length; j++) {
                Cell cell = house.getCell(i, j);
                assertThat(cell).extracting(Cell::getPosX, Cell::getPosY)
                        .containsExactly(i, j);
            }
        }

        assertThat(house.getCell(3, 3).getWallExist(Direction.DOWN)).isTrue();
    }
}
