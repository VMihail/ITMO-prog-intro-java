package helloWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reverse {

    public static void main(String[] args) {
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

        for (int i = matrix.size() - 1; i >= 0; i--) {
            for (int j = matrix.get(i).size() - 1; j >= 0; j--) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
