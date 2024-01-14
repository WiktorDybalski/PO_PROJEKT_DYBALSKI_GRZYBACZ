package model.simulation;
import model.utils.Vector2d;

import java.io.FileWriter;
import java.io.IOException;

public class SimulationConfigurator {
    private int numberOfDays;
    private int mapSizeX;
    private int mapSizeY;
    private String mapType;
    private int initialPlantCount;
    private int plantEnergy;
    private int numberOfPlantsGrowingPerDay;
    private String modeOfPlantGrowing;
    private int initialAnimalCount;
    private int initialAnimalEnergy;
    private int readyToReproduceEnergy;
    private int reproduceEnergyLoss;
    private int minimumMutationCount;
    private int maximumMutationCount;
    private String mutationVariant;
    private int genomeLength;
    private String animalBehaviourType;

    private int dayLength;

    public SimulationConfigurator() {
        this.mapSizeX = 10;
        this.mapSizeY = 10;
        this.mapType = "WorldMap";
        this.initialAnimalCount = 10;
        this.initialPlantCount = 50;
        this.plantEnergy = 2;
        this.numberOfPlantsGrowingPerDay = 3;
        this.modeOfPlantGrowing = "Random";
        this.initialAnimalEnergy = 5;
        this.readyToReproduceEnergy = 10;
        this.reproduceEnergyLoss = 10;
        this.minimumMutationCount = 1;
        this.maximumMutationCount = 3;
        this.mutationVariant = "Random";
        this.genomeLength = 8;
        this.animalBehaviourType = "Random";
        this.numberOfDays = 100;
        this.dayLength = 1000;
    }

    public Vector2d getMapSize() {
        return new Vector2d(mapSizeX, mapSizeY);
    }

    public void setMapSize(int x, int y) {
        if (x > 0 && y > 0) {
            this.mapSizeX = x;
            this.mapSizeY = y;
        }
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        if (mapType != null && !mapType.isEmpty()) {
            this.mapType = mapType;
        }
    }

    public int getInitialPlantCount() {
        return initialPlantCount;
    }

    public void setInitialPlantCount(int initialPlantCount) {
        if (initialPlantCount > 0) {
            this.initialPlantCount = initialPlantCount;
        }
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public void setPlantEnergy(int plantEnergy) {
        if(plantEnergy > 0) {
            this.plantEnergy = plantEnergy;
        }
    }

    public int getNumberOfPlantsGrowingPerDay() {
        return numberOfPlantsGrowingPerDay;
    }

    public void setNumberOfPlantsGrowingPerDay(int numberOfPlantsGrowingPerDay) {
        if (numberOfPlantsGrowingPerDay > 0) {
            this.numberOfPlantsGrowingPerDay = numberOfPlantsGrowingPerDay;
        }
    }

    public String getModeOfPlantGrowing() {
        return modeOfPlantGrowing;
    }

    public void setModeOfPlantGrowing(String modeOfPlantGrowing) {
        if (modeOfPlantGrowing != null && !modeOfPlantGrowing.isEmpty()) {
            this.modeOfPlantGrowing = modeOfPlantGrowing;
        }
    }

    public int getInitialAnimalCount() {
        return initialAnimalCount;
    }

    public void setInitialAnimalCount(int initialAnimalCount) {
        if (initialAnimalCount > 0) {
            this.initialAnimalCount = initialAnimalCount;
        }
    }

    public int getInitialAnimalEnergy() {
        return initialAnimalEnergy;
    }

    public void setInitialAnimalEnergy(int initialAnimalEnergy) {
        if (initialAnimalEnergy > 0) {
            this.initialAnimalEnergy = initialAnimalEnergy;
        }
    }

    public int getReadyToReproduceEnergy() {
        return readyToReproduceEnergy;
    }

    public void setReadyToReproduceEnergy(int readyToReproduceEnergy) {
        if (readyToReproduceEnergy > 0) {
            this.readyToReproduceEnergy = readyToReproduceEnergy;
        }
    }

    public int getReproduceEnergyLoss() {
        return reproduceEnergyLoss;
    }

    public void setReproduceEnergyLoss(int reproduceEnergyLoss) {
        if (reproduceEnergyLoss > 0) {
            this.reproduceEnergyLoss = reproduceEnergyLoss;
        }
    }

    public int getMinimumMutationCount() {
        return minimumMutationCount;
    }

    public void setMinimumMutationCount(int minimumMutationCount) {
        if (minimumMutationCount >= 0) {
            this.minimumMutationCount = minimumMutationCount;
        }
    }

    public int getMaximumMutationCount() {
        return maximumMutationCount;
    }

    public void setMaximumMutationCount(int maximumMutationCount) {
        if (maximumMutationCount >= this.minimumMutationCount) {
            this.maximumMutationCount = maximumMutationCount;
        }
    }

    public String getMutationVariant() {
        return mutationVariant;
    }

    public void setMutationVariant(String mutationVariant) {
        if (mutationVariant != null && !mutationVariant.isEmpty()) {
            this.mutationVariant = mutationVariant;
        }
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public void setGenomeLength(int genomeLength) {
        if (genomeLength > 0) {
            this.genomeLength = genomeLength;
        }
    }

    public String getAnimalBehaviourType() {
        return animalBehaviourType;
    }

    public void setAnimalBehaviourType(String animalBehaviourType) {
        if (animalBehaviourType != null && !animalBehaviourType.isEmpty()) {
            this.animalBehaviourType = animalBehaviourType;
        }
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        if (numberOfDays > 0) {
            this.numberOfDays = numberOfDays;
        }
    }

    public int getDayLength() {
        return dayLength;
    }

    public void setDayLength(int dayLength) {
        if (dayLength > 0) {
            this.dayLength = dayLength;
        }
    }

    public void toTextFile() {
        try (FileWriter writer = new FileWriter("config.txt")) {
            writer.write("numberOfDays: " + numberOfDays + "\n");
            writer.write("mapSizeX: " + mapSizeX + "\n");
            writer.write("mapSizeY: " + mapSizeY + "\n");
            writer.write("mapType: " + mapType + "\n");
            writer.write("initialPlantCount: " + initialPlantCount + "\n");
            writer.write("plantEnergy: " + plantEnergy + "\n");
            writer.write("numberOfPlantsGrowingPerDay: " + numberOfPlantsGrowingPerDay + "\n");
            writer.write("modeOfPlantGrowing: " + modeOfPlantGrowing + "\n");
            writer.write("initialAnimalCount: " + initialAnimalCount + "\n");
            writer.write("initialAnimalEnergy: " + initialAnimalEnergy + "\n");
            writer.write("readyToReproduceEnergy: " + readyToReproduceEnergy + "\n");
            writer.write("reproduceEnergyLoss: " + reproduceEnergyLoss + "\n");
            writer.write("minimumMutationCount: " + minimumMutationCount + "\n");
            writer.write("maximumMutationCount: " + maximumMutationCount + "\n");
            writer.write("mutationVariant: " + mutationVariant + "\n");
            writer.write("genomeLength: " + genomeLength + "\n");
            writer.write("animalBehaviourType: " + animalBehaviourType + "\n");
            writer.write("dayLength: " + dayLength + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromTextFile() {
        try {
            String[] lines = new String[20];
            int i = 0;
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("config.txt"));
            String line = reader.readLine();
            while (line != null) {
                lines[i] = line;
                line = reader.readLine();
                i++;
            }
            reader.close();
            this.numberOfDays = Integer.parseInt(lines[0].split(" ")[1]);
            this.mapSizeX = Integer.parseInt(lines[1].split(" ")[1]);
            this.mapSizeY = Integer.parseInt(lines[2].split(" ")[1]);
            this.mapType = lines[3].split(" ")[1];
            this.initialPlantCount = Integer.parseInt(lines[4].split(" ")[1]);
            this.plantEnergy = Integer.parseInt(lines[5].split(" ")[1]);
            this.numberOfPlantsGrowingPerDay = Integer.parseInt(lines[6].split(" ")[1]);
            this.modeOfPlantGrowing = lines[7].split(" ")[1];
            this.initialAnimalCount = Integer.parseInt(lines[8].split(" ")[1]);
            this.initialAnimalEnergy = Integer.parseInt(lines[9].split(" ")[1]);
            this.readyToReproduceEnergy = Integer.parseInt(lines[10].split(" ")[1]);
            this.reproduceEnergyLoss = Integer.parseInt(lines[11].split(" ")[1]);
            this.minimumMutationCount = Integer.parseInt(lines[12].split(" ")[1]);
            this.maximumMutationCount = Integer.parseInt(lines[13].split(" ")[1]);
            this.mutationVariant = lines[14].split(" ")[1];
            this.genomeLength = Integer.parseInt(lines[15].split(" ")[1]);
            this.animalBehaviourType = lines[16].split(" ")[1];
            this.dayLength = Integer.parseInt(lines[17].split(" ")[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String sb = "SimulationConfigurator: " +
                "mapSizeX: " + this.mapSizeX +
                ", mapSizeY: " + this.mapSizeY +
                ", mapType: " + this.mapType +
                ", initialPlantCount: " + this.initialPlantCount +
                ", plantEnergy: " + this.plantEnergy +
                ", numberOfPlantsGrowingPerDay: " + this.numberOfPlantsGrowingPerDay +
                ", modeOfPlantGrowing: " + this.modeOfPlantGrowing +
                ", initialAnimalCount: " + this.initialAnimalCount +
                ", initialAnimalEnergy: " + this.initialAnimalEnergy +
                ", readyToReproduceEnergy: " + this.readyToReproduceEnergy +
                ", reproduceEnergyLoss: " + this.reproduceEnergyLoss +
                ", minimumMutationCount: " + this.minimumMutationCount +
                ", maximumMutationCount: " + this.maximumMutationCount +
                ", mutationVariant: " + this.mutationVariant +
                ", genomeLength: " + this.genomeLength +
                ", animalBehaviourType: " + this.animalBehaviourType;
        return sb;
    }
}


