package org.example.factory;

import org.example.transport.Motorbike;
import org.example.transport.Transportable;

public class MotorbikeFactory implements TransportFactory {
    @Override
    public Transportable createInstance(String brand, int size) {
        return new Motorbike(brand, size);
    }
}
