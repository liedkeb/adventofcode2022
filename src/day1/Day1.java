package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) {
        URL url = Day1.class.getClassLoader().getResource("day1/example.txt");
        if (url == null) {
            System.out.println("file not found...");
            return;
        }
        try (Stream<String> lines = Files.lines(Paths.get(url.toURI()))) {
            long currentCalories = 0;
            List<Long> sorted = new ArrayList<>();
            for (String line : lines.toList()) {
                if (line.isEmpty()) {
                    sorted.add(currentCalories);
                    currentCalories = 0;
                } else {
                    currentCalories += Long.parseLong(line);
                }
            }
            Collections.sort(sorted);
            int size = sorted.size();
            System.out.println("Most calories Elf = " + sorted.get(size - 1));
            System.out.println("Three most caloric Elfs = " + (sorted.get(size - 1) + sorted.get(size - 2) + sorted.get(size - 3)));

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
