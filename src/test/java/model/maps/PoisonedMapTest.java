package model.maps;

import model.utils.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoisonedMapTest {
    PoisonedMap map;
    @BeforeEach
    void setUp() {
        map = new PoisonedMap(10, 10, 0.3, 20, 10);
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
        System.out.println(map.toString());
        map.move();
        System.out.println(map.toString());
    }
    @Test
    void testGenerateMap() {
        assertEquals(10, map.getAnimals().size());
        assertEquals(20, map.getPlants().size());
    }

    @Test
    void testEat() {
        map.eat();
        assertEquals(10, map.getAnimals().size());
        assertEquals(10, map.getPlants().size());
        System.out.println(map.toString());
        map.eat();
        assertEquals(10, map.getAnimals().size());
        assertEquals(10, map.getPlants().size());
        System.out.println(map.toString());
    }

}
