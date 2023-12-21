package model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapObjectTest {
    MapObjects mapObjects;
    @BeforeEach
    void setUp() {
        mapObjects = new MapObjects();
    }

    @Test
    void testAddPlant() {
        Plant plant = new Plant(new Vector2d(1, 1), 1, false, 1);
        mapObjects.addPlant(plant);
        assert(mapObjects.getPlant().equals(plant));
    }

    @Test
    void testRemovePlant() {
        assertNull(mapObjects.getPlant());
        Plant plant = new Plant(new Vector2d(1, 1), 1, false, 1);
        mapObjects.addPlant(plant);
        assertEquals(mapObjects.getPlant(), plant);
    }

    @Test
    void testAddAnimal() {
        ArrayList<Integer> genes= new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal);
        assertTrue(mapObjects.getAnimals().contains(animal));

        Animal animal2 = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal2);
        assertTrue(mapObjects.getAnimals().contains(animal2));
    }

    @Test
    void testRemoveAnimal() {
        ArrayList<Integer> genes= new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal);
        assertTrue(mapObjects.getAnimals().contains(animal));
        mapObjects.removeAnimal(animal);
        assertFalse(mapObjects.getAnimals().contains(animal));
    }

    @Test
    void testGetObjects() {
        ArrayList<Integer> genes= new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal);
        assertTrue(mapObjects.getObjects().contains(animal));
        Plant plant = new Plant(new Vector2d(1, 1), 1, false, 1);
        mapObjects.addPlant(plant);
        assertTrue(mapObjects.getObjects().contains(plant));
    }

    @Test
    void testIsOccupied() {
        assertFalse(mapObjects.isOccupied());
        ArrayList<Integer> genes= new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal);
        assertTrue(mapObjects.isOccupied());
    }

    @Test
    void testGetStrongestAnimal() {
        ArrayList<Integer> genes= new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal);
        assertEquals(mapObjects.getStrongestAnimal(), animal);
        Animal animal2 = new Animal(new Vector2d(1, 1), 2, genotype, 1,2);
        mapObjects.addAnimal(animal2);
        assertEquals(mapObjects.getStrongestAnimal(), animal2);
    }

    @Test
    void testGetStrongestAnimals() {
        ArrayList<Integer> genes= new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal);
        assertNull(mapObjects.getStrongestAnimals());
        Animal animal2 = new Animal(new Vector2d(1, 1), 2, genotype, 1,2);
        mapObjects.addAnimal(animal2);
        assertEquals(mapObjects.getStrongestAnimals(), new ArrayList<Animal>(List.of(animal2, animal)));
        Animal animal3 = new Animal(new Vector2d(1, 1), 2, genotype, 1,2);

        mapObjects.addAnimal(animal3);

        assertEquals(mapObjects.getStrongestAnimals(),new ArrayList<Animal>(List.of(animal2, animal3)));
    }

    @Test
    void testRemoveDeadAnimals() {
        ArrayList<Integer> genes= new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);
        Animal animal = new Animal(new Vector2d(1, 1), 0, genotype, 1,2);
        mapObjects.addAnimal(animal);
        assertEquals(mapObjects.getAnimals().size(), 1);
        mapObjects.removeDeadAnimals();
        assertEquals(mapObjects.getAnimals().size(), 0);
    }

    @Test
    void testGetPlant() {
        assertNull(mapObjects.getPlant());
        Plant plant = new Plant(new Vector2d(1, 1), 1, false, 1);
        mapObjects.addPlant(plant);
        assertEquals(mapObjects.getPlant(), plant);
    }

    @Test
    void testGrowAnimals(){
        ArrayList<Integer> genes= new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        Animal animal = new Animal(new Vector2d(1, 1), 1, genotype, 1,2);
        mapObjects.addAnimal(animal);
        mapObjects.growAnimals();
        assertEquals(animal.getAge(), 1);
    }


}
