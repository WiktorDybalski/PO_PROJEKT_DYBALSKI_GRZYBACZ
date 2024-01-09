package model.maps;
import model.simulation.SimulationConfigurator;
import model.utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface WorldMap {

    public UUID getID();
    public HashMap<Vector2d, Tile> getMapTiles();
    SimulationConfigurator getConfig();
    public int getCurrentDay();
    public List<Animal> getAnimals();
    public List<Plant> getPlants();
    public Vector2d getLowerLeft();
    public Vector2d getUpperRight();
    public int getWidth();
    public int getHeight();
    public List<MapElement> getElements();
    public boolean canMoveTo(Vector2d position);
    public void move();
    public void placeAnimal(Animal animal, Vector2d position);
    public MapObjects objectsAt(Vector2d position);
    public boolean isOccupied(Vector2d position);
    public void placePlant(Plant plant, Vector2d position);
    public default void generateMap(List<Plant> plants, List<Animal> animals){}
    public String toString();
    public Tile getTile(Vector2d currentPosition);
    public void firstDay();
    public void dailyUpdate();
}
