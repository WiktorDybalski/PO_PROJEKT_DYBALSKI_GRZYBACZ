package model.utils;

import model.maps.WorldMap;

import java.io.File;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

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
    }

    //getters
    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public int getNumberOfPlants() {
        return numberOfPlants;
    }

    public double getAverageLifeSpan() {
        return (double) Math.round(averageLifeSpan * 100.0) /100;
    }

    public double getAverageNumberOfChildren() {
        return (double) Math.round(averageNumberOfChildren * 100.0) /100;
    }

    public double getAverageEnergyLevel() {
        return (double) Math.round(averageEnergyLevel * 100.0) /100;
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

    /**
     * Method returning statistics about the map.
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
        for (Tile tile: map.getMapTiles().values()) {
            if (!tile.isOccupied()) {
                freeTilesCount++;
            } else {
                numberOfAliveAnimals += tile.getAnimals().size();
                numberOfPlants += tile.getPlant() == null ? 0 : 1;
                for (Animal animal : tile.getAnimals()) {
                    averageNumberOfChildren += animal.getChildren().size();
                    averageEnergyLevel += animal.getEnergy();
                }
            }
        }
        for (Animal animal : map.getAnimals()) {
            Genotype genotype = animal.getGenotype();
            if (genotypeCounter.containsKey(genotype)) {
                genotypeCounter.put(genotype, genotypeCounter.get(genotype) + 1);
            } else {
                genotypeCounter.put(genotype, 1);
            }
            if (animal.getIsDead()) {
                numberOfDeadAnimals++;
                cumulativeLifeSpan += animal.getAge();
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
        if (numberOfDeadAnimals > 0) {
            this.averageLifeSpan = (double) cumulativeLifeSpan / numberOfDeadAnimals;
        }
        this.numberOfAnimals = this.map.getAnimals().size();
        this.numberOfAliveAnimals = numberOfAliveAnimals;
        this.numberOfDeadAnimals= numberOfDeadAnimals;

        this.numberOfPlants = numberOfPlants;
        this.averageNumberOfChildren = averageNumberOfChildren/numberOfAliveAnimals/2.0;
        this.averageEnergyLevel = averageEnergyLevel/numberOfAliveAnimals;
        this.freeTilesCount = freeTilesCount;
        this.dominantGenotype = dominantGenotype;
    }

    /**
     * Method returning statistics about the map.
     * @return statistics about the map
     */
    public String toString() {
        return "Number of animals: " + getNumberOfAnimals() + "\n" +
                "Number of alive animals: " + getNumberOfAliveAnimals() + "\n" +
                "Number of dead animals: " + getNumberOfDeadAnimals() + "\n" +
                "Number of plants: " + getNumberOfPlants() + "\n" +
                "Average life span: " + getAverageLifeSpan() + "\n" +
                "Average number of children: " + getAverageNumberOfChildren() + "\n" +
                "Average energy level: " + getAverageEnergyLevel() + "\n" +
                "Dominant genotype: " + getDominantGenotype() + "\n" +
                "Free tiles: " + getFreeTilesCount() + "\n";
    }

    /**
     * Method saving statistics about the map to the csv file.
     * @param filePath path to the csv file
     */
    public void toCSV(String filePath) {
        StringBuilder csvBuilder = new StringBuilder();

        boolean fileExists = new File(filePath).exists();

        if (!fileExists) {
            csvBuilder.append("Statistic,Value\n");
        }
        csvBuilder.append("Day,").append(map.getCurrentDay()).append("\n");
        csvBuilder.append("Number of Animals,").append(getNumberOfAnimals()).append("\n");
        csvBuilder.append("Number of Alive Animals,").append(getNumberOfAliveAnimals()).append("\n");
        csvBuilder.append("Number of Dead Animals,").append(getNumberOfDeadAnimals()).append("\n");
        csvBuilder.append("Number of Plants,").append(getNumberOfPlants()).append("\n");
        csvBuilder.append("Average Life Span,").append(getAverageLifeSpan()).append("\n");
        csvBuilder.append("Average Number of Children,").append(getAverageNumberOfChildren()).append("\n");
        csvBuilder.append("Average Energy Level,").append(getAverageEnergyLevel()).append("\n");
        csvBuilder.append("Dominant Genotype,").append(getDominantGenotype() != null ? getDominantGenotype().toString() : "None").append("\n");
        csvBuilder.append("Free Tiles,").append(getFreeTilesCount()).append("\n");
        csvBuilder.append("\n");

        try (FileWriter writer = new FileWriter(filePath, true)) { // 'true' to append data
            writer.write(csvBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
