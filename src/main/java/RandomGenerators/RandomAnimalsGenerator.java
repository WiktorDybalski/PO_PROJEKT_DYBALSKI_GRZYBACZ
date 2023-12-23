package RandomGenerators;

import model.maps.WorldMap;
import model.utils.Animal;
import model.utils.Genotype;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAnimalsGenerator {
    /**
     * List to store the generated animals.
     */
    private List<Animal> animals;

    /**
     * Random object for generating random numbers.
     */
    private Random random;

    /**
     * The energy value each animal starts with.
     */
    private static final int ENERGY = 5;

    /**
     * The birthday of each animal, set to a constant value.
     */
    private static final int BIRTHDAY = 0;

    /**
     * The minimum energy required for an animal to reproduce.
     */
    public final int minimalReproductionEnergy;

    /**
     * The map where the animals will be placed.
     */
    private final WorldMap map;

    /**
     * Constructor for RandomAnimalsGenerator.
     * Initializes the generator and creates an initial set of animals.
     *
     * @param initialAmount The number of animals to generate initially.
     * @param minimalReproductionEnergy The minimal energy required for animals to reproduce.
     * @param map The map where the animals are to be placed.
     */
    public RandomAnimalsGenerator(int initialAmount, int minimalReproductionEnergy, WorldMap map) {
        animals = new ArrayList<>();
        random = new Random(1111);
        this.minimalReproductionEnergy = minimalReproductionEnergy;
        this.map = map;
        generateAnimals(initialAmount);
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
        return new Animal(position, ENERGY, genotype, BIRTHDAY, minimalReproductionEnergy);
    }

    /**
     * Generates a specified number of animals and adds them to the animals list.
     * Utilizes RandomPositionsGenerator for positions and RandomGensGenerator for genotypes.
     *
     * @param amount The number of animals to generate.
     */
    private void generateAnimals(int amount) {
        RandomPositionsGenerator positionsGenerator = new RandomPositionsGenerator(map, amount);
        List<Vector2d> positions = positionsGenerator.getResult();
        RandomGensGenerator gensGenerator = new RandomGensGenerator(amount);
        List<Genotype> genotypes = gensGenerator.getGens();

        for (int i = 0; i < amount; i++) {
            animals.add(generateRandomAnimal(positions.get(i), genotypes.get(i)));
        }
    }
}

