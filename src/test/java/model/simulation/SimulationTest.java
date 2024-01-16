package model.simulation;

import model.maps.GlobeMap;
import model.simulation.Simulation;
import model.utils.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
    SimulationConfigurator config = new SimulationConfigurator();

    @Test
    public void testSimulation() {
        Simulation simulation = new Simulation(new GlobeMap(config), config);
        simulation.run();
    }
}
