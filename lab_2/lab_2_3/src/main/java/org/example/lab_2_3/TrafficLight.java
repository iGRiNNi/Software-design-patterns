package org.example.lab_2_3;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

class TrafficLight {
    private enum State {
        GREEN, YELLOW, RED
    }

    private State state = State.GREEN;

    private final Pane view;
    private final Circle redLamp;
    private final Circle yellowLamp;
    private final Circle greenLamp;

    public TrafficLight(double x, double yBottom) {
        double poleHeight = 86;
        double bodyWidth = 46;
        double bodyHeight = 108;
        double bodyX = x;
        double bodyY = yBottom - poleHeight - bodyHeight;

        Rectangle base = new Rectangle(x + 10, yBottom - 6, 28, 6);
        base.setArcWidth(4);
        base.setArcHeight(4);
        base.setFill(Color.web("#555555"));

        Rectangle pole = new Rectangle(x + 20, yBottom - poleHeight, 8, poleHeight);
        pole.setArcWidth(4);
        pole.setArcHeight(4);
        pole.setFill(Color.web("#2F2F2F"));

        Rectangle body = new Rectangle(bodyX, bodyY, bodyWidth, bodyHeight);
        body.setArcWidth(18);
        body.setArcHeight(18);
        body.setFill(Color.web("#404040"));
        body.setStroke(Color.web("#1F1F1F"));
        body.setStrokeWidth(2);

        redLamp = new Circle(bodyX + bodyWidth / 2.0, bodyY + 24, 11);
        yellowLamp = new Circle(bodyX + bodyWidth / 2.0, bodyY + 54, 11);
        greenLamp = new Circle(bodyX + bodyWidth / 2.0, bodyY + 84, 11);

        Rectangle backPlate = new Rectangle(bodyX - 4, bodyY + 8, bodyWidth + 8, bodyHeight - 16);
        backPlate.setArcWidth(16);
        backPlate.setArcHeight(16);
        backPlate.setFill(Color.rgb(0, 0, 0, 0.08));

        view = new Pane(backPlate, base, pole, body, redLamp, yellowLamp, greenLamp);
        updateLights();
    }

    public Pane getView() {
        return view;
    }

    public boolean isRed() {
        return state == State.RED;
    }

    public boolean isYellow() {
        return state == State.YELLOW;
    }

    public void nextState() {
        switch (state) {
            case GREEN -> state = State.YELLOW;
            case YELLOW -> state = State.RED;
            case RED -> state = State.GREEN;
        }
        updateLights();
    }

    private void updateLights() {
        redLamp.setFill(state == State.RED ? Color.RED : Color.web("#5A1414"));
        yellowLamp.setFill(state == State.YELLOW ? Color.YELLOW : Color.web("#786A16"));
        greenLamp.setFill(state == State.GREEN ? Color.LIMEGREEN : Color.web("#1B5A1B"));
    }
}