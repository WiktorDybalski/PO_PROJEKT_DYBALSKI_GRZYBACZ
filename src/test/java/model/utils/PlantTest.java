package model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO: add tests
public class PlantTest {

    private Plant plant;

    @BeforeEach
    void setUp() {
        plant = new Plant(new Vector2d(2, 2), 5, false, 0);
    }
    @Test
    void getEnergyTest() {
        assertEquals( plant.getEnergy(),5);
    }

    @Test
    void getDayOfGrowthTest() {
        assertEquals(plant.getDayOfGrowth(),0);
    }

    @Test
    void getPositionTest() {
        assertEquals(plant.getPosition(),new Vector2d(2, 2));
    }

    @Test
    void getIsPoisonedTest() {
        assertFalse(plant.getIsPoisoned());
    }

    @Test
    void getIsEatenTest() {
        assertFalse(plant.getIsEaten());
    }

    @Test
    void setIsEatenTest() {
        plant.setIsEaten();
        assertTrue(plant.getIsEaten());
    }

    @Test
    void growTest() {
        plant.grow();
        assertEquals(plant.getEnergy(),6);
    }

    @Test
    void equalsTest() {
        Plant plant2 = new Plant(new Vector2d(2, 2), 5, false, 0);
        Plant plant3 = new Plant(new Vector2d(2, 2), 5, false, 1);
        Plant plant4 = new Plant(new Vector2d(2, 2), 5, true, 0);
        Plant plant5 = new Plant(new Vector2d(2, 2), 6, false, 0);
        Plant plant6 = new Plant(new Vector2d(2, 3), 5, false, 0);
        Plant plant7 = new Plant(new Vector2d(3, 2), 5, false, 0);
        Plant plant8 = new Plant(new Vector2d(3, 3), 5, false, 0);
        assertEquals(plant, plant2);
        assertNotEquals(plant, plant3);
        assertNotEquals(plant, plant4);
        assertNotEquals(plant, plant5);
        assertNotEquals(plant, plant6);
        assertNotEquals(plant, plant7);
        assertNotEquals(plant, plant8);
    }

}
