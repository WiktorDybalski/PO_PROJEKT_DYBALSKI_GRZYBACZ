package RandomGenerators;

import model.maps.WorldMap;
import model.utils.Plant;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlantsInPoisonedGenerator {
    /**
     * The maximum width of the map.
     */
    private final int maxWidth;

    /**
     * The maximum height of the map.
     */
    private final int maxHeight;
    /**
     * The energy value each plant starts with.
     */
    private int plantEnergy;

    /**
     * The day on which the plant starts growing.
     */
    private static final int DAY_OF_GROWTH = 2;

    /**
     * List to store the generated plants.
     */
    private List<Plant> plants;

    /**
     * Random object for generating random numbers.
     */
    private Random random;

    /**
     * The map where the plants will be placed.
     */
    private final WorldMap map;

    private final Vector2d lowerDownCornerSquare;
    private final Vector2d rightUpperCornerSquare;

    /**
     * Constructor for RandomPlantsGenerator.
     * Generates an initial number of plants on the given map.
     *
     * @param initialAmount The number of plants to generate initially.
     * @param plantEnergy
     * @param map           The map where the plants are to be placed.
     */
    public RandomPlantsInPoisonedGenerator(int initialAmount, int plantEnergy, WorldMap map) {
        this.maxWidth = map.getWidth();
        this.maxHeight = map.getHeight();
        plants = new ArrayList<>();
        random = new Random(1112);
        this.map = map;
        this.plantEnergy = plantEnergy;
        lowerDownCornerSquare = generatePoisonedSquare();
        rightUpperCornerSquare = new Vector2d(lowerDownCornerSquare.getX() + (int) (0.2 * maxWidth), lowerDownCornerSquare.getY() + (int) (0.2 * maxHeight));
        generatePlants(initialAmount);
    }

    public Vector2d getLowerDownCornerSquare() {
        return lowerDownCornerSquare;
    }

    public Vector2d getRightUpperCornerSquare() {
        return rightUpperCornerSquare;
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
     * Generates a random position within a specified area of the map to define a poisoned square.
     * This square is a region where plants are likely to be poisoned.
     * The area of this square is 20% of the map's width and height.
     *
     * @return A Vector2d object representing the lower-left corner of the poisoned square.
     */

    private Vector2d generatePoisonedSquare() {
        Random random = new Random(1115);
        int x = random.nextInt((int) (0.8 * maxWidth));
        int y = random.nextInt((int) (0.8 * maxWidth));
        return new Vector2d(x, y);
    }

    /**
     * Determines if a plant should be poisoned based on a random probability.
     * There is a 1 in 5 chance that this method will return true, indicating the plant is poisoned.
     *
     * @return true if the plant is poisoned, false otherwise.
     */

    private boolean isPoisoned() {
        Random random = new Random(1115);
        int randomInt = random.nextInt(10);
        return randomInt % 5 == 0;
    }

    /**
     * Generates a plant with a random energy level. The plant is determined to be poisoned or not
     * based on its position relative to the poisoned square and a random chance.
     *
     * @param position               The position of the plant on the map.
     * @param lowerDownCornerSquare  The lower-left corner of the poisoned square.
     * @param rightUpperCornerSquare The upper-right corner of the poisoned square.
     * @return A Plant object with specified properties, including its poisoned status.
     */

    private Plant generateRandomPlant(Vector2d position, Vector2d lowerDownCornerSquare, Vector2d rightUpperCornerSquare) {
        if (lowerDownCornerSquare.precedes(position) && rightUpperCornerSquare.follows(position) && isPoisoned())
            return new Plant(position, plantEnergy, true, DAY_OF_GROWTH);
        return new Plant(position, plantEnergy, true, DAY_OF_GROWTH);
    }

    /**
     * Generates a specified number of plants and adds them to the plants list.
     * Utilizes RandomPositionsGenerator to get random positions for each plant.
     *
     * @param amount The number of plants to generate.
     */
    private void generatePlants(int amount) {
        RandomPositionsGenerator positionsGenerator = new RandomPositionsGenerator(map, amount);
        List<Vector2d> positions = positionsGenerator.getPlantInPoisonedResult();
        for (int i = 0; i < amount; i++) {
            plants.add(generateRandomPlant(positions.get(i), lowerDownCornerSquare, rightUpperCornerSquare));
        }
    }
}
