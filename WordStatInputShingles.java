package helloWorld;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class WordStatInputShingles {

    public static void main(String[] args) {
        String inputFileName = args[0];
        String outputFileName = args[1];
        Scanner in = null;
        Formatter out = null;

        try {
            in = new Scanner(new File(inputFileName));
            out = new Formatter(new File(outputFileName));

            List<WordAndQuantity> words = new ArrayList<>();

            while (in.hasNextLine()) {
                String line = in.nextLine().toLowerCase();
                StringBuilder word = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'') {
                        word.append(ch);
                    } else if (word.length() > 0) {
                        addWord(word, words);
                        word.setLength(0);
                    }
                }

                if (word.length() > 0) {
                    addWord(word, words);
                }
            }

            for (WordAndQuantity word : words) {
                out.format(word.toString() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        } finally {
            assert in != null;
            in.close();
            assert out != null;
            out.close();
        }
    }

    private static boolean suchAWordAlreadyExists(String word, List<WordAndQuantity> words) {
        for (WordAndQuantity wordAndQuantity : words) {
            if (wordAndQuantity.word.equals(word)) {
                return true;
            }
        }

        return false;
    }

    private static void addWord(StringBuilder word, List<WordAndQuantity> words) {
        for (int i = 0; i < word.length() - 2; i++) {
            String subString = word.substring(i, i + 3);
            if (suchAWordAlreadyExists(subString, words)) {
                for (WordAndQuantity wordAndQuantity : words) {
                    wordAndQuantity.increaseTheQuantity(subString);
                }
            } else {
                words.add(new WordAndQuantity(subString));
            }

        }
        word.setLength(0);
    }
}
