package model.utils;


//done for now.

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents the genetic makeup of an animal. This class provides functionalities to manipulate
 * and combine genotypes, simulating genetic inheritance and mutation.
 */
public class Genotype {

    // List to store the genes which make up the genotype.
    private ArrayList<Integer> genes;

    /**
     * Constructor to initialize a Genotype with a given set of genes.
     * @param genes The list of genes to be assigned to the genotype.
     */
    public Genotype(ArrayList<Integer> genes) {
        this.genes = new ArrayList<>(genes);
    }

    //getters and setters
    public List<Integer> getGenes() {
        return new ArrayList<>(genes);
    }
    public int getGenomeLength() {
        return genes.size();
    }
    public int getGene(int index) {
        return genes.get(index);
    }

    /**
     * Mutates the genotype by randomly changing a specified number of genes.
     */
    public void mutate() {
        Random random = new Random();
        int mutationCount = random.nextInt(genes.size()) + 1;
        for (int i = 0; i < mutationCount; i++) {
            int geneIndex = random.nextInt(genes.size());
            int geneValue = (genes.get(geneIndex) + random.nextInt()) % 7+1; // to avoid mutation to the same value
            genes.set(geneIndex, geneValue);
        }
    }

    /**
     * Combines two genotypes by randomly mixing their genes.
     * @param genotype The genotype to be mixed with.
     * @param ratio The ratio of genes to be taken from the other genotype.
     * @return The new genotype created by mixing the two genotypes.
     */
    public Genotype mixGenotypes(Genotype genotype, double ratio) {
        ArrayList<Integer> newGenes = new ArrayList<>();
        for (int i = 0; i < genes.size(); i++) {
            if (i < ratio * genes.size()) {
                newGenes.add(genes.get(i));
            } else {
                newGenes.add(genotype.genes.get(i));
            }
        }
        return new Genotype(newGenes);
    }


    /**
     * Overrides the default hashCode method.
     */
    public int hashCode() {
        return Objects.hash(genes);
    }

    /**
     * Checks if two genotypes are equal.
     * @param obj The object to be compared to.
     * @return True if the genotypes are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Genotype)) {
            return false;
        }
        Genotype other = (Genotype) obj;
        return Objects.equals(genes, other.genes);
    }

    /**
     * Returns a string representation of the genotype.
     * @return The string representation of the genotype.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer gene : genes) {
            builder.append(gene);
        }
        return builder.toString();
    }
}