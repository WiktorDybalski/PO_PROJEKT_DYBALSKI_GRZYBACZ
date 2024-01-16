package model.utils;

import model.maps.WorldMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Statistics {
    private WorldMap map;
    private int numberOfAnimals;
    private int numberOfPlants;
    private double averageLifeSpan;
    private double averageNumberOfChildren;
    private double averageEnergyLevel;
    private Genotype dominantGenotype;
    private int freeTilesCount;
    private int numberOfAliveAnimals;
    private int numberOfDeadAnimals;

    private Set<Vector2d> dominantGenotypeAnimals;

    public Statistics(WorldMap map) {
        this.map = map;
        this.numberOfAnimals = 0;
        this.numberOfPlants = 0;
        this.averageLifeSpan = 0;
        this.averageNumberOfChildren = 0;
        this.averageEnergyLevel = 0;
        this.dominantGenotype = null;
        this.freeTilesCount = 0;
        this.numberOfAliveAnimals = 0;
        this.numberOfDeadAnimals = 0;
        this.dominantGenotypeAnimals = new HashSet<>();
    }

    //getters
    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public int getNumberOfPlants() {
        return numberOfPlants;
    }

    public double getAverageLifeSpan() {
        return (double) Math.round(averageLifeSpan * 100.0) / 100;
    }

    public double getAverageNumberOfChildren() {
        return (double) Math.round(averageNumberOfChildren * 100.0) / 100;
    }

    public double getAverageEnergyLevel() {
        return (double) Math.round(averageEnergyLevel * 100.0) / 100;
    }

    public Genotype getDominantGenotype() {
        return dominantGenotype;
    }

    public int getFreeTilesCount() {
        return freeTilesCount;
    }


    public int getNumberOfAliveAnimals() {
        return numberOfAliveAnimals;
    }

    public int getNumberOfDeadAnimals() {
        return numberOfDeadAnimals;
    }

    public Set<Vector2d> getDominantGenotypeAnimals() {
        return dominantGenotypeAnimals;
    }

    public WorldMap getMap() {
        return map;
    }

    /**
     * Method returning statistics about the map.
     *
     * @return statistics about the map
     */
    public String getStatistics() {
        updateStatistics();
        return this.toString();
    }

    /**
     * Method updating the statistics about the map.
     */
    public void updateStatistics() {
        int numberOfAliveAnimals = 0;
        int numberOfDeadAnimals = 0;
        int numberOfPlants = 0;
        int cumulativeLifeSpan = 0;
        double averageNumberOfChildren = 0;
        double averageEnergyLevel = 0;
        int freeTilesCount = 0;
        HashMap<Genotype, Integer> genotypeCounter = new HashMap<>();

        for (Tile tile : map.getMapTiles().values()) {
            if (!tile.isOccupied()) {
                freeTilesCount++;
            } else {
                numberOfAliveAnimals += tile.getAnimals().size();
                numberOfPlants += tile.getPlant() == null ? 0 : 1;
                for (Animal animal : tile.getAnimals()) {
                    averageNumberOfChildren += animal.getChildren().size();
                    averageEnergyLevel += animal.getEnergy();

                    if (genotypeCounter.containsKey(animal.getGenotype())) {
                        Integer temp = genotypeCounter.get(animal.getGenotype()) + 1;
                        genotypeCounter.replace(animal.getGenotype(), temp);
                    } else {
                        genotypeCounter.put(animal.getGenotype(), 1);
                    }
                }
            }
        }

        int maxCount = 0;

        for (Genotype genotype : genotypeCounter.keySet()) {
            if (genotypeCounter.get(genotype) > maxCount) {
                maxCount = genotypeCounter.get(genotype);
                this.dominantGenotype = genotype;
            }
        }

        this.dominantGenotypeAnimals = new HashSet<>(genotypeCounter.get(this.dominantGenotype));

        for (Animal animal : map.getAnimals()) {
            if (animal.getIsDead()) {
                numberOfDeadAnimals++;
                cumulativeLifeSpan += animal.getAge();
            }
        }
        if (numberOfDeadAnimals > 0) {
            this.averageLifeSpan = (double) cumulativeLifeSpan / numberOfDeadAnimals;
        }
        this.numberOfAnimals = numberOfAliveAnimals + numberOfDeadAnimals;
        this.numberOfAliveAnimals = numberOfAliveAnimals;
        this.numberOfDeadAnimals = numberOfDeadAnimals;

        if (numberOfAliveAnimals > 0) {
            this.averageNumberOfChildren = averageNumberOfChildren / numberOfAliveAnimals / 2.0;
            this.averageEnergyLevel = averageEnergyLevel / numberOfAliveAnimals;
        } else {
            this.averageNumberOfChildren = 0;
            this.averageEnergyLevel = 0;
        }
        this.numberOfPlants = numberOfPlants;
        this.freeTilesCount = freeTilesCount;
    }

    /**
     * Method returning statistics about the map.
     *
     * @return statistics about the map
     */
    public String toString() {
        return "Statistics:" + "\n" +
                "Number of animals: " + getNumberOfAnimals() + "\n" +
                "Number of alive animals: " + getNumberOfAliveAnimals() + "\n" +
                "Number of dead animals: " + getNumberOfDeadAnimals() + "\n" +
                "Number of plants: " + getNumberOfPlants() + "\n" +
                "Average life span: " + getAverageLifeSpan() + "\n" +
                "Average number of children: " + getAverageNumberOfChildren() + "\n" +
                "Average energy level: " + getAverageEnergyLevel() + "\n" +
                "Dominant genotype: " + getDominantGenotype() + "\n" +
                "Free tiles: " + getFreeTilesCount() + "\n";
    }
}
