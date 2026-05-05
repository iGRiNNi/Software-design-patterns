package org.example.lab_4.view;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.lab_4.model.FunctionPoint;

public class FunctionView {
    private final BorderPane root = new BorderPane();

    private final TableView<FunctionPoint> table = new TableView<>();
    private final TableColumn<FunctionPoint, Double> xColumn = new TableColumn<>("x");
    private final TableColumn<FunctionPoint, Double> yColumn = new TableColumn<>("y");

    private final TextField xInput = new TextField();

    private final Button addButton = new Button("Добавить");
    private final Button deleteButton = new Button("Удалить");

    private final LineChart<Number, Number> chart;

    public FunctionView() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("x");
        yAxis.setLabel("y");

        chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("График функции y = 0.2x³ - x² + 2x + 1");
        chart.setCreateSymbols(true);
        chart.setAnimated(false);

        configureTable();

        xInput.setPromptText("Введите x");
        xInput.setPrefWidth(100);

        HBox buttons = new HBox(
                10,
                new Label("x:"),
                xInput,
                addButton,
                deleteButton
        );
        buttons.setPadding(new Insets(10));

        BorderPane leftPanel = new BorderPane();
        leftPanel.setCenter(table);
        leftPanel.setBottom(buttons);
        leftPanel.setPrefWidth(330);

        root.setLeft(leftPanel);
        root.setCenter(chart);
    }

    private void configureTable() {
        table.setEditable(true);

        xColumn.setPrefWidth(150);
        yColumn.setPrefWidth(150);

        table.getColumns().add(xColumn);
        table.getColumns().add(yColumn);
    }

    public BorderPane getRoot() {
        return root;
    }

    public TableView<FunctionPoint> getTable() {
        return table;
    }

    public TableColumn<FunctionPoint, Double> getXColumn() {
        return xColumn;
    }

    public TableColumn<FunctionPoint, Double> getYColumn() {
        return yColumn;
    }

    public TextField getXInput() {
        return xInput;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public LineChart<Number, Number> getChart() {
        return chart;
    }
}
