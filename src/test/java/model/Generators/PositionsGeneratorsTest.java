package model.Generators;

import RandomGenerators.RandomPositionsGenerator;
import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.simulation.SimulationConfigurator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionsGeneratorsTest {
    PoisonedMap poisonedMap = new PoisonedMap(new SimulationConfigurator());
    GlobeMap globeMap = new GlobeMap(new SimulationConfigurator());

    @Test
    void plantsInPoisonedPositionsGeneratorTest() {

        for (int i = 10; i < 30; i++) {
            RandomPositionsGenerator generator = new RandomPositionsGenerator(poisonedMap, i);
            assertEquals(i, generator.generatePlantsInPoisoned().size());
        }
    }

    @Test
    void plantsPositionsGeneratorTest() {

        for (int i = 10; i < 30; i++) {
            RandomPositionsGenerator generator = new RandomPositionsGenerator(globeMap, i);
            assertEquals(i, generator.generatePlants().size());
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
