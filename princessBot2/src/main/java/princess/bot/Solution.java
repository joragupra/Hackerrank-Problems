package princess.bot;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m;
        m = in.nextInt();
        int r = in.nextInt();
        int c = in.nextInt(); // we don't really use these two params
        List<String> grid = new ArrayList<>(m);
        IntStream.range(0, m).forEach(x -> grid.add(in.next()));

        GridScanner scanner = new GridScanner();
        Location l = scanner.locate(new Grid(grid));
        Bot bot = new Bot(l.robot);
        bot.findPath(l.princess).stream().findFirst().ifPresent(System.out::println);
    }

    enum Move {
        LEFT, RIGHT, UP, DOWN
    }

    static class Bot {

        private Position me;

        Bot(Position me) {
            this.me = me;
        }

        List<Move> findPath(Position princess) {
            int x = princess.x - me.x;
            int y = princess.y - me.y;
            final List<Move> path = new ArrayList<>();
            Move xMove = x < 0 ? Move.LEFT : Move.RIGHT;
            if (x < 0) {
                x = x * -1;
            }
            IntStream.range(0, x).forEach(r -> path.add(xMove));

            Move yMove = y < 0 ? Move.UP : Move.DOWN;
            if (y < 0) {
                y = y * -1;
            }
            IntStream.range(0, y).forEach(r -> path.add(yMove));

            return path;
        }

    }

    static class Grid {

        static final char EMPTY_CELL = '-';

        static final char OCCUPIED_BY_PRINCESS = 'p';

        static final char OCCUPIED_BY_ROBOT = 'm';

        final Character[][] cells;

        Grid(List<String> grid) {
            cells = new Character[grid.size()][];
            IntStream.range(0, grid.size())
                     .forEach(i -> {
                                  Character[] r = new Character[grid.get(i).length()];
                                  int j = 0;
                                  for (char c : grid.get(i).toCharArray()) {
                                      r[j] = Character.valueOf(c);
                                      j++;
                                  }
                                  cells[i] = r;
                              });

        }

    }

    static class GridScanner {

        boolean isEmpty(char cell) {
            return Grid.EMPTY_CELL == cell;
        }

        boolean isPrincessInCell(char cell) {
            return Grid.OCCUPIED_BY_PRINCESS == cell;
        }

        boolean isRobotInCell(char cell) {
            return Grid.OCCUPIED_BY_ROBOT == cell;
        }

        Optional<Integer> findRobot(Character[] row) {
            return find(Grid.OCCUPIED_BY_ROBOT, row);
        }

        Optional<Integer> findPrincess(Character[] row) {
            return find(Grid.OCCUPIED_BY_PRINCESS, row);
        }

        private Optional<Integer> find(Character c, Character[] row) {
            int pos = Arrays.asList(row).indexOf(c);
            return pos == -1 ? Optional.empty() : Optional.of(pos);
        }

        Location locate(Grid grid) {
            return locate(grid.cells);
        }

        Location locate(Character[][] grid) {
            Position robot = new Position();
            Position princess = new Position();
            final int[] y = {0};
            Arrays.asList(grid)
                  .stream()
                  .forEach(row -> {
                               Optional<Integer> robotX = findRobot(row);
                               if (robotX.isPresent()) {
                                   robot.y = y[0];
                                   robot.x = robotX.get();
                               }

                               Optional<Integer> princessX = findPrincess(row);
                               if (princessX.isPresent()) {
                                   princess.y = y[0];
                                   princess.x = princessX.get();
                               }

                               y[0]++;
                           });
            return new Location(robot, princess);
        }

    }

    static class Location {

        Position robot;
        Position princess;

        Location(Position robot, Position princess) {
            this.robot = robot;
            this.princess = princess;
        }

    }

    static class Position {

        int x;
        int y;

        Position() {
        }

    }

}