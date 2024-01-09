package model.simulation;

import model.maps.WorldMap;
import model.utils.Statistics;


public class Simulation extends Thread {
    private final WorldMap worldMap;

    private final SimulationConfigurator config;
    private final Statistics statistics;

    public Simulation(WorldMap worldmap, SimulationConfigurator config) {
        this.worldMap = worldmap;
        this.config = config;
        this.statistics = new Statistics(worldMap);
    }

    public void run() {
        for (int i = 0; i < config.getNumberOfDays(); i++) {
            synchronized (this.worldMap) {
                synchronized (System.out) {
                    System.out.println(this.worldMap);
                    this.statistics.updateStatistics();
                    this.worldMap.dailyUpdate();
                    System.out.println(this.statistics.getStatistics());
                }
                try {
                    Thread.sleep(1000); // Wait 1 second
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}


