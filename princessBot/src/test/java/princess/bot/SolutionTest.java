package princess.bot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.*;

public class SolutionTest {

    private static final char BOT_CELL = 'm';

    private static final char PRINCESS_CELL = 'p';

    private static final char EMPTY_CELL = 45;

    @Test
    public void testIsEmptyCell() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();

        assertTrue(EMPTY_CELL + " should be recognized as empty cell", gridScanner.isEmpty(EMPTY_CELL));
        assertFalse(PRINCESS_CELL + " should not be recognized as empty cell", gridScanner.isEmpty(PRINCESS_CELL));
        assertFalse(BOT_CELL + " should not be recognized as empty cell", gridScanner.isEmpty(BOT_CELL));
    }

    @Test
    public void testPrincessIsInCell() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();

        assertTrue(PRINCESS_CELL + " is the cell where the princess is", gridScanner.isPrincessInCell(PRINCESS_CELL));
        assertFalse(EMPTY_CELL + " is not the cell where the princess is", gridScanner.isPrincessInCell(EMPTY_CELL));
        assertFalse(BOT_CELL + " is not the cell where the princess is", gridScanner.isPrincessInCell(BOT_CELL));
    }

    @Test
    public void testRobotIsInCell() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();

        assertTrue(BOT_CELL + " is the cell where the robot is", gridScanner.isRobotInCell(BOT_CELL));
        assertFalse(EMPTY_CELL + " is not the cell where the robot is", gridScanner.isRobotInCell(EMPTY_CELL));
        assertFalse(PRINCESS_CELL + " is not the cell where the robot is", gridScanner.isRobotInCell(PRINCESS_CELL));
    }

    @Test
    public void testFindRobotInRow() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();

        assertEquals(1, gridScanner.findRobot(new Character[]{'-', 'm', '-', '-'}).get().intValue());
    }

    @Test
    public void testFindRobotInRow_RobotIsNotInRow() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();

        assertFalse(gridScanner.findRobot(new Character[]{'-', '-', '-', '-'}).isPresent());
        assertFalse(gridScanner.findRobot(new Character[]{'-', 'p', '-', '-'}).isPresent());
    }

    @Test
    public void testFindPrincessInRow() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();

        assertEquals(1, gridScanner.findPrincess(new Character[]{'-', 'p', '-', '-'}).get().intValue());
    }

    @Test
    public void testFindPrincessInRow_RobotIsNotInRow() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();

        assertFalse(gridScanner.findPrincess(new Character[]{'-', '-', '-', '-'}).isPresent());
        assertFalse(gridScanner.findPrincess(new Character[]{'-', '-', 'm', '-'}).isPresent());
    }

    @Test
    public void testLocate() {
        Solution.GridScanner gridScanner = new Solution.GridScanner();
        Character[][] grid = new Character[][]{{'-', '-', '-', '-'},{'-', 'm', '-', '-'},{'-', '-', '-', '-'},{'-', '-', 'p', '-'}};

        assertEquals(1, gridScanner.locate(grid).robot.x);
        assertEquals(1, gridScanner.locate(grid).robot.y);
        assertEquals(2, gridScanner.locate(grid).princess.x);
        assertEquals(3, gridScanner.locate(grid).princess.y);
    }
}
