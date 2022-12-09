package day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 {

    public static void main(String[] args) {


        InputStream inputStream = Day9.class.getClassLoader().getResourceAsStream("day9/example.txt");
        if (inputStream == null) {
            System.out.println("File not found");
            return;
        }

        try (
                InputStreamReader streamReader =
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            Map<Coords, Integer> visitMap = new HashMap<>();
            List<Coords> nodes = new ArrayList<>(List.of(new Coords(0, 0), new Coords(0, 0),
                    new Coords(0, 0), new Coords(0, 0), new Coords(0, 0), new Coords(0, 0),
                    new Coords(0, 0), new Coords(0, 0), new Coords(0, 0), new Coords(0, 0)));
            Coords node;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                Direction direction = Direction.valueOf(split[0]);
                int distance = Integer.parseInt(split[1]);
                for (int d = 0; d < distance; d++) {
                    // move head
                    node = nodes.get(0).displace(direction.coords);
                    nodes.set(0, node);
                    // move rest
                    for (int n = 1; n < nodes.size(); n++) {
                        node = nodes.get(n).newTailPosition(node);
                        nodes.set(n, node);
                    }
                    visitMap.put(node, 1);
                }
            }
            System.out.println(visitMap);
            // print
            System.out.println((long) visitMap.values().size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
