package org.example;

public class CountContext {
    private CountStrategy strategy;

    public CountContext(CountStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(CountStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }

        this.strategy = strategy;
    }

    public CountResult count(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        return strategy.count(array);
    }
}
