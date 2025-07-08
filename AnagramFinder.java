import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AnagramFinder {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java AnagramFinder <input_file>");
            return;
        }

        String filePath = args[0];
        Map<String, List<String>> anagramGroups = new LinkedHashMap<>();

        try {
            Files.lines(Paths.get(filePath))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .forEach(word -> {
                        String key = getAnagramKey(word);
                        anagramGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
                    });
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        for (List<String> group : anagramGroups.values()) {
            Collections.sort(group);
            System.out.println(String.join(" ", group));
        }
    }

    private static String getAnagramKey(String word) {
        char[] chars = word.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
