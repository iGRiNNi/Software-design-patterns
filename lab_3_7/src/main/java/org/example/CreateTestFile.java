package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateTestFile {
    public static void main(String[] args) {
        int[] array = {
                5, 1, 2, 5, 3, 2, 5, 1, 4, 3, 3
                // 1 - 2, 2 - 2, 3 - 3, 4 - 1, 5 - 3
        };

        String fileName = "input.txt";

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(array);
            System.out.println("Файл " + fileName + " создан.");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}
