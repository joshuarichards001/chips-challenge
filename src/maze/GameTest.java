package maze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.io.File;

class GameTest {

    private Game gameUnderTest;

    @BeforeEach
    void setUp() {
        gameUnderTest = new Game("level1.txt");
    }

    @Test
    void testAddNotify() {
        // Setup

        // Run the test
        gameUnderTest.addNotify();

        // Verify the results
    }

    @Test
    void testKeyTyped() {
        // Setup
        final KeyEvent e = null;

        // Run the test
        gameUnderTest.keyTyped(e);

        // Verify the results
    }

    @Test
    void testKeyPressed() {
        // Setup
        final KeyEvent e = null;

        // Run the test
        gameUnderTest.keyPressed(e);

        // Verify the results
    }

    @Test
    void testKeyReleased() {
        // Setup
        final KeyEvent e = null;

        // Run the test
        gameUnderTest.keyReleased(e);

        // Verify the results
    }

    @Test
    void testSaveGame() {
        // Setup

        // Run the test
        gameUnderTest.saveGame();

        // Verify the results
    }

    @Test
    void testLoad() {
        // Setup
        final File file = null;

        // Run the test
        gameUnderTest.loadGame(file);

        // Verify the results
    }

    @Test
    void testMain() {
        // Setup
        final String[] args = new String[]{};

        // Run the test
        Game.main(args);

        // Verify the results
    }
}
