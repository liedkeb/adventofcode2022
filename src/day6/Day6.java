package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

public class Day6 {

    public static final int MARKER = 14;

    public static void main(String[] args) {

        InputStream inputStream = Day6.class.getClassLoader().getResourceAsStream("day6/example.txt");
        if (inputStream == null) {
            System.out.println("File not found");
            return;
        }
        try (
                InputStreamReader streamReader =
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            long position = 1;
            Queue<Character> queue = new LinkedList<>();
            CharBuffer buffer = CharBuffer.allocate(1);
            while (reader.read(buffer) > 0) {
                buffer.flip();
                queue.add(buffer.get());
                if (queue.size() > MARKER) {
                    queue.poll();
                }
                if (queue.size() == MARKER && queue.stream().distinct().count() == MARKER) {
                    break;
                }
                buffer.clear();
                position++;
            }
            System.out.println("position: " + position);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
