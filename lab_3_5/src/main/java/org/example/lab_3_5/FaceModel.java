package org.example.lab_3_5;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class FaceModel {

    private final List<FaceObserver> observers = new ArrayList<>();

    private boolean leftEyeOpen = true;
    private boolean rightEyeOpen = true;
    private boolean smiling = false;

    private int noseColorIndex = 0;

    private final Color[] noseColors = {
            Color.ORANGE,
            Color.RED,
            Color.DEEPPINK,
            Color.BLUEVIOLET,
            Color.DARKCYAN
    };

    public void addObserver(FaceObserver observer) {
        observers.add(observer);
        observer.update(this);
    }

    public void removeObserver(FaceObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (FaceObserver observer : observers) {
            observer.update(this);
        }
    }

    public void toggleLeftEye() {
        leftEyeOpen = !leftEyeOpen;
        notifyObservers();
    }

    public void toggleRightEye() {
        rightEyeOpen = !rightEyeOpen;
        notifyObservers();
    }

    public void changeNoseColor() {
        noseColorIndex = (noseColorIndex + 1) % noseColors.length;
        notifyObservers();
    }

    public void toggleSmile() {
        smiling = !smiling;
        notifyObservers();
    }

    public boolean isLeftEyeOpen() {
        return leftEyeOpen;
    }

    public boolean isRightEyeOpen() {
        return rightEyeOpen;
    }

    public boolean isSmiling() {
        return smiling;
    }

    public Color getNoseColor() {
        return noseColors[noseColorIndex];
    }
}
