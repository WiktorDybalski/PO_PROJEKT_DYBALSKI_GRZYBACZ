package model.Generators;

import RandomGenerators.RandomPositionsGenerator;
import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.maps.WorldMap;
import model.simulation.SimulationConfigurator;
import model.utils.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionsGeneratorsTest {
    PoisonedMap poisonedMap = new PoisonedMap(new SimulationConfigurator());
    GlobeMap globeMap = new GlobeMap(new SimulationConfigurator());

    private List<Vector2d> generateAllPositions(WorldMap map) {
        List<Vector2d> positions = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                positions.add(new Vector2d(x, y));
            }
        }
        return positions;
    }
    @Test
    void plantsInPoisonedPositionsGeneratorTest() {
        List<Vector2d> allPositions = generateAllPositions(poisonedMap);
        for (int i = 10; i < 30; i++) {
            RandomPositionsGenerator generator = new RandomPositionsGenerator(poisonedMap, i);
            assertEquals(i, generator.generatePlants(allPositions).size());
        }
    }

    @Test
    void plantsPositionsGeneratorTest() {
        List<Vector2d> allPositions = generateAllPositions(globeMap);
        for (int i = 10; i < 30; i++) {
            RandomPositionsGenerator generator = new RandomPositionsGenerator(globeMap, i);
            assertEquals(i, generator.generatePlants(allPositions).size());
        }
    }

    @Test
    void animalPositionGeneratorTest() {
        for (int i = 10; i < 30; i++) {
            RandomPositionsGenerator generator = new RandomPositionsGenerator(poisonedMap, i);
            assertEquals(i, generator.generateAnimals().size());
        }
    }
}
