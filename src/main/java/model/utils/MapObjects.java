package model.utils;


import java.lang.reflect.Array;
import java.util.*;

//done for now.
/**
 * Represents the objects which can be placed on a tile.
 */
public class MapObjects {
    // The plant which is on the tile.
    private Plant plant;

    // The animals which are on the tile.
    private ArrayList<Animal> animals;

    /**
     * Constructor to initialize the map objects.
     */
    public MapObjects() {
        this.plant = null;
        this.animals = new ArrayList<>();
    }

    //getters and setters
    public Plant getPlant() {
        return plant;
    }
    public List<Animal> getAnimals() {
        return animals;
    }
    public List<MapElement> getObjects() {
        ArrayList<MapElement> objects = new ArrayList<>();
        objects.add(plant);
        objects.addAll(animals);
        return objects;
    }

    /**
     * Returns the list of animals sorted by their energy, age, and number of children.
     */
    public ArrayList<Animal> getSortedAnimals() {
        if (animals.isEmpty()) {
            return new ArrayList<Animal>();
        }
        ArrayList<Animal> sortedAnimals = new ArrayList<>(animals);
        sortedAnimals.sort(Comparator.comparingInt(Animal::getEnergy).reversed());
        sortedAnimals.sort((a1, a2) -> {
            if (a1.getEnergy() != a2.getEnergy()) {
                return Integer.compare(a2.getEnergy(), a1.getEnergy());
            } else if (a1.getAge() != a2.getAge()) {
                return Integer.compare(a2.getAge(), a1.getAge());
            } else {
                return Integer.compare(a2.getChildren().size(), a1.getChildren().size());
            }
        });
        return sortedAnimals;
    }
    /**
     * Returns the strongest animal on the tile.
     * @return The strongest animal on the tile.
     */
    public Animal getStrongestAnimal() {
        if (animals.isEmpty()) {
            return null;
        }
        return getSortedAnimals().get(0);
    }

    /**
     * Returns the two strongest animals on the tile.
     * @return The two strongest animals on the tile.
     */
    public ArrayList<Animal> getStrongestAnimals() {
        if (animals.size()<2) {
            return new ArrayList<>();
        }
        ArrayList<Animal> sortedAnimals = this.getSortedAnimals();
        ArrayList<Animal> strongestAnimals = new ArrayList<>();

        int maxEnergy = sortedAnimals.get(0).getEnergy();
        for (Animal animal : sortedAnimals) {
            if (animal.getEnergy() == maxEnergy) {
                strongestAnimals.add(animal);
            } else {
                break;
            }
        }
        if (strongestAnimals.size() >=2) {
            Collections.shuffle(strongestAnimals);
            return new ArrayList<>(strongestAnimals.subList(0, 2));
        }
        else{
            ArrayList<Animal> strongestAnimals2 = new ArrayList<>();
            int secondMaxEnergy = sortedAnimals.get(1).getEnergy();
            for (Animal animal : sortedAnimals) {
                if (animal.getEnergy() == secondMaxEnergy) {
                    strongestAnimals2.add(animal);
                }
            }
            Collections.shuffle(strongestAnimals2);
            //add the strongest animal to the beginning of the list
            strongestAnimals2.add(0, strongestAnimals.get(0));
            return new ArrayList<>(strongestAnimals2.subList(0, 2));
        }
    }

    /**
     * Adds a plant to the tile.
     * @param plant The plant to be added.
     */
    public void addPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     * Removes the plant from the tile.
     */
    public void removePlant() {
        this.plant = null;
    }

    /**
     * Adds an animal to the tile.
     * @param animal The animal to be added.
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    /**
     * Removes an animal from the tile.
     * @param animal The animal to be removed.
     */
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    /**
     * Removes all dead animals from the tile.
     */
    public void removeDeadAnimalsFromMapObjects() {
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            if (animal.getIsDead() || animal.getEnergy() <= 0) {
                animal.die();
                iterator.remove(); // Use iterator deletes the current element safely
            }
        }
    }

    /**
     * Checks if the tile is occupied by any object.
     * @return True if the tile is occupied, false otherwise.
     */
    public boolean isOccupied() {
        return plant != null || !animals.isEmpty();
    }

    /**
     * Grows the plant on the tile.
     */
    public void growPlant() {
        if (plant != null) {
            plant.grow();
        }
    }

    /**
     * Grows all animals on the tile.
     */
    public void growAnimals() {
        for (Animal animal : animals) {
            animal.grow();
        }
    }

    /**
     * Returns a string representation of the map objects.
     * @return A string representation of the map objects.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Plant: ").append(plant).append("\n");
        stringBuilder.append("Animals:\n");
        for (Animal animal : animals) {
            stringBuilder.append(animal).append("\n");
        }
        return stringBuilder.toString();
    }
}

