package org.example.lab_3_8;

import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Shape;

public class StarFigure extends BouncingFigure {

    public StarFigure(double fieldWidth, double fieldHeight) {
        super(fieldWidth, fieldHeight);
    }

    @Override
    protected Shape createShape() {
        double centerX = size / 2.0;
        double centerY = size / 2.0;

        double outer = size / 2.0;
        double inner = size / 10.0;

        Path star = new Path();

        star.getElements().add(new MoveTo(centerX, centerY - outer));

        star.getElements().add(new QuadCurveTo(
                centerX + inner, centerY - inner,
                centerX + outer, centerY
        ));

        star.getElements().add(new QuadCurveTo(
                centerX + inner, centerY + inner,
                centerX, centerY + outer
        ));

        star.getElements().add(new QuadCurveTo(
                centerX - inner, centerY + inner,
                centerX - outer, centerY
        ));

        star.getElements().add(new QuadCurveTo(
                centerX - inner, centerY - inner,
                centerX, centerY - outer
        ));

        star.getElements().add(new ClosePath());

        star.setFill(randomColor());
        star.setStroke(Color.BLACK);
        star.setStrokeWidth(2);

        return star;
    }
}
