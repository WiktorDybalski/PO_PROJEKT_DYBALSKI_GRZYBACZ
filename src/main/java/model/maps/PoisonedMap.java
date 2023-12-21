package model.maps;


import model.utils.Animal;
import model.utils.MapObjects;
import model.utils.Plant;
import model.utils.Vector2d;

public class PoisonedMap extends AbstractWorldMap {
    private float poisonChance;
    private Vector2d poisonedAreaLowerLeft;
    private Vector2d poisonedAreaUpperRight;

    public PoisonedMap(Vector2d lowerLeft, Vector2d upperRight, float poisonChance) {
        super(lowerLeft, upperRight);
        this.poisonChance = poisonChance;
        this.poisonedAreaLowerLeft = generatePoisonedAreaLowerLeft(lowerLeft, upperRight);
        this.poisonedAreaUpperRight = generatePoisonedAreaUpperRight(lowerLeft, upperRight);
    }

    private Vector2d generatePoisonedAreaUpperRight(Vector2d lowerLeft, Vector2d upperRight) {
        return null;
    }

    private Vector2d generatePoisonedAreaLowerLeft(Vector2d lowerLeft, Vector2d upperRight) {
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public void move(Animal animal) {

    }

    @Override
    public boolean placeAnimal(Animal animal) {
        return false;
    }

    @Override
    public MapObjects objectsAt(Vector2d position) {
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public boolean placePlant(Plant plant) {
        return false;
    }

    @Override
    public void generateMap() {

    }

    @Override
    public void updateMap() {

    }

    @Override
    public void drawMap() {

    }
}
