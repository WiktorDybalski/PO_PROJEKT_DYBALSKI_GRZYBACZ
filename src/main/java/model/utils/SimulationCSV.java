package model.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SimulationCSV {

    private Path currentFilePath;

    private Statistics statistics;

    public SimulationCSV(Statistics statistics) {
        this.statistics = statistics;
    }

    public void toCSV(String baseFileName, String directoryPath) {
        try {
            Path simulationsFolderPath = Paths.get(directoryPath, "Simulations");
            if (!Files.exists(simulationsFolderPath)) {
                Files.createDirectory(simulationsFolderPath);
            }
            if (currentFilePath == null) {
                Path baseFilePath = simulationsFolderPath.resolve(baseFileName + ".csv");
                currentFilePath = createUniqueFilePath(baseFilePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (currentFilePath == null) {
            Path directory = Paths.get(directoryPath);
            Path baseFilePath = directory.resolve(baseFileName + ".csv");
            currentFilePath = createUniqueFilePath(baseFilePath);
        }

        StringBuilder csvBuilder = new StringBuilder();

        if (!Files.exists(currentFilePath)) {
            csvBuilder.append("Statistic,Value\n");
        }

        csvBuilder.append("Day,").append(statistics.getMap().getCurrentDay()).append("\n");
        csvBuilder.append("Number of Animals,").append(statistics.getNumberOfAnimals()).append("\n");
        csvBuilder.append("Number of Alive Animals,").append(statistics.getNumberOfAliveAnimals()).append("\n");
        csvBuilder.append("Number of Dead Animals,").append(statistics.getNumberOfDeadAnimals()).append("\n");
        csvBuilder.append("Number of Plants,").append(statistics.getNumberOfPlants()).append("\n");
        csvBuilder.append("Average Life Span,").append(statistics.getAverageLifeSpan()).append("\n");
        csvBuilder.append("Average Number of Children,").append(statistics.getAverageNumberOfChildren()).append("\n");
        csvBuilder.append("Average Energy Level,").append(statistics.getAverageEnergyLevel()).append("\n");
        csvBuilder.append("Dominant Genotype,").append(statistics.getDominantGenotype() != null ? statistics.getDominantGenotype().toString() : "None").append("\n");
        csvBuilder.append("Free Tiles,").append(statistics.getFreeTilesCount()).append("\n");
        csvBuilder.append("\n");

        try {
            Files.write(currentFilePath, csvBuilder.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path createUniqueFilePath(Path originalPath) {
        int counter = 1;
        Path filePath = originalPath;

        while (Files.exists(filePath)) {
            String fileName = String.format("%s(%d).csv", originalPath.toString().replace(".csv", ""), counter);
            filePath = Paths.get(fileName);
            counter++;
        }

        return filePath;
    }
}

