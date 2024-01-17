package model.utils.RandomGenerators;

import model.utils.Genotype;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class RandomGensGenerator {

    private final int genomeLength;
    /**
     * List to store the generated genotypes.
     */
    private List<Genotype> gens;
    /**
     * Random object for generating random numbers.
     */
    private final Random random;

    /**
     * Constructor for RandomGensGenerator.
     * Generates an initial set of genotypes.
     *
     * @param initialNumberOfGens The number of genotypes to generate initially.
     */
    public RandomGensGenerator(int initialNumberOfGens, int genomeLength) {
        this.gens = new ArrayList<>();
        this.random = new Random();
        this.genomeLength = genomeLength;
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
        for (int i = 0; i < genomeLength; i++) {
            // Random number between 0 and 7
            int digit = random.nextInt(8);
            listOfGens.add(abs(digit));
        }
        return new Genotype(listOfGens, genomeLength);
    }
}

