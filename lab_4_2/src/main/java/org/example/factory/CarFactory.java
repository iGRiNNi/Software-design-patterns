package org.example.factory;


import org.example.transport.Car;
import org.example.transport.Transportable;

public class CarFactory implements TransportFactory {
    @Override
    public Transportable createInstance(String brand, int size) {
        return new Car(brand, size);
    }
}
