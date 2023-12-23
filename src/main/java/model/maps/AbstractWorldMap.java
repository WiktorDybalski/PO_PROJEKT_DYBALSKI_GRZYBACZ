package model.maps;

import RandomGenerators.RandomAnimalsGenerator;
import RandomGenerators.RandomPlantsGenerator;
import RandomGenerators.RandomPositionsGenerator;
import model.utils.*;
import presenters.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    /**
     * Lower, Left corner of the Map
     */
    private final Vector2d lowerLeft;

    /**
     * Upper, Right corner of the Map
     */
    private final Vector2d upperRight;
    /**
     * Initial number of plants
     * Initial parameter of the simulation
     */
    protected final int initialNumberOfPlants;

    /**
     * Initial number of Animals
     * Initial parameter of the simulation
     */
    protected final int initialNumberOfAnimals;
    /**
     * List of animals on the Map
     */
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
    public final int minimalReproductionEnergy;

    /**
     * Constructor of the Map
     */
    public AbstractWorldMap(Vector2d lowerLeft, Vector2d upperRight, int initialNumberOfPlants, int initialNumberOfAnimals, int minimalReproductionEnergy) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.initialNumberOfPlants = initialNumberOfPlants;
        this.initialNumberOfAnimals = initialNumberOfAnimals;
        this.minimalReproductionEnergy = minimalReproductionEnergy;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        generateMap(initialNumberOfPlants, initialNumberOfAnimals);
    }

    /**
     * Getters
     */

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
        if (this.lowerLeft.follows(position) || this.upperRight.precedes(position) ){
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
        RandomAnimalsGenerator animalsGenerator = new RandomAnimalsGenerator(amountOfAnimals, minimalReproductionEnergy, this);
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
        RandomPlantsGenerator plantsGenerator = new RandomPlantsGenerator(amountOfPlants, this);
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
            if (tile.getPlant()!=null){
                if(!tile.getAnimals().isEmpty()){
                    tile.getStrongestAnimal().eat(tile.getPlant());
                    tile.removePlant();
                }
            }
        }
        this.removeEatenPlants();
    }
    /**
     * The move method is responsible for the movement of all moving objects on the map
     * TODO: FIX
     */
    public void move() {
        for (Tile tile : mapTiles.values()) {
            for (Animal animal : tile.getAnimals()) {
                Vector2d oldPosition = animal.getPosition();
                Directions oldDirection = animal.getDirection();
                int oldActualActiveGeneIndex = animal.getActualActiveGenIndex();
                Vector2d newPosition = oldPosition.add(new Vector2d(Directions.toUnitVector(oldActualActiveGeneIndex).getX(), Directions.toUnitVector(oldActualActiveGeneIndex).getY() % getHeight()));
                if (newPositionOutOfLeftBound(newPosition)) {
                    newPosition = new Vector2d(upperRight.getX(), newPosition.getY());
                } else if (newPositionOutOfRightBound(newPosition)) {
                    newPosition = new Vector2d(lowerLeft.getX(), newPosition.getY());
                }
                mapTiles.get(oldPosition).removeAnimal(animal);
                animal.move(oldDirection, newPosition);
                mapTiles.get(newPosition).addAnimal(animal);
                animal.setActualActiveGenIndex(animal.getNextGene());
            }
        }
    }

    /**
     * The generateMap method is using in Map constructor to set all objects on the map
     */
    public void generateMap(int plants, int animals) {
        mapTiles = new HashMap<>();
        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            for (int j = lowerLeft.getY(); j <= upperRight.getY(); j++) {
                mapTiles.put(new Vector2d(i, j), new Tile(new Vector2d(i, j)));
            }
        }
        placePlants(plants);
        placeAnimals(animals);
    }

    /**
     * The removeEatenPlants method delete plants that has been eaten
     */

    public void removeEatenPlants() {
        plants.removeIf(Plant::getIsEaten);
    }

    /**
     * The toString method draw the Map using mapVisualizer
     */

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
