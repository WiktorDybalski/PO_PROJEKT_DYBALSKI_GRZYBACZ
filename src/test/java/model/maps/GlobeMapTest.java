package model.maps;

import RandomGenerators.RandomAnimalsGenerator;
import RandomGenerators.RandomPlantsGenerator;
import model.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GlobeMapTest {

    GlobeMap map;
    @BeforeEach
    void setUp() {
        map = new GlobeMap(10, 10, 20, 10);
    }

    @Test
    void testCanMoveTo() {
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(1, 1)));
        assertTrue(map.canMoveTo(new Vector2d(9, 9)));
        assertTrue(map.canMoveTo(new Vector2d(10, 10)));
        assertFalse(map.canMoveTo(new Vector2d(-1, -1)));
    }

    @Test
    void testIsOccupied() {
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertFalse(map.isOccupied(new Vector2d(9, 9)));
        assertFalse(map.isOccupied(new Vector2d(10, 10)));
        assertFalse(map.isOccupied(new Vector2d(-1, -1)));
    }

    void testObjectsAt() {
        assertNull(map.objectsAt(new Vector2d(0, 0)));
        assertNull(map.objectsAt(new Vector2d(1, 1)));
        assertNull(map.objectsAt(new Vector2d(9, 9)));
        assertNull(map.objectsAt(new Vector2d(10, 10)));
        assertNull(map.objectsAt(new Vector2d(-1, -1)));
    }

    @Test //TODO: check if it's ok after FIX in AbstractWorldMap
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
