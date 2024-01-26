package model.simulation;

import model.maps.WorldMap;
import model.utils.Genotype;
import model.utils.Statistics;
import model.utils.Tile;
import model.utils.Vector2d;
import presenters.ConsoleMapDisplay;

import java.util.HashSet;
import java.util.Set;


public class Simulation extends Thread {

    /**
     * The map of the world where the simulation occurs.
     */
    private final WorldMap worldMap;

    /**
     * Configuration settings for the simulation.
     */
    private final SimulationConfigurator config;

    /**
     * Lock object for thread synchronization.
     */
    private final Object lock = new Object();

    /**
     * Statistics tracker for the simulation.
     */
    private Statistics statistics;

    /**
     * Flag to control the simulation's running state.
     */
    private volatile boolean running = false;

    public Simulation(WorldMap worldmap, SimulationConfigurator config) {
        this.worldMap = worldmap;
        this.config = config;
    }

    public boolean isRunning() {
        return running;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void run() {
        worldMap.addObserver(new ConsoleMapDisplay());
        this.worldMap.firstDay();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // czemu nie po prostu return?
        }
        for (int i = 1; i < config.getNumberOfDays(); i++) {
            synchronized (lock) {
                while (!running) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            this.worldMap.dailyUpdate();
            try {
                Thread.sleep(this.worldMap.getConfig().getDayLength());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pauseSimulation() {
        synchronized (lock) {
            running = false;
        }
    }

    public void resumeSimulation() {
        synchronized (lock) {
            running = true;
            lock.notify();
        }
    }

    public void stopSimulation() {
        synchronized (lock) {
            running = false;
            lock.notify();
        }
        interrupt();
    }

    public Set<Vector2d> getDominantGenotypeAnimals() {
        Set<Vector2d> dominantGenotypePositions = new HashSet<>();
        Genotype dominantGenotype = this.statistics.getDominantGenotype();
        for (Tile tile : this.worldMap.getMapTiles().values()) {
            if (tile.getStrongestAnimal() != null && tile.getStrongestAnimal().getGenotype().equals(dominantGenotype)) {
                dominantGenotypePositions.add(tile.getStrongestAnimal().getPosition());
            }
        }
        return dominantGenotypePositions;
    }

    public Set<Vector2d> getPlantPreferredFields() {
        int mapHeight = this.worldMap.getConfig().getMapSizeY();
        int equatorialStart = (mapHeight - mapHeight % 3) / 3;
        int equatorialEnd = mapHeight - equatorialStart + 1;

        Set<Vector2d> plantPreferredFields = new HashSet<>();
        for (int i = 1; i <= this.worldMap.getConfig().getMapSizeX() + 1; i++) {
            for (int j = equatorialStart + 1; j < equatorialEnd; j++) {
                plantPreferredFields.add(new Vector2d(i, j));
            }
        }
        return plantPreferredFields;
    }
}


