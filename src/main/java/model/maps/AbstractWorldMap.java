package model.maps;

import model.simulation.MapChangeListener;
import model.simulation.SimulationConfigurator;
import model.utils.*;
import model.utils.RandomGenerators.RandomAnimalsGenerator;
import model.utils.RandomGenerators.RandomPlantsGenerator;
import presenters.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap, MapChangeListener {

    /**
     * Lower, Left corner of the Map
     */
    protected final Vector2d lowerLeft;
    /**
     * Upper, Right corner of the Map
     */
    protected final Vector2d upperRight;
    protected SimulationConfigurator config;
    protected List<Animal> animals;
    /**
     * List of plants on the Map
     */
    protected List<Plant> plants;
    /**
     * Map: key - position of each single tile, value: a tile
     */
    protected HashMap<Vector2d, Tile> mapTiles;
    /**
     * List of Tiles without plant
     */

    protected List<Vector2d> freePositions;
    /**
     * mapVisualizer to draw a map.
     * One day it will be replaced by GUI
     */
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);
    private List<MapChangeListener> listeners = new ArrayList<>();
    private int currentDay;

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
        this.freePositions = new ArrayList<>();
        generateMap();
    }

    /**
     * Getters
     */

    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0, 0), new Vector2d(this.getWidth() - 1, this.getHeight() - 1));

    }

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

    @Override
    public void addObserver(MapChangeListener observer) {
        listeners.add(observer);
    }

    @Override
    public void removeObserver(MapChangeListener observer) {

        listeners.remove(observer);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {

    }

    protected synchronized void mapChanged(String message) {
        for (MapChangeListener observer : listeners) {
            observer.mapChanged(this, message);
        }
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
        if (mapTiles.get(position) != null) {
            return mapTiles.get(position).getObjects();
        }
        return null;
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
        animals.add(animal);
    }

    /**
     * The placeAnimals method create random positions for Animals and using for to add Animal to the Map using placeAnimal
     */
    public void placeAnimals(int amountOfAnimals) {
        RandomAnimalsGenerator animalsGenerator = new RandomAnimalsGenerator(amountOfAnimals, config.getInitialAnimalEnergy(), this);
        animalsGenerator.generateAnimals(amountOfAnimals);
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
        RandomPlantsGenerator plantsGenerator = new RandomPlantsGenerator(this.config.getPlantEnergy(), this);
        plantsGenerator.generatePlants(amountOfPlants, freePositions);
        List<Plant> plants = plantsGenerator.getPlants();
        for (Plant plant : plants) {
            placePlant(plant, plant.getPosition());
            freePositions.remove(plant.getPosition());
        }
    }

    /**
     * The eat method is responsible for the eating of all animals on the map
     */
    public void eat() {
        for (Tile tile : mapTiles.values()) {
            if (tile.getPlant() != null) {
                if (!tile.getAnimals().isEmpty()) {
                    tile.getStrongestAnimal().eat(tile.getPlant());
                    tile.removePlant();
                    freePositions.add(tile.getPosition());
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
    private void growAnimals() {
        for (Animal animal : animals) {
            if (animal.getIsDead()) {
                continue;
            }
            animal.grow();
        }
    }

    /**
     * The move method is responsible for the movement of all moving objects on the map
     */
    public void move() {
        for (Animal animal : animals) {
            if (animal.getIsDead()) {
                continue;
            }
            Vector2d oldPosition = animal.getPosition();
            Directions oldDirection = animal.getDirection();
            int oldActualActiveGeneIndex = animal.getActualActiveGenIndex();
            int activeGene = animal.getGenotype().getGene(oldActualActiveGeneIndex);
            Vector2d vector = Directions.toUnitVector(activeGene);
            Vector2d newPosition = oldPosition.add(vector);
            if (newPositionOutOfLeftBound(newPosition)) {
                newPosition = new Vector2d(upperRight.getX(), newPosition.getY());
            } else if (newPositionOutOfRightBound(newPosition)) {
                newPosition = new Vector2d(lowerLeft.getX(), newPosition.getY());
            }
            if (canMoveTo(newPosition)) {
                mapTiles.get(oldPosition).removeAnimal(animal);
                animal.move(oldDirection, newPosition);
                animal.setDirection(Directions.fromUnitVector(vector));
                mapTiles.get(newPosition).addAnimal(animal);
            }
            int newActiveGenIndex=(oldActualActiveGeneIndex+1)%this.config.getGenomeLength();
            animal.setActualActiveGenIndex(newActiveGenIndex);
            animal.decreaseEnergy();
        }
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
                    Animal child = parent1.reproduce(parent2, currentDay, config.getReproduceEnergyLoss(), config.getMutationVariant()=="Random");
                    placeAnimal(child, child.getPosition());
                    this.animals.add(child);
                }
            }
        }
    }

    /**
     * The generateMap method is using in Map constructor to set all objects on the map
     */
    public void generateMap() {
        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            for (int j = lowerLeft.getY(); j <= upperRight.getY(); j++) {
                Tile tile = new Tile(new Vector2d(i, j));
                mapTiles.put(new Vector2d(i, j), tile);
                freePositions.add(tile.getPosition());
            }
        }
        placePlants(config.getInitialPlantCount());
        placeAnimals(config.getInitialAnimalCount());
    }

    /**
     * The firstDay method is responsible for the first day of the World
     */
    public void firstDay() {
        //System.out.println(this);
        this.eat();
        this.reproduce();
        this.placePlants(config.getNumberOfPlantsGrowingPerDay());
        this.removeDeadAnimals();
        this.currentDay++;
        this.move();
        mapChanged("First day left");
    }

    /**
     * The dailyUpdate method is responsible for the daily update of the map
     */
    public void dailyUpdate() {
        this.growAnimals();
        this.removeEatenPlants();
        this.move();
        this.eat();
        this.reproduce();
        this.placePlants(config.getNumberOfPlantsGrowingPerDay());
        this.removeDeadAnimals();
        this.currentDay++;
        mapChanged(currentDay + " day left");
    }

    /**
     * The toString method draw the Map using mapVisualizer
     */
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
