package factory;

import transport.Car;
import transport.Transportable;

public class CarFactory implements TransportFactory{
    @Override
    public Transportable createInstance(String brand, int size) {
        return new Car(brand, size);
    }
}
