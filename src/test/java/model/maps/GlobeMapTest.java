package model.maps;

import model.simulation.SimulationConfigurator;
import model.utils.Animal;
import model.utils.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GlobeMapTest {

    GlobeMap map;

    @BeforeEach
    void setUp() {

        map = new GlobeMap(new SimulationConfigurator());
    }

    @Test
    void testCanMoveTo() {
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(1, 1)));
        assertTrue(map.canMoveTo(new Vector2d(9, 9)));
        assertTrue(map.canMoveTo(new Vector2d(10, 10)));
        assertFalse(map.canMoveTo(new Vector2d(-1, -1)));
        assertFalse(map.canMoveTo(new Vector2d(0, 11)));
        assertTrue(map.canMoveTo(new Vector2d(11, 1)));
        assertFalse(map.canMoveTo(new Vector2d(11, 12)));
    }

    @Test
    void testIsOccupied() {
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertFalse(map.isOccupied(new Vector2d(9, 9)));
        assertFalse(map.isOccupied(new Vector2d(10, 10)));
        assertFalse(map.isOccupied(new Vector2d(-1, -1)));
    }

    @Test
    void testNewPositionOutOfLeftBound() {
        assertFalse(map.newPositionOutOfLeftBound(new Vector2d(0, 0)));
        assertFalse(map.newPositionOutOfLeftBound(new Vector2d(1, 1)));
        assertFalse(map.newPositionOutOfLeftBound(new Vector2d(9, 9)));
        assertFalse(map.newPositionOutOfLeftBound(new Vector2d(10, 10)));
        assertFalse(map.newPositionOutOfLeftBound(new Vector2d(-1, -1)));
        assertTrue(map.newPositionOutOfLeftBound(new Vector2d(-1, 0)));
        assertFalse(map.newPositionOutOfLeftBound(new Vector2d(0, 0)));
    }

    @Test
    void testNewPositionOutOfRightBound() {
        assertFalse(map.newPositionOutOfRightBound(new Vector2d(0, 0)));
        assertFalse(map.newPositionOutOfRightBound(new Vector2d(1, 1)));
        assertFalse(map.newPositionOutOfRightBound(new Vector2d(9, 9)));
        assertFalse(map.newPositionOutOfRightBound(new Vector2d(10, 10)));
        assertTrue(map.newPositionOutOfRightBound(new Vector2d(11, 9)));
        assertFalse(map.newPositionOutOfRightBound(new Vector2d(0, 11)));
        assertFalse(map.newPositionOutOfRightBound(new Vector2d(-1, -1)));
    }

    void testObjectsAt() {
        assertNull(map.objectsAt(new Vector2d(0, 0)));
        assertNull(map.objectsAt(new Vector2d(1, 1)));
        assertNull(map.objectsAt(new Vector2d(9, 9)));
        assertNull(map.objectsAt(new Vector2d(10, 10)));
        assertNull(map.objectsAt(new Vector2d(-1, -1)));
    }

    @Test
    void testMove() {
        for (int i = 0; i < 10; i++) {
            System.out.println(map.toString());
            map.move();
        }
    }

    @Test
    void testGenerateMap() {
        assertEquals(10, map.getAnimals().size());
    }

    @Test
    void testDailyUpdate() {
        for (int i = 0; i < 10; i++) {
            map.dailyUpdate();
            System.out.println(map.toString());
        }
    }

    @Test
    void animalAgeTest() {
        for (int i = 0; i < 10; i++) {
            map.dailyUpdate();
            for (Animal animal : map.getAnimals()) {
                System.out.println(animal.getAge());
            }
        }
    }
}
