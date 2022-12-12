package day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    int id;
    int monkeyIdTrue;
    int monkeyIdFalse;
    List<Long> items;
    String sign;
    long value;
    long division;

    public Monkey(int id) {
        this.id = id;
    }

    void addItems(List<Long> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.addAll(items);
    }

    void addItem(Long item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Monkey " + id + " {" +
                "items=" + items +
                '}';
    }
}
