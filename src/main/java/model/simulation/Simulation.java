package model.simulation;

import model.maps.WorldMap;


public class Simulation {
    //    TODO
//      Configure simulation class

    private WorldMap worldMap;

    private SimulationConfigurator config;

    public Simulation(WorldMap worldmap, SimulationConfigurator config) {
        this.worldMap = worldmap;
        this.config = config;
    }

    public void run(int days) {
        this.worldMap.firstDay();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i < days; i++) {
            this.worldMap.dailyUpdate();
            try {
                Thread.sleep(1000); // Wait 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


