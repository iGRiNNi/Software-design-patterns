package org.example.lab_3_5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class MouthView implements FaceObserver {
    private final Group view;

    private final Arc sadMouth;
    private final Arc smileMouth;

    public MouthView(double x, double y) {
        // Грустный рот
        sadMouth = new Arc(x, y + 35, 125, 55, 25, 130);
        sadMouth.setType(ArcType.OPEN);
        sadMouth.setFill(Color.TRANSPARENT);
        sadMouth.setStroke(Color.web("#1D3517"));
        sadMouth.setStrokeWidth(8);

        // Весёлый рот
        smileMouth = new Arc(x, y, 125, 55, 205, 130);
        smileMouth.setType(ArcType.OPEN);
        smileMouth.setFill(Color.TRANSPARENT);
        smileMouth.setStroke(Color.BLACK);
        smileMouth.setStrokeWidth(8);
        smileMouth.setVisible(false);

        // Невидимая область клика по рту
        Rectangle clickArea = new Rectangle(x - 150, y - 20, 300, 150);
        clickArea.setFill(Color.TRANSPARENT);

        view = new Group(
                sadMouth,
                smileMouth,
                clickArea
        );
    }

    public Group getView() {
        return view;
    }

    @Override
    public void update(FaceModel model) {
        boolean smiling = model.isSmiling();

        sadMouth.setVisible(!smiling);
        smileMouth.setVisible(smiling);
    }
}