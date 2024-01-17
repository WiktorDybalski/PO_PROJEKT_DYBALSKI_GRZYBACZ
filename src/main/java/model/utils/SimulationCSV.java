package model.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SimulationCSV {

    private Path currentFilePath;

    private Statistics s;

    public SimulationCSV(Statistics s) {
        this.s = s;
    }

    public void toCSV(String baseFileName, String directoryPath) {
        try {
            // 1. Sprawdź, czy folder 'Simulations' istnieje na pulpicie
            Path simulationsFolderPath = Paths.get(directoryPath, "Simulations");
            if (!Files.exists(simulationsFolderPath)) {
                // 2. Jeśli nie istnieje, utwórz go
                Files.createDirectory(simulationsFolderPath);
            }

            // Zmodyfikuj ścieżkę zapisu plików CSV
            if (currentFilePath == null) {
                Path baseFilePath = simulationsFolderPath.resolve(baseFileName + ".csv");
                currentFilePath = createUniqueFilePath(baseFilePath);
            }

            // ... (reszta twojego kodu bez zmian) ...

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (currentFilePath == null) {
            Path directory = Paths.get(directoryPath);
            Path baseFilePath = directory.resolve(baseFileName + ".csv");
            currentFilePath = createUniqueFilePath(baseFilePath);
        }

        StringBuilder csvBuilder = new StringBuilder();

        // Dodajemy nagłówek tylko jeśli plik jest nowo utworzony
        if (!Files.exists(currentFilePath)) {
            csvBuilder.append("Statistic,Value\n"); // Nagłówek pliku
        }

        // Dodajemy dane do pliku CSV
        csvBuilder.append("Day,").append(s.getMap().getCurrentDay()).append("\n");
        csvBuilder.append("Number of Animals,").append(s.getNumberOfAnimals()).append("\n");
        csvBuilder.append("Number of Alive Animals,").append(s.getNumberOfAliveAnimals()).append("\n");
        csvBuilder.append("Number of Dead Animals,").append(s.getNumberOfDeadAnimals()).append("\n");
        csvBuilder.append("Number of Plants,").append(s.getNumberOfPlants()).append("\n");
        csvBuilder.append("Average Life Span,").append(s.getAverageLifeSpan()).append("\n");
        csvBuilder.append("Average Number of Children,").append(s.getAverageNumberOfChildren()).append("\n");
        csvBuilder.append("Average Energy Level,").append(s.getAverageEnergyLevel()).append("\n");
        csvBuilder.append("Dominant Genotype,").append(s.getDominantGenotype() != null ? s.getDominantGenotype().toString() : "None").append("\n");
        csvBuilder.append("Free Tiles,").append(s.getFreeTilesCount()).append("\n");
        csvBuilder.append("\n");

        // Zapis do pliku
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

