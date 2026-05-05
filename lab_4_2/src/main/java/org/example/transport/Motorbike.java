package org.example.transport;

import org.example.exception.DuplicateModelNameException;
import org.example.exception.ModelPriceOutOfBoundsException;
import org.example.exception.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Arrays;


public class Motorbike implements Transportable {
    private class Model implements Serializable {
        String nameModel = null;
        double priceModel = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model() {
        }

        Model(String nameModel, double priceModel) {
            this.nameModel = nameModel;
            this.priceModel = priceModel;
        }
    }

    private int size = 0;
    private Model head;
    private transient long lastModified;

    {
        lastModified = System.currentTimeMillis();
    }

    private String brand;

    public Motorbike() {
        head = new Model();
        head.next = head;
        head.prev = head;

    }

    public Motorbike(String brand, int sizeModelList) {
        head = new Model();
        head.next = head;
        head.prev = head;
        this.brand = brand;
        Model p;
        size = sizeModelList;
        for (int i = 0; i < sizeModelList; i++) {
            p = new Model(Integer.toString(i), Math.random() * 100000);
            p.next = head;
            p.prev = head.prev;
            head.prev.next = p;
            head.prev = p;
        }
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
        lastModified = System.currentTimeMillis();
    }


    public String[] getModelsNames() {            //метод, возвращающий массив названий всех моделей,
        String names[] = new String[size];
        Model p = head.next;
        //if(head != null
        int i = 0;
        while (p != head) {
            names[i] = p.nameModel;
            p = p.next;
            i++;
        }
        return names;
    }


    public double modelPriceByName(String nameModel) throws NoSuchModelNameException {       //метод для получения значения цены модели по её названию
        boolean flag = true;
        Model p = head.next;
        double price = 0;
        while (flag && p != head) {
            if (!(nameModel.equals(p.nameModel))) {
                p = p.next;
            } else {
                flag = false;
                price = p.priceModel;
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
            Model p = head.next;
            boolean flag = true;
            while (flag && p != head) {
                if (!(name.equals(p.nameModel))) {
                    p = p.next;
                } else {
                    flag = false;
                }
            }
            if (flag) {
                throw new NoSuchModelNameException(name);
            } else {
                p.priceModel = price;
                lastModified = System.currentTimeMillis();
            }
        }
    }

    public double[] getModelsPrice() {           //метод, возвращающий массив значений цен моделей
        double[] prices = new double[size];
        int i = 0;
        Model p = head.next;
        while (p != head) {
            prices[i] = p.priceModel;
            p = p.next;
            i++;
        }
        return prices;
    }


    public void addModel(String name, double price) throws DuplicateModelNameException {                    //метод добавления названия модели и её цены (путем вставки нового узла в конец)

        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        } else {
            boolean flag = true;
            Model p = head.next;
            while (flag && p != head) {
                if (name.equals(p.nameModel)) {
                    flag = false;
                }
                p = p.next;
            }
            if (!flag) {
                throw new DuplicateModelNameException(name);
            } else {
                p = head.prev;
                Model q; // q – ссылка на новый узел
                q = new Model(name, price); // создание и инициализация нового узла
                q.next = p.next; // 1 – установка связи нового узла со следуюшим
                q.prev = p; // 2 – установка связи нового узла с предыдущим
                p.next.prev = q; // 3 – установка связи следующего узла с новым
                p.next = q; // 4 - установка связи предыдущего узла с новым
                lastModified = System.currentTimeMillis();
                size++;     //Надо или нет?
            }
        }
    }

    public void deleteModelByName(String name) throws NoSuchModelNameException {                    //метод удаления модели по заданному имени
        Model p = head.next;
        boolean flag = true;
        while (p != head && flag) {
            if (name.equals(p.nameModel)) {
                p.prev.next = p.next;
                p.next.prev = p.prev;
                size--;
                flag = false;
                lastModified = System.currentTimeMillis();
            } else {
                p = p.next;
                if (p.next == head && flag) {
                    throw new NoSuchModelNameException(name);
                }
            }

        }
    }

    public int getModelArraySize() {             //метод для получения размера списка Моделей
        return size;
    }

    public void printMotoList(Model head) {            //метод для вывода списка в консоль

        Model p = head.next;
        while (p != head) {
            System.out.println(p.nameModel + " " + Double.toString(p.priceModel));
            p = p.next;
        }
    }

    public Model getHead() {
        return head;
    }

    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {          //Модификация значения названия модели.
        boolean flag = true;
        Model p = head.next;
        Model h = head.next;
        while (p != head) {
            if (newName.equals(p.nameModel)) {
                throw new DuplicateModelNameException(newName);
            } else {
                if (name.equals(p.nameModel)) {
                    //p.nameModel = newName;
                    flag = false;
                    lastModified = System.currentTimeMillis();
                    h = p;
                }
            }
            p = p.next;
        }
        if (flag) {
            throw new NoSuchModelNameException(name);
        }
        else{
            h.nameModel = newName;
        }
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(brand).append("\n");
        Model p = head.next;
        while(p != head) {
            strBuilder.append(p.nameModel).append(" ");
            p = p.next;
        }
        strBuilder.append("\n");
        p = head.next;
        while(p != head) {
            strBuilder.append(p.priceModel).append(" ");
            p = p.next;
        }
        return strBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Motorbike)) return false;
        Motorbike objMoto = (Motorbike) obj;
        if (!(this.brand.equals(objMoto.getBrand()))) return false;
        if (this.getModelArraySize() != objMoto.getModelArraySize()) return false;
        if (Arrays.equals(getModelsNames(), objMoto.getModelsNames()) && Arrays.equals(getModelsPrice(), objMoto.getModelsPrice())) return true;
        return false;

    }

    @Override
    public int hashCode() {
        int result = brand == null ? 0 : brand.hashCode();
        result = 31 * result + this.getModelArraySize();
        Model p = head.next;
        for ( int i = 0; i < this.getModelArraySize(); i++) {
            result = 31 * result + p.nameModel.hashCode();
            result = (int) (31 * result + p.priceModel);
            p = p.next;
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Motorbike result = null;
        result = (Motorbike) super.clone();
        result.size = 0;
        Model p = head.next;
        result.head = new Model();
        result.head.next = result.head;
        result.head.prev = result.head;
        while (p != head) {
            try {
                result.addModel(p.nameModel, p.priceModel);
            } catch (DuplicateModelNameException e) {
                throw new RuntimeException(e);
            }
            p = p.next;
        }
        return result;
    }
}
