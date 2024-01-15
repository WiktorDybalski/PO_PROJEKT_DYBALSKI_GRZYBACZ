package model.simulation;

import model.maps.WorldMap;
import model.utils.Genotype;
import model.utils.Statistics;
import model.utils.Tile;
import model.utils.Vector2d;
import presenters.ConsoleMapDisplay;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Simulation extends Thread {
    private final WorldMap worldMap;

    private final SimulationConfigurator config;
    private Statistics statistics;
    private final Object lock = new Object();

    private volatile boolean running = false;

    public boolean isRunning() {
        return running;
    }

    public Simulation(WorldMap worldmap, SimulationConfigurator config) {
        this.worldMap = worldmap;
        this.config = config;
        this.statistics = new Statistics(worldMap);
    }

    public Statistics getStatistics() {
        return statistics;
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
            //System.out.println(this.statistics.getStatistics());
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
        int mapHeight= this.worldMap.getConfig().getMapSizeY();
        int equatorialStart = (mapHeight-mapHeight%3) / 3;
        int equatorialEnd = mapHeight - equatorialStart+1;

        Set<Vector2d> plantPreferredFields = new HashSet<>();
        for (int i = 1; i <= this.worldMap.getConfig().getMapSizeX()+1; i++) {
            for (int j = equatorialStart+1; j < equatorialEnd; j++) {
                plantPreferredFields.add(new Vector2d(i, j));
            }
        }
        return plantPreferredFields;
    }
}


