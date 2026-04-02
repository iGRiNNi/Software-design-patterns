package main.factory;

import main.transport.Car;
import main.transport.Transportable;

public class CarFactory implements TransportFactory{
    @Override
    public Transportable createInstance(String brand, int size) {
        return new Car(brand, size);
    }
}
