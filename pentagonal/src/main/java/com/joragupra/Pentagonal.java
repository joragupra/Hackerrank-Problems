package com.joragupra;

public class Pentagonal {

    public int pentagonal(int n) {
        if (n > 1) {
            int newPentagon = formNewPentagon(n);
            int previousPentagonalNumber = pentagonal(n - 1);
            int commonDots = assemble(n - 1, 2, 0);
            return newPentagon + previousPentagonalNumber - commonDots;
        }
        return 1;
    }

    int formNewPentagon(int n) {
        return assemble(n, 5, 0) - 1;
    }

    int assemble(int points, int edges, int count) {
        if (edges == 1) {
            return points + count;
        }

        return assemble(points, edges - 1, count + points - 1);
    }

}
