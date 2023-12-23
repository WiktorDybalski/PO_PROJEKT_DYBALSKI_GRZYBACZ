package model.utils;

import model.maps.WorldMap;
import java.util.HashMap;

public class Statistics {
    private WorldMap map;
    private int numberOfAnimals;
    private int numberOfPlants;
    private double averageLifeSpan;
    private double averageNumberOfChildren;
    private double averageEnergyLevel;
    private Genotype dominantGenotype;
    private int freeTilesCount;

    public Statistics(WorldMap map) {
        this.map = map;
        this.numberOfAnimals = 0;
        this.numberOfPlants = 0;
        this.averageLifeSpan = 0;
        this.averageNumberOfChildren = 0;
        this.averageEnergyLevel = 0;
        this.dominantGenotype = null;
        this.freeTilesCount = 0;
    }

    //getters
    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public int getNumberOfPlants() {
        return numberOfPlants;
    }

    public double getAverageLifeSpan() {
        return averageLifeSpan;
    }

    public double getAverageNumberOfChildren() {
        return averageNumberOfChildren;
    }

    public double getAverageEnergyLevel() {
        return averageEnergyLevel;
    }

    public Genotype getDominantGenotype() {
        return dominantGenotype;
    }

    public int getFreeTilesCount() {
        return freeTilesCount;
    }
    /**
     * @return number of animals living on the map at the moment
     */
    public int updateNumberOfAnimals() {
        int counter = 0;
        for (Animal animal : map.getAnimals()) {
            if (!animal.getIsDead()) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return number of plants living on the map at the moment
     */
    public int updateNumberOfPlants() {
        int counter = 0;
        for (Plant plant : map.getPlants()) {
            if (!plant.getIsEaten()) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return average life span of dead animals
     */
    public double updateAverageLifeSpan() {
        double sum = 0;
        int counter = 0;
        for (Animal animal : map.getAnimals()) {
            if (animal.getIsDead()) {
                sum += animal.getAge();
                counter++;
            }
        }
        return sum / counter;
    }

    /**
     * @return average number of children of all animals
     */
    public double updateAverageNumberOfChildren() {
        double sum = 0;
        int counter = 0;
        for (Animal animal : map.getAnimals()) {
            sum += animal.getChildren().size();
            counter++;
        }
        return sum / counter;
    }

    /**
     * @return average energy level of animals living on the map at the moment
     */
    public double updateAverageEnergyLevel() {
        double sum = 0;
        int counter = 0;
        for (Animal animal : map.getAnimals()) {
            if (!animal.getIsDead()) {
                sum += animal.getEnergy();
                counter++;
            }
        }
        return sum / counter;
    }

    /**
     * @return the most popular genotype of all animals (include dead)
     */
    public Genotype updateDominantGenotype() {
        HashMap<Genotype, Integer> genotypeCounter = new HashMap<>();
        for (Animal animal : map.getAnimals()) {
            Genotype genotype = animal.getGenotype();
            if (genotypeCounter.containsKey(genotype)) {
                genotypeCounter.put(genotype, genotypeCounter.get(genotype) + 1);
            } else {
                genotypeCounter.put(genotype, 1);
            }
        }
        int max = 0;
        Genotype dominantGenotype = null;
        for (Genotype genotype : genotypeCounter.keySet()) {
            if (genotypeCounter.get(genotype) > max) {
                max = genotypeCounter.get(genotype);
                dominantGenotype = genotype;
            }
        }
        return dominantGenotype;
    }

    /**
     * @return the count of free fields on the map at the moment
     */
    public int updateFreeTilesCount() {
        int counter = 0;
        for (Tile tile : map.getMapTiles().values()) {
            if (!tile.getObjects().isOccupied()) {
                counter++;
            }
        }
        return counter;
    }

    public void updateStatistics() {
        this.numberOfAnimals = updateNumberOfAnimals();
        this.numberOfPlants = updateNumberOfPlants();
        this.averageLifeSpan = updateAverageLifeSpan();
        this.averageNumberOfChildren = updateAverageNumberOfChildren();
        this.averageEnergyLevel = updateAverageEnergyLevel();
        this.dominantGenotype = updateDominantGenotype();
        this.freeTilesCount = updateFreeTilesCount();
    }

    public String toString() {
        return "Number of animals: " + getNumberOfAnimals() + "\n" +
                "Number of plants: " + getNumberOfPlants() + "\n" +
                "Average life span: " + getAverageLifeSpan() + "\n" +
                "Average number of children: " + getAverageNumberOfChildren() + "\n" +
                "Average energy level: " + getAverageEnergyLevel() + "\n" +
                "Dominant genotype: " + getDominantGenotype() + "\n" +
                "Free tiles: " + getFreeTilesCount() + "\n";
    }
}
