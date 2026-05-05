package org.example.factory;

import org.example.transport.Transportable;

public interface TransportFactory {
    Transportable createInstance(String brand, int size);
}
