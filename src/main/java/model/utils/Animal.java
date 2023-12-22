package model.utils;

import java.util.ArrayList;
import java.util.Random;

//done for now.

/**
 * Represents an animal on a map with characteristics like position, energy, and genotype.
 */
public class Animal implements MapElement {

    // Current position of the animal in a two-dimensional map.
    private Vector2d position;

    // Current energy level of the animal, influencing its survival and reproduction.
    private int energy;

    // Current direction in which the animal is moving.
    private Directions direction;

    // Genotype of the animal, inherited and affecting its hereditary traits.
    private final Genotype genotype;

    // List of the animal's offspring.
    private ArrayList<Animal> children;

    // Number of plants eaten by the animal.
    private int eatenPlantCount;

    // Day of birth for the animal.
    private final int birthDay;

    // Age of the animal.
    private int age;

    // Flag indicating whether the animal is dead.
    private boolean isDead;

    // Index of the currently active gene in the genotype.
    private int actualActiveGenIndex;

    // Minimal energy required for reproduction.
    private final int minimalReproductionEnergy;

    /**
     * Constructor to initialize an Animal object with specified attributes.
     *
     * @param position                  Initial position of the animal.
     * @param energy                    Initial energy level of the animal.
     * @param genotype                  Genotype of the animal.
     * @param birthDay                  Day of birth for the animal.
     * @param minimalReproductionEnergy Minimal energy required for reproduction.
     */
    public Animal(Vector2d position, int energy, Genotype genotype, int birthDay, int minimalReproductionEnergy) {
        this.position = position;
        this.energy = energy;
        this.direction = Directions.getRandomDirection();
        this.genotype = genotype;
        this.children = new ArrayList<Animal>();
        this.eatenPlantCount = 0;
        this.birthDay = birthDay;
        this.age = 0;
        this.isDead = false;
        this.actualActiveGenIndex = (new Random()).nextInt(genotype.getGenomeLength());
        this.minimalReproductionEnergy = minimalReproductionEnergy;
    }

    //getters and setters
    public int getEnergy() {
        return energy;
    }

    public int getChildrenCount() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public Vector2d getPosition() {
        return position;
    }

    public Directions getDirection() {
        return direction;
    }

    public int getEatenPlantCount() {
        return eatenPlantCount;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public ArrayList<Animal> getChildren() {
        return children;
    }

    public int getDescendantCount() {
        int descendantCount = 0;

        if (children == null) {
            return descendantCount;
        }
        for (Animal child : children) {
            descendantCount++;
            descendantCount += child.getDescendantCount();
        }

        return descendantCount;
    }

    public int getAge() {
        return age;
    }

    public int getActualActiveGenIndex() {
        return actualActiveGenIndex;
    }

    public int getMinimalReproductionEnergy() {
        return minimalReproductionEnergy;
    }

    public int getNextGene() {
        this.actualActiveGenIndex = (this.actualActiveGenIndex + 1) % this.genotype.getGenomeLength();
        return this.genotype.getGene(this.actualActiveGenIndex);
    }

    public void setActualActiveGenIndex(int actualActiveGenIndex) {
        this.actualActiveGenIndex = actualActiveGenIndex;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }


    //behavioral methods

    /**
     * Determines if the animal has enough energy to reproduce.
     *
     * @return true if the animal's energy is greater than or equal to the minimal reproduction energy, false otherwise.
     */
    public boolean canReproduce() {
        return this.energy >= this.minimalReproductionEnergy;
    }

    /**
     * Moves the animal to a new position and changes its direction.
     * Consumes one unit of energy for the movement.
     *
     * @param newDirection The new direction in which the animal is moving.
     * @param newPosition  The new position of the animal on the map.
     */
    public void move(Directions newDirection, Vector2d newPosition) {
        this.direction = newDirection;
        this.position = newPosition;
        this.energy--;
    }

    /**
     * Increases the animal's energy by the energy value of the consumed plant and increments the eaten plant count.
     * Marks the consumed plant as eaten.
     *
     * @param plant The plant to be eaten by the animal.
     */
    public void eat(Plant plant) {
        this.energy += plant.getEnergy();
        this.eatenPlantCount++;
        plant.setIsEaten();
    }

    /**
     * Adds a new child to the animal's list of offspring.
     * Initializes the children list if it's null.
     *
     * @param child The new offspring to be added to the list.
     */
    public void addChild(Animal child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    /**
     * Reproduces a new animal with a partner. The new animal's genotype is a mix of its parents' genotypes.
     * Both parents lose a portion of energy during reproduction.
     *
     * @param partner          The partner animal to reproduce with.
     * @param currentDay       The current day which will be the birth day of the new animal.
     * @param energyToTransfer The energy transferred to the new animal.
     * @return The new offspring animal.
     */
    public Animal reproduce(Animal partner, int currentDay, int energyToTransfer) {
        double ratio = (double) this.getEnergy() / (this.getEnergy() + partner.getEnergy());
        Random random = new Random();
        //if 0 then left, if 1 then right
        int side = random.nextInt(2);
        Genotype childgenotype;
        if (side == 0) {
            childgenotype = this.getGenotype().mixGenotypes(partner.getGenotype(), ratio);
        } else {
            childgenotype = partner.getGenotype().mixGenotypes(this.getGenotype(), ratio);
        }
        childgenotype.mutate();
        Animal child = new Animal(this.getPosition(), energyToTransfer, childgenotype, currentDay, this.minimalReproductionEnergy);
        this.energy -= (int) (ratio * energyToTransfer);
        partner.energy -= (int) ((1 - ratio) * energyToTransfer);
        this.addChild(child);
        partner.addChild(child);
        return child;
    }

    /**
     * Sets the animal's status to dead.
     */
    public void die() {
        this.energy = 0;
        this.isDead = true;
    }

    /**
     * Increments the age of the animal by one day.
     */
    public void grow() {
        age++;
    }

    //overridden methods
    public int hashCode() {
        return this.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Animal))
            return false;
        Animal that = (Animal) other;
        return this.position.equals(that.position) && this.energy == that.energy && this.direction.equals(that.direction) && this.genotype.equals(that.genotype) && this.children.equals(that.children) && this.eatenPlantCount == that.eatenPlantCount && this.birthDay == that.birthDay && this.isDead == that.isDead && this.age == that.age;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Animal: ");
        sb.append(this.position.toString());
        sb.append(", energy: ");
        sb.append(this.energy);
        sb.append(", direction: ");
        sb.append(this.direction.toString());
        sb.append(", genotype: ");
        sb.append(this.genotype.toString());
        sb.append(", children: ");
        sb.append(this.children.toString());
        sb.append(", eatenPlantCount: ");
        sb.append(this.eatenPlantCount);
        sb.append(", birthDay: ");
        sb.append(this.birthDay);
        sb.append(", isDead: ");
        sb.append(this.isDead);
        if (this.isDead) {
            sb.append(", deathDay: ");
            sb.append(this.birthDay + this.age);
        }
        return sb.toString();
    }
}
