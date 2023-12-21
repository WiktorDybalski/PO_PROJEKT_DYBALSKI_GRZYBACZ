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
    public void move(Animal animal);
    public boolean placeAnimal(Animal animal);
    public MapObjects objectsAt(Vector2d position);
    public boolean isOccupied(Vector2d position);
    public boolean placePlant(Plant plant);

    public void generateMap();
    public void updateMap();
    public void drawMap();

}
