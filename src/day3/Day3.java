package day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    public static void main(String[] args) {

        URL url = Day3.class.getClassLoader().getResource("day3/example.txt");
        if (url == null) {
            System.out.println("file not found...");
            return;
        }
        try (Stream<String> lines = Files.lines(Paths.get(url.toURI()))) {
            long totalSize = 0;
            long totalThreeBackpackSize = 0;
            int lineNumber = 0;
            String firstBackpack = "";
            String secondBackpack = "";
            String thirdBackpack = "";
            for (String line : lines.toList()) {
                if (lineNumber % 3 == 0) {
                    firstBackpack = line;
                } else if (lineNumber % 3 == 1) {
                    secondBackpack = line;
                } else if (lineNumber % 3 == 2) {
                    thirdBackpack = line;
                }
                String firstHalf = line.substring(0, line.length() / 2);
                String secondHalf = line.substring(line.length() / 2);
                {
                    char aChar = getRepeatedChar(firstHalf, secondHalf).stream().findFirst().get();
                    totalSize += getPriority(aChar);
                }
                lineNumber++;
                if (lineNumber % 3 == 0 && !firstBackpack.isEmpty() && !secondBackpack.isEmpty() && !thirdBackpack.isEmpty()) {
                    Set<Character> chars = getRepeatedChar(firstBackpack, secondBackpack);
                    String finalThirdBackpack = thirdBackpack;
                    char aChar = chars.stream().filter(p -> finalThirdBackpack.contains("" + p)).findFirst().get();
                    totalThreeBackpackSize += getPriority(aChar);
                }

            }
            System.out.println("Total size " + totalSize);
            System.out.println("Total three size " + totalThreeBackpackSize);

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    private static Set<Character> getRepeatedChar(String first, String second) {
        return first.chars().mapToObj(c -> (char) c).filter(p -> second.contains("" + p)).collect(Collectors.toSet());
    }

    private static long getPriority(char aChar) {
        final int priority;
        if (Character.isUpperCase(aChar)) {
            priority = aChar - (int) 'A' + 27;
        } else {
            priority = aChar - (int) 'a' + 1;
        }
        return priority;
    }
}
