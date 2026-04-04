package main.factory;

import main.transport.Transportable;

public interface TransportFactory {
    Transportable createInstance(String brand, int size);
}
