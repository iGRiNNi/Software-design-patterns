package main.exception;

public class ModelPriceOutOfBoundsException extends RuntimeException{           //Задание неверной цены модели.
    public ModelPriceOutOfBoundsException() {

        super("Цена модели задана неверно!");
    }
}
