package org.example.lab_3_5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NoseView implements FaceObserver {
    private final Group view;
    private final Circle nose;

    public NoseView(double x, double y) {
        nose = new Circle(x, y, 15);
        nose.setFill(Color.DARKGREEN);
        nose.setStroke(Color.web("#1D3517"));
        nose.setStrokeWidth(3);

        Circle clickArea = new Circle(x, y, 28);
        clickArea.setFill(Color.TRANSPARENT);

        view = new Group(nose, clickArea);
    }

    public Group getView() {
        return view;
    }

    @Override
    public void update(FaceModel model) {
        nose.setFill(model.getNoseColor());
    }
}