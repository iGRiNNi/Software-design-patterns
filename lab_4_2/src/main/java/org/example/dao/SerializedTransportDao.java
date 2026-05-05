package org.example.dao;

import org.example.transport.Transportable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializedTransportDao implements TransportDao {

    @Override
    public Transportable read(String fileName) throws DaoException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            Object object = input.readObject();

            if (!(object instanceof Transportable)) {
                throw new DaoException("В файле должен быть объект типа Transportable.");
            }

            return (Transportable) object;
        } catch (IOException e) {
            throw new DaoException("Ошибка чтения сериализованного файла.", e);
        } catch (ClassNotFoundException e) {
            throw new DaoException("Класс объекта из файла не найден.", e);
        }
    }
}
