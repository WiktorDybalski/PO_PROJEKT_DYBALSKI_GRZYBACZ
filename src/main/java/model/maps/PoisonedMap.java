package model.maps;

import model.simulation.SimulationConfigurator;
import model.utils.Animal;
import model.utils.MapObjects;
import model.utils.Plant;
import model.utils.Vector2d;

import java.util.List;
import java.util.Random;

public class PoisonedMap extends AbstractWorldMap {
    /**
     * Constructor of the Map
     */
    private final Vector2d leftDownPoisonedCorner;
    private final Vector2d rightUpperPoisonedCorner;

    public PoisonedMap(SimulationConfigurator config) {
        super(config);
        this.leftDownPoisonedCorner = generateLeftDownCornerPoisonedSquare();
        this.rightUpperPoisonedCorner = new Vector2d(leftDownPoisonedCorner.getX() + (int) (0.14 * this.getWidth()), leftDownPoisonedCorner.getY() + (int) (0.14 * this.getHeight()));
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
     * Generates a random position within a specified area of the map to define a poisoned square.
     * This square is a region where plants are likely to be poisoned.
     * The area of this square is 20% of the map's width and height.
     *
     * @return A Vector2d object representing the lower-left corner of the poisoned square.
     */

    private Vector2d generateLeftDownCornerPoisonedSquare() {
        Random random = new Random(1115);
        int x = random.nextInt((int) (0.8 * this.getWidth()));
        int y = random.nextInt((int) (0.8 * this.getHeight()));
        return new Vector2d(x, y);
    }

    /**
     * Determines if a plant should be poisoned based on a random probability.
     * There is a 1 in 5 chance that this method will return true, indicating the plant is poisoned.
     *
     * @return true if the plant is poisoned, false otherwise.
     */

    private boolean isPoisonous() {
        Random random = new Random(1115);
        int randomInt = random.nextInt(10);
        return randomInt % 5 == 0;
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

    public void placeAnimals(int amountOfAnimals) {
        super.placeAnimals(amountOfAnimals);
    }

    /**
     * The placePlant method set Plant on the Map
     */
    @Override
    public void placePlant(Plant plant, Vector2d position) {
        if (leftDownPoisonedCorner.precedes(position) && rightUpperPoisonedCorner.follows(position) && isPoisonous()) {
            plant.setPoison();
            plant.setEnergy(-plant.getEnergy());
        }
        mapTiles.get(position).setPlant(plant);
        plants.add(plant);
    }

    /**
     * The placePlants method create random positions for Plants and using for to set Plants on the Map using placePlant
     */

    public void placePlants(int amountOfPlants) {
        super.placePlants(amountOfPlants);
    }

    /**
     * This method removes all dead animals from the map
     */
    public void removeDeadAnimals() {
        super.removeDeadAnimals();
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
    @Override
    public void generateMap() {
        super.generateMap();
    }

    public void firstDay() {
        super.firstDay();
    }

    /**
     * The dailyUpdate method is responsible for the daily update of the map
     */

    public void dailyUpdate() {
        super.dailyUpdate();
    }

    /**
     * The toString method draw the Map using mapVisualizer
     */
    public String toString() {
        return super.toString();
    }
}
