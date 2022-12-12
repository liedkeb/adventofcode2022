package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 {

    public static final String MONKEY = "Monkey";
    public static final String STARTING_ITEMS = "Starting items: ";
    public static final String OPERATION = "Operation:";
    public static final String TEST_DIVISIBLE_BY = "Test: divisible by ";
    public static final String IF_TRUE_THROW_TO_MONKEY = "If true: throw to monkey ";
    public static final String IF_FALSE_THROW_TO_MONKEY = "If false: throw to monkey ";

    public static void main(String[] args) {


        try (
                FileReader fileReader =
                        new FileReader("resources/day11/example.txt");
                BufferedReader reader = new BufferedReader(fileReader)) {
            String line;

            List<Monkey> monkeys = new ArrayList<>();
            Monkey monkey = null;
            long divisor = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains(MONKEY)) {
                    int id = Integer.parseInt(line.replaceAll(MONKEY, "").split("[ ,:]")[1]);
                    monkey = new Monkey(id);
                } else if (line.contains(STARTING_ITEMS)) {
                    String[] items = line.replaceAll(STARTING_ITEMS, "").split(", ");
                    monkey.addItems(Arrays.stream(items).map(p -> Long.parseLong(p.trim())).toList());
                } else if (line.contains(OPERATION)) {
                    Matcher matcher = Pattern.compile(".+.new.=.old.([*+]).(.+)").matcher(line);
                    if (matcher.find()) {
                        monkey.sign = matcher.group(1);
                        if (matcher.group(2).equals("old")) {
                            monkey.value = 0;
                        } else {
                            monkey.value = Integer.parseInt(matcher.group(2));
                        }
                    }
                } else if (line.contains(TEST_DIVISIBLE_BY)) {
                    monkey.division = Integer.parseInt(line.trim().replaceAll(TEST_DIVISIBLE_BY, ""));
                    divisor *= monkey.division;
                } else if (line.contains(IF_TRUE_THROW_TO_MONKEY)) {
                    monkey.monkeyIdTrue = Integer.parseInt(line.trim().replaceAll(IF_TRUE_THROW_TO_MONKEY, ""));
                } else if (line.contains(IF_FALSE_THROW_TO_MONKEY)) {
                    monkey.monkeyIdFalse = Integer.parseInt(line.trim().replaceAll(IF_FALSE_THROW_TO_MONKEY, ""));
                } else {
                    monkeys.add(monkey);
                }
            }
            List<Integer> inspection = new ArrayList<>(Collections.nCopies(monkeys.size(), 0));
            // process
            for (int round = 0; round < 10000; round++) {
                for (Monkey m : monkeys) {
                    for (long item : m.items) {
                        inspection.set(m.id, inspection.get(m.id) + 1);
                        long newItem;
                        if (m.sign.equals("+")) {
                            newItem = (item + (m.value == 0 ? item : m.value));
                        } else {
                            newItem = (item * (m.value == 0 ? item : m.value));
                        }
                        newItem = newItem % divisor;
                        if (newItem % m.division == 0) {
                            monkeys.get(m.monkeyIdTrue).addItem(newItem);
                        } else {
                            monkeys.get(m.monkeyIdFalse).addItem(newItem);
                        }
                    }
                    m.items = new ArrayList<>();
                }
            }
            // print
            monkeys.forEach(System.out::println);
            System.out.println(inspection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
