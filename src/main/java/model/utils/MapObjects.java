package model.utils;


import java.util.*;

//done for now. TODO: add comments, tests
/**
 * Represents the objects which can be placed on a tile.
 */
public class MapObjects {
    // The plant which is on the tile.
    private Plant plant;

    // The animals which are on the tile.
    private PriorityQueue<Animal> animals;

    /**
     * Constructor to initialize the map objects.
     */
    public MapObjects() {
        this.plant = null;
        Comparator<Animal> animalComparator = (a1, a2) -> {
            if (a1.getEnergy() != a2.getEnergy()) {
                return Integer.compare(a2.getEnergy(), a1.getEnergy());
            } else if (a1.getAge() != a2.getAge()) {
                return Integer.compare(a2.getAge(), a1.getAge());
            } else {
                return Integer.compare(a2.getChildren().size(), a1.getChildren().size());
            }
        };
        this.animals = new PriorityQueue<>(animalComparator);
    }

    //getters and setters
    public Plant getPlant() {
        return plant;
    }
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
    public List<MapElement> getObjects() {
        ArrayList<MapElement> objects = new ArrayList<>();
        objects.add(plant);
        objects.addAll(animals);
        return objects;
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
    public void removeDeadAnimals() {
        ArrayList<Animal> deadAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getEnergy() <= 0) {
                deadAnimals.add(animal);
                animal.die();
            }
        }
        animals.removeAll(deadAnimals);
    }

    /**
     * Checks if the tile is occupied by any object.
     * @return True if the tile is occupied, false otherwise.
     */
    public boolean isOccupied() {
        return plant != null || !animals.isEmpty();
    }

    /**
     * Returns the strongest animal on the tile.
     * @return The strongest animal on the tile.
     */
    public Animal getStrongestAnimal() {
        return animals.peek();
    }

    /**
     * Returns the two strongest animals on the tile.
     * @return The two strongest animals on the tile.
     */
    public ArrayList<Animal> getStrongestAnimals() {
        ArrayList<Animal> strongestAnimals = new ArrayList<>();
        if (animals.size()<2){
            return null;
        }
        if (animals.size()==2) {
            strongestAnimals.addAll(animals);
            return strongestAnimals;
        }
        if (animals.size()>2) {
            int maxEnergy = animals.peek().getEnergy();
            int secondMaxEnergy = animals.peek().getEnergy();
            for (Animal animal : animals) {
                if (animal.getEnergy() == maxEnergy) {
                    strongestAnimals.add(animal);
                } else {
                    secondMaxEnergy = animal.getEnergy();
                    break;
                }
            }
            if (strongestAnimals.size() >=2) {
                Collections.shuffle(strongestAnimals);
                return new ArrayList<>(strongestAnimals.subList(0, Math.min(2, strongestAnimals.size())));
            }
            ArrayList<Animal> secondStrongestAnimals = new ArrayList<>();
            for (Animal animal : animals) {
                if (animal.getEnergy() == secondMaxEnergy) {
                    secondStrongestAnimals.add(animal);
                } else {
                    break;
                }
            }
            Collections.shuffle(secondStrongestAnimals);
            strongestAnimals.add(secondStrongestAnimals.get(0));
            return strongestAnimals;
        }
        return null;
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

