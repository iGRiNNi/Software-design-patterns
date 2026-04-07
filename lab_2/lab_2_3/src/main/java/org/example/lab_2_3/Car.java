package org.example.lab_2_3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class Car {
    private final Pane view;
    private final ImageView imageView;

    public Car(String imagePath, double x, double centerY, boolean moveRight, double fitWidth) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));

        imageView = new ImageView(image);
        imageView.setFitWidth(fitWidth);
        imageView.setPreserveRatio(true);

        if (!moveRight) {
            imageView.setScaleX(-1);
        }

        imageView.setX(x);
        imageView.setY(centerY - getHeight() / 2.0);

        view = new Pane(imageView);
    }

    public Pane getView() {
        return view;
    }

    public void move(double dx) {
        view.setLayoutX(view.getLayoutX() + dx);
    }

    public double getX() {
        return imageView.getX() + view.getLayoutX();
    }

    public void setX(double x) {
        view.setLayoutX(x - imageView.getX());
    }

    public double getWidth() {
        return imageView.getBoundsInParent().getWidth();
    }

    public double getHeight() {
        return imageView.getBoundsInLocal().getHeight();
    }
}