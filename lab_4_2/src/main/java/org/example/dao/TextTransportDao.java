package org.example.dao;

import org.example.exception.DuplicateModelNameException;
import org.example.transport.Car;
import org.example.transport.Motorbike;
import org.example.transport.Transportable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextTransportDao implements TransportDao {

    @Override
    public Transportable read(String fileName) throws DaoException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String type = reader.readLine();
            String brand = reader.readLine();
            String modelCountLine = reader.readLine();

            if (type == null || brand == null || modelCountLine == null) {
                throw new DaoException("Файл имеет неверный формат.");
            }

            int modelCount = Integer.parseInt(modelCountLine);

            Transportable transport = createTransport(type, brand);

            for (int i = 0; i < modelCount; i++) {
                String line = reader.readLine();

                if (line == null) {
                    throw new DaoException("Количество моделей в файле меньше заявленного.");
                }

                String[] parts = line.split(";");

                if (parts.length != 2) {
                    throw new DaoException("Неверный формат строки модели: " + line);
                }

                String modelName = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());

                transport.addModel(modelName, price);
            }

            return transport;
        } catch (IOException e) {
            throw new DaoException("Ошибка чтения текстового файла.", e);
        } catch (NumberFormatException e) {
            throw new DaoException("Ошибка преобразования числа.", e);
        } catch (DuplicateModelNameException e) {
            throw new DaoException("В файле есть повторяющаяся модель.", e);
        }
    }

    private Transportable createTransport(String type, String brand) throws DaoException {
        if (type.equalsIgnoreCase("Car")) {
            return new Car(brand, 0);
        }

        if (type.equalsIgnoreCase("Motorbike")) {
            return new Motorbike(brand, 0);
        }

        throw new DaoException("Неизвестный тип транспорта: " + type);
    }
}
