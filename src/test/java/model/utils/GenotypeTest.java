package model.utils;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

//done for now
public class GenotypeTest {
    private ArrayList<Integer> genes;
    private Genotype genotype;

    @BeforeEach
    public void setUp() {
        genes = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            genes.add(i);

        genotype = new Genotype(genes,8);
    }

    @Test
    public void testGetGenes() {
        assertEquals(genotype.getGenes(), genes);
    }

    @Test
    public void testGetGenomeLength() {
        assertEquals(genotype.getGenomeLength(), genes.size());
    }

    @Test
    public void testMutate() {
        Genotype genotypeCopy = new Genotype(new ArrayList<>(genes),8);
        genotype.mutate(true);
        assertEquals(genotype.getGenomeLength(), genotypeCopy.getGenomeLength());
        //due to uncontrollable nature of random, we can't test if the genotype has changed
    }
}
