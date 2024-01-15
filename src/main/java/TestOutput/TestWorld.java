package TestOutput;

import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;

public class TestWorld {
    private final Simulation simulation1;
    private final Simulation simulation2;
    private final SimulationConfigurator config;

    public TestWorld(SimulationConfigurator config) {
        this.config = config;
        this.simulation1 = new Simulation(new GlobeMap(config), config);
        this.simulation2 = new Simulation(new PoisonedMap(config), config);
    }

    public void runSimulation(int steps) {
        simulation1.start();
        //simulation2.start();
    }

    public static void main(String[] args) {
        SimulationConfigurator config = new SimulationConfigurator();

        TestWorld worldGUI = new TestWorld(config);
        worldGUI.runSimulation(10);
    }
}

