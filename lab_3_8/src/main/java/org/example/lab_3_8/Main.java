package org.example.lab_3_8;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static final double FIELD_WIDTH = 700;
    private static final double FIELD_HEIGHT = 400;

    private final List<BouncingFigure> figures = new ArrayList<>();

    private FigureType currentFigureType = FigureType.BALL;

    @Override
    public void start(Stage stage) {
        Pane field = new Pane();
        field.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);
        field.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2;");

        Button startButton = new Button("Пуск");
        Button changeFigureButton = new Button(getFigureButtonText());
        Button closeButton = new Button("Закрыть");

        startButton.setOnAction(event -> {
            BouncingFigure figure = createFigure(currentFigureType);

            field.getChildren().add(figure.getView());
            figures.add(figure);

            Thread thread = new Thread(figure);
            thread.setDaemon(true);
            thread.start();
        });

        changeFigureButton.setOnAction(event -> {
            currentFigureType = currentFigureType.next();
            changeFigureButton.setText(getFigureButtonText());
        });

        closeButton.setOnAction(event -> closeApplication());

        HBox buttons = new HBox(20, startButton, changeFigureButton, closeButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(15));

        BorderPane root = new BorderPane();
        root.setCenter(field);
        root.setBottom(buttons);

        Scene scene = new Scene(root, FIELD_WIDTH, FIELD_HEIGHT + 55);

        stage.setTitle("Template Method - Bouncing Figures");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> closeApplication());
        stage.show();
    }

    private BouncingFigure createFigure(FigureType type) {
        return switch (type) {
            case BALL -> new BallFigure(FIELD_WIDTH, FIELD_HEIGHT);
            case SQUARE -> new SquareFigure(FIELD_WIDTH, FIELD_HEIGHT);
            case STAR -> new StarFigure(FIELD_WIDTH, FIELD_HEIGHT);
            default -> throw new IllegalStateException("Неизвестный тип фигуры");
        };
    }

    private String getFigureButtonText() {
        return "Фигура: " + currentFigureType.getTitle();
    }

    private void closeApplication() {
        for (BouncingFigure figure : figures) {
            figure.stopMoving();
        }

        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private enum FigureType {
        BALL("Круг"),
        SQUARE("Квадрат"),
        STAR("Звезда");

        private final String title;

        FigureType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public FigureType next() {
            return switch (this) {
                case BALL -> SQUARE;
                case SQUARE -> STAR;
                case STAR -> BALL;
                default -> throw new IllegalStateException("Неизвестный тип фигуры");
            };
        }
    }
}
