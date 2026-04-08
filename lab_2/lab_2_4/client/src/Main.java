import java.util.*;
import java.net.*;


public class Main {
    public static void main(String[] args) {
        MultiplicationService proxy = new DoubleMultiplier("localhost", 5000);
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.print("\nВведите первое число или 'exit' для выхода: ");
                    String first = scanner.nextLine().trim();

                    if (first.equalsIgnoreCase("exit")) {
                        System.out.println("Клиент завершил работу.");
                        break;
                    }

                    double a = Double.parseDouble(first);

                    System.out.print("Введите второе число: ");
                    double b = Double.parseDouble(scanner.nextLine().trim());

                    double result = proxy.multiply(a, b);

                    System.out.println("Результат: " + a + " * " + b + " = " + result);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное число.");
                } catch (Exception e) {
                    System.out.println("Ошибка клиента: " + e.getMessage());
                }
            }
        }
    }
}
