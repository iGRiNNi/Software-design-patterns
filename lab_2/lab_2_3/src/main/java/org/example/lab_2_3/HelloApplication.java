package org.example.lab_2_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        RoadFacade facade = new RoadFacade(1200, 520);

        Scene scene = new Scene(facade.getView(), 1200, 520);
        stage.setTitle("Facade pattern - Car and Traffic Light");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        facade.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}