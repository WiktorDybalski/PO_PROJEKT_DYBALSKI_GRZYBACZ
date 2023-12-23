package model.simulation;

import model.maps.WorldMap;


public class Simulation {
    //    TODO
//      Configure simulation class

    private WorldMap worldMap;

    public Simulation(WorldMap worldmap) {
        this.worldMap = worldmap;
    }
    public void run(int days, int dailyPlantCountIncrease) {
        for (int i = 0; i < days; i++) {
            this.worldMap.dailyUpdate(dailyPlantCountIncrease);
            try {
                Thread.sleep(1000); // Wait 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


