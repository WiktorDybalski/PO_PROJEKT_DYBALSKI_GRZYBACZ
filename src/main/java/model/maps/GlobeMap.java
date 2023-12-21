package model.maps;

import model.utils.Animal;
import model.utils.Vector2d;

public class GlobeMap extends AbstractWorldMap {
    public GlobeMap(int width, int height) {
        super(new Vector2d(0, 0), new Vector2d(width, height));
    }

    //needs to be implemented
    public boolean canMoveTo(Vector2d position) {
        // Implements logic
        return true;
    }

    //needs to be implemented
    public void move(Animal animal) {
        // Implements logic
    }

    //needs to be implemented
    public void generateMap() {
        // Implements logic
    }

    //needs to be implemented
    public void updateMap() {
        // Implements logic
    }

    //needs to be implemented
    public void drawMap() {
        // Implements logic
    }
}