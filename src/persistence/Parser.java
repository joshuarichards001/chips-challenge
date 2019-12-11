package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * parses in a txt file representing a level and turns it into a character array
 *
 * @author Josh Richards
 */
public class Parser {

    /**
     * level represented as an array of characters
     */
    private char[][] levelAsArray;

    /**
     * returns the width of the level
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns the height of a level
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    private int width, height;

    /**
     * constructor which calls the main method of the class
     *
     * @param file
     */
    public Parser(String file) {
        parseFile(file);
    }

    /**
     * takes in a file in string form and turns it into a character array
     *
     * @param file
     */
    private void parseFile(String file){
        try {
            Stream<String> lines = Files.lines(Paths.get(file));
            String string = lines.collect(Collectors.joining(System.lineSeparator()));
            String[] rows = string.split("\r*\n");
            levelAsArray = new char[rows.length][rows[0].length()];
            this.height = rows.length;
            this.width = rows[0].length();
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    levelAsArray[i][j] = rows[i].charAt(j);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * returns the board as a character array
     *
     * @return
     */
    public char[][] getLevelAsArray() {
        return levelAsArray;
    }
}
