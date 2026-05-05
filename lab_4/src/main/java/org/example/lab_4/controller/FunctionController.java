package org.example.lab_4.controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import org.example.lab_4.model.FunctionModel;
import org.example.lab_4.model.FunctionPoint;
import org.example.lab_4.view.FunctionView;

import java.util.Comparator;
import java.util.List;

public class FunctionController {
    private final FunctionModel model;
    private final FunctionView view;

    public FunctionController(FunctionModel model, FunctionView view) {
        this.model = model;
        this.view = view;

        configureTable();
        configureButtons();

        refreshChart();

        model.getPoints().addListener((ListChangeListener<FunctionPoint>) change -> refreshChart());
    }

    private void configureTable() {
        view.getTable().setItems(model.getPoints());

        view.getXColumn().setCellValueFactory(cellData ->
                cellData.getValue().xProperty().asObject()
        );

        view.getYColumn().setCellValueFactory(cellData ->
                cellData.getValue().yProperty().asObject()
        );

        view.getXColumn().setCellFactory(
                TextFieldTableCell.forTableColumn(new CommaDoubleStringConverter())
        );

        view.getXColumn().setOnEditCommit(event -> {
            FunctionPoint point = event.getRowValue();
            double oldX = event.getOldValue();
            double newX = event.getNewValue();

            if (model.containsXExcept(newX, point)) {
                showError("Значение x = " + newX + " уже есть в таблице.");
                point.setX(oldX);
                view.getTable().refresh();
                return;
            }

            point.setX(newX);

            view.getTable().refresh();
            refreshChart();
        });

        view.getYColumn().setEditable(false);
    }

    private void configureButtons() {
        view.getAddButton().setOnAction(event -> addPointFromInput());

        view.getDeleteButton().setOnAction(event -> {
            FunctionPoint selectedPoint = view.getTable()
                    .getSelectionModel()
                    .getSelectedItem();

            if (selectedPoint != null) {
                model.removePoint(selectedPoint);
            }
        });
    }

    private void addPointFromInput() {
        String text = view.getXInput().getText().trim();

        if (text.isEmpty()) {
            showError("Введите значение x.");
            return;
        }

        try {
            double x = parseDouble(text);

            if (model.containsX(x)) {
                showError("Значение x = " + x + " уже есть в таблице.");
                return;
            }

            model.addPoint(x);
            view.getXInput().clear();
        } catch (NumberFormatException e) {
            showError("Значение x должно быть числом. Можно вводить через точку или запятую.");
        }
    }

    private double parseDouble(String text) {
        return Double.parseDouble(text.replace(',', '.'));
    }

    private void refreshChart() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("y = 0.2x³ - x² + 2x + 1");

        List<FunctionPoint> sortedPoints = model.getPoints()
                .stream()
                .sorted(Comparator.comparingDouble(FunctionPoint::getX))
                .toList();

        for (FunctionPoint point : sortedPoints) {
            series.getData().add(
                    new XYChart.Data<>(point.getX(), point.getY())
            );
        }

        view.getChart().getData().clear();
        view.getChart().getData().add(series);

        Platform.runLater(() -> {
            String chartColor = "blue";

            // Цвет линии графика
            if (series.getNode() != null) {
                series.getNode().setStyle("-fx-stroke: " + chartColor + ";");
            }

            // Цвет точек на графике
            for (XYChart.Data<Number, Number> data : series.getData()) {
                if (data.getNode() != null) {
                    data.getNode().setStyle("-fx-background-color: " + chartColor + ", white;");
                }
            }

            // Цвет маркера в легенде
            for (Node node : view.getChart().lookupAll(".chart-legend-item-symbol")) {
                node.setStyle("-fx-background-color: " + chartColor + ";");
            }

            // Цвет текста подписи серии в легенде
            for (Node node : view.getChart().lookupAll(".chart-legend-item")) {
                if (node instanceof Label label) {
                    label.setTextFill(javafx.scene.paint.Color.BLUE);
                }
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static class CommaDoubleStringConverter extends StringConverter<Double> {

        @Override
        public String toString(Double value) {
            if (value == null) {
                return "";
            }

            return value.toString();
        }

        @Override
        public Double fromString(String text) {
            if (text == null || text.trim().isEmpty()) {
                throw new NumberFormatException("Empty value");
            }

            return Double.parseDouble(text.trim().replace(',', '.'));
        }
    }
}