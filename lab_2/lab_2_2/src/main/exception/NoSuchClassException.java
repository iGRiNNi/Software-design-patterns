package main.exception;

public class NoSuchClassException extends Exception{
    private String className;

    public String getClassName() {
        return className;
    }

    public NoSuchClassException(String name) {

        super("Класс с именем " + name + " не найден.");
        className = name;
    }
}
