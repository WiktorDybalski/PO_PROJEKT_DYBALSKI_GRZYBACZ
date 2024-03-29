package model.utils.RandomGenerators;

import model.maps.WorldMap;
import model.utils.Animal;
import model.utils.Genotype;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class RandomAnimalsGenerator {
    /**
     * The birthday of each animal, set to a constant value.
     */
    private static final int BIRTHDAY = 0;
    /**
     * The energy value each animal starts with.
     */
    private final int animalEnergy;
    /**
     * The minimum energy required for an animal to reproduce.
     */
    private final int minimalReproductionEnergy;
    /**
     * The map where the animals will be placed.
     */
    private final WorldMap map;
    /**
     * List to store the generated animals.
     */
    private List<Animal> animals;

    /**
     * Constructor for RandomAnimalsGenerator.
     * Initializes the generator and creates an initial set of animals.
     *
     * @param minimalReproductionEnergy The minimal energy required for animals to reproduce.
     * @param map                       The map where the animals are to be placed.
     */
    public RandomAnimalsGenerator(int initialAnimalEnergy, int minimalReproductionEnergy, WorldMap map) {
        animals = new ArrayList<>();
        this.minimalReproductionEnergy = minimalReproductionEnergy;
        this.animalEnergy = initialAnimalEnergy;
        this.map = map;
    }

    /**
     * Returns the list of generated animals.
     *
     * @return A list of Animal objects.
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Generates a random animal at a specified position with a given genotype.
     *
     * @param position The position where the animal will be placed.
     * @param genotype The genotype of the animal.
     * @return A new Animal object.
     */
    private Animal generateRandomAnimal(Vector2d position, Genotype genotype) {
        return new Animal(position, animalEnergy, genotype, BIRTHDAY, minimalReproductionEnergy);
    }

    /**
     * Generates a specified number of animals and adds them to the animals list.
     * Utilizes RandomPositionsGenerator for positions and RandomGensGenerator for genotypes.
     *
     * @param amount The number of animals to generate.
     */
    public void generateAnimals(int amount) {
        RandomPositionsGenerator positionsGenerator = new RandomPositionsGenerator(map, amount);
        List<Vector2d> positions = positionsGenerator.generateAnimals();
        RandomGensGenerator gensGenerator = new RandomGensGenerator(amount, map.getConfig().getGenomeLength());
        List<Genotype> genotypes = gensGenerator.getGens();

        for (int i = 0; i < amount; i++) {
            animals.add(generateRandomAnimal(positions.get(i), genotypes.get(i)));
        }
    }
}

