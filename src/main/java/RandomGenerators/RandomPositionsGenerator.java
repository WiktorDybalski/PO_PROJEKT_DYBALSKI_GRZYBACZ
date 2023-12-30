package RandomGenerators;

import model.maps.WorldMap;
import model.simulation.SimulationConfigurator;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
public class RandomPositionsGenerator {
    /**
     * The maximum width of the map.
     */
    private final int maxWidth;

    /**
     * The maximum height of the map.
     */
    private final int maxHeight;

    /**
     * The number of objects to be placed on the map.
     */
    private final int objectCount;

    /**
     * A list of all possible positions on the map.
     */
    private final List<Vector2d> allPositions;

    /**
     * A list of the generated positions for anima;s.
     */
    private List<Vector2d> animalResult = new ArrayList<>();

    /**
     * A list of the generated positions for plants in GlobeMap.
     */
    private List<Vector2d> plantResult = new ArrayList<>();

    /**
     * A list of the generated positions for plants in PoisonedMap.
     */
    private List<Vector2d> plantInPoisonedResult = new ArrayList<>();

    /**
     * Constructor for RandomPositionsGenerator.
     * Initializes the object with the map's dimensions and the desired number of objects.
     *
     * @param map         The map on which the objects are to be placed.
     * @param objectCount The number of objects to generate positions for.
     */
    public RandomPositionsGenerator(WorldMap map, int objectCount) {
        this.maxWidth = map.getWidth();
        this.maxHeight = map.getHeight();
        this.objectCount = objectCount;
        this.allPositions = generateAllPositions();
    }

    /**
     * Returns the list of generated positions.
     *
     * @return A list of Vector2d animals representing the generated positions.
     */
    public List<Vector2d> getAnimalResult() {
        return animalResult;
    }

    /**
     * Returns the list of generated positions.
     *
     * @return A list of Vector2d plants representing the generated positions.
     */

    public List<Vector2d> getPlantResult() {
        return plantResult;
    }

    /**
     * Returns the list of generated positions.
     *
     * @return A list of Vector2d plants in poisoned Map representing the generated positions.
     */
    public List<Vector2d> getPlantInPoisonedResult() {
        return plantInPoisonedResult;
    }


    private List<Vector2d> generateAllPositions() {
        List<Vector2d> positions = new ArrayList<>();
        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                positions.add(new Vector2d(x, y));
            }
        }
        return positions;
    }
    /**
     * Generates random positions for the specified number of objects.
     * Ensures that each position is unique by removing it from the list of all positions.
     *
     * @return A list of Vector2d objects representing the random positions.
     */
    public List<Vector2d> generateAnimals() {
        Random random = new Random(1111);
        for (int i = 0; i < objectCount; i++) {
            int randomIndex = random.nextInt(allPositions.size());
            animalResult.add(allPositions.get(randomIndex));
        }
        return animalResult;
    }

    public List<Vector2d> generatePlantsInPoisoned(List<Vector2d> freePositions) {
        List<Vector2d> tempAllPosition = new ArrayList<>(freePositions);
        Random random = new Random(1111);
        for (int i = 0; i < objectCount; i++) {
            int randomIndex = random.nextInt(tempAllPosition.size());
            animalResult.add(tempAllPosition.get(randomIndex));
            tempAllPosition.remove(randomIndex);
        }
        return animalResult;
    }

    public List<Vector2d> generatePlants(List<Vector2d> freePositions) {
        List<Vector2d> tempAllPosition = new ArrayList<>(freePositions);
        Random random = new Random(1111);
        // Start of the equatorial band
        int equatorStart = maxHeight / 3;
        // End of the equatorial band
        int equatorEnd = 2 * maxHeight / 3;

        for (int i = 0; i < objectCount; i++) {
            int randomIndex;
            // Decide whether to place the plant in the equatorial band
            // 50% chance to be in the equatorial band
            if (random.nextBoolean()) {
                List<Vector2d> equatorialPositions = tempAllPosition.stream()
                        .filter(p -> p.getY() >= equatorStart && p.getY() <= equatorEnd)
                        .toList();
                if (!equatorialPositions.isEmpty()) {
                    randomIndex = random.nextInt(equatorialPositions.size());
                    plantResult.add(equatorialPositions.get(randomIndex));
                    tempAllPosition.remove(equatorialPositions.get(randomIndex));
                    continue;
                }
            }
            // Else, select from any available position
            randomIndex = random.nextInt(allPositions.size());
            plantResult.add(allPositions.get(randomIndex));
            allPositions.remove(randomIndex);
        }
        return plantResult;
    }
}
