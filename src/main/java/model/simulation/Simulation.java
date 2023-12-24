package model.simulation;

import model.maps.WorldMap;


public class Simulation {
    //    TODO
//      Configure simulation class

    private WorldMap worldMap;

    private SimulationConfigurator config;

    public Simulation(WorldMap worldmap) {
        this.worldMap = worldmap;
        this.config = new SimulationConfigurator();
    }
    public void run(int days) {
        for (int i = 0; i < days; i++) {
            this.worldMap.dailyUpdate();
            try {
                Thread.sleep(1000); // Wait 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


