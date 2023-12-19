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
        String engText = getText("data/textEng.txt");

        System.out.println("Найти и вывести в консоль все слова, начинающиеся с гласной буквы:");
        System.out.println(getWordsStartingWithVowelLetter(rusText));
        System.out.println(getWordsStartingWithVowelLetter(engText));

        System.out.println("\nНайти и вывести в консоль все слова, для которых последняя буква одного слова " +
                "совпадает с первой буквой следующего слов:");
        System.out.println(String.join(", ", getWordsWithMatchingChars(rusText)));
        System.out.println(String.join(", ", getWordsWithMatchingChars(engText)));

        System.out.println("\nВ каждой строке найти и вывести наибольшее число цифр, идущих подряд:");

        System.out.println("\tЦифровой текст:");
        List<Integer> digitsCount = findLongestDigitSequenceInEachRow("data/digitalPoem.txt");
        digitsCount.stream()
                .map(count -> count + " ")
                .forEach(System.out::print);

        System.out.println("\n\tТекст без цифр:");
        List<Integer> digitsCount2 = findLongestDigitSequenceInEachRow("data/textRus.txt");
        digitsCount2.stream().map(count -> count + " ").forEach(System.out::print);
    }

    private static String getText(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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

    private static List<Integer> findLongestDigitSequenceInEachRow(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.replaceAll("\\D+", " ").trim())
                    .map(line -> Arrays.stream(line.split("\\s+"))
                            .mapToInt(String::length)
                            .max().orElse(0))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}