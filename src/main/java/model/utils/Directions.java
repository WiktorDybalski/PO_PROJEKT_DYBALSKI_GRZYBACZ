package model.utils;

//done for now.
public enum Directions {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;


    /**
     * @return random direction
     */
    public static Directions getRandomDirection() {
        return values()[(int) (Math.random() * values().length)];
    }

    /**
     * @return direction name
     */
    public static Directions getDirectionName(int index) {
        return values()[index];
    }

    /**
     * @return direction index
     */
    public static int getDirectionIndex(Directions direction) {
        return direction.ordinal();
    }

    /**
     * @return direction as unit vector
     */
    public static Vector2d toUnitVector(int index){
        return switch (index) {
            case 0 -> new Vector2d(0, 1); //north
            case 1 -> new Vector2d(1, 1); //north-east
            case 2 -> new Vector2d(1, 0); //east
            case 3 -> new Vector2d(1, -1); //south-east
            case 4 -> new Vector2d(0, -1); //south
            case 5 -> new Vector2d(-1, -1); //south-west
            case 6 -> new Vector2d(-1, 0); //west
            case 7 -> new Vector2d(-1, 1); //north-west
            default -> new Vector2d(0, 0); //should never happen but better safe than sorry
        };
    }

    public static Directions fromUnitVector(Vector2d vector) {
        if (vector.equals(new Vector2d(0, 1))) {
            return NORTH;
        } else if (vector.equals(new Vector2d(1, 1))) {
            return NORTH_EAST;
        } else if (vector.equals(new Vector2d(1, 0))) {
            return EAST;
        } else if (vector.equals(new Vector2d(1, -1))) {
            return SOUTH_EAST;
        } else if (vector.equals(new Vector2d(0, -1))) {
            return SOUTH;
        } else if (vector.equals(new Vector2d(-1, -1))) {
            return SOUTH_WEST;
        } else if (vector.equals(new Vector2d(-1, 0))) {
            return WEST;
        } else if (vector.equals(new Vector2d(-1, 1))) {
            return NORTH_WEST;
        } else {
            return NORTH;
        }
    }
}
