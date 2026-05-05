package org.example.lab_4.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class FunctionPoint {
    private final DoubleProperty x = new SimpleDoubleProperty();
    private final DoubleProperty y = new SimpleDoubleProperty();

    public FunctionPoint(double x) {
        setX(x);
    }

    public double getX() {
        return x.get();
    }

    public void setX(double x) {
        this.x.set(x);
        this.y.set(calculateY(x));
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    private double calculateY(double x) {
        return 0.2 * x * x * x - x * x + 2 * x + 1;
    }
}
