package model.utils;


//done for now. TODO: add comments, tests

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a single tile on the map.
 * It contains a list of animals and a plant.
 */
public class Tile {

    //position of the tile on the map
    private final Vector2d position;

    //list of animals and a plant
    private final MapObjects objects;

    /**
     * Constructor of the Tile class.
     * @param position position of the tile on the map
     */
    public Tile(Vector2d position) {
        this.position = position;
        this.objects = new MapObjects();
    }

    //getters and setters
    public Vector2d getPosition() {
        return position;
    }

    public List<Animal> getAnimals() {
        return objects.getAnimals();
    }

    public Plant getPlant() {
        return objects.getPlant();
    }

    public MapObjects getObjects() {
        return objects;
    }

    public void setPlant(Plant plant) {
        objects.addPlant(plant);
    }

    /**
     * Method adding an animal to the list of animals on the tile.
     * @param animal animal to be added
     */
    public void addAnimal(Animal animal) {
        objects.addAnimal(animal);
    }

    /**
     * Method removing an animal from the list of animals on the tile.
     * @param animal animal to be removed
     */
    public void removeAnimal(Animal animal) {
        objects.removeAnimal(animal);
    }

    /**
     * Method checking if the tile is occupied by any object.
     * @return true if the tile is occupied by an animal, false otherwise
     */
    public boolean isOccupied() {
        return objects.isOccupied();
    }

    /**
     * Method returning the strongest animal on the tile.
     * @return strongest animal on the tile
     */
    public Animal getStrongestAnimal() {
        return objects.getStrongestAnimal();
    }

    /**
     * Method returning the list of the strongest animals on the tile.
     * @return list of the strongest animals on the tile
     */
    ArrayList<Animal> getStrongestAnimals() {
        return objects.getStrongestAnimals();
    }

    /**
     * Method removes dead animals from the list of animals on the tile.
     * It is called in the simulation of a single day.
     */
    public void removeDeadAnimals() {
        objects.removeDeadAnimals();
    }

    /**
     * Metod simulates the eating of a plant by the strongest animal on the tile.
     */
    public void eatPlant() {
        Animal strongestAnimal = objects.getStrongestAnimal();
        if (strongestAnimal != null) {
            strongestAnimal.eat(objects.getPlant());
            objects.removePlant();
        }
    }

    /**
     * Method simulates reproduction of the strongest animals on the tile.
     * @param currentDay current day of the simulation
     * @param energyToTransfer energy to be transferred from the parents to child
     */
    public void reproduceAnimals(int currentDay, int energyToTransfer) {
        if (objects.getAnimals().size() < 2) {
            return;
        }
        ArrayList<Animal> strongestAnimals = objects.getStrongestAnimals();
        Animal animal1 = strongestAnimals.get(0);
        Animal animal2 = strongestAnimals.get(1);
        if (animal1.canReproduce() && animal2.canReproduce()) {
            Animal child = animal1.reproduce(animal2, currentDay, energyToTransfer);
            objects.addAnimal(child);
        }
    }

    /**
     * Method simulates growing of the plant and animals on the tile.
     */
    public void grow(){
        objects.growPlant();
        objects.growAnimals();
    }

    public void removePlant() {
        objects.removePlant();
    }

    /**
     * Method returning the hash code of the tile.
     * @return hash code of the tile
     * Nessessary for the implementation of the equals method.
     */
    public int hashCode() {
        return this.hashCode();
    }

    /**
     * Method checking if two tiles are equal.
     * @param other tile to be compared with
     * @return true if the tiles are equal, false otherwise
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Tile that)) {
            return false;
        }
        return this.equals(other);
    }

    /**
     * Method returning the string representation of the tile.
     * @return string representation of the tile
     */
    public String toString() {
        String s = "Tile: " +
                "position: " + position.toString() +
                ", objects: " + objects.toString();
        return s;
    }

}
