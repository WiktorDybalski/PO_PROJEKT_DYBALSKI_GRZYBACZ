package model.maps;

import model.simulation.SimulationConfigurator;
import model.utils.*;

import java.util.List;
import java.util.Random;

public class PoisonedMap extends AbstractWorldMap {
    /**
     * Constructor of the Map
     */
    private Vector2d leftDownPoisonedCorner;
    private Vector2d rightUpperPoisonedCorner;

    public PoisonedMap(SimulationConfigurator config) {
        super(config);
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
     * The move method is responsible for the movement of all moving objects on the map
     */

    public void move(List<Animal> animals) {
        for (Animal animal : animals) {
            Vector2d oldPosition = animal.getPosition();
            Directions oldDirection = animal.getDirection();
            int oldActualActiveGeneIndex = animal.getActualActiveGenIndex();
            Vector2d vector = new Vector2d(Directions.toUnitVector(oldActualActiveGeneIndex).getX(), Directions.toUnitVector(oldActualActiveGeneIndex).getY());
            Vector2d newPosition = oldPosition.add(vector);
            if (newPositionOutOfLeftBound(newPosition)) {
                newPosition = new Vector2d(upperRight.getX(), newPosition.getY());
            } else if (newPositionOutOfRightBound(newPosition)) {
                newPosition = new Vector2d(lowerLeft.getX(), newPosition.getY());
            }
            if (canMoveTo(newPosition)) {
                if(mapTiles.get(newPosition).getPlant()!=null && mapTiles.get(newPosition).getPlant().getIsPoisoned()){
                    Random random = new Random();
                    if(random.nextInt(10)%5==0){
                        oldActualActiveGeneIndex = (oldActualActiveGeneIndex + 1) % 8;
                        vector = new Vector2d(Directions.toUnitVector(oldActualActiveGeneIndex).getX(), Directions.toUnitVector(oldActualActiveGeneIndex).getY());
                        newPosition = oldPosition.add(vector);
                        if (newPositionOutOfLeftBound(newPosition)) {
                            newPosition = new Vector2d(upperRight.getX(), newPosition.getY());
                        } else if (newPositionOutOfRightBound(newPosition)) {
                            newPosition = new Vector2d(lowerLeft.getX(), newPosition.getY());
                        }
                    }
                }
                mapTiles.get(oldPosition).removeAnimal(animal);
                animal.move(oldDirection, newPosition);
                animal.setDirection(Directions.fromUnitVector(vector));
                mapTiles.get(newPosition).addAnimal(animal);
            }
            animal.setActualActiveGenIndex(animal.getNextGene());
            animal.decreaseEnergy();
        }
    }

    /**
     * The generateMap method is using in Map constructor to set all objects on the map
     */
    @Override
    public void generateMap() {
        this.leftDownPoisonedCorner = generateLeftDownCornerPoisonedSquare();
        this.rightUpperPoisonedCorner = new Vector2d(leftDownPoisonedCorner.getX() + (int) (0.14 * this.getWidth()), leftDownPoisonedCorner.getY() + (int) (0.14 * this.getHeight()));
        super.generateMap();
    }
}
