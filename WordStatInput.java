import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStat {

    public static void main(String[] args) {
        final String inputFileName = args[0];
        final String outputFileName = args[1];

        try {
            final BufferedReader in = new BufferedReader(new FileReader(new File(inputFileName), StandardCharsets.UTF_8));
            try {
                LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
                StringBuilder word = new StringBuilder();
                char[] buffer = new char[1024];
                int read = in.read(buffer);

                while (read != -1) {
                    String string = new String(buffer, 0, read).toLowerCase();
                    read = in.read(buffer);
                    addWordToList(string, word, words);
                }

                final Writer out = new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8);
                try {
                    for (Map.Entry<String, Integer> entry : words.entrySet()) {
                        out.write(entry.getKey() + " " + entry.getValue() + "\n");

                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.out.println("I/O error " + e.getMessage());
        }
    }

    private static void addWordToList(String string, StringBuilder word, LinkedHashMap<String, Integer> words) {
        for (int i = 0; i < string.length(); i++) {
            char now = string.charAt(i);
            if (Character.isLetter(now) || Character.getType(now) == Character.DASH_PUNCTUATION || now == '\'') {
                word.append(now);
            } else if (word.length() > 0) {
                if (words.containsKey(word.toString())) {
                    words.put(word.toString(), words.get(word.toString()) + 1);
                } else {
                    words.put(word.toString(), 1);
                }
                word.setLength(0);
            }
        }
    }
}
