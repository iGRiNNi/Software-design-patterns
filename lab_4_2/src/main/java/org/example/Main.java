package org.example;

import org.example.dao.DaoException;
import org.example.dao.SerializedTransportDao;
import org.example.dao.TextTransportDao;
import org.example.dao.TransportDao;
import org.example.exception.DuplicateModelNameException;
import org.example.transport.Car;
import org.example.transport.Transportable;

public class Main {
    public static void main(String[] args) {
        try {
            Car car = createCar();

            TransportDao textDao = new TextTransportDao();
            TransportDao serializedDao = new SerializedTransportDao();

            textDao.write("transport.txt", car);
            serializedDao.write("transport.bin", car);

            System.out.println("Объекты записаны в файлы.");
            System.out.println();

            Transportable textTransport = textDao.read("transport.txt");
            System.out.println("Транспорт из текстового файла:");
            printTransport(textTransport);

            System.out.println();

            Transportable serializedTransport = serializedDao.read("transport.bin");
            System.out.println("Транспорт из сериализованного файла:");
            printTransport(serializedTransport);

        } catch (DaoException e) {
            System.out.println("Ошибка DAO: " + e.getMessage());
        }
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

    private static void printTransport(Transportable transport) {
        System.out.println("Brand: " + transport.getBrand());

        String[] models = transport.getModelsNames();
        double[] prices = transport.getModelsPrice();

        for (int i = 0; i < models.length; i++) {
            System.out.println("Model: " + models[i] + ", price: " + prices[i]);
        }
    }
}