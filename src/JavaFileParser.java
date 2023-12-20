import java.io.*;

public class JavaFileParser {
    public static void main(String[] args) {
        String fromFile = "data/JavaCodeFilePublic.java";
        String toFile = "data/JavaCodeFilePrivate.java";
        changeModifierPublicToPrivate(fromFile, toFile);
    }

    public static void changeModifierPublicToPrivate(String from, String to) {
        try (BufferedReader reader = new BufferedReader(new FileReader(from));
             BufferedWriter writer = new BufferedWriter(new FileWriter(to))) {

            for (String line : reader.lines().toList()) {
                if (line.stripLeading().startsWith("public")) {
                    System.out.println(line);
                    line = line.replaceAll("\\spublic", "private");
                }

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
