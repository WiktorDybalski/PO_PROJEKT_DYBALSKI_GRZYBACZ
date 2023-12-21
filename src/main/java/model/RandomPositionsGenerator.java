package model;

import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomPositionsGenerator {
    private final int maxWidth;
    private final int maxHeight;
    private final int objectCount;
    private final List<Vector2d> allPositions;
    private List<Vector2d> result = new ArrayList<>();

    public RandomPositionsGenerator(int maxWidth, int maxHeight, int objectCount) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.objectCount = objectCount;
        this.allPositions = generateAllPositions();
        this.result = generateObject();
    }

    public List<Vector2d> getResult() {
        return result;
    }

    private List<Vector2d> generateObject() {
        Random random = new Random(1112);
        for (int i = 0; i < objectCount; i++) {
            int randomIndex = random.nextInt(allPositions.size());
            result.add(allPositions.get(randomIndex));
            allPositions.remove(randomIndex);
        }
        return result;
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
}
