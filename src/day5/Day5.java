package day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day5 {
    private final static List<Stack<String>> stacks = new ArrayList<>();
    private final static List<Stack<String>> stacks_test = new ArrayList<>();

    public static void main(String[] args) {
        Stack<String> stack;
        stack = new Stack<>();
        stack.push("N");
        stack.push("R");
        stack.push("G");
        stack.push("P");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("J");
        stack.push("T");
        stack.push("B");
        stack.push("L");
        stack.push("F");
        stack.push("G");
        stack.push("D");
        stack.push("C");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("M");
        stack.push("S");
        stack.push("V");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("L");
        stack.push("S");
        stack.push("R");
        stack.push("C");
        stack.push("Z");
        stack.push("P");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("P");
        stack.push("S");
        stack.push("L");
        stack.push("V");
        stack.push("C");
        stack.push("W");
        stack.push("D");
        stack.push("Q");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("C");
        stack.push("T");
        stack.push("N");
        stack.push("W");
        stack.push("D");
        stack.push("M");
        stack.push("S");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("H");
        stack.push("D");
        stack.push("G");
        stack.push("W");
        stack.push("P");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("Z");
        stack.push("L");
        stack.push("P");
        stack.push("H");
        stack.push("S");
        stack.push("C");
        stack.push("M");
        stack.push("V");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("R");
        stack.push("P");
        stack.push("F");
        stack.push("L");
        stack.push("W");
        stack.push("G");
        stack.push("Z");
        stacks.add(stack);

        stack = new Stack<>();
        stack.push("Z");
        stack.push("N");
        stacks_test.add(stack);

        stack = new Stack<>();
        stack.push("M");
        stack.push("C");
        stack.push("D");
        stacks_test.add(stack);

        stack = new Stack<>();
        stack.push("P");
        stacks.add(stack);

        List<Stack<String>> secondStack = new ArrayList<>();
        for (Stack<String> s : stacks) {
            secondStack.add((Stack<String>) s.clone());
        }

        URL url = Day5.class.getClassLoader().getResource("day5/example.txt");
        if (url == null) {
            System.out.println("file not found...");
            return;
        }
        int lineNumber = 1;
        Stack<String> tempStack = new Stack<>();
        try (Stream<String> lines = Files.lines(Paths.get(url.toURI()))) {
            for (String line : lines.toList()) {
                Matcher matcher = Pattern.compile("move (.+) from (.+) to (.+)").matcher(line);
                if (matcher.find()) {
                    int numberOfLetters = Integer.parseInt(matcher.group(1));
                    int from = Integer.parseInt(matcher.group(2)) - 1;
                    int to = Integer.parseInt(matcher.group(3)) - 1;
                    for (int i = 0; i < numberOfLetters; i++) {
                        stacks.get(to).push(stacks.get(from).pop());
                        tempStack.push(secondStack.get(from).pop());
                    }
                    while (!tempStack.isEmpty()) {
                        secondStack.get(to).push(tempStack.pop());
                    }
                }
                lineNumber++;
            }
            System.out.println("last letters: " + stacks.get(0).pop() + stacks.get(1).pop() + stacks.get(2).pop()
                    + stacks.get(3).pop() + stacks.get(4).pop() + stacks.get(5).pop() + stacks.get(6).pop()
                    + stacks.get(7).pop() + stacks.get(8).pop());
            System.out.println("last letters: " + secondStack.get(0).pop() + secondStack.get(1).pop() + secondStack.get(2).pop()
                    + secondStack.get(3).pop() + secondStack.get(4).pop() + secondStack.get(5).pop() + secondStack.get(6).pop()
                    + secondStack.get(7).pop() + secondStack.get(8).pop());

        } catch (IOException | URISyntaxException | EmptyStackException e) {
            System.out.println("line number " + lineNumber);
            throw new RuntimeException(e);
        }

    }

}
