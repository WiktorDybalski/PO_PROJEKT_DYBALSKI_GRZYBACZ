package GUI;

import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;

// In the future
// The starting class which start with the entire simulation
public class WorldGUI {
    private final Simulation simulation;
    private final SimulationConfigurator config;

    public WorldGUI(SimulationConfigurator config) {
        this.config = config;
        this.simulation = new Simulation(new GlobeMap(config), config);
    }

    public void runSimulation(int steps) {
        simulation.run(steps);
    }

    public static void main(String[] args) {
        SimulationConfigurator config = new SimulationConfigurator();

        WorldGUI worldGUI = new WorldGUI(config);
        worldGUI.runSimulation(10);
    }
}

