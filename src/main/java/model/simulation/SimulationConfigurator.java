package model.simulation;

import model.utils.Vector2d;

public class SimulationConfigurator {
    private final int numberOfDays;
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
        this.mapSizeX = 10;
        this.mapSizeY = 10;
        this.mapType = "WorldMap";
        this.initialAnimalCount = 10;
        this.initialPlantCount = 30;
        this.plantEnergy = 2;
        this.numberOfPlantsGrowingPerDay = 10;
        this.modeOfPlantGrowing = "Random";
        this.initialAnimalEnergy = 10;
        this.readyToReproduceEnergy = 10;
        this.reproduceEnergyLoss = 10;
        this.minimumMutationCount = 1;
        this.maximumMutationCount = 3;
        this.mutationVariant = "Random";
        this.genomeLength = 8;
        this.animalBehaviourType = "Random";
        this.numberOfDays = 5;
    }

    public SimulationConfigurator( SimulationConfigurator other){
        this.mapSizeX = other.getMapSize().getX();
        this.mapSizeY = other.getMapSize().getY();
        this.mapType = other.getMapType();
        this.initialAnimalCount = other.getInitialAnimalCount();
        this.initialPlantCount = other.getInitialPlantCount();
        this.plantEnergy = other.getPlantEnergy();
        this.numberOfPlantsGrowingPerDay = other.getNumberOfPlantsGrowingPerDay();
        this.modeOfPlantGrowing = other.getModeOfPlantGrowing();
        this.initialAnimalEnergy = other.getInitialAnimalEnergy();
        this.readyToReproduceEnergy = other.getReadyToReproduceEnergy();
        this.reproduceEnergyLoss = other.getReproduceEnergyLoss();
        this.minimumMutationCount = other.getMinimumMutationCount();
        this.maximumMutationCount = other.getMaximumMutationCount();
        this.mutationVariant = other.getMutationVariant();
        this.genomeLength = other.getGenomeLength();
        this.animalBehaviourType = other.getAnimalBehaviourType();
        this.numberOfDays = other.getNumberOfDays();
    }

    public Vector2d getMapSize() {
        return new Vector2d(mapSizeX, mapSizeY);
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public int getInitialPlantCount() {
        return initialPlantCount;
    }

    public void setInitialPlantCount(int initialPlantCount) {
        this.initialPlantCount = initialPlantCount;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public void setPlantEnergy(int plantEnergy) {
        this.plantEnergy = plantEnergy;
    }

    public int getNumberOfPlantsGrowingPerDay() {
        return numberOfPlantsGrowingPerDay;
    }

    public void setNumberOfPlantsGrowingPerDay(int numberOfPlantsGrowingPerDay) {
        this.numberOfPlantsGrowingPerDay = numberOfPlantsGrowingPerDay;
    }

    public String getModeOfPlantGrowing() {
        return modeOfPlantGrowing;
    }

    public void setModeOfPlantGrowing(String modeOfPlantGrowing) {
        this.modeOfPlantGrowing = modeOfPlantGrowing;
    }

    public int getInitialAnimalCount() {
        return initialAnimalCount;
    }

    public void setInitialAnimalCount(int initialAnimalCount) {
        this.initialAnimalCount = initialAnimalCount;
    }

    public int getInitialAnimalEnergy() {
        return initialAnimalEnergy;
    }

    public void setInitialAnimalEnergy(int initialAnimalEnergy) {
        this.initialAnimalEnergy = initialAnimalEnergy;
    }

    public int getReadyToReproduceEnergy() {
        return readyToReproduceEnergy;
    }

    public void setReadyToReproduceEnergy(int readyToReproduceEnergy) {
        this.readyToReproduceEnergy = readyToReproduceEnergy;
    }

    public int getReproduceEnergyLoss() {
        return reproduceEnergyLoss;
    }

    public void setReproduceEnergyLoss(int reproduceEnergyLoss) {
        this.reproduceEnergyLoss = reproduceEnergyLoss;
    }

    public int getMinimumMutationCount() {
        return minimumMutationCount;
    }

    public void setMinimumMutationCount(int minimumMutationCount) {
        this.minimumMutationCount = minimumMutationCount;
    }

    public int getMaximumMutationCount() {
        return maximumMutationCount;
    }

    public void setMaximumMutationCount(int maximumMutationCount) {
        this.maximumMutationCount = maximumMutationCount;
    }

    public String getMutationVariant() {
        return mutationVariant;
    }

    public void setMutationVariant(String mutationVariant) {
        this.mutationVariant = mutationVariant;
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public void setGenomeLength(int genomeLength) {
        this.genomeLength = genomeLength;
    }

    public String getAnimalBehaviourType() {
        return animalBehaviourType;
    }

    public void setAnimalBehaviourType(String animalBehaviourType) {
        this.animalBehaviourType = animalBehaviourType;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setMapSize(int x, int y) {
        this.mapSizeX = x;
        this.mapSizeY = y;
    }

    //for CSV file like: mapSizeX,mapSizeY,mapType,initialPlantCount,plantEnergy,numberOfPlantsGrowingPerDay,modeOfPlantGrowing,initialAnimalCount,initialAnimalEnergy,readyToReproduceEnergy,reproduceEnergyLoss,minimumMutationCount,maximumMutationCount,mutationVariant,genomeLength
    public static SimulationConfigurator fromCSV(String csvString) {
        SimulationConfigurator configurator = new SimulationConfigurator();

        String[] values = csvString.split(",");

        if (values.length != 15) {
            throw new IllegalArgumentException("Invalid CSV string format");
        }

        configurator.setMapSize(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        configurator.setMapType(values[2]);
        configurator.setInitialPlantCount(Integer.parseInt(values[3]));
        configurator.setPlantEnergy(Integer.parseInt(values[4]));
        configurator.setNumberOfPlantsGrowingPerDay(Integer.parseInt(values[5]));
        configurator.setModeOfPlantGrowing(values[6]);
        configurator.setInitialAnimalCount(Integer.parseInt(values[7]));
        configurator.setInitialAnimalEnergy(Integer.parseInt(values[8]));
        configurator.setReadyToReproduceEnergy(Integer.parseInt(values[9]));
        configurator.setReproduceEnergyLoss(Integer.parseInt(values[10]));
        configurator.setMinimumMutationCount(Integer.parseInt(values[11]));
        configurator.setMaximumMutationCount(Integer.parseInt(values[12]));
        configurator.setMutationVariant(values[13]);
        configurator.setGenomeLength(Integer.parseInt(values[14]));

        return configurator;
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


