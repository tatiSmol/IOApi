import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaFileParser {
    public static void main(String[] args) {
        String fromFile = "data/JavaCodeFilePublic.java";

        changeModifierPublicToPrivate(fromFile, "data/JavaCodeFilePrivate.java");
        reverseText(fromFile, "data/reversedJavaCodeFile.java");
    }

    private static List<String> getText(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().toList();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void writeText(List<String> lines, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void changeModifierPublicToPrivate(String from, String to) {
        List<String> lines = new ArrayList<>();
        for (String line : getText(from)) {
            if (line.stripLeading().startsWith("public")) {
                line = line.replaceAll("\\spublic", "private");
            }
            lines.add(line);
        }
        writeText(lines, to);
    }

    public static void reverseText(String from, String to) {
        List<String> lines = new ArrayList<>();
        for (String line : getText(from)) {
            lines.add(new StringBuilder(line).reverse().toString());
        }
        writeText(lines, to);
    }
}
