package maze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveTest {

    private Move moveUnderTest;

    @BeforeEach
    void setUp() {
        moveUnderTest = new Move(null, null);
    }

    @Test
    void testIsValid() {
        // Setup

        // Run the test
        final boolean result = moveUnderTest.isValid();

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testApply() {
        // Setup
        final Maze expectedResult = null;

        // Run the test
        final Maze result = moveUnderTest.apply();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
