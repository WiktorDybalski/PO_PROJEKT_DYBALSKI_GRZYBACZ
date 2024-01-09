package GUI;

import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;
import model.simulation.SimulationEngine;

import java.util.ArrayList;

public class WorldGUI {
    private ArrayList<Simulation> simulations;
    private final SimulationConfigurator globeConfig;

    private final SimulationConfigurator poisonedConfig;

    private SimulationEngine engine;

    public WorldGUI(SimulationConfigurator config, int numberOfSimulations) {
        SimulationConfigurator globeConfig = new SimulationConfigurator(config);
        globeConfig.setMapType("Globe Map");
        this.globeConfig = globeConfig;

        SimulationConfigurator poisonedConfig = new SimulationConfigurator(config);
        poisonedConfig.setMapType("Poisoned Map");
        this.poisonedConfig = poisonedConfig;

        this.simulations = new ArrayList<>();
        addSimulations(numberOfSimulations);
        this.engine = new SimulationEngine(simulations);
    }

    public void runSimulations() {
        engine.runAsyncInThreadPool();
    }

    public void addSimulations(int numberOfSimulations) {
        for (int i = 0; i < numberOfSimulations; i++) {
            if(i%2 == 0) {
                simulations.add(new Simulation(new GlobeMap(globeConfig), globeConfig));
            }
            else {
                simulations.add(new Simulation(new PoisonedMap(poisonedConfig), poisonedConfig));
            }
        }
    }

    public static void main(String[] args) {
        int numberOfSimulations = 1;
        SimulationConfigurator config = new SimulationConfigurator();

        WorldGUI worldGUI = new WorldGUI(config, numberOfSimulations);
        worldGUI.runSimulations();
    }

}

