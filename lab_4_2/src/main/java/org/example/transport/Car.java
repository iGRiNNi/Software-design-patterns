package org.example.transport;

import org.example.exception.DuplicateModelNameException;
import org.example.exception.ModelPriceOutOfBoundsException;
import org.example.exception.NoSuchModelNameException;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Car implements Transportable, Iterable<Car.Model> {

    static class Model implements Serializable, Cloneable {
        String nameModel;           //Название модели
        double priceModel;          //Цена модели

        private Model() {
        }

        private Model(String nameModel, Double priceModel) {
            this.nameModel = nameModel;
            this.priceModel = priceModel;
        }

        public void setNameModel(String nameModel) {
            this.nameModel = nameModel;
        }

        public String getNameModel() {
            return nameModel;
        }

        public double getPriceModel() {
            return priceModel;
        }

        public void setPriceModel(Double priceModel) {
            this.priceModel = priceModel;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "Model: " + nameModel + ", price: " + priceModel;
        }
    }

    private String brand;       //Марка авто

    private Model[] models;     //массив моделей

    public Car(){
        models = new Model[0];
    }


    public Car(String brand, int sizeModelArray) {
        this.brand = brand;
        models = new Model[sizeModelArray];
        for (int i = 0; i < sizeModelArray; i++) {
            models[i] = new Model(Integer.toString(i), Math.random() * 100000);
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getModelsNames() {                            //Метод, возвращающий массив названий всех моделей
        String names[] = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            names[i] = models[i].nameModel;
        }
        return names;
    }

    public double modelPriceByName(String nameModel) throws NoSuchModelNameException {       //метод для получения значения цены модели по её названию
        boolean flag = true;
        int i = 0;
        double price = 0;
        while (flag && i < models.length) {
            if (!(nameModel.equals(models[i].nameModel))) {
                i++;
            } else {
                flag = false;
                price = models[i].getPriceModel();
            }
        }
        if (flag) {
            throw new NoSuchModelNameException(nameModel);
        }
        return price;
    }

    public void modelModifPriceByName(double price, String name) throws NoSuchModelNameException {        //метод для модификации значения цены модели по её названию
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        } else {
            boolean flag = true;
            int i = 0;
            while (flag && i < models.length) {
                if (!(name.equals(models[i].getNameModel()))) {
                    i++;
                } else {
                    flag = false;
                }
            }
            if (flag) {
                throw new NoSuchModelNameException(name);
            } else {
                models[i].setPriceModel(price);
            }
        }
    }

    public double[] getModelsPrice() {           //метод, возвращающий массив значений цен моделей
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].priceModel;
        }
        return prices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {                    //метод добавления названия модели и её цены (путем создания нового массива Моделей)
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        } else {
            boolean flag = false;
            for (int i = 0; i < models.length; i++) {
                if (name.equals(models[i].nameModel)) {
                    flag = true;
                }
            }
            if (flag) {
                throw new DuplicateModelNameException(name);
            } else {
                models = Arrays.copyOf(models, models.length + 1);
                models[models.length - 1] = new Model(name, price);
            }
        }
    }

    public void deleteModelByName(String name) throws NoSuchModelNameException {                    //метод удаления модели по заданному имени

        int x;
        boolean flag = false;
        for (int i = 0; i < models.length; i++) {
            if (name.equals(models[i].nameModel)) {
                Model[] y = new Model[models.length - 1];
                if (i != 0 && i != models.length - 1) {
                    System.arraycopy(models, 0, y, 0, i);
                    System.arraycopy(models, i + 1, y, i, models.length - i - 1);
                    flag = true;
                }
                if (i == 0) {
                    System.arraycopy(models, 1, y, 0, models.length - 1);
                    flag = true;
                }
                if (i == models.length - 1) {
                    y = Arrays.copyOf(models, models.length - 1);
                    flag = true;
                }
                models = y;
            } else {
                if (i == models.length - 1 && !flag) {
                    throw new NoSuchModelNameException(name);
                }
            }
        }
    }

    public void printCarArray() {                //метод для вывода массива в консоль
        for (int i = 0; i < models.length; i++) {
            System.out.println(models[i].nameModel + " " + models[i].priceModel);
        }
    }

    public int getModelArraySize() {             //метод для получения размера массива Моделей
        int count = 0;
        for (Model i : models) {
            count++;
        }
        return count;
    }

    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {          //Модификация значения названия модели.
        boolean flag = true;
        int x = -1;
        for (int i = 0; i < models.length; i++) {
            if (newName.equals(models[i].nameModel)) {
                throw new DuplicateModelNameException(newName);
            }
            if (name.equals(models[i].nameModel)) {
                flag = false;
                x = i;
            }
        }
        if (flag) {
            throw new NoSuchModelNameException(name);
        }
        else{
            models[x].nameModel = newName;
        }
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(brand).append("\n");
        for (Model value : models) {
            strBuilder.append(value.nameModel).append(" ");
        }
        strBuilder.append("\n");
        for (Model model : models) {
            strBuilder.append(model.priceModel).append(" ");
        }
        return strBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Car)) return false;
        Car objCar = (Car) obj;
        if (!(this.brand.equals(objCar.getBrand()))) return false;
        if (this.getModelArraySize() != objCar.getModelArraySize()) return false;
        if (Arrays.equals(getModelsNames(), objCar.getModelsNames()) && Arrays.equals(getModelsPrice(), objCar.getModelsPrice()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = brand == null ? 0 : brand.hashCode();
        result = 31 * result + this.getModelArraySize();
        for ( int i = 0; i < this.getModelArraySize(); i++) {
            result = 31 * result + models[i].nameModel.hashCode();
            result = (int) (31 * result + models[i].priceModel);
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car result = null;
        result = (Car) super.clone();
        result.models = (Model[]) models.clone();
        for (int i = 0; i < models.length; i++) {
            result.models[i] = (Model) models[i].clone();
        }
        return result;
    }

    @Override
    public Iterator<Model> iterator() {
        return new AutoIterator();
    }

    private class AutoIterator implements Iterator<Model> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < models.length;
        }

        @Override
        public Model next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return models[currentIndex++];
        }
    }

    public static class Memento {
        private byte[] state;

        public void setAuto(Car car) {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

                objectOutputStream.writeObject(car);
                objectOutputStream.flush();

                state = byteArrayOutputStream.toByteArray();

            } catch (IOException e) {
                throw new RuntimeException("Ошибка сохранения состояния автомобиля", e);
            }
        }

        public Car getAuto() {
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(state);
                 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {

                return (Car) objectInputStream.readObject();

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Ошибка восстановления состояния автомобиля", e);
            }
        }
    }

    public Memento createMemento() {
        Memento memento = new Memento();
        memento.setAuto(this);
        return memento;
    }

    public void setMemento(Memento memento) {
        Car restoredCar = memento.getAuto();

        this.brand = restoredCar.brand;
        this.models = restoredCar.models;
    }

}
