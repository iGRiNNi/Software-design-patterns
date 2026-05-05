package org.example.lab_4;

import org.example.lab_4.controller.FunctionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.lab_4.model.FunctionModel;
import org.example.lab_4.view.FunctionView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        FunctionModel model = new FunctionModel();
        FunctionView view = new FunctionView();

        new FunctionController(model, view);

        Scene scene = new Scene(view.getRoot(), 900, 600);

        stage.setTitle("MVC: график функции и таблица значений");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
