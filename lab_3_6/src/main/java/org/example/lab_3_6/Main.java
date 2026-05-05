package org.example.lab_3_6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StudentCharacter student = new StudentCharacter();

        Button semesterButton = new Button("Семестр");
        Button vacationButton = new Button("Каникулы");
        Button sessionButton = new Button("Сессия");

        semesterButton.setOnAction(event -> student.setState(new SemesterState()));
        vacationButton.setOnAction(event -> student.setState(new VacationState()));
        sessionButton.setOnAction(event -> student.setState(new SessionState()));

        HBox buttons = new HBox(20, semesterButton, vacationButton, sessionButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));

        BorderPane root = new BorderPane();
        root.setCenter(student);
        root.setBottom(buttons);

        student.setState(new NormalState());

        Scene scene = new Scene(root, 600, 520);

        stage.setTitle("State Pattern - Student");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}