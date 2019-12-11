package maze;

import javax.swing.*;
import java.util.ArrayList;
import static java.lang.System.out;

import static maze.Tile.Type.*;

/**
 * This class is responsible for moving the around the board, as well as updates the players inventory, moves any box they
 * are trying to push, and also checks whether the move they are trying to input is valid.
 *
 */

public class Move {
    private int padCount;
    private final Move.Direction direction;
    private Tile[][] layout;
    private static ArrayList<Tile.Type> inventory;

    private Position nextPos;

    public Direction getDirection() {
        return direction;
    }

    private static Tile.Type nextTileType;
    private static Tile nextTile;
    private static Tile.Type nextNextTileType;
    private static Tile nextNextTile;
    private int chips, height, width;

    public enum Direction {
        UP,
        LEFT,
        RIGHT,
        DOWN
    }

    Move(Maze maze, Direction direction) {
        this.direction = direction;
        layout = maze.getLayout();
        inventory = maze.getInventory();
        chips = maze.getChips();
        height = maze.getChapPos().getHeight();
        width = maze.getChapPos().getWidth();
        padCount = maze.getTotalPads();
        setNext();
    }

    /**
     * Switch case used to determine which direction the player is trying to go, updating positions accordingly
     */

    private void setNext() {
        switch (direction) {
            case LEFT: {
                nextNextTile = layout[height][width - 2];
                nextTile = layout[height][width - 1];
                nextPos = new Position(height, width - 1);
                break;
            }
            case UP: {
                nextTile = layout[height - 1][width];
                nextNextTile = layout[height - 2][width];
                nextPos = new Position(height - 1, width);
                break;
            }
            case DOWN: {
                nextTile = layout[height + 1][width];
                nextNextTile = layout[height + 2][width];
                nextPos = new Position(height + 1, width);
                break;
            }
            case RIGHT: {
                nextTile = layout[height][width + 1];
                nextNextTile = layout[height][width + 2];
                nextPos = new Position(height, width + 1);
                break;
            }
        }
        nextTileType = nextTile.getType();
        nextNextTileType = nextNextTile.getType();
    }

    /**
     * Checks the inventory of the player to see if they are able to open a door or exit the level
     *
     * @return item boolean
     */

    private boolean checkInventory() {
        switch (nextTileType) {
            case PINK_DOOR:
                return inventory.contains(PINK_KEY) || inventory.contains(PINK_KEY_2);
            case BLUE_DOOR:
                return inventory.contains(BLUE_KEY) || inventory.contains(BLUE_KEY_2);
            case GOLD_DOOR:
                return inventory.contains(GOLD_KEY) || inventory.contains(GOLD_KEY_2);
            case WHITE_DOOR:
                return inventory.contains(WHITE_KEY) || inventory.contains(WHITE_KEY_2);
            case EXIT_LOCK:
                return chips == 0;
            default:
                return false;
        }
    }

    /**
     * Updates the inventory of the player
     */
    private void updateInventory() {
        if (nextTileType.equals(TREASURE)) chips--;
        else if (inventory.contains(nextTileType)) addKey();
        else if (nextTileType.equals(BOX) && nextNextTileType.equals(TREASURE)) {
            chips--;
            return;
        } else if (!nextTileType.equals(BOX)) {
            inventory.add(nextTileType);
        }
    }

    /**
     * Checks if the move is valid
     */
    boolean isValid() {
        if (nextTile.isFree()) return true;
        if (nextTileType.equals(BOX) && nextNextTile.isFree()) return true;
        if (nextTileType.equals(PAD_DOOR1) && (padCount == 6)) return true;
        if (nextTileType.equals(PAD_DOOR2) && (padCount == 2)) return true;
        if (nextTileType.equals(PAD_DOOR3) && (padCount == 0)) return true;
        return checkInventory();
    }


    /**
     * Removes a key from the players inventory when they open the same coloured door
     */
    private void removeKey() {
        switch (nextTileType) {
            case PINK_DOOR:
                if (inventory.contains(PINK_KEY_2)) {
                    inventory.remove(PINK_KEY_2);
                    inventory.add(PINK_KEY);
                    break;
                } else inventory.remove(PINK_KEY);
                break;
            case BLUE_DOOR:
                if (inventory.contains(BLUE_KEY_2)) {
                    inventory.remove(BLUE_KEY_2);
                    inventory.add(BLUE_KEY);
                    break;
                } else inventory.remove(BLUE_KEY);
                break;
            case GOLD_DOOR:
                if (inventory.contains(GOLD_KEY_2)) {
                    inventory.remove(GOLD_KEY_2);
                    inventory.add(GOLD_KEY);
                    break;
                } else inventory.remove(GOLD_KEY);
                break;
            case WHITE_DOOR:
                if (inventory.contains(WHITE_KEY_2)) {
                    inventory.remove(WHITE_KEY_2);
                    inventory.add(WHITE_KEY);
                    break;
                } else inventory.remove(WHITE_KEY);
                break;
        }
    }

    /**
     * Adds key to players inventory when one is picked up
     */

    private void addKey() {
        switch (nextTileType) {
            case PINK_KEY:
                inventory.remove(PINK_KEY);
                inventory.add(PINK_KEY_2);
                break;

            case BLUE_KEY:
                inventory.remove(BLUE_KEY);
                inventory.add(BLUE_KEY_2);
                break;

            case GOLD_KEY:
                inventory.remove(GOLD_KEY);
                inventory.add(GOLD_KEY_2);
                break;

            case WHITE_KEY:
                inventory.remove(WHITE_KEY);
                inventory.add(WHITE_KEY_2);
                break;

        }
    }

    private void checkInfo() {
        if (nextTile.isInfo()) {
            switch (direction) {
                case UP:
                    layout[height - 1][width].setInfo();
                    break;
                case LEFT:
                    layout[height][width - 1].setInfo();
                    break;
                case RIGHT:
                    layout[height][width + 1].setInfo();
                    break;
                case DOWN:
                    layout[height + 1][width].setInfo();
            }
        }
    }

    private void checkPad() {
        if (nextTile.isPad()) {
            switch (direction) {
                case UP:
                    layout[height - 1][width].setPad();
                    break;
                case LEFT:
                    layout[height][width - 1].setPad();
                    break;
                case RIGHT:
                    layout[height][width + 1].setPad();
                    break;
                case DOWN:
                    layout[height + 1][width].setPad();
            }
        }
    }

    /**
     * Moves the boxes on the board
     */

    void moveBox() {
        updateInventory();
        switch (direction) {
            case LEFT:
                layout[height][width - 2] = new Tile(BOX);
                break;

            case UP:
                layout[height - 2][width] = new Tile(BOX);
                break;

            case DOWN:
                layout[height + 2][width] = new Tile(BOX);
                break;

            case RIGHT:
                layout[height][width + 2] = new Tile(BOX);
                break;
        }
    }

    Maze apply() {
        if (nextTile.isHoldable()) updateInventory();
        if (checkInventory()) removeKey();
        if (layout[height][width].isInfo()) layout[height][width] = new Tile(INFO);
        if (layout[height][width].isPad()) layout[height][width] = new Tile(WEIGHT_PAD);
        else layout[height][width] = new Tile(FREE);
        if (nextTileType.equals(BOX) && nextNextTile.isFree()) {
            moveBox();
            if (nextNextTile.getType() == WEIGHT_PAD) {
                padCount--;
                out.println(padCount);
            }
        }

        if (nextTileType.equals(BOX) && nextTile.isPad() && nextNextTile.isFree() && !nextNextTile.isPad()) {
            moveBox();
            padCount++;
            nextTile = new Tile(WEIGHT_PAD);
            out.println(padCount);
        }


        switch (direction){
            case UP:
                layout[height - 1][width] = new Tile(CHAP_UP);
                break;
            case LEFT:
                layout[height][width - 1] = new Tile(CHAP_LEFT);
                break;
            case RIGHT:
                layout[height][width + 1] = new Tile(CHAP_RIGHT);
                break;
            case DOWN:
                layout[height + 1][width] = new Tile(CHAP_DOWN);
                break;
        }

        checkInfo();
        checkPad();
        Maze newMaze = new Maze(layout, nextPos, padCount, inventory);
        newMaze.setChips(chips);
        return newMaze;
    }



}
