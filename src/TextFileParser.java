import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextFileParser {
    public static void main(String[] args) {
        String rusText = getText("data/textRus.txt");
        System.out.println(getWordsStartingWithVowelLetter(rusText));
        System.out.println(String.join(", ", getWordsWithMatchingChars(rusText)));

        String engText = getText("data/textEng.txt");
        System.out.println(getWordsStartingWithVowelLetter(engText));
        System.out.println(String.join(", ", getWordsWithMatchingChars(engText)));
    }

    private static String getText(String fileName) {
        try (BufferedReader reader  = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line ->
                            line.replaceAll("[\\p{Punct}—]", "").stripTrailing())
                    .collect(Collectors.joining(" "));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String getWordsStartingWithVowelLetter(String text) {
        String regex = "[аеёиоуыэюяaeiouАЕЁИОУЫЭЮЯAEIOU][а-яА-Яa-zA-Z]*";
        return Arrays.stream(text.split("\\s+"))
                .filter(word -> word.matches(regex))
                .collect(Collectors.joining("\s"));
    }

    private static List<String> getWordsWithMatchingChars(String text) {
        String[] words = text.toLowerCase().split("\\s+");

        return IntStream.range(0, words.length - 1)
                .filter(i -> words[i].charAt(words[i].length() - 1) == words[i + 1].charAt(0))
                .mapToObj(i -> words[i] + " " + words[i + 1])
                .toList();
    }
}