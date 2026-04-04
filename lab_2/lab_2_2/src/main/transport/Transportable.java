package main.transport;

import main.exception.DuplicateModelNameException;
import main.exception.NoSuchModelNameException;

import java.io.Serializable;

public interface Transportable extends Serializable, Cloneable {
    String getBrand();             //Получение марки.

    void setBrand(String brand);         //Модификация марки.

    void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException;                   //Модификация значения названия модели.

    String[] getModelsNames();                  //Возвращение массива названий всех моделей.

    double modelPriceByName(String nameModel) throws NoSuchModelNameException;       //Получение значения цены модели по её названию.

    void modelModifPriceByName(double price, String name) throws NoSuchModelNameException;        //Модификация значения цены модели по её названию.

    double[] getModelsPrice();                                  //Возвращение массива значений цен моделей.

    void addModel(String name, double price) throws DuplicateModelNameException;                //Добавление названия модели и её цены.

    void deleteModelByName(String name) throws NoSuchModelNameException;                 //Удаление модели по заданному имени.

    int getModelArraySize();                                 //Получение размера массива Моделей.

    Object clone() throws CloneNotSupportedException;
}

