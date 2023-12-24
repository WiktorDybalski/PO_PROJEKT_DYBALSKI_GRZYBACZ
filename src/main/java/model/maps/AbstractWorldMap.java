package model.maps;

import RandomGenerators.RandomAnimalsGenerator;
import RandomGenerators.RandomPlantsGenerator;
import model.simulation.SimulationConfigurator;
import model.utils.*;
import presenters.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {

    private SimulationConfigurator config;

    private int currentDay;
    /**
     * Lower, Left corner of the Map
     */
    private final Vector2d lowerLeft;

    /**
     * Upper, Right corner of the Map
     */
    private final Vector2d upperRight;

    private List<Animal> animals;

    /**
     * List of plants on the Map
     */
    private List<Plant> plants;

    /**
     * Map: key - position of each single tile, value: a tile
     */
    private HashMap<Vector2d, Tile> mapTiles;

    /**
     * mapVisualizer to draw a map.
     * One day it will be replaced by GUI
     */
    MapVisualizer mapVisualizer = new MapVisualizer(this);

    /**
     * Constructor of the Map
     */
    public AbstractWorldMap(SimulationConfigurator config) {
        this.config = config;
        this.currentDay = 0;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = config.getMapSize();
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.mapTiles = new HashMap<>();
        this.generateMap();
    }

    /**
     * Getters
     */

    public SimulationConfigurator getConfig() {
        return config;
    }
    public int getCurrentDay() {
        return currentDay;
    }

    public HashMap<Vector2d, Tile> getMapTiles() {
        return mapTiles;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public int getWidth() {
        return upperRight.getX() - lowerLeft.getX() + 1;
    }

    public int getHeight() {
        return upperRight.getY() - lowerLeft.getY() + 1;
    }

    public List<MapElement> getElements() {
        List<MapElement> elements = new ArrayList<>();
        elements.addAll(animals);
        elements.addAll(plants);
        return elements;
    }

    public Tile getTile(Vector2d position) {
        return mapTiles.get(position);
    }

    /**
     * The canMoveTo method checks if the Animal can move to
     *
     * @param position The position the object wants to get to
     * @return bool value is it possible to move there
     */
    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.getY() <= position.getY() && upperRight.getY() >= position.getY();
    }

    /**
     * The newPositionOutOfBound method checks if the Animal's new position is the Left bound of the Map
     *
     * @param position The position the object wants to get to
     * @return bool value is it possible to move there
     */

    public boolean newPositionOutOfLeftBound(Vector2d position) {
        return position.getX() < lowerLeft.getX() && canMoveTo(position);
    }

    /**
     * The newPositionOutOfBound method checks if the Animal's new position is the Right bound of the Map
     *
     * @param position The position the object wants to get to
     * @return bool value is it possible to move there
     */
    public boolean newPositionOutOfRightBound(Vector2d position) {
        return position.getX() > upperRight.getX() && canMoveTo(position);
    }

    /**
     * The objectsAt method
     *
     * @param position The position the object wants to get to
     * @return bool value is it possible to move there
     */
    public MapObjects objectsAt(Vector2d position) {
        return mapTiles.get(position).getObjects();
    }

    /**
     * The isOccupied method checks whether the MapObject is in position
     *
     * @return bool value
     */

    public boolean isOccupied(Vector2d position) {
        if (this.lowerLeft.follows(position) || this.upperRight.precedes(position)) {
            return false;
        }
        MapObjects objects = objectsAt(position);
        if (objects == null) return false;
        return objects.isOccupied();
    }

    /**
     * The placeAnimal method add animal to the Map
     */

    public void placeAnimal(Animal animal, Vector2d position) {
        mapTiles.get(position).addAnimal(animal);
        if (!animals.contains(animal)) //TODO: check if it is necessary
            animals.add(animal);
    }

    /**
     * The placeAnimals method create random positions for Animals and using for to add Animal to the Map using placeAnimal
     */
    public void placeAnimals(int amountOfAnimals) {
        RandomAnimalsGenerator animalsGenerator = new RandomAnimalsGenerator(amountOfAnimals, config.getInitialAnimalEnergy(), config.getReproduceEnergyLoss(), this);
        List<Animal> animals = animalsGenerator.getAnimals();
        for (Animal animal : animals) {
            placeAnimal(animal, animal.getPosition());
        }
    }

    /**
     * The placePlant method set Plant on the Map
     */
    public void placePlant(Plant plant, Vector2d position) {
        mapTiles.get(position).setPlant(plant);
        if (!plants.contains(plant))
            plants.add(plant);
    }

    /**
     * The placePlants method create random positions for Plants and using for to set Plants on the Map using placePlant
     */

    public void placePlants(int amountOfPlants) {
        RandomPlantsGenerator plantsGenerator = new RandomPlantsGenerator(amountOfPlants, this.config.getPlantEnergy(), this);
        List<Plant> plants = plantsGenerator.getPlants();
        for (Plant plant : plants) {
            placePlant(plant, plant.getPosition());
        }
    }

    /**
     * The eat method is responsible for the eating of all animals on the map
     * TODO: Update for poisoned map
     */
    public void eat() {
        for (Tile tile : mapTiles.values()) {
            if (tile.getPlant() != null) {
                if (!tile.getAnimals().isEmpty()) {
                    tile.getStrongestAnimal().eat(tile.getPlant());
                    tile.removePlant();
                }
            }
        }
        this.removeEatenPlants();
    }

    public void removeDeadAnimals() {
        for (Tile tile : mapTiles.values()) {
            tile.removeDeadAnimalsFromTile();
        }
    }

    /**
     * The move method is responsible for the movement of all moving objects on the map
     * TODO: FIX
     */
    public void move() {
        for (Animal animal : animals) {
            Vector2d oldPosition = animal.getPosition();
            Directions oldDirection = animal.getDirection();
            int oldActualActiveGeneIndex = animal.getActualActiveGenIndex();
            Vector2d vector = new Vector2d(Directions.toUnitVector(oldActualActiveGeneIndex).getX(), Directions.toUnitVector(oldActualActiveGeneIndex).getY());
            Vector2d newPosition = oldPosition.add(vector);
            if (newPositionOutOfLeftBound(newPosition)) {
                newPosition = new Vector2d(upperRight.getX(), newPosition.getY());
            } else if (newPositionOutOfRightBound(newPosition)) {
                newPosition = new Vector2d(lowerLeft.getX(), newPosition.getY());
            }
            if(canMoveTo(newPosition)) {
                mapTiles.get(oldPosition).removeAnimal(animal);
                animal.move(oldDirection, newPosition);
                mapTiles.get(newPosition).addAnimal(animal);
            }
            animal.setActualActiveGenIndex(animal.getNextGene());
            animal.decreaseEnergy();
        }
    }

    /**
     * The generateMap method is using in Map constructor to set all objects on the map
     */
    public void generateMap() {
        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            for (int j = lowerLeft.getY(); j <= upperRight.getY(); j++) {
                mapTiles.put(new Vector2d(i, j), new Tile(new Vector2d(i, j)));
            }
        }
        placePlants(config.getInitialPlantCount());
        placeAnimals(config.getInitialAnimalCount());
    }

    /**
     * The removeEatenPlants method delete plants that has been eaten
     */

    public void removeEatenPlants() {
        plants.removeIf(Plant::getIsEaten);
    }

    public void reproduce() {
        for (Tile tile : mapTiles.values()) {
            ArrayList<Animal> strongestAnimals = tile.getStrongestAnimals();
            if (strongestAnimals.size() == 2) {

                Animal parent1 = strongestAnimals.get(0);
                Animal parent2 = strongestAnimals.get(1);
                if (parent1.canReproduce() && parent2.canReproduce()) {
                    Animal child = parent1.reproduce(parent2, currentDay, config.getReproduceEnergyLoss());
                }
            }
        }
    }
    /**
     * The dailyUpdate method is responsible for the daily update of the map
     */
    public void dailyUpdate() {
        System.out.println(this);
        this.removeEatenPlants();
        this.move();
        this.eat();
        this.reproduce();
        this.placePlants(config.getNumberOfPlantsGrowingPerDay());
        this.removeDeadAnimals();
        this.currentDay++;
    }

    /**
     * The toString method draw the Map using mapVisualizer
     */
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
