package model.utils.RandomGenerators;

import model.maps.WorldMap;
import model.utils.Plant;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

public class RandomPlantsGenerator {
    /**
     * The map where the plants will be placed.
     */
    private final WorldMap map;
    /**
     * The energy value each plant starts with.
     */
    private final int plantEnergy;
    /**
     * List to store the generated plants.
     */
    private List<Plant> plants;

    /**
     * Constructor for RandomPlantsGenerator.
     * Generates an initial number of plants on the given map.
     *
     * @param plantEnergy
     * @param map         The map where the plants are to be placed.
     */
    public RandomPlantsGenerator(int plantEnergy, WorldMap map) {
        plants = new ArrayList<>();
        this.plantEnergy = plantEnergy;
        this.map = map;
    }

    /**
     * Returns the list of generated plants.
     *
     * @return A list of Plant objects.
     */
    public List<Plant> getPlants() {
        return plants;
    }

    /**
     * Generates a random plant at a specified position.
     * Currently, poison attribute is set to false by default.
     *
     * @param position The position where the plant will be placed.
     * @return A new Plant object.
     */
    private Plant generateRandomPlant(Vector2d position) {
        return new Plant(position, plantEnergy, false, map.getCurrentDay());
    }

    /**
     * Generates a specified number of plants and adds them to the plants list.
     * Utilizes RandomPositionsGenerator to get random positions for each plant.
     *
     * @param amount The number of plants to generate.
     */
    public void generatePlants(int amount, List<Vector2d> freePositions) {
        RandomPositionsGenerator positionsGenerator = new RandomPositionsGenerator(map, amount);
        List<Vector2d> positions = positionsGenerator.generatePlants(freePositions);
        for (int i = 0; i < min(amount, freePositions.size()); i++) {
            plants.add(generateRandomPlant(positions.get(i)));

        }
    }
}

