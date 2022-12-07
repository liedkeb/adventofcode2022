package day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day4 {
    public static void main(String[] args) {

        URL url = Day4.class.getClassLoader().getResource("day4/example.txt");
        if (url == null) {
            System.out.println("file not found...");
            return;
        }
        long total = 0;
        long totalPart2 = 0;
        try (Stream<String> lines = Files.lines(Paths.get(url.toURI()))) {
            for (String line : lines.toList()) {
                String[] pair = line.split(",");
                String[] range1s = pair[0].split("-");
                String[] range2s = pair[1].split("-");
                Range range1 = new Range(Integer.parseInt(range1s[0]), Integer.parseInt(range1s[1]));
                Range range2 = new Range(Integer.parseInt(range2s[0]), Integer.parseInt(range2s[1]));
                if (range1.contains(range2) || range2.contains(range1)) {
                    total++;
                }
                if( range1.overlaps(range2)) {
                    totalPart2++;
                }
            }
            System.out.println("Total size " + total);
            System.out.println("Total size " + totalPart2);

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
