package org.example.lab_3_8;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareFigure extends BouncingFigure {

    public SquareFigure(double fieldWidth, double fieldHeight) {
        super(fieldWidth, fieldHeight);
    }

    @Override
    protected Shape createShape() {
        Rectangle rectangle = new Rectangle(0, 0, size, size);

        rectangle.setFill(randomColor());
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeWidth(2);

        return rectangle;
    }
}
