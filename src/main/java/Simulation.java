import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<MoveDirection> listOfMoves;
    private List<Vector2d> listOfPositions;
    private final WorldMap<Animal, Vector2d> worldMap;

    private List<Animal> animals = new ArrayList<>();

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<MoveDirection> listOfMoves, List<Vector2d> listOfPositions, WorldMap<Animal, Vector2d> worldMap) {
        this.listOfPositions = listOfPositions;
        this.listOfMoves = listOfMoves;
        if (listOfPositions.isEmpty()) {
            throw new IllegalArgumentException("No animals");
        }
        for (Vector2d position : listOfPositions) {
            animals.add(new Animal(position));
        }
        this.worldMap = worldMap;
        for (Animal animal : animals) {
            worldMap.place(animal);
        }
    }


    public void run() {
        worldMap.addObserver(new ConsoleMapDisplay());
        try {
            for (int i = 0; i < listOfMoves.size(); i++) {
                Thread.sleep(600);
                int currIndex = i % animals.size();
                Animal currAnimal = animals.get(currIndex);
                MoveDirection currDirection = listOfMoves.get(i);
                worldMap.move(currAnimal, currDirection);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
