package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

public class Day7 {

    public static void main(String[] args) {

        InputStream inputStream = Day7.class.getClassLoader().getResourceAsStream("day7/example.txt");
        if (inputStream == null) {
            System.out.println("File not found");
            return;
        }

        try (
                InputStreamReader streamReader =
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            Tree.Node currentNode = null;
            Tree tree = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("$")) {
                    if (line.contains("$ cd")) {
                        String folder = line.split("cd ")[1];
                        if (folder.equals("/")) {
                            tree = new Tree(new Root());
                            currentNode = tree.getRoot();
                        } else if (folder.equals("..")) {
                            currentNode = currentNode.getParent();
                        } else {
                            currentNode = currentNode.findChildByName(folder);
                        }
                    }
                } else {
                    String[] split = line.split(" ");
                    if (split[0].equals("dir")) {
                        currentNode.addChild(new Tree.Node(new Directory(split[1])));
                    } else {
                        currentNode.addChild(new Tree.Node(new File(split[1], Long.parseLong(split[0]))));
                    }
                }
            }
            tree.calculate();
            System.out.println(tree);
            System.out.println("directories sizes upto 100000: " + tree.getSizeUpto());
            System.out.println("smallest directory to remove: " + tree.getSmallestDirAbove(30000000 - (70000000 - tree.getRoot().getData().getSize())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
