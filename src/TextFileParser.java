import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextFileParser {
    public static void main(String[] args) {
        String rusText = getText("data/textRus.txt");
        System.out.println(getWordsStartingWithVowelLetter(rusText));

        String engText = getText("data/textEng.txt");
        System.out.println(getWordsStartingWithVowelLetter(engText));
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
}