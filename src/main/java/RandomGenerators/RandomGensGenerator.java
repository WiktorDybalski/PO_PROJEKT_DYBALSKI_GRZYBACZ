package RandomGenerators;

import model.utils.Genotype;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGensGenerator {
    /**
     * The length of each genotype.
     */
    private static final int GEN_LENGTH = 7;

    /**
     * List to store the generated genotypes.
     */
    private List<Genotype> gens;

    /**
     * Random object for generating random numbers.
     */
    private Random random;

    /**
     * Constructor for RandomGensGenerator.
     * Generates an initial set of genotypes.
     *
     * @param initialNumberOfGens The number of genotypes to generate initially.
     */
    public RandomGensGenerator(int initialNumberOfGens) {
        gens = new ArrayList<>();
        random = new Random(1111);
        generateGens(initialNumberOfGens);
    }

    /**
     * Returns the list of generated genotypes.
     *
     * @return A list of Genotype objects.
     */
    public List<Genotype> getGens() {
        return gens;
    }

    /**
     * Adds additional genotypes to the list.
     *
     * @param numberOfGens The number of additional genotypes to generate.
     */
    public void addGens(int numberOfGens) {
        for (int i = 0; i < numberOfGens; i++) {
            gens.add(generateRandomGen());
        }
    }

    /**
     * Generates a specified number of genotypes.
     *
     * @param numberOfGens The number of genotypes to generate.
     */
    private void generateGens(int numberOfGens) {
        for (int i = 0; i < numberOfGens; i++) {
            gens.add(generateRandomGen());
        }
    }

    /**
     * Generates a single random genotype.
     * Creates a list of integers, each representing a digit of the genotype.
     *
     * @return A new Genotype object containing a random sequence of digits.
     */
    private Genotype generateRandomGen() {
        ArrayList<Integer> listOfGens = new ArrayList<>();
        for (int i = 0; i < GEN_LENGTH; i++) {
            // Random number between 0 and 7
            Integer digit = random.nextInt(8);
            listOfGens.add(digit);
        }
        return new Genotype(listOfGens);
    }
}

