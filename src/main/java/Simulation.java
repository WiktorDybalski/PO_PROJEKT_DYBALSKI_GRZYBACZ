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

//    private WorldMap worldMap;
//
//    private List<Animal> animals = new ArrayList<>();
//
//    public List<Animal> getAnimals() {
//        return animals;
//    }
//
//    public Simulation(List<Directions> listOfMoves, List<Vector2d> listOfPositions, WorldMap<Animal, Vector2d> worldMap) {
//        this.listOfPositions = listOfPositions;
//        this.listOfMoves = listOfMoves;
//        if (listOfPositions.isEmpty()) {
//            throw new IllegalArgumentException("No animals");
//        }
//        for (Vector2d position : listOfPositions) {
//            animals.add(new Animal(position));
//        }
//        this.worldMap = worldMap;
//        for (Animal animal : animals) {
//            worldMap.place(animal);
//        }
//    }
//
//
//    public void run() {
//        try {
//            for (int i = 0; i < listOfMoves.size(); i++) {
//                Thread.sleep(600);
//                int currIndex = i % animals.size();
//                Animal currAnimal = animals.get(currIndex);
//                Directions currDirection = listOfMoves.get(i);
//                worldMap.move(currAnimal, currDirection);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
