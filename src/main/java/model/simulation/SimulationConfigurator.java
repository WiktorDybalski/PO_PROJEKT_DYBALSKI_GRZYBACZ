package model.simulation;

import model.utils.Vector2d;

import java.io.IOException;

public class SimulationConfigurator {
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

    public SimulationConfigurator() {
        this.mapSizeX = 20;
        this.mapSizeY = 20;
        this.mapType = "GlobeMap";
        this.initialPlantCount = 20;
        this.plantEnergy = 10;
        this.numberOfPlantsGrowingPerDay = 5;
        this.modeOfPlantGrowing = "Random";
        this.initialAnimalCount = 10;
        this.initialAnimalEnergy = 100;
        this.readyToReproduceEnergy = 200;
        this.reproduceEnergyLoss = 100;
        this.minimumMutationCount = 1;
        this.maximumMutationCount = 3;
        this.mutationVariant = "Random";
        this.genomeLength = 32;
        this.animalBehaviourType = "Random";
    }

    public void SimulationConfiguratorFromInput() throws IOException {
        System.out.println("SimulationConfiguratorFromInput");
        System.out.println("If you wanna use default values enter y: ");
        String input = System.in.toString();
        if (input.equals("y")) {
            return;
        }
        System.out.println("mapSizeX: ");
        mapSizeX=(int)System.in.read();
        System.out.println("mapSizeY: ");
        mapSizeY=(int)System.in.read();
        System.out.println("mapType: ");
        mapType=System.in.toString();
        System.out.println("initialPlantCount: ");
        initialPlantCount=(int)System.in.read();
        System.out.println("plantEnergy: ");
        plantEnergy=(int)System.in.read();
        System.out.println("numberOfPlantsGrowingPerDay: ");
        numberOfPlantsGrowingPerDay=(int)System.in.read();
        System.out.println("modeOfPlantGrowing: ");
        modeOfPlantGrowing=System.in.toString();
        System.out.println("initialAnimalCount: ");
        initialAnimalCount=(int)System.in.read();
        System.out.println("initialAnimalEnergy: ");
        initialAnimalEnergy=(int)System.in.read();
        System.out.println("readyToReproduceEnergy: ");
        readyToReproduceEnergy=(int)System.in.read();
        System.out.println("reproduceEnergyLoss: ");
        reproduceEnergyLoss=(int)System.in.read();
        System.out.println("minimumMutationCount: ");
        minimumMutationCount=(int)System.in.read();
        System.out.println("maximumMutationCount: ");
        maximumMutationCount=(int)System.in.read();
        System.out.println("mutationVariant: ");
        mutationVariant=System.in.toString();
        System.out.println("genomeLength: ");
        genomeLength=(int)System.in.read();
        System.out.println("animalBehaviourType: ");
        animalBehaviourType=System.in.toString();
    }
    public Vector2d getMapSize() {
        return new Vector2d(mapSizeX, mapSizeY);
    }

    public String getMapType() {
        return mapType;
    }

    public int getInitialPlantCount() {
        return initialPlantCount;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public int getNumberOfPlantsGrowingPerDay() {
        return numberOfPlantsGrowingPerDay;
    }

    public String getModeOfPlantGrowing() {
        return modeOfPlantGrowing;
    }

    public int getInitialAnimalCount() {
        return initialAnimalCount;
    }

    public int getInitialAnimalEnergy() {
        return initialAnimalEnergy;
    }

    public int getReadyToReproduceEnergy() {
        return readyToReproduceEnergy;
    }

    public int getReproduceEnergyLoss() {
        return reproduceEnergyLoss;
    }

    public int getMinimumMutationCount() {
        return minimumMutationCount;
    }

    public int getMaximumMutationCount() {
        return maximumMutationCount;
    }

    public String getMutationVariant() {
        return mutationVariant;
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public String getAnimalBehaviourType() {
        return animalBehaviourType;
    }

    public void setMapSize(int x, int y) {
        this.mapSizeX = x;
        this.mapSizeY = y;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public void setInitialPlantCount(int initialPlantCount) {
        this.initialPlantCount = initialPlantCount;
    }

    public void setPlantEnergy(int plantEnergy) {
        this.plantEnergy = plantEnergy;
    }

    public void setNumberOfPlantsGrowingPerDay(int numberOfPlantsGrowingPerDay) {
        this.numberOfPlantsGrowingPerDay = numberOfPlantsGrowingPerDay;
    }

    public void setModeOfPlantGrowing(String modeOfPlantGrowing) {
        this.modeOfPlantGrowing = modeOfPlantGrowing;
    }

    public void setInitialAnimalCount(int initialAnimalCount) {
        this.initialAnimalCount = initialAnimalCount;
    }

    public void setInitialAnimalEnergy(int initialAnimalEnergy) {
        this.initialAnimalEnergy = initialAnimalEnergy;
    }

    public void setReadyToReproduceEnergy(int readyToReproduceEnergy) {
        this.readyToReproduceEnergy = readyToReproduceEnergy;
    }

    public void setReproduceEnergyLoss(int reproduceEnergyLoss) {
        this.reproduceEnergyLoss = reproduceEnergyLoss;
    }

    public void setMinimumMutationCount(int minimumMutationCount) {
        this.minimumMutationCount = minimumMutationCount;
    }

    public void setMaximumMutationCount(int maximumMutationCount) {
        this.maximumMutationCount = maximumMutationCount;
    }

    public void setMutationVariant(String mutationVariant) {
        this.mutationVariant = mutationVariant;
    }

    public void setGenomeLength(int genomeLength) {
        this.genomeLength = genomeLength;
    }

    public void setAnimalBehaviourType(String animalBehaviourType) {
        this.animalBehaviourType = animalBehaviourType;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimulationConfigurator: ");
        sb.append("mapSizeX: ").append(this.mapSizeX);
        sb.append(", mapSizeY: ").append(this.mapSizeY);
        sb.append(", mapType: ").append(this.mapType);
        sb.append(", initialPlantCount: ").append(this.initialPlantCount);
        sb.append(", plantEnergy: ").append(this.plantEnergy);
        sb.append(", numberOfPlantsGrowingPerDay: ").append(this.numberOfPlantsGrowingPerDay);
        sb.append(", modeOfPlantGrowing: ").append(this.modeOfPlantGrowing);
        sb.append(", initialAnimalCount: ").append(this.initialAnimalCount);
        sb.append(", initialAnimalEnergy: ").append(this.initialAnimalEnergy);
        sb.append(", readyToReproduceEnergy: ").append(this.readyToReproduceEnergy);
        sb.append(", reproduceEnergyLoss: ").append(this.reproduceEnergyLoss);
        sb.append(", minimumMutationCount: ").append(this.minimumMutationCount);
        sb.append(", maximumMutationCount: ").append(this.maximumMutationCount);
        sb.append(", mutationVariant: ").append(this.mutationVariant);
        sb.append(", genomeLength: ").append(this.genomeLength);
        sb.append(", animalBehaviourType: ").append(this.animalBehaviourType);
        return sb.toString();
    }
}


