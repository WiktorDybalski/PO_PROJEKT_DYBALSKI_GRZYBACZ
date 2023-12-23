package model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    Animal animal1;
    Animal animal2;

    Genotype genotype1;
    Genotype genotype2;

    @BeforeEach
    public void setUp() {
        Vector2d position = new Vector2d(1,1);
        int energy = 10;
        ArrayList<Integer> genes1;
        ArrayList<Integer> genes2;
        genes1 = new ArrayList<>();
        genes2 = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes1.add(i);
            genes2.add(7-i);
        }

        genotype1 = new Genotype(genes1);
        genotype2 = new Genotype(genes2);

        int birthDay = 0;
        int minimal_reproduction_energy = 5;
        animal1 = new Animal(position, energy, genotype1, birthDay, minimal_reproduction_energy);
        animal2 = new Animal(position, energy, genotype2, birthDay, minimal_reproduction_energy);
    }

    @Test
    public void testGetEnergy() {
        assertEquals(animal1.getEnergy(), 10);
    }

    @Test
    public void testGetChildrenCount() {
        assertEquals(animal1.getChildrenCount(), 0);
    }

    @Test
    public void testGetGenotype() {
        assertEquals(animal1.getGenotype(), genotype1);
    }

    @Test
    public void testGetPosition() {
        assertEquals(animal1.getPosition(), new Vector2d(1,1));
    }
    @Test
    public void testGetEatenPlantCount() {
        assertEquals(animal1.getEatenPlantCount(), 0);
    }

    @Test
    public void testGetBirthDay() {
        assertEquals(animal1.getBirthDay(), 0);
    }

    @Test
    public void testGetIsDead() {
        assertFalse(animal1.getIsDead());
    }

    @Test
    public void testGetChildren() {assertEquals(new ArrayList<>(), animal1.getChildren());
    }

    @Test
    public void testGetDescendantCount() {
        assertEquals(animal1.getDescendantCount(), 0);
    }

    @Test
    public void testGetAge() {
        assertEquals(animal1.getAge(), 0);
    }

    @Test
    public void testSetActualActiveGenIndex() {
        animal1.setActualActiveGenIndex(1);
        assertEquals(animal1.getActualActiveGenIndex(), 1);
    }

    @Test
    public void testMove() {
        animal1.move(Directions.NORTH, new Vector2d(1,2));
        assertEquals(animal1.getPosition(), new Vector2d(1,2));

        animal1.move(Directions.EAST, new Vector2d(2,2));
        assertEquals(animal1.getPosition(), new Vector2d(2,2));

        animal1.move(Directions.SOUTH_WEST, new Vector2d(1,1));
        assertEquals(animal1.getPosition(), new Vector2d(1,1));

        animal1.move(Directions.NORTH_WEST, new Vector2d(0,2));
        assertEquals(animal1.getPosition(), new Vector2d(0,2));

        animal1.move(Directions.SOUTH_EAST, new Vector2d(1,1));
        assertEquals(animal1.getPosition(), new Vector2d(1,1));

        animal1.move(Directions.NORTH_EAST, new Vector2d(2,2));
        assertEquals(animal1.getPosition(), new Vector2d(2,2));
    }

    @Test
    public void testCanReproduce() {
        assertTrue(animal1.canReproduce());
        for (int i = 0; i < 6; i++) {
            animal1.decreaseEnergy();
        }
        assertFalse(animal1.canReproduce());
    }

    @Test
    public void reproduce() {
        Animal animal3=animal1.reproduce(animal2, 0,10);
        assertEquals(animal1.getChildrenCount(), 1);
        assertEquals(animal2.getChildrenCount(), 1);
        assertEquals(animal1.getEnergy(), 5);
        assertEquals(animal2.getEnergy(), 5);
        assertEquals(animal3.getEnergy(), 10);
        //we can't test if the genotype is correct due to uncontrollable nature of random
        System.out.println(animal1.getGenotype());
        System.out.println(animal2.getGenotype());
        System.out.println(animal3.getGenotype());
    }

    @Test
    public void testEat() {
        assertEquals(animal1.getEatenPlantCount(), 0);
        assertEquals(animal1.getEnergy(), 10);
        Plant plant= new Plant (new Vector2d(2,2), 10,false,0);
        animal1.eat(plant);
        assertEquals(animal1.getEatenPlantCount(), 1);
        assertEquals(animal1.getEnergy(), 20);
    }

    @Test
    public void testDie() {
        assertFalse(animal1.getIsDead());
        animal1.die();
        assertTrue(animal1.getIsDead());
    }
}
