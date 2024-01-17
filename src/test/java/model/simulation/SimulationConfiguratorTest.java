package model.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationConfiguratorTest {
    SimulationConfigurator configurator;

    @BeforeEach
    void setUp() {
        configurator = new SimulationConfigurator();
    }

    @Test
    void testSimulationConfigurator() {
        assertEquals(10, configurator.getInitialAnimalCount());
        assertEquals(30, configurator.getInitialPlantCount());
        assertEquals(1, configurator.getPlantEnergy());
        assertEquals(3, configurator.getNumberOfPlantsGrowingPerDay());
        assertEquals("Random", configurator.getModeOfPlantGrowing());
        assertEquals(5, configurator.getInitialAnimalEnergy());
        assertEquals(10, configurator.getReadyToReproduceEnergy());
        assertEquals(10, configurator.getReproduceEnergyLoss());
        assertEquals(1, configurator.getMinimumMutationCount());
        assertEquals(3, configurator.getMaximumMutationCount());
        assertEquals("Random", configurator.getMutationVariant());
        assertEquals(8, configurator.getGenomeLength());
        assertEquals(10, configurator.getNumberOfDays());
    }


}
