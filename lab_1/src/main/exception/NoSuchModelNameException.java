package main.exception;

public class NoSuchModelNameException extends Exception {           //задания несуществующего имени модели

    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public NoSuchModelNameException(String name) {

        super("Модель с именем " + name + " не найдена.");
        modelName = name;
    }
}
