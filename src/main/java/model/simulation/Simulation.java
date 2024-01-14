package model.simulation;

import model.maps.WorldMap;
import model.utils.Statistics;
import presenters.ConsoleMapDisplay;


public class Simulation extends Thread {
    private final WorldMap worldMap;

    private final SimulationConfigurator config;
    private Statistics statistics;
    private final Object lock = new Object();

    private boolean running = false;

    public boolean isRunning() {
        return running;
    }

    public Simulation(WorldMap worldmap, SimulationConfigurator config) {
        this.worldMap = worldmap;
        this.config = config;
        this.statistics = new Statistics(worldMap);
    }

    public void run() {
        worldMap.addObserver(new ConsoleMapDisplay());
        this.worldMap.firstDay();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i < config.getNumberOfDays(); i++) {
            this.worldMap.dailyUpdate();
            System.out.println(this.statistics.getStatistics());
            try {
                Thread.sleep(this.worldMap.getConfig().getDayLength()); // Wait 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void pauseSimulation() {
        running = false;
    }

    public void resumeSimulation() {
        running = true;
        synchronized (lock) {
            lock.notify();
        }
    }
}


