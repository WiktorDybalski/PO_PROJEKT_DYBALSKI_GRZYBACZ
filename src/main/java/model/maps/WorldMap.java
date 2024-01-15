package model.maps;
import model.simulation.MapChangeListener;
import model.simulation.SimulationConfigurator;
import model.utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface WorldMap {
    public HashMap<Vector2d, Tile> getMapTiles();
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
    public void generateMap();
    public String toString();
    public Tile getTile(Vector2d currentPosition);
    public void firstDay();
    public void dailyUpdate();
    public Boundary getCurrentBounds();

    public SimulationConfigurator getConfig();

    public void addObserver(MapChangeListener observer);
    public void removeObserver(MapChangeListener observer);

}
