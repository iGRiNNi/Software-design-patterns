package org.example.lab_4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FunctionModel {
    private static final double EPS = 1e-9;

    private final ObservableList<FunctionPoint> points = FXCollections.observableArrayList();

    public FunctionModel() {
        addPoint(-4);
        addPoint(-3);
        addPoint(-2);
        addPoint(-1);
        addPoint(0);
        addPoint(1);
        addPoint(2);
        addPoint(3);
        addPoint(4);
    }

    public ObservableList<FunctionPoint> getPoints() {
        return points;
    }

    public void addPoint(double x) {
        points.add(new FunctionPoint(x));
    }

    public void removePoint(FunctionPoint point) {
        points.remove(point);
    }

    public boolean containsX(double x) {
        return containsXExcept(x, null);
    }

    public boolean containsXExcept(double x, FunctionPoint ignoredPoint) {
        for (FunctionPoint point : points) {
            if (point == ignoredPoint) {
                continue;
            }

            if (Math.abs(point.getX() - x) < EPS) {
                return true;
            }
        }

        return false;
    }
}
