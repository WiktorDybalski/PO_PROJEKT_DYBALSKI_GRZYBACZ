package GUI;

import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;

// In the future
// The starting class which start with the entire simulation
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
        System.out.println("GlobeMap");
        simulation1.run(steps);
        System.out.println("PoisonedMap");
        simulation2.run(steps);
    }

    public static void main(String[] args) {
        SimulationConfigurator config = new SimulationConfigurator();

        WorldGUI worldGUI = new WorldGUI(config);
        worldGUI.runSimulation(10);
    }
}

