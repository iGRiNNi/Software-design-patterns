package org.example;

import org.example.dao.DaoException;
import org.example.dao.SerializedTransportDao;
import org.example.dao.TextTransportDao;
import org.example.dao.TransportDao;
import org.example.transport.Transportable;

public class Main {
    public static void main(String[] args) {
        try {
            TransportDao textDao = new TextTransportDao();
            Transportable textTransport = textDao.read("transport.txt");

            System.out.println("Транспорт из текстового файла:");
            printTransport(textTransport);

            System.out.println();

            TransportDao serializedDao = new SerializedTransportDao();
            Transportable serializedTransport = serializedDao.read("transport.dat");

            System.out.println("Транспорт из сериализованного файла:");
            printTransport(serializedTransport);

        } catch (DaoException e) {
            System.out.println("Ошибка DAO: " + e.getMessage());
        }
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