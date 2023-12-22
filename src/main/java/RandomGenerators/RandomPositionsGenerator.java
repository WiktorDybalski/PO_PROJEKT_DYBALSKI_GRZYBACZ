package RandomGenerators;

import model.maps.WorldMap;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * A list of the generated positions for objects.
     */
    private List<Vector2d> result = new ArrayList<>();

    /**
     * Constructor for RandomPositionsGenerator.
     * Initializes the object with the map's dimensions and the desired number of objects.
     *
     * @param map The map on which the objects are to be placed.
     * @param objectCount The number of objects to generate positions for.
     */
    public RandomPositionsGenerator(WorldMap map, int objectCount) {
        this.maxWidth = map.getWidth();
        this.maxHeight = map.getHeight();
        this.objectCount = objectCount;
        this.allPositions = generateAllPositions();
        this.result = generateObject();
    }

    /**
     * Returns the list of generated positions.
     *
     * @return A list of Vector2d objects representing the generated positions.
     */
    public List<Vector2d> getResult() {
        return result;
    }

    /**
     * Generates random positions for the specified number of objects.
     * Ensures that each position is unique by removing it from the list of all positions.
     *
     * @return A list of Vector2d objects representing the random positions.
     */
    private List<Vector2d> generateObject() {
        Random random = new Random(1111);
        for (int i = 0; i < objectCount; i++) {
            int randomIndex = random.nextInt(allPositions.size());
            result.add(allPositions.get(randomIndex));
            allPositions.remove(randomIndex);
        }
        return result;
    }

    /**
     * Generates a list of all possible positions on the map.
     *
     * @return A list of Vector2d objects representing all possible positions.
     */
    private List<Vector2d> generateAllPositions() {
        List<Vector2d> positions = new ArrayList<>();
        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                positions.add(new Vector2d(x, y));
            }
        }
        return positions;
    }
}
