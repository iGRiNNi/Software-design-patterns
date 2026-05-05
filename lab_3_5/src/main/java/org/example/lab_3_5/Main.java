package org.example.lab_3_5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        FaceModel model = new FaceModel();
        MonsterView monsterView = new MonsterView(model);

        Scene scene = new Scene(monsterView, 560, 560);

        stage.setTitle("Observer Pattern");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
