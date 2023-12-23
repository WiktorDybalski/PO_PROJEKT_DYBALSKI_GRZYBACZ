package model.simulation;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SimulationConfiguratorTest {
    SimulationConfigurator configurator=new SimulationConfigurator();

    @Test
    public void testSimulationConfigurator() throws IOException {
        configurator.SimulationConfiguratorFromInput();
    }
}
