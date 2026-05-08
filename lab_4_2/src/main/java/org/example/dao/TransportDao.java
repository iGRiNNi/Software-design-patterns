package org.example.dao;

import org.example.transport.Transportable;

public interface TransportDao {
    Transportable read(String fileName) throws DaoException;

    void write(String fileName, Transportable transport) throws DaoException;
}
