package model.maps;

import model.utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap{
    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private List<Animal> animals;
    private List<Plant> plants;
    private HashMap<Vector2d, Tile> mapTiles;

    public AbstractWorldMap(Vector2d lowerLeft, Vector2d upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.mapTiles = new HashMap<>();
        generateMap();
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

    public abstract boolean canMoveTo(Vector2d position);

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

    public abstract void drawMap();
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
