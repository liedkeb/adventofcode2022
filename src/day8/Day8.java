package day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {
    private static int SIZE = 99;

    public static void main(String[] args) {


        InputStream inputStream = Day8.class.getClassLoader().getResourceAsStream("day8/example.txt");
        if (inputStream == null) {
            System.out.println("File not found");
            return;
        }

        try (
                InputStreamReader streamReader =
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            Map<Coords, Boolean> isVisibleMap = new HashMap<>();
            int[][] sizes = new int[SIZE][SIZE];
            int[] top = new int[SIZE];
            int[] bottom = new int[SIZE];
            int[] left = new int[SIZE];
            int[] right = new int[SIZE];
            Arrays.fill(top, -1);
            Arrays.fill(bottom, -1);
            Arrays.fill(left, -1);
            Arrays.fill(right, -1);
            int y = 0;
            int x = 0;
            while ((line = reader.readLine()) != null) {
                for (x = 0; x < line.length(); x++) {
                    sizes[x][y] = Integer.parseInt(line.substring(x, x + 1));
                    if (x == 0 || x == SIZE - 1 || y == 0 || y == SIZE - 1) {
                        isVisibleMap.put(new Coords(x, y), true);
                        if (x == 0) {
                            left[y] = sizes[x][y];
                        }
                        if (x == SIZE - 1) {
                            right[y] = sizes[x][y];
                        }
                        if (y == 0) {
                            top[x] = sizes[x][y];
                        }
                        if (y == SIZE - 1) {
                            bottom[x] = sizes[x][y];
                        }
                    } else {
                        isVisibleMap.put(new Coords(x, y), false);
                    }
                }
                y++;
            }
            // top
            for (x = 0; x < SIZE; x++) {
                for (y = 0; y < SIZE; y++) {
                    if (sizes[x][y] > top[x]) {
                        top[x] = sizes[x][y];
                        isVisibleMap.put(new Coords(x, y), true);
                    }
                    if (isVisibleMap.get(new Coords(x, y))) {
                        continue;
                    }
                    if (sizes[x][y] == 9) {
                        break;
                    }
                }
            }

//             bottom
            for (x = 0; x < SIZE; x++) {
                for (y = SIZE - 1; y >= 0; y--) {
                    if (sizes[x][y] > bottom[x]) {
                        bottom[x] = sizes[x][y];
                        isVisibleMap.put(new Coords(x, y), true);
                    }
                    if (isVisibleMap.get(new Coords(x, y))) {
                        continue;
                    }
                    if (sizes[x][y] == 9) {
                        break;
                    }

                }
            }

            // left
            for (y = 0; y < SIZE; y++) {
                for (x = 0; x < SIZE; x++) {
                    if (sizes[x][y] > left[y]) {
                        left[y] = sizes[x][y];
                        isVisibleMap.put(new Coords(x, y), true);
                    }
                    if (isVisibleMap.get(new Coords(x, y))) {
                        continue;
                    }
                    if (sizes[x][y] == 9) {
                        break;
                    }
                }
            }

            // right
            for (y = 0; y < SIZE; y++) {
                for (x = SIZE - 1; x >= 0; x--) {
                    if (sizes[x][y] > right[y]) {
                        right[y] = sizes[x][y];
                        isVisibleMap.put(new Coords(x, y), true);
                    }
                    if (isVisibleMap.get(new Coords(x, y))) {
                        continue;
                    }
                    if (sizes[x][y] == 9) {
                        break;
                    }
                }
            }
            print(isVisibleMap);

            List<Integer> score = new ArrayList<>();
            for (x = 0; x < SIZE; x++) {
                for (y = 0; y < SIZE; y++) {
                    int height = sizes[x][y];
                    // top
                    int upDistance = 0;
                    for (int yt = y - 1; yt >= 0; yt--) {
                        upDistance++;
                        if (sizes[x][yt] >= height) {
                            break;
                        }
                    }
                    // bottom
                    int downDistance = 0;
                    for (int yt = y + 1; yt < SIZE; yt++) {
                        downDistance++;
                        if (sizes[x][yt] >= height) {
                            break;
                        }
                    }

                    // left
                    int leftDistance = 0;
                    for (int xt = x - 1; xt >= 0; xt--) {
                        leftDistance++;
                        if (sizes[xt][y] >= height) {
                            break;
                        }
                    }
                    // right
                    int rightDistance = 0;
                    for (int xt = x + 1; xt < SIZE; xt++) {
                        rightDistance++;
                        if (sizes[xt][y] >= height) {
                            break;
                        }
                    }

                    int scorei = upDistance * downDistance * leftDistance * rightDistance;
                    score.add(scorei);
                }
            }
            // print
            System.out.println(Collections.max(score));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print(Map<Coords, Boolean> isVisibleMap) {
        int y;
        int x;
        int total = 0;
        for (y = 0; y < SIZE; y++) {
            for (x = 0; x < SIZE; x++) {
                String visible = isVisibleMap.get(new Coords(x, y)) ? "1" : "0";
                System.out.print(visible);
                total += Integer.parseInt(visible);
            }
            System.out.println();
        }
        System.out.println(total);
    }

}
