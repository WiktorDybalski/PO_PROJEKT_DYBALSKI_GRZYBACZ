package model.maps;

import model.utils.*;
import presenters.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap{
    /**
     * Lower, Left corner of the Map
     */
    private Vector2d lowerLeft;

    /**
     * Upper, Right corner of the Map
     */
    private Vector2d upperRight;

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

    public AbstractWorldMap(Vector2d lowerLeft, Vector2d upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.mapTiles = new HashMap<>();
        generateMap();
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
     * @param position The position the object wants to get to
     * @return bool value is it possible to move there
     */

    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.getY() <= position.getY() && upperRight.getY() >= position.getY();
    }
    public abstract void move(Animal animal);


    //needs to be implemented
    public boolean placeAnimal(Animal animal){
        return false;
    }

    //needs to be implemented
    public MapObjects objectsAt(Vector2d position){
        return null;
    }

    //needs to be checked
    public boolean isOccupied(Vector2d position) {
        MapObjects objects = objectsAt(position);
        return objects != null && objects.isOccupied();
    }

    //needs to be implemented
    public boolean placePlant(Plant plant){
        return false;
    }

    public abstract void generateMap();

    public abstract void updateMap();

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}

//private void moveAnimals() {
//    for (Animal animal : objects.getAnimals()) {
//        Vector2d oldPosition = animal.getPosition();
//        Vector2d newPosition = oldPosition.add(Directions.toUnitVector(animal.getGenotype().getGene((animal.getActualActiveGenIndex()))));
//        int oldDirectionIndex = Directions.getDirectionIndex(animal.getDirection());
//        Directions newDirection = Directions.getDirectionName((oldDirectionIndex + animal.getGenotype().getGene(animal.getActualActiveGenIndex())) % 8);
//        animal.move(newDirection, newPosition);
//    }
//}

//        if (objects.getAnimals()==null || objects.getPlant()!=null) {
//        objects.growPlant();
//        }
//                else
//        }
