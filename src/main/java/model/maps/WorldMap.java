package model.maps;

import model.simulation.MapChangeListener;
import model.simulation.SimulationConfigurator;
import model.utils.*;

import java.util.HashMap;
import java.util.List;

public interface WorldMap {
    HashMap<Vector2d, Tile> getMapTiles();

    int getCurrentDay();

    List<Animal> getAnimals();

    List<Plant> getPlants();

    int getWidth();

    int getHeight();

    boolean canMoveTo(Vector2d position);

    void move();

    void placeAnimal(Animal animal, Vector2d position);

    MapObjects objectsAt(Vector2d position);

    boolean isOccupied(Vector2d position);

    void placePlant(Plant plant, Vector2d position);

    void generateMap();

    String toString();

    Tile getTile(Vector2d currentPosition);

    void firstDay();

    void dailyUpdate();

    Boundary getCurrentBounds();

    SimulationConfigurator getConfig();

    void addObserver(MapChangeListener observer);

}
