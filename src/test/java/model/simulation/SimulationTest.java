package model.simulation;

import model.maps.GlobeMap;
import model.simulation.Simulation;
import org.junit.jupiter.api.Test;

public class SimulationTest {
    SimulationConfigurator config = new SimulationConfigurator();
    @Test
    public void testSimulation() {
        Simulation simulation = new Simulation(new GlobeMap(config), config);
        simulation.run(20);
    }
}
