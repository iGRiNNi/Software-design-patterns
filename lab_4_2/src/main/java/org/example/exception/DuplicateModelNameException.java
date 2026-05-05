package org.example.exception;;

public class DuplicateModelNameException extends Exception {        //дублирования названия моделей

    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public DuplicateModelNameException(String name) {

        super("Модель с именем " + name + " уже существует.");
        modelName = name;
    }
}
