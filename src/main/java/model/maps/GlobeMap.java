package model.maps;

import model.simulation.SimulationConfigurator;
import model.utils.Animal;
import model.utils.MapObjects;
import model.utils.Plant;
import model.utils.Vector2d;

import java.util.List;

public class GlobeMap extends AbstractWorldMap {
    /**
     * Constructor of the Map
     */

    public GlobeMap(SimulationConfigurator config) {
        super(config);
    }

    /**
     * The move method is responsible for the movement of all moving objects on the map
     */
    public void move(List<Animal> animals) {
        super.move();
    }
}