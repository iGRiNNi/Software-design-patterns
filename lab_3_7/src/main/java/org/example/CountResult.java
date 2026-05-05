package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountResult {
    private final List<ElementCount> elements = new ArrayList<>();

    public void add(int value, int count) {
        elements.add(new ElementCount(value, count));
    }

    public List<ElementCount> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public void print() {
        for (ElementCount element : elements) {
            System.out.println(element);
        }
    }
}
