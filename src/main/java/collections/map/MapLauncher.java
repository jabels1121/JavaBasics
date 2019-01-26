package collections.map;

import java.util.*;

public class MapLauncher {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Map<String, Integer> wordMap = new HashMap<>();
        System.out.println("Please enter some text");
        String string = scanner.nextLine();
        String[] tokens = string.split(" ");
        for (String s : tokens) {
            String word = s.toLowerCase();
            Integer count = wordMap.get(word);
            if (count == null) {
                wordMap.put(word, 1);
            } else {
                wordMap.put(word, count + 1);
            }
        }

        NavigableSet<WordWrapper> wordWrappers = convertToSet(wordMap);
        printSet(wordWrappers);
    }

    private static void printSet(NavigableSet<WordWrapper> wordWrappers) {
        for (WordWrapper wordWrapper:
             wordWrappers) {
            System.out.println();
        }
    }

    private static NavigableSet<WordWrapper> convertToSet(Map<String, Integer> wordMap) {
        NavigableSet<WordWrapper> wordSet = new TreeSet<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            wordSet.add(new WordWrapper(entry.getKey(), entry.getValue()));
        }
        return wordSet;
    }
}
