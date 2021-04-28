package helloWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class K {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();

        final List<Coordinate> children = new ArrayList<>();
        Coordinate childA = null;

        final char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            final String line = in.next();
            for (int j = 0; j < line.length(); j++) {
                line.getChars(0, m, map[i], 0);

                final char c = line.charAt(j);
                if (c != '.') {
                    if (c == 'A') {
                        childA = new Coordinate(i, j);
                    } else {
                        children.add(new Coordinate(i, j));
                    }
                }
            }
        }

        assert childA != null;

        int maxArea = 1;
        Coordinate topLeft = childA;
        Coordinate bottomRight = childA;

        for (int i = childA.row; i >= 0; i--) {
            for (int j = childA.row; j < n; j++) {

                int l = 0;
                int r = m - 1;

                for (Coordinate child : children) {
                    if (child.row >= i && child.row <= j) {
                        if (child.col <= childA.col) {
                            l = Math.max(l, child.col + 1);
                        }
                        if (child.col >= childA.col) {
                            r = Math.min(r, child.col - 1);
                        }
                    }
                }

                final int area = (j - i + 1) * (r - l + 1);

                if (area > maxArea) {
                    maxArea = area;
                    topLeft = new Coordinate(i, l);
                    bottomRight = new Coordinate(j, r);
                }
            }
        }

        for (int i = topLeft.row; i <= bottomRight.row; i++) {
            for (int j = topLeft.col; j <= bottomRight.col; j++) {
                if (map[i][j] != 'A') {
                    map[i][j] = 'a';
                }
            }
        }

        final List<Integer> ups = new ArrayList<>();
        final List<Integer> downs = new ArrayList<>();

        for (Coordinate child : children) {
            final char letter = Character.toLowerCase(map[child.row][child.col]);
            int up = child.row - 1;
            while (up >= 0 && map[up][child.col] == '.') {
                map[up][child.col] = letter;
                up--;
            }
            ups.add(up + 1);

            int down = child.row + 1;
            while (down < n && map[down][child.col] == '.') {
                map[down][child.col] = letter;
                down++;
            }
            downs.add(down - 1);
        }

        for (int i = 0; i < children.size(); i++) {
            final Coordinate child = children.get(i);
            expand(map, child, ups.get(i), downs.get(i), -1);
            expand(map, child, ups.get(i), downs.get(i), +1);
        }

        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(map[i]).append('\n');
        }
        System.out.println(builder.toString());
    }

/*
6 8
......X.
.F......
...A....
........
.....P..
..L.....
 */

    private static void expand(final char[][] map, final Coordinate child, final int i, final int j, final int direction) {
        final char letter = map[child.row][child.col];
        int l = child.col + direction;

        while (l >= 0 && l < map[child.row].length) {
            for (int k = i; k <= j; k++) {
                final char c = map[k][l];
                if (c != '.' && c != letter) {
                    return;
                }
            }
            for (int k = i; k <= j; k++) {
                if (map[k][l] != letter) {
                    map[k][l] = Character.toLowerCase(letter);
                }
            }
            l += direction;
        }
    }

    private static class Coordinate {
        final int row;
        final int col;

        Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }
}