import model.maps.WorldMap;
import model.utils.Animal;
import model.utils.Directions;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Directions> listOfMoves;
    private List<Vector2d> listOfPositions;
//    TODO
//      Configure simulation class

    private WorldMap worldMap;

    private List<Animal> animals = new ArrayList<>();

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Directions> listOfMoves, List<Vector2d> listOfPositions, WorldMap worldMap) {
        this.listOfPositions = listOfPositions;
        this.listOfMoves = listOfMoves;
        if (listOfPositions.isEmpty()) {
            throw new IllegalArgumentException("No animals");
        }
        this.worldMap = worldMap;
    }


    public void run() {
        worldMap.move(animals);
    }
}
