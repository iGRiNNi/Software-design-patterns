package org.example.lab_3_5;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class MonsterView extends Pane {

    public MonsterView(FaceModel model) {

        setPrefSize(560, 560);

        // Цвет фона всей области
        setStyle("-fx-background-color: white;");

        // Основное зелёное тело
        Path body = createBody();

        // Антенна на верхней части головы
        Path antenna = createAntenna();

        // Боковые лапы / боковые выступы тела.
        Path leftSide = createSidePart(180, 280, false);
        Path rightSide = createSidePart(465, 355, true);

        Ellipse bottomShadow = new Ellipse(280, 448, 90, 24);
        bottomShadow.setFill(Color.web("#6BA82E"));
        bottomShadow.setStroke(Color.web("#1D3517"));
        bottomShadow.setStrokeWidth(5);

        Path leftFrontPaw = createFrontPaw(175, 450, false);
        Path rightFrontPaw = createFrontPaw(330, 450, true);

        EyeView leftEye = new EyeView(200, 210, true);
        EyeView rightEye = new EyeView(360, 210, false);

        NoseView nose = new NoseView(280, 310);

        MouthView mouth = new MouthView(280, 365);


        getChildren().addAll(
                // Самые дальние элементы — боковые лапы
                leftSide,
                rightSide,

                // Основное тело поверх боковых лап
                body,

                // Нижняя тень и передние лапы поверх тела
                bottomShadow,
                leftFrontPaw,
                rightFrontPaw,

                // Антенна поверх тела
                antenna,

                // Части лица поверх всех остальных элементов
                leftEye.getView(),
                rightEye.getView(),
                nose.getView(),
                mouth.getView()
        );

        model.addObserver(leftEye);
        model.addObserver(rightEye);
        model.addObserver(nose);
        model.addObserver(mouth);


        leftEye.getView().setOnMouseClicked(event -> model.toggleLeftEye());

        rightEye.getView().setOnMouseClicked(event -> model.toggleRightEye());

        nose.getView().setOnMouseClicked(event -> model.changeNoseColor());

        mouth.getView().setOnMouseClicked(event -> model.toggleSmile());
    }

    private Path createBody() {

        Path body = new Path();

        body.getElements().add(new MoveTo(90, 270));

        body.getElements().add(new CubicCurveTo(100, 130, 180, 95, 250, 110));

        body.getElements().add(new CubicCurveTo(290, 80, 340, 100, 390, 120));

        body.getElements().add(new CubicCurveTo(470, 150, 520, 235, 490, 330));

        body.getElements().add(new CubicCurveTo(500, 410, 430, 460, 330, 455));

        body.getElements().add(new CubicCurveTo(270, 480, 175, 455, 125, 420));

        body.getElements().add(new CubicCurveTo(75, 390, 70, 330, 90, 270));

        body.getElements().add(new ClosePath());

        body.setFill(Color.web("#96D900"));

        body.setStroke(Color.web("#1D3517"));
        body.setStrokeWidth(8);

        return body;
    }

    private Path createAntenna() {
        Path antenna = new Path();

        antenna.getElements().add(new MoveTo(270, 115));

        antenna.getElements().add(new CubicCurveTo(275, 80, 260, 45, 300, 25));

        antenna.getElements().add(new CubicCurveTo(340, 5, 365, 45, 335, 70));

        antenna.getElements().add(new CubicCurveTo(310, 90, 305, 105, 315, 125));

        antenna.getElements().add(new LineTo(280, 145));

        antenna.getElements().add(new ClosePath());

        antenna.setFill(Color.web("#8FD000"));

        antenna.setStroke(Color.web("#1D3517"));
        antenna.setStrokeWidth(8);

        return antenna;
    }

    private Path createFrontPaw(double x, double y, boolean right) {

        Path paw = new Path();

        if (!right) {

            paw.getElements().add(new MoveTo(x, y));

            paw.getElements().add(new CubicCurveTo(x - 8, y + 16, x - 2, y + 42, x + 22, y + 50));

            paw.getElements().add(new CubicCurveTo(x + 40, y + 56, x + 64, y + 54, x + 80, y + 42));

            paw.getElements().add(new CubicCurveTo(x + 90, y + 30, x + 88, y + 12, x + 76, y));

            paw.getElements().add(new CubicCurveTo(x + 0, y + 0, x + 0, y + 0, x + 0, y));
        } else {

            paw.getElements().add(new MoveTo(x + 80, y));

            paw.getElements().add(new CubicCurveTo(x + 88, y + 16, x + 82, y + 42, x + 58, y + 50));

            paw.getElements().add(new CubicCurveTo(x + 40, y + 56, x + 16, y + 54, x, y + 42));

            paw.getElements().add(new CubicCurveTo(x - 10, y + 30, x - 8, y + 12, x + 4, y));

            paw.getElements().add(new CubicCurveTo(x + 0, y + 0, x + 0, y + 0, x + 80, y));
        }

        paw.getElements().add(new ClosePath());

        paw.setFill(Color.web("#96D900"));

        paw.setStroke(Color.web("#1D3517"));
        paw.setStrokeWidth(7);

        return paw;
    }

    private Path createSidePart(double x, double y, boolean right) {

        Path part = new Path();

        if (!right) {

            part.getElements().add(new MoveTo(x, y));

            part.getElements().add(new LineTo(35, 430));

            part.getElements().add(new CubicCurveTo(45, 480, 100, 455, 135, 440));

            part.getElements().add(new ClosePath());
        } else {

            part.getElements().add(new MoveTo(x, y));

            part.getElements().add(new LineTo(525, 430));

            part.getElements().add(new CubicCurveTo(515, 480, 460, 455, 425, 440));

            part.getElements().add(new ClosePath());
        }

        part.setFill(Color.web("#73B83A"));

        part.setStroke(Color.web("#1D3517"));
        part.setStrokeWidth(7);

        return part;
    }
}