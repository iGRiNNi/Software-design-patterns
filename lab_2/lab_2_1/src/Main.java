import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[] lines = {
                "Наступит день, уйдёт печаль",
                "Придет весна, уйдёт февраль",
        };

        try (TextAdapter adapter = new TextAdapter(new FileOutputStream("output.txt"))) {
            adapter.printText(lines);
            System.out.println("Текст записан в файл output.txt");
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }

        try (TextAdapter adapter = new TextAdapter(System.out)) {
            adapter.printText(lines);
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }
}