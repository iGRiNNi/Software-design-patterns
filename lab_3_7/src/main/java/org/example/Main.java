package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: укажите имя входного файла.");
            return;
        }

        String inputFileName = args[0];

        try {
            int[] array = readArrayFromFile(inputFileName);

            System.out.println("Исходный массив:");
            printArray(array);

            CountContext context = new CountContext(new HashMapCountStrategy());

            System.out.println("Подсчёт через HashMap:");
            CountResult hashMapResult = context.count(array);
            hashMapResult.print();

            context.setStrategy(new SortCountStrategy());

            System.out.println("Подсчёт через сортировку:");
            CountResult sortResult = context.count(array);
            sortResult.print();

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка: невозможно прочитать объект из файла.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static int[] readArrayFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            Object object = input.readObject();

            if (!(object instanceof int[])) {
                throw new IllegalArgumentException("В файле должен быть сериализованный объект типа int[].");
            }

            return (int[]) object;
        }
    }

    private static void printArray(int[] array) {
        for (int number : array) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}