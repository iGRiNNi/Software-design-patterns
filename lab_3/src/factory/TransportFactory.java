package factory;

import transport.Transportable;

public interface TransportFactory {
    Transportable createInstance(String brand, int size);
}
