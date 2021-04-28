package helloWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseMin {

    public static void main(String[] args) {
        // Существует более быстрое решение, но менее наглядное
        final Scanner in = new Scanner(System.in);
        List<List<Integer>> matrix = new ArrayList<>();

        while(in.hasNextLine()) {
            String line = in.nextLine();
            List<Integer> numbersInLine = new ArrayList<>();
            Scanner sc = new Scanner(line);
            while (sc.hasNextInt()) {
                numbersInLine.add(sc.nextInt());
            }
            matrix.add(numbersInLine);
        }

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(minInRowCol(i, j, matrix) + " ");
            }
            System.out.println();
        }
    }

    private static int minInRowCol(int i, int j, List<List<Integer>> matrix) {
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;

        for (int e = 0; e < matrix.get(i).size(); e++) {
            int element = matrix.get(i).get(e);
            if (element < minRow) {
                minRow = element;
            }
        }

        for (List<Integer> integers : matrix) {
            if (integers.size() > j) {
                int element = integers.get(j);
                if (element < minCol) {
                    minCol = element;
                }
            }
        }

        return Math.min(minRow, minCol);
    }
}
