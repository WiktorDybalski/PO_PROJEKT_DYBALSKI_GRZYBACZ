package model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    Tile tile;
    @BeforeEach
    void setUp() {
        tile = new Tile(new Vector2d(0,0));
    }
    @Test
    void testGetters() {
        assertEquals(tile.getPosition(),new Vector2d(0,0));
        assertTrue(tile.getAnimals().isEmpty());
        assertNull(tile.getPlant());
    }

    @Test
    void testSetters() {
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        Animal animal = new Animal(new Vector2d(0,0), 10, genotype , 10, 10);
        tile.addAnimal(animal);
        assertEquals(tile.getAnimals().size(),1);
        assertEquals(tile.getAnimals().get(0),animal);
        Plant plant = new Plant(new Vector2d(0,0), 10, false, 10);
        tile.setPlant(plant);
        assertEquals(tile.getPlant(),plant);
    }

    @Test
    void testRemoveAnimal() {
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        Animal animal = new Animal(new Vector2d(0,0), 10, genotype , 10, 10);
        tile.addAnimal(animal);
        assertEquals(tile.getAnimals().size(),1);
        assertEquals(tile.getAnimals().get(0),animal);
        tile.removeAnimal(animal);
        assertTrue(tile.getAnimals().isEmpty());
    }

    @Test
    void testEatPlant() {
        Plant plant = new Plant(new Vector2d(0,0), 10, false, 10);
        tile.setPlant(plant);
        assertEquals(tile.getPlant(),plant);
        tile.eatPlant();
        assertEquals(tile.getPlant(),plant);

        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        Animal animal = new Animal(new Vector2d(0,0), 10, genotype , 10, 10);
        tile.addAnimal(animal);
        tile.eatPlant();
        assertNull(tile.getPlant());
    }

    @Test
    void testIsOccupied() {
        assertFalse(tile.isOccupied());
        Plant plant = new Plant(new Vector2d(0,0), 10, false, 10);
        tile.setPlant(plant);
        assertTrue(tile.isOccupied());
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        Animal animal = new Animal(new Vector2d(0,0), 10, genotype , 10, 10);
        tile.addAnimal(animal);
        assertTrue(tile.isOccupied());
    }

    @Test
    void testGetStrongestAnimal() {
        assertNull(tile.getStrongestAnimal());
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        for (int i= 0; i < 10; i++) {
            Animal animal = new Animal(new Vector2d(0,0), i*i%10, genotype , 10, 10);
            tile.addAnimal(animal);
        }
        assertEquals(tile.getStrongestAnimal().getEnergy(),9);
    }

    @Test
    void testGetStrongestAnimals() {
        assertNull(tile.getStrongestAnimals());
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        for (int i= 0; i < 10; i++) {
            Animal animal = new Animal(new Vector2d(0,0), i , genotype , 10, 10);
            tile.addAnimal(animal);
            Animal animal2 = new Animal(new Vector2d(0,0), i-1 , genotype , 10, 10);
            tile.addAnimal(animal2);
        }
        assertEquals(tile.getStrongestAnimals().size(),2);
        assertEquals(tile.getStrongestAnimals().get(0).getEnergy(),9);
        assertEquals(tile.getStrongestAnimals().get(1).getEnergy(),8);
    }

    @Test
    void testRemoveDeadAnimals() {
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(0,0), 10, genotype , 10, 10);
        tile.addAnimal(animal);
        assertEquals(tile.getAnimals().size(),1);
        tile.removeDeadAnimals();
        assertEquals(tile.getAnimals().size(),1);
        animal.die();
        tile.removeDeadAnimals();
        assertEquals(tile.getAnimals().size(),0);
    }

    @Test
    void testReproduceAnimals() {
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype1 = new Genotype(genes);
        Genotype genotype2 = new Genotype(genes);
        Animal animal1 = new Animal(new Vector2d(0,0), 10, genotype1 , 10, 10);
        Animal animal2 = new Animal(new Vector2d(0,0), 10, genotype2 , 10, 10);
        tile.addAnimal(animal1);
        tile.addAnimal(animal2);
        assertEquals(tile.getAnimals().size(),2);
        tile.reproduceAnimals(10, 10);
        assertEquals(tile.getAnimals().size(),3);
        tile.reproduceAnimals(10, 10);
        assertEquals(tile.getAnimals().size(),3);
        tile.reproduceAnimals(10, 10);
        Animal animal3 = new Animal(new Vector2d(0,0), 10, genotype2 , 10, 10);
        tile.addAnimal(animal3);
        Animal animal4 = new Animal(new Vector2d(0,0), 10, genotype2 , 10, 10);
        tile.addAnimal(animal4);
        tile.reproduceAnimals(10, 10);
        assertEquals(tile.getAnimals().size(),6);
    }

    @Test
    void testGrow() {
        Plant plant = new Plant(new Vector2d(0,0), 10, false, 10);
        tile.setPlant(plant);
        assertEquals(tile.getPlant().getEnergy(),10);
        tile.grow();
        assertEquals(tile.getPlant().getEnergy(),11);
    }
}
