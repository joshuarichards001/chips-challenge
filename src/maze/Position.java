package maze;

/**
 * A position represents the location within array. [height][width]
 *
 * @author Matthew
 *
 */
class Position {
    private int height;
    private int width;

    /**
     * Create a new position based on the coordinates within an array
     *
     * @param height ---
     * @param width ---
     */
    Position(int height, int width) {
        this.height = height;
        this.width = width;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    public boolean equals(Position pos) {
        if (this.getWidth() == pos.getWidth() && this.getHeight() == pos.getHeight()) {
            return true;
        }
        return false;
    }
}
