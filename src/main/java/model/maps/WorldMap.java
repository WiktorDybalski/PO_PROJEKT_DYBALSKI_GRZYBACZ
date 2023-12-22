package model.maps;
import model.utils.*;

import java.util.HashMap;
import java.util.List;

public interface WorldMap {

    public HashMap<Vector2d, Tile> getMapTiles();
    public List<Animal> getAnimals();
    public List<Plant> getPlants();
    public Vector2d getLowerLeft();
    public Vector2d getUpperRight();
    public int getWidth();
    public int getHeight();
    public List<MapElement> getElements();

    public boolean canMoveTo(Vector2d position);
    public void move(List<Animal> animals);
    public void placeAnimal(Animal animal, Vector2d position);
    public MapObjects objectsAt(Vector2d position);
    public boolean isOccupied(Vector2d position);
    public void placePlant(Plant plant, Vector2d position);

    public void generateMap(List<Plant> plants, List<Animal> animals);
    public String toString();

    Tile getTile(Vector2d currentPosition);
}
