import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {

                    double a = input.readDouble();
                    double b = input.readDouble();

                    double result = a * b;

                    System.out.println("Получено от клиента: " + a + " * " + b + " = " + result);

                    output.writeDouble(result);
                    output.flush();

                } catch (IOException e) {
                    System.out.println("Ошибка при обработке клиента: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }
}