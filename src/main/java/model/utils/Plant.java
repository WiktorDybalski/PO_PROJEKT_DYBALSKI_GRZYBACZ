package model.utils;

/**
 * Class representing a plant on the map.
 */
public class Plant implements MapElement {
    // position of the plant
    private final Vector2d position;
    // day of growth of the plant
    private final int dayOfGrowth;
    // energy of the plant
    private int energy;
    // is the plant poisoned
    private boolean isPoisoned;
    // is the plant eaten
    private boolean isEaten;

    /**
     * Constructor of the plant.
     *
     * @param position    position of the plant
     * @param energy      energy of the plant
     * @param isPoisoned  is the plant poisoned
     * @param dayOfGrowth day of growth of the plant
     */
    public Plant(Vector2d position, int energy, boolean isPoisoned, int dayOfGrowth) {
        this.position = position;
        this.energy = energy;
        this.dayOfGrowth = dayOfGrowth;
        this.isPoisoned = isPoisoned;
        this.isEaten = false;
    }

    //getters and setters
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int newEnergy) {
        this.energy = newEnergy;
    }

    public int getDayOfGrowth() {
        return dayOfGrowth;
    }

    public Vector2d getPosition() {
        return position;
    }

    public boolean getIsPoisoned() {
        return isPoisoned;
    }

    public boolean getIsEaten() {
        return isEaten;
    }

    public void setIsEaten() {
        isEaten = true;
    }

    public void setPoison() {
        isPoisoned = true;
    }

    /**
     * Method simulates growth of plant.
     */
    public void grow() {
        if (!isEaten) {
            energy++;
        }
    }

    public int hashCode() {
        return position.hashCode() + energy + dayOfGrowth + (isPoisoned ? 1 : 0) + (isEaten ? 1 : 0);
    }

    /**
     * Method checks if two plants are equal.
     *
     * @param other other plant
     * @return true if plants are equal, false otherwise
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Plant))
            return false;
        Plant that = (Plant) other;
        return this.position.equals(that.position) && this.energy == that.energy && this.dayOfGrowth == that.dayOfGrowth && this.isPoisoned == that.isPoisoned && this.isEaten == that.isEaten;
    }

    /**
     * Method ovverides toString method.
     *
     * @return string representation of the plant
     */
    public String toString() {
//        StringBuilder s = new StringBuilder();
//        s.append("Plant: ");
//        s.append("position: ").append(position.toString());
//        s.append(", energy: ").append(energy);
//        s.append(", dayOfGrowth: ").append(dayOfGrowth);
//        s.append(", isPoisoned: ").append(isPoisoned);
//        s.append(", isEaten: ").append(isEaten);
        return "*";
    }
}
