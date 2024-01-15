package model.utils;


//done for now.

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Represents the genetic makeup of an animal. This class provides functionalities to manipulate
 * and combine genotypes, simulating genetic inheritance and mutation.
 */
public class Genotype {

    // List to store the genes which make up the genotype.
    private ArrayList<Integer> genes;

    private final int genomeLength;

    /**
     * Constructor to initialize a Genotype with a given set of genes.
     * @param genes The list of genes to be assigned to the genotype.
     */
    public Genotype(ArrayList<Integer> genes, int genomeLength) {
        this.genes = new ArrayList<>(genes);
        this.genomeLength=genomeLength;
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
    public void mutate(boolean mutationType) {
        if (mutationType) {
            fullyRandomMutate();
        } else {
            littleMutate();
        }
    }

    private void littleMutate() {
        Random random = new Random();
        int mutationCount = random.nextInt(genes.size()) + 1;
        for (int i = 0; i < mutationCount; i++) {
            int geneIndex = random.nextInt(genes.size());
            int newGeneValue;
            do {
                newGeneValue = random.nextInt(8) + 1; // Random value between 1 and 8
            } while (newGeneValue == genes.get(geneIndex));
            genes.set(geneIndex, newGeneValue);
        }
    }

    private void fullyRandomMutate() {
        Random random = new Random();
        int mutationCount = random.nextInt(genes.size()) + 1;
        for (int i = 0; i < mutationCount; i++) {
            int geneIndex = random.nextInt(genes.size());
            genes.set(geneIndex, random.nextInt(8) + 1); // Random value between 1 and 8
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
        return new Genotype(newGenes,genomeLength);
    }

    /**
     * Checks if two genotypes are equal.
     * @param obj The object to be compared to.
     * @return True if the genotypes are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Genotype other = (Genotype) obj;
        return genes.equals(other.genes);
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