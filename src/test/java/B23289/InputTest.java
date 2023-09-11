package B23289;

import B23289.logic.*;
import B23289.logic.object.Cell;
import B23289.logic.object.Heater;
import B23289.logic.object.House;
import B23289.logic.utils.Direction;
import B23289.logic.utils.Tuple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


public class InputTest {

    @Test
    void setHouseTest() {
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

        // given
        List<List<Integer>> lines = Arrays.stream(houseInput).map(line ->
                Arrays.asList(Arrays.stream(line).boxed().toArray(Integer[]::new))
        ).toList();
        List<List<Integer>> wallLines = Arrays.stream(wallInput).map(line ->
                Arrays.asList(Arrays.stream(line).boxed().toArray(Integer[]::new))
        ).toList();

        List<Tuple> investigators = new ArrayList<>();
        List<Heater> heaters = new ArrayList<>();

        // when
        House house = Solve.setHouse(lines, investigators, heaters);
        Solve.setWall(wallLines, house);

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
                Cell cell = house.getCellByCoordinate(i, j);
                assertThat(cell).extracting(Cell::getPosX, Cell::getPosY)
                        .containsExactly(i, j);
            }
        }

        assertThat(house.getCellByCoordinate(3, 3).wallExist(Direction.DOWN)).isTrue();
    }
}
