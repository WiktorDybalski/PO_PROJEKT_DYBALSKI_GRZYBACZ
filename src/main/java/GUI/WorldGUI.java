package GUI;

import model.maps.GlobeMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;

// In the future
// The starting class which start with the entire simulation
public class WorldGUI {
    private static Simulation simulation;

    public WorldGUI() {
        simulation = new Simulation(new GlobeMap(new SimulationConfigurator()));
    }
    public static void main(String[] args) {

        simulation.run(10);
    }
}
