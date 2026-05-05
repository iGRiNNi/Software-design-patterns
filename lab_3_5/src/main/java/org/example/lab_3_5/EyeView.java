package org.example.lab_3_5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class EyeView implements FaceObserver {
    private final Group view;

    private final boolean leftEye;

    private final Ellipse eye;
    private final Ellipse pupil;
    private final Line closedEye;

    public EyeView(double x, double y, boolean leftEye) {
        this.leftEye = leftEye;

        eye = new Ellipse(x, y, 80, 105);
        eye.setFill(Color.WHITE);
        eye.setStroke(Color.web("#1D3517"));
        eye.setStrokeWidth(7);

        if (leftEye) {
            eye.setRotate(-8);
        } else {
            eye.setRotate(8);
        }

        pupil = new Ellipse(x + (leftEye ? 25 : -25), y + 10, 13, 23);
        pupil.setFill(Color.BLACK);

        closedEye = new Line(x - 55, y, x + 55, y);
        closedEye.setStroke(Color.BLACK);
        closedEye.setStrokeWidth(8);
        closedEye.setVisible(false);

        Ellipse clickArea = new Ellipse(x, y, 90, 110);
        clickArea.setFill(Color.TRANSPARENT);

        view = new Group(eye, pupil, closedEye, clickArea);
    }

    public Group getView() {
        return view;
    }

    @Override
    public void update(FaceModel model) {
        boolean open = leftEye ? model.isLeftEyeOpen() : model.isRightEyeOpen();

        eye.setVisible(open);
        pupil.setVisible(open);
        closedEye.setVisible(!open);
    }
}