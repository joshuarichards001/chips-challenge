package maze;

import application.MainFrame;
import persistence.Parser;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;

import static maze.Move.Direction.*;


/**
 * Game Class.
 * This class is responsible for setting up and running the game,
 * It extends the mainframe class in order to be able to be displayed.
 *
 * @author Matthew Jones
 * @author Campbell Longmire
 * @author Joshua Richards
 * @author Melina Ariyani
 */

public class Game extends MainFrame implements KeyListener {

    /**
     * The complete layout of the maze.
     */
    private Tile[][] layout;

    /**
     * The maze object.
     */
    private Maze maze;

    /**
     * The height and width of the grid.
     */
    private int HEIGHT, WIDTH;

    /**
     * The file name of the level
     */
    private String level;

    /**
     * The relevant times for the games
     */
    long startTime;
    int endTime = 100;
    int finishTime;

    boolean noTime;

    double pauseTime;
    double currentTime;

    long countdown;
    boolean paused = false;
    private int totalPads = 10;
    Position info;
    Position exit;


    /**
     * The game object.
     * This is where the game is set up on the screen and run
     *
     * @param levelName -- takes the level name as a string in order to parse the correct file
     */
    public Game(String levelName) {
        // Set up level 1

        setUpGame(levelName);
        level = levelName;
        info = findInfo();
        exit = findExit();
        // Add a key listener for move input
        SwingUtilities.invokeLater(() -> {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            addKeyListener(this);
        });
    }

    /**
     * Overrides the redraw method so that the maze can be rendered on the screen
     */
    @Override
    protected void redraw(Graphics g) {
        maze.printOnGraphics(g);
    }

    /**
     * Overrides the inventory redraw method so the inventory can be rendered on the screen
     */
    protected void redrawInventory(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 3 * 40, 2 * 40);
        maze.printInventoryOnGraphics(g);

    }

    /**
     * Draws the number of chips left in the game on the screen
     */
    protected void chipsLeftGraphics(Graphics g) {
        maze.printNumChipsLeft(g);
    }

    /**
     * Draws the time left on the screen
     */
    protected void timeLeft(Graphics g) {
        double elapsedTime = System.currentTimeMillis() - startTime;

        int time = (int) elapsedTime / 1000;
        g.setFont(new Font("Ariel", Font.PLAIN, 17));
        if (time >= 100) {
            time = 100;
            noTime = true;
        }
        if (paused == false) {
            g.drawString(Double.toString(endTime - time + pauseTime), 52, 15);
            finishTime = time;
            countdown = endTime - time;
            currentTime = time;

        } else if (paused) {
            pauseTime = (time - currentTime);

            g.drawString(Double.toString(countdown), 52, 15);
        }
    }

    /**
     * Applies a valid move to the game
     *
     * @param move -- the move to be applied
     */
    private void apply(Move move) {

        // Checks the move is valid
        if (move.isValid()) {

            // Checks if the game has run out of time
            if (!noTime) {
                this.maze = move.apply();
            } else {
                final JFrame parent = new JFrame();
                JButton button = new JButton();

                parent.add(button);
                parent.pack();
                parent.setVisible(false);
                JOptionPane.showMessageDialog(null, "You Lose! You have run out of time, Please select 'New Game' from the 'Game' tab to start again", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

            // Checks if chap has landed on exit tile
            if (findChap().equals(exit)) {
                this.paused = !paused;
                DialogBox box = new DialogBox();
                box.infoBox("You finished in " + finishTime + " seconds", "YOU WON!!");
                new Game("level2.txt");
            }
            // Checks if chap has landed on an info tile in level 1
            if (level.equals("level1.txt") && (findChap().equals(info))) {
                this.paused = !paused;
                DialogBox box = new DialogBox();
                box.infoBox("Make sure you collect all the chips in the level and the head to the exit to move onto the next level (Enter to exit)", "Info");
                this.paused = !paused;
            }

            // Checks if chap has landed on an info tile in level 2
            if (level.equals("level2.txt") && (findChap().equals(info))) {

                this.paused = !paused;
                DialogBox box = new DialogBox();
                box.infoBox("Unlock the pink gates by pushing the boxes onto the pink pads. They will unlock when they're all covered! (Enter to exit)", "Info");
                this.paused = !paused;
            }

        }
    }

    /**
     * Sets up the game.
     * This will always be the level in level1.txt first.
     * The chap position is set using the findChap() method.
     * Makes a new maze
     *
     * @param levelName -- the level we are loading
     */
    private void setUpGame(String levelName) {
        noTime = false;
        startTime = System.currentTimeMillis();
        createLayout(levelName);
        Position chapPos = findChap();
        maze = new Maze(layout, chapPos, totalPads, new ArrayList<>());
        maze.setChips(getTotalChips());
        maze.setHeight(HEIGHT);
        maze.setWidth(WIDTH);
    }

    /**
     * Finds chap on the board.
     *
     * @return His position
     */
    private Position findChap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(layout[i][j].toChar());
                if (layout[i][j].toChar() == 'C') {
                    return new Position(i, j);
                }
            }
            System.out.println();
        }
        System.out.println("hi");
        return null;
    }

    /**
     * Finds the exit.
     *
     * @return the exit's position
     */
    private Position findExit() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (layout[i][j].getType().equals(Tile.Type.EXIT)) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    /**
     * Finds the info tile.
     *
     * @return the info's position
     */
    private Position findInfo() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (layout[i][j].toChar() == '?') {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    /**
     * Finds the number of chips.
     *
     * @return the number of total chips in the game
     */
    private int getTotalChips() {
        int totalChips = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (layout[i][j].getType().equals(Tile.Type.TREASURE)) {
                    totalChips++;
                }
            }
        }
        return totalChips;
    }


    /**
     * Return a Tile based on Char.
     * This is useful for the parser.
     *
     * @param c The character to base Type from
     * @return Tile with an appropriate type.f
     */
    private Tile createTileFromChar(char c) {
        switch (c) {
            case '#':
                return new Tile(Tile.Type.WALL);
            case '_':
                return new Tile(Tile.Type.FREE);
            case 'P':
                return new Tile(Tile.Type.PINK_KEY);
            case 'B':
                return new Tile(Tile.Type.BLUE_KEY);
            case 'W':
                return new Tile(Tile.Type.WHITE_KEY);
            case 'G':
                return new Tile(Tile.Type.GOLD_KEY);
            case 'K':
                return new Tile(Tile.Type.PINK_DOOR);
            case 'L':
                return new Tile(Tile.Type.BLUE_DOOR);
            case 'D':
                return new Tile(Tile.Type.GOLD_DOOR);
            case 'E':
                return new Tile(Tile.Type.WHITE_DOOR);
            case '@':
                return new Tile(Tile.Type.WEIGHT_PAD);
            case '&':
                return new Tile(Tile.Type.PAD_DOOR1);
            case 'M':
                return new Tile(Tile.Type.PAD_DOOR2);
            case 'Z':
                return new Tile(Tile.Type.PAD_DOOR3);
            case '?':
                return new Tile(Tile.Type.INFO);
            case '$':
                return new Tile(Tile.Type.TREASURE);
            case '+':
                return new Tile(Tile.Type.EXIT_LOCK);
            case 'o':
                return new Tile(Tile.Type.EXIT);
            case 'C':
                return new Tile(Tile.Type.CHAP);
            case 'X':
                return new Tile(Tile.Type.BOX);
            default:
                return null;
        }
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Move move;
        switch (key) {
            case 37:
            case 65:
                move = new Move(maze, LEFT);         // Left Arrow, A
                break;
            case 38:
            case 87:
                move = new Move(maze, UP);      // Up Arrow, W
                break;
            case 39:
            case 68:
                move = new Move(maze, RIGHT);   // Right Arrow, D
                break;
            case 40:
            case 83:
                move = new Move(maze, DOWN);    // Down Arrow, S
                break;
            default:
                move = null;
        }
        if (move != null) apply(move);
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    /**
     * Method for creating a layout using a Parser class
     *
     * @param file -- the file we are parsing
     */
    private void createLayout(String file) {
        Parser parser = new Parser(file);
        this.WIDTH = parser.getWidth();
        this.HEIGHT = parser.getHeight();

        char[][] levelAsString = parser.getLevelAsArray();
        layout = new Tile[HEIGHT][WIDTH];

        for (int x = 0; x < HEIGHT; x++){
            for (int y = 0; y < WIDTH; y++){
                layout[x][y] = createTileFromChar(levelAsString[x][y]);
            }
        }
    }

    /**
     * Method for creating a layout using a Parser class
     */
    public void saveGame() {
        try (PrintWriter out = new PrintWriter("SavedGame.txt")) {
            StringBuilder GameBoard = new StringBuilder();
            for (Tile[] tiles : layout) {
                for (Tile tile : tiles) {
                    GameBoard.append(tile.toChar());
                }
                GameBoard.append("\n");
            }
            out.print(GameBoard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a new game.
     */
    @Override
    protected void newGame() {
        setUpGame(level);
    }

    /**
     * pauses the game.
     */
    @Override
    protected void pauseGame() {
        this.paused =  !paused;
        DialogBox box = new DialogBox();
        box.infoBox("Paused", "Paused");
        this.paused = !paused;
    }


    /**
     * Loads a game from a file.
     */
    protected void loadGame(File file) {

        setUpGame(file.getName());
    }

    /**
     * Main method for starting the game.
     */
    public static void main(String[] args){
        new Game("level1.txt");
    }


}
