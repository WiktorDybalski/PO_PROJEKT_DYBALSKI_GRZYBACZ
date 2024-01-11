package model.simulation;

import model.maps.WorldMap;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, String message);
}
