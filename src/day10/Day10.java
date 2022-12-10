package day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day10 {

    public static void main(String[] args) {


        InputStream inputStream = Day10.class.getClassLoader().getResourceAsStream("day10/test.txt");
        if (inputStream == null) {
            System.out.println("File not found");
            return;
        }

        try (
                InputStreamReader streamReader =
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            long register = 1L;
            long cycle = 1;
            long signalStrength = 0L;
            String prevWord = "";
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if ((cycle - 20) % 40 == 0) {
                        signalStrength += cycle * register;
                        System.out.println(register + " " + cycle * register);
                    }

                    if (prevWord.equals("addx")) {
                        register += Long.parseLong(word);
                    }

                    prevWord = word;
                    cycle++;
                }
            }
            // print
            System.out.println("signal strength: " + signalStrength);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
