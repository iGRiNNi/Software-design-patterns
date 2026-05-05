package org.example;

public record ElementCount(int value, int count) {

    @Override
    public String toString() {
        return value + " встречается " + count + " раз(а)";
    }
}
