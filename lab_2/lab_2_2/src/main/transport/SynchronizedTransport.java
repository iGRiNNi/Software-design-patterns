package main.transport;

import main.exception.DuplicateModelNameException;
import main.exception.NoSuchModelNameException;

public class SynchronizedTransport implements Transportable {

    private final Transportable transport;

    public SynchronizedTransport(Transportable transport) {
        this.transport = transport;
    }

    @Override
    public String getBrand() {
        synchronized (transport) {
            return transport.getBrand();
        }
    }

    @Override
    public void setBrand(String brand) {
        synchronized (transport) {
            transport.setBrand(brand);
        }
    }

    @Override
    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        synchronized (transport) {
            transport.setModelName(name, newName);
        }
    }

    @Override
    public String[] getModelsNames() {
        synchronized (transport) {
            return transport.getModelsNames();
        }
    }

    @Override
    public double modelPriceByName(String nameModel) throws NoSuchModelNameException {
        synchronized (transport) {
            return transport.modelPriceByName(nameModel);
        }
    }

    @Override
    public void modelModifPriceByName(double price, String name) throws NoSuchModelNameException {
        synchronized (transport) {
            transport.modelModifPriceByName(price, name);
        }
    }

    @Override
    public double[] getModelsPrice() {
        synchronized (transport) {
            return transport.getModelsPrice();
        }
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        synchronized (transport) {
            transport.addModel(name, price);
        }
    }

    @Override
    public void deleteModelByName(String name) throws NoSuchModelNameException {
        synchronized (transport) {
            transport.deleteModelByName(name);
        }
    }

    @Override
    public int getModelArraySize() {
        synchronized (transport) {
            return transport.getModelArraySize();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        synchronized (transport) {
            return new SynchronizedTransport((Transportable) transport.clone());
        }
    }
}
