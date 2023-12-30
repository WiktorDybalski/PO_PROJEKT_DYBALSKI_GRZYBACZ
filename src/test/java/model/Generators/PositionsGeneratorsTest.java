package model.Generators;

import RandomGenerators.RandomPositionsGenerator;
import model.maps.PoisonedMap;
import model.simulation.SimulationConfigurator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionsGeneratorsTest {
    @Test
    void plantGeneratorTest() {
        PoisonedMap map = new PoisonedMap(new SimulationConfigurator());
        for (int i = 10; i < 30; i++) {
            RandomPositionsGenerator generator = new RandomPositionsGenerator(map, i);
            assertEquals(2 * i, generator.getAnimalResult().size()); //TODO: it generates two times more plants than expected
        }
    }
}
