package model.utils;


import java.util.Objects;

//done for now.
/**
 * The Vector2d class represents a vector in 2D space.
 * It is used to represent positions on the map.
 */
public class Vector2d {

    /**
     * The x coordinate of the vector.
     */
    private final int x;

    /**
     * The y coordinate of the vector.
     */
    private final int y;

    /**
     * The Vector2d constructor.
     * @param x The x coordinate of the vector.
     * @param y The y coordinate of the vector.
     */
    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //getter methods
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * The add method adds two vectors.
     * @param other The vector to be added.
     * @return The sum of the two vectors.
     */
    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    /**
     * The subtract method subtracts two vectors.
     * @param other The vector to be subtracted.
     * @return The difference of the two vectors.
     */
    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    /**
     * The precedes method checks if the vector precedes another vector.
     * @param other The vector to be compared to.
     * @return True if the vector precedes the other vector, false otherwise.
     */
    public boolean precedesY(Vector2d other) {
        return (this.y <= other.y);
    }


    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    /**
     * The lowerLeft method returns the lower left corner of the rectangle
     * created by the two vectors.
     * @param other The vector to be compared to.
     * @return The lower left corner of the rectangle created by the two vectors.
     */
    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    /**
     * The ovverrided hashCode method.
     */
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    /**
     * The equals method checks if the vector is equal to another object.
     * @param other The object to be compared to.
     * @return True if the vector is equal to the other object, false otherwise.
     */
    public boolean equals(Object other) {
        if (other instanceof Vector2d) {
            return (this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y);
        }
        return false;
    }

    /**
     * The toString method.
     * @return The string representation of the vector.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
