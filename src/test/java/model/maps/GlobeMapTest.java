package model.maps;

import model.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobeMapTest {

    GlobeMap map;
    @BeforeEach
    void setUp() {
        map = new GlobeMap(10, 10, 20, 10);
        map.generateMap(generatePlantList( 20, 10, 10), generateAnimalList(10, 10, 10));
    }

    public static ArrayList<Animal> generateAnimalList(int width, int height, int numberOfAnimals) {
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        ArrayList<Animal> animalList = new ArrayList<>();
        for (int i = 0; i < numberOfAnimals; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            Animal animal = new Animal(new Vector2d(x, y), i + 1, genotype, 0, 10);
            animalList.add(animal);
        }

        return animalList;
    }

    public static ArrayList<Plant> generatePlantList( int numberOfPlants, int width, int height) {
        ArrayList<Plant> plantList = new ArrayList<>();
        for (int i = 0; i < numberOfPlants; i++) {
            int x = (int) (width-Math.random() * width);
            int y = (int) (height-Math.random() * height);
            Plant plant = new Plant(new Vector2d(x, y), 1, false, 0);
            plantList.add(plant);
        }
        return plantList;
    }

    @Test
    void testGenerateMap() {
        assertEquals(10, map.getAnimals().size());
        assertEquals(20, map.getPlants().size());
    }

    @Test
    void testToString() {
        System.out.println(map.toString());
    }

    @Test
    void testToString2() {
        for(Tile tile : map.getMapTiles().values()){
            if(tile.isOccupied()){
                if(!tile.getAnimals().isEmpty()){
                    tile.getStrongestAnimal().eat(tile.getPlant());
                    tile.removePlant();
                }
            }
        }
        map.removeEatenPlants();
        assertEquals(10, map.getAnimals().size());
        assertEquals(10, map.getPlants().size());
        System.out.println(map.toString());

    }

}
