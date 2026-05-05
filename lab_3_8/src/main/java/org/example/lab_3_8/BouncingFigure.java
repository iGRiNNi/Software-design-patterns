package org.example.lab_3_8;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BouncingFigure implements Runnable {
    private static final int DELAY = 15;

    protected final double fieldWidth;
    protected final double fieldHeight;
    protected final double size = 45;

    protected double x;
    protected double y;

    protected double dx;
    protected double dy;

    private volatile boolean moving = true;

    private final Shape view;

    public BouncingFigure(double fieldWidth, double fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;

        this.view = createShape();

        Bounds bounds = view.getBoundsInLocal();

        this.x = fieldWidth - bounds.getMaxX();
        this.y = fieldHeight - bounds.getMaxY();

        this.dx = -3.2;
        this.dy = -2.4;

        updateView();
    }

    public Shape getView() {
        return view;
    }

    public void stopMoving() {
        moving = false;
    }

    @Override
    public final void run() {
        while (moving) {
            move();
            checkWallCollision();
            updateView();
            pause();
        }
    }

    protected abstract Shape createShape();

    protected Color randomColor() {
        Color[] colors = {
                Color.DODGERBLUE,
                Color.ORANGE,
                Color.LIMEGREEN,
                Color.HOTPINK,
                Color.GOLD,
                Color.MEDIUMPURPLE,
                Color.TOMATO,
                Color.CYAN,
                Color.LIGHTCORAL,
                Color.YELLOWGREEN
        };

        int index = ThreadLocalRandom.current().nextInt(colors.length);
        return colors[index];
    }

    private void move() {
        x += dx;
        y += dy;
    }

    private void checkWallCollision() {
        Bounds bounds = view.getBoundsInLocal();

        if (x + bounds.getMinX() <= 0) {
            x = -bounds.getMinX();
            dx = -dx;
        }

        if (x + bounds.getMaxX() >= fieldWidth) {
            x = fieldWidth - bounds.getMaxX();
            dx = -dx;
        }

        if (y + bounds.getMinY() <= 0) {
            y = -bounds.getMinY();
            dy = -dy;
        }

        if (y + bounds.getMaxY() >= fieldHeight ) {
            y = fieldHeight - bounds.getMaxY();
            dy = -dy;
        }
    }

    private void updateView() {
        Platform.runLater(() -> {
            view.setLayoutX(x);
            view.setLayoutY(y);
        });
    }

    private void pause() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            moving = false;
        }
    }
}
