package GUI;

import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;

public class WorldGUI {
    private final Simulation simulation1;
    private final Simulation simulation2;
    private final SimulationConfigurator config;

    public WorldGUI(SimulationConfigurator config) {
        this.config = config;
        this.simulation1 = new Simulation(new GlobeMap(config), config);
        this.simulation2 = new Simulation(new PoisonedMap(config), config);
    }

    public void runSimulation(int steps) {
        simulation1.start();
        simulation2.start();
    }

    public static void main(String[] args) {
        SimulationConfigurator config = new SimulationConfigurator();

        WorldGUI worldGUI = new WorldGUI(config);
        worldGUI.runSimulation(10);
    }
}

