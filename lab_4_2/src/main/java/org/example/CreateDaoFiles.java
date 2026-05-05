package org.example;

import org.example.exception.DuplicateModelNameException;
import org.example.transport.Car;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateDaoFiles {
    public static void main(String[] args) {
        try {
            createTextFile();
            createSerializedFile();

            System.out.println("Файлы созданы.");
        } catch (IOException | DuplicateModelNameException e) {
            System.out.println("Ошибка создания файлов: " + e.getMessage());
        }
    }

    private static void createTextFile() throws IOException {
        try (FileWriter writer = new FileWriter("transport.txt")) {
            writer.write("Car\n");
            writer.write("BMW\n");
            writer.write("3\n");
            writer.write("X5;5000000\n");
            writer.write("X3;4200000\n");
            writer.write("M5;8500000\n");
        }
    }

    private static void createSerializedFile() throws IOException, DuplicateModelNameException {
        Car car = new Car("Mercedes-Benz", 0);
        car.addModel("C-Class", 4500000);
        car.addModel("E-Class", 6200000);
        car.addModel("S-Class", 12000000);

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("transport.dat"))) {
            output.writeObject(car);
        }
    }
}
