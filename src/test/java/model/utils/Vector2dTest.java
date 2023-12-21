package model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2dTest {

    Vector2d v1;
    Vector2d v2;

    @BeforeEach
    void setUp() {
        v1 = new Vector2d(1, 2);
        v2 = new Vector2d(3, 4);
    }

    @Test
    void add() {
        assertEquals( v1.add(v2), new Vector2d(4, 6));
        assertEquals(v2.add(v1), new Vector2d(4, 6));
    }

    @Test
    void subtract() {
        assertEquals(v1.subtract(v2), new Vector2d(-2, -2));
        assertEquals(v2.subtract(v1), new Vector2d(2, 2));
    }

    @Test
    void testEquals() {
        assertEquals(v1, new Vector2d(1, 2));
        assertEquals(v2, new Vector2d(3, 4));
    }

    @Test
    void testToString() {
        assertEquals(v1.toString(), "(1,2)");
        assertEquals(v2.toString(), "(3,4)");
    }

    @Test
    void testPrecedes() {
        assertEquals(v1.precedes(v2), true);
        assertEquals(v2.precedes(v1), false);
    }

    @Test
    void testFollows() {
        assertEquals(v1.follows(v2), false);
        assertEquals(v2.follows(v1), true);
    }

    @Test
    void testUpperRight() {
        assertEquals(v1.upperRight(v2), new Vector2d(3, 4));
        assertEquals(v2.upperRight(v1), new Vector2d(3, 4));
    }

    @Test
    void testLowerLeft() {
        assertEquals(v1.lowerLeft(v2), new Vector2d(1, 2));
        assertEquals(v2.lowerLeft(v1), new Vector2d(1, 2));
    }

    @Test
    void testHashCode() {
        assertEquals(v1.hashCode(), new Vector2d(1, 2).hashCode());
        assertEquals(v2.hashCode(), new Vector2d(3, 4).hashCode());
    }

}
