package org.example.lab_3_8;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class BallFigure extends BouncingFigure {

    public BallFigure(double fieldWidth, double fieldHeight) {
        super(fieldWidth, fieldHeight);
    }

    @Override
    protected Shape createShape() {
        Circle circle = new Circle(size / 2, size / 2, size / 2);

        circle.setFill(randomColor());
        circle.setStroke(javafx.scene.paint.Color.BLACK);
        circle.setStrokeWidth(2);

        return circle;
    }
}
