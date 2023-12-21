package model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionsTest {

    Directions direction;
    @BeforeEach
    public void setUp() {
        Directions direction= Directions.NORTH;
    }

    @Test
    public void testGetRandomDirection() {
        //unpredictable
        for (int i = 0; i < 10; i++){
            Directions direction = Directions.getRandomDirection();
            assertEquals(direction.getClass(), Directions.class);
            System.out.println(direction);
        }
    }

    @Test
    public void testGetDirectionName() {
        assertEquals(Directions.getDirectionName(0), Directions.NORTH);
        assertEquals(Directions.getDirectionName(1), Directions.NORTH_EAST);
        assertEquals(Directions.getDirectionName(2), Directions.EAST);
        assertEquals(Directions.getDirectionName(3), Directions.SOUTH_EAST);
        assertEquals(Directions.getDirectionName(4), Directions.SOUTH);
        assertEquals(Directions.getDirectionName(5), Directions.SOUTH_WEST);
        assertEquals(Directions.getDirectionName(6), Directions.WEST);
        assertEquals(Directions.getDirectionName(7), Directions.NORTH_WEST);
    }

    @Test
    public void testGetDirectionIndex() {
        assertEquals(Directions.getDirectionIndex(Directions.NORTH), 0);
        assertEquals(Directions.getDirectionIndex(Directions.NORTH_EAST), 1);
        assertEquals(Directions.getDirectionIndex(Directions.EAST), 2);
        assertEquals(Directions.getDirectionIndex(Directions.SOUTH_EAST), 3);
        assertEquals(Directions.getDirectionIndex(Directions.SOUTH), 4);
        assertEquals(Directions.getDirectionIndex(Directions.SOUTH_WEST), 5);
        assertEquals(Directions.getDirectionIndex(Directions.WEST), 6);
        assertEquals(Directions.getDirectionIndex(Directions.NORTH_WEST), 7);
    }

    @Test
    public void testToUnitVector() {
        assertEquals(Directions.toUnitVector(0), new Vector2d(0, 1));
        assertEquals(Directions.toUnitVector(1), new Vector2d(1, 1));
        assertEquals(Directions.toUnitVector(2), new Vector2d(1, 0));
        assertEquals(Directions.toUnitVector(3), new Vector2d(1, -1));
        assertEquals(Directions.toUnitVector(4), new Vector2d(0, -1));
        assertEquals(Directions.toUnitVector(5), new Vector2d(-1, -1));
        assertEquals(Directions.toUnitVector(6), new Vector2d(-1, 0));
        assertEquals(Directions.toUnitVector(7), new Vector2d(-1, 1));
    }
}
