package model.maps;

import model.RandomPositionsGenerator;
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
    private final int initialNumberOfPlants;

    /**
     * Initial number of Animals
     * Initial parameter of the simulation
     */
    private final int initialNumberOfAnimals;
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

    MapVisualizer mapVisualizer = new MapVisualizer((WorldMap) this);

    /**
     * Constructor of the Map
     */

    public AbstractWorldMap(Vector2d lowerLeft, Vector2d upperRight, int initialNumberOfPlants, int initialNumberOfAnimals) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.initialNumberOfPlants = initialNumberOfPlants;
        this.initialNumberOfAnimals = initialNumberOfAnimals;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        generateMap(plants, animals);
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
        MapObjects objects = objectsAt(position);
        return objects != null && objects.isOccupied();
    }

    /**
     * The placeAnimal method add animal to the Map
     */

    public void placeAnimal(Animal animal, Vector2d position) {
        mapTiles.get(position).addAnimal(animal);
    }

    /**
     * The placeAnimals method create random positions for Animals and using for to add Animal to the Map using placeAnimal
     */
    public void placeAnimals(List<Animal> animals) {
        RandomPositionsGenerator randomPositionsGenerator = new RandomPositionsGenerator(upperRight.getX(), upperRight.getY(), initialNumberOfAnimals);
        List<Vector2d> positions = randomPositionsGenerator.getResult();
        for (int i = 0; i < animals.size(); i++) {
            placeAnimal(animals.get(i), positions.get(i));
        }
    }

    /**
     * The placePlant method set Plant on the Map
     */

    public void placePlant(Plant plant, Vector2d position) {
        mapTiles.get(position).setPlant(plant);
    }

    /**
     * The placePlants method create random positions for Plants and using for to set Plants on the Map using placePlant
     */

    public void placePlants(List<Plant> plants) {
        RandomPositionsGenerator randomPositionsGenerator = new RandomPositionsGenerator(upperRight.getX(), upperRight.getY(), initialNumberOfPlants);
        List<Vector2d> positions = randomPositionsGenerator.getResult();
        for (int i = 0; i < plants.size(); i++) {
            placePlant(plants.get(i), positions.get(i));
        }
    }

    /**
     * The move method is responsible for the movement of all moving objects on the map
     */
    public void move(List<Animal> animals) {
        for (Animal animal : animals) {
            Vector2d oldPosition = animal.getPosition();
            Vector2d newPosition = oldPosition.add(Directions.toUnitVector(animal.getGenotype().getGene((animal.getActualActiveGenIndex()))));
            int oldDirectionIndex = Directions.getDirectionIndex(animal.getDirection());
            Directions newDirection = Directions.getDirectionName((oldDirectionIndex + animal.getGenotype().getGene(animal.getActualActiveGenIndex())) % 8);
            animal.move(newDirection, newPosition);
        }
    }

    /**
     * The generateMap method is using in Map constructor to set all objects on the map
     */
    public void generateMap(List<Plant> plants, List<Animal> animals) {
        placePlants(plants);
        placeAnimals(animals);
    }

    /**
     * The toString method draw the Map using mapVisualizer
     */
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
