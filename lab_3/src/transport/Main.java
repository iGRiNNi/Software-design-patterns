package transport;

import chainOfResponsibility.InColumnTransportPrintHandler;
import chainOfResponsibility.InLineTransportPrintHandler;
import chainOfResponsibility.TransportPrintHandler;
import chainOfResponsibility.TransportPrintHandlerException;
import command.IncolumnPrintCommand;
import command.InlinePrintCommand;
import exception.DuplicateModelNameException;
import visitor.PrintVisitor;
import visitor.Visitor;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


public class Main {
    public static void main(String[] args) {
        checkChainOfResponsibility();
        checkCommand();

        Car car = createCar();

        checkIterator(car);
        checkMemento(car);
        checkVisitor(car);
    }

    private static void checkChainOfResponsibility() {
        System.out.println("=== Chain of Responsibility ===");

        String fileName = "transport_output.txt";
        clearFile(fileName);

        TransportPrintHandler inlinePrint = new InLineTransportPrintHandler(fileName);
        TransportPrintHandler columnPrint = new InColumnTransportPrintHandler(fileName);

        inlinePrint.setNextHandler(columnPrint);

        Transportable transport1 = TransportUtils.createInstance("Alfa Romeo", 2);
        Transportable transport2 = TransportUtils.createInstance("BMW", 5);

        try {
            inlinePrint.print(transport1);
            inlinePrint.print(transport2);
            System.out.println("Данные записаны в файл " + fileName);
        } catch (TransportPrintHandlerException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println();
    }

    private static void checkCommand() {
        System.out.println("=== Command ===");

        Car car = createCar();

        try (OutputStream outputStream = new FileOutputStream("cars.txt")) {
            car.setPrintCommand(new InlinePrintCommand());
            car.print(outputStream);

            car.setPrintCommand(new IncolumnPrintCommand());
            car.print(outputStream);

            System.out.println("Данные записаны в файл cars.txt");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }

        System.out.println();
    }

    private static Car createCar() {
        Car car = new Car("BMW", 0);

        try {
            car.addModel("X5", 5_000_000);
            car.addModel("X3", 4_200_000);
            car.addModel("M5", 8_500_000);
        } catch (DuplicateModelNameException e) {
            System.out.println("Ошибка добавления модели: " + e.getMessage());
        }

        return car;
    }

    private static void checkIterator(Car car) {
        System.out.println("=== Iterator ===");

        for (Car.Model model : car) {
            System.out.println(model);
        }

        System.out.println();
    }

    private static void checkMemento(Car car) {
        System.out.println("=== Memento ===");

        System.out.println("Исходное состояние:");
        System.out.println(car);
        System.out.println();

        Car.Memento memento = car.createMemento();

        try {
            car.setModelName("X5", "3-series");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Изменённое состояние:");
        System.out.println(car);
        System.out.println();

        car.setMemento(memento);

        System.out.println("Восстановленное состояние:");
        System.out.println(car);
        System.out.println();
    }

    private static void checkVisitor(Car car) {
        System.out.println("=== Visitor ===");

        Visitor printVisitor = new PrintVisitor();

        Transportable motorbike = new Motorbike("Harley-Davidson", 4);

        car.accept(printVisitor);
        System.out.println();
        motorbike.accept(printVisitor);

        System.out.println();
    }

    private static void clearFile(String fileName) {
        try (FileWriter ignored = new FileWriter(fileName, false)) {
        } catch (IOException e) {
            System.out.println("Не удалось очистить файл " + fileName + ": " + e.getMessage());
        }
    }
}