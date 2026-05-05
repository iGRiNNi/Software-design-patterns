package factory;

import transport.Motorbike;
import transport.Transportable;

public class MotorbikeFactory implements TransportFactory{
    @Override
    public Transportable createInstance(String brand, int size) {
        return new Motorbike(brand, size);
    }
}
