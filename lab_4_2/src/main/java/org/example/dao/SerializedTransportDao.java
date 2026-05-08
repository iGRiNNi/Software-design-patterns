package org.example.dao;

import org.example.transport.Transportable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    @Override
    public void write(String fileName, Transportable transport) throws DaoException {
        if (transport == null) {
            throw new DaoException("Transport не должен быть null.");
        }

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(transport);
        } catch (IOException e) {
            throw new DaoException("Ошибка записи сериализованного файла.", e);
        }
    }
}
