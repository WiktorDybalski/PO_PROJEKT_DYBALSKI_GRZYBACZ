package model.maps;


import model.utils.Animal;
import model.utils.MapObjects;
import model.utils.Plant;
import model.utils.Vector2d;

import java.util.List;

public class PoisonedMap extends AbstractWorldMap {
    private final double poisonChance;

    public static final int MINIMAL_REPRODUCTION_ENERGY = 50; //TODO: check if it's ok

    public PoisonedMap(int width, int height, double poisonChance, int initialNumberOfPlants, int initialNumberOfAnimals) {
        super(new Vector2d(0, 0), new Vector2d(width, height), initialNumberOfPlants, initialNumberOfAnimals, MINIMAL_REPRODUCTION_ENERGY);
        this.poisonChance = poisonChance;
    }

    private Vector2d generatePoisonedAreaUpperRight(Vector2d lowerLeft, Vector2d upperRight) {
        return null;
    }

    private Vector2d generatePoisonedAreaLowerLeft(Vector2d lowerLeft, Vector2d upperRight) {
        return null;
    }

    /**
     * The canMoveTo method checks if the Animal can move to
     *
     * @param position The position the object wants to get to
     * @return bool value is it possible to move there
     */

    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position);
    }

    /**
     * The objectsAt method
     *
     * @param position The position the object wants to get to
     * @return bool value is it possible to move there
     */

    public MapObjects objectsAt(Vector2d position) {
        return super.objectsAt(position);
    }

    /**
     * The isOccupied method checks whether the MapObject is in position
     *
     * @return bool value
     */

    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    /**
     * The placeAnimal method add animal to the Map
     */

    public void placeAnimal(Animal animal, Vector2d position) {
        super.placeAnimal(animal, position);
    }

    /**
     * The placeAnimals method create random positions for Animals and using for to add Animal to the Map using placeAnimal
     */

    public void placeAnimals(List<Animal> animals) {
        super.placeAnimals(initialNumberOfAnimals);
    }

    /**
     * The placePlant method set Plant on the Map
     */

    public void placePlant(Plant plant, Vector2d position) {
        super.placePlant(plant, position);
    }

    /**
     * The placePlants method create random positions for Plants and using for to set Plants on the Map using placePlant
     */

    public void placePlants(List<Plant> plants) {
        super.placePlants(initialNumberOfPlants);
    }

    /**
     * The move method is responsible for the movement of all moving objects on the map
     */

    public void move(List<Animal> animals) {
        super.move();
    }

    /**
     * The eat method is responsible for the eating of all animals on the map
     */
    public void eat() {
        super.eat();
    }
    /**
     * The generateMap method is using in Map constructor to set all objects on the map
     */
    public void generateMap(List<Plant> plants, List<Animal> animals) {
        super.generateMap(plants, animals);
    }

    /**
     * The toString method draw the Map using mapVisualizer
     */
    public String toString() {
        return super.toString();
    }
}
