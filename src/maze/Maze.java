package maze;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;

import java.util.ArrayList;

/**
 * A Maze instance represents a configuration of tiles for a game of Chip's Challenge.
 *
 * @author Matthew
 * @author Josh
 * @author Melina
 */
public class Maze {
    /**
     * The complete layout of the maze.
     */
    private Tile[][] layout;

    /**
     * The 9x9 grid visible to the player, centered on Chap.
     */
    private Tile[][] scope;

    /**
     * The width of the layout.
     */
    private int width;

    /**
     * The height of the layout.
     */
    private int height;

    /**
     * Chap's current position within the maze.
     */
    private Position chapPos; // Chap's current position


    ArrayList<Tile.Type> getInventory() {
        return inventory;
    }

    private int chips;
    private int totalPads;

    private ArrayList<Tile.Type> inventory;


    private final BufferedImage freeTile = openImage("free_tile.jpg");
    private final BufferedImage goldKey = openImage("gold_key.jpg");
    private final BufferedImage pinkKey = openImage("pink_key.jpg");
    private final BufferedImage whiteKey = openImage("white_key.jpg");
    private final BufferedImage blueKey = openImage("blue_key.jpg");
    private final BufferedImage goldDoor = openImage("gold_door.jpg");
    private final BufferedImage pinkDoor = openImage("pink_door.jpg");
    private final BufferedImage whiteDoor =  openImage("white_door.jpg");
    private final BufferedImage blueDoor = openImage("blue_door.jpg");
    private final BufferedImage padDoor1 = openImage("pad_door.jpg");
    private final BufferedImage padDoor2 = openImage("pad_door.jpg");
    private final BufferedImage padDoor3 = openImage("pad_door.jpg");
    private final BufferedImage weightPad = openImage("weight_pad.jpg");
    private final BufferedImage wallTile = openImage("wall_tile.jpg");
    private final BufferedImage chapDown = openImage("chap_down.png");
    private final BufferedImage chapLeft = openImage("chap_left.png");
    private final BufferedImage chapRight = (openImage("chap_right.png"));
    private final BufferedImage chapUp = openImage("chap_up.png");
    private final BufferedImage treasure = openImage("treasure_chip.jpg");
    private final BufferedImage infoField = openImage("info_field.jpg");
    private final BufferedImage exit = openImage("portal.gif");
    private final BufferedImage blueKey2 = openImage("blue_key_two.jpg");
    private final BufferedImage goldKey2 = openImage("gold_key_two.jpg");
    private final BufferedImage whiteKey2 = openImage("white_key_two.jpg");
    private final BufferedImage pinkKey2 = openImage("pink_key_two.jpg");
    private final BufferedImage exitLock = openImage("exit_lock.jpg");
    private final BufferedImage box = openImage("box-01.jpg");


    /**
     * Constructs the Maze.
     *
     * @param layout The 'blueprint' of the maze
     * @param chap  Chap's current position
     */
    Maze(Tile[][] layout, Position chap, int padCount, ArrayList<Tile.Type> inventory){
        this.layout = layout;
        this.chapPos = chap;
        this.inventory = inventory;
        this.totalPads = padCount;
        scope = findScope(layout, chap);
    }

    /**
     Prints the game board onto the GUI using given icons
     @param g Graphic that the image will be drawn onto
     */

    void printOnGraphics(Graphics g) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Tile.Type type = scope[i][j].getType();
                g.drawImage(getTileImage(type), j * 40, i * 40, null);
            }
        }
    }
    /**
     Prints the items in the players inventory onto the GUI
     @param g Graphic that the image will be drawn onto
     */
    void printInventoryOnGraphics(Graphics g) {
        for (int i  = 0; i < getInventory().size(); i++)
            if (i < 3) g.drawImage(getTileImage(getInventory().get(i)), i * 40, 0, null);
            else g.drawImage(getTileImage(getInventory().get(i)), (i - 3) * 40, 40, null);
    }

    /**
     Prints the the number of chips left onto the GUI using given icons
     */
    void printNumChipsLeft(Graphics g) {
        g.setFont(new Font("Ariel", Font.PLAIN, 17));
        g.drawString(Integer.toString(getChips()), 52, 15);
    }
    /**
     Uses a switch case in order to match corresponding tiles to image icons
     @param type The tile type that will be used in the switch case
     */

    private BufferedImage getTileImage(Tile.Type type){
        switch (type) {
            case WALL:
                return wallTile;
            case BOX:
                return box;
            case FREE:
                return freeTile;
            case CHAP:
            case CHAP_DOWN:
                return chapDown;
            case CHAP_LEFT:
                return chapLeft;
            case CHAP_RIGHT:
                return chapRight;
            case CHAP_UP:
                return chapUp;
            case BLUE_DOOR:
                return blueDoor;
            case PINK_DOOR:
                return pinkDoor;
            case GOLD_DOOR:
                return goldDoor;
            case WHITE_DOOR:
                return whiteDoor;
            case WEIGHT_PAD:
                return weightPad;
            case PAD_DOOR1:
                return padDoor1;
            case PAD_DOOR2:
                return padDoor2;
            case PAD_DOOR3:
                return padDoor3;
            case BLUE_KEY:
                return blueKey;
            case GOLD_KEY:
                return goldKey;
            case PINK_KEY:
                return pinkKey;
            case WHITE_KEY:
                return whiteKey;
            case BLUE_KEY_2:
                return blueKey2;
            case GOLD_KEY_2:
                return goldKey2;
            case PINK_KEY_2:
                return pinkKey2;
            case WHITE_KEY_2:
                return whiteKey2;
            case TREASURE:
                return treasure;
            case INFO:
                return infoField;
            case EXIT:
                return exit;
            case EXIT_LOCK:
                return exitLock;
        }
        return null;
    }



    /**
     * Create a scope centred on Chap's current position.
     *
     * @param chap Chap's version within the layout
     * @param layout The information about nearby tiles
     *
     * @return Scope - A centred view of Chap's surroundings
     */
    private Tile[][] findScope(Tile[][] layout, Position chap){
        scope = new Tile[9][9];

        int height = chap.getHeight();
        int width = chap.getWidth();

        for (int i = 0; i < 9; i++ )
            System.arraycopy(layout[height - 4 + i], width - 4, scope[i], 0, 9);
        return scope;
    }

    /**
     * Creates a string version of the maze from an array of Tiles.
     *
     * @return string version of the maze
     */
    public String toString() {
        StringBuilder mazeAsString = new StringBuilder();

        for (int x = 0; x < height; x++)
            for (int y = 0; y < width; y++)
                mazeAsString.append(layout[x][y].toChar());

        return mazeAsString.toString();
    }


    /**
     * Image handler helper method
     *
     * @param fileName -- Path to file
     * @return Image
     */
    private BufferedImage openImage(String fileName){
        try {
            return resize(ImageIO.read(new File("design/game_pieces/img/" + fileName)));}
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Resize helper method
     * @param img -- Takes a buffered image
     * @return -- returns the image resized to be 40 x 40 pixels
     */
    private static BufferedImage resize(BufferedImage img) {
        Image tmp = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    /**
     * Getter method for Chap's current position in this maze.
     *
     * @return Chap's position as a Position class
     */
    Position getChapPos() {
        return chapPos;
    }

    public int getTotalPads(){
        return totalPads;
    }

    /**
     * Getter method for the layout of this maze.
     *
     * @return The layout as a Tile array
     */
    Tile[][] getLayout() {
        return layout;
    }

    /**
     * Setter method for the width of the grid.
     *
     * @param width from the parser
     */
    void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setter method for the height of the grid.
     *
     * @param height from the parser
     */
    void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter method for the number of chips.
     *
     * @return number of chips
     */
    int getChips() {
        return chips;
    }
    /**
     * Setter method for the number of the chips
     * @param chips -- number of chips
     */
    void setChips(int chips) {
        this.chips = chips;
    }

}
