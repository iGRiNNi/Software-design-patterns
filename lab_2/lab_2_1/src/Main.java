import java.io.*;


public class Main {
    public static void main(String[] args) {
        String[] sourceLines = {
                "Наступит день, уйдёт печаль",
                "Придет весна, уйдёт февраль"
        };

        System.out.println("Исходные строки:");
        printLines(sourceLines);

        try {
            byte[] bytes = writeToBytes(sourceLines);
            System.out.println("Строки конвертированные в байты:");
            printBytes(bytes);

            String[] restoredLines = readFromBytes(bytes);
            System.out.println("Строки полученные из байтов:");
            printLines(restoredLines);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    private static byte[] writeToBytes(String[] lines) throws IOException {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             TextWriterAdapter writerAdapter = new TextWriterAdapter(byteOut)) {

            writerAdapter.printText(lines);
            return byteOut.toByteArray();
        }
    }

    private static String[] readFromBytes(byte[] bytes) throws IOException {
        try (ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
             TextReaderAdapter readerAdapter = new TextReaderAdapter(byteIn)) {

            return readerAdapter.readText();
        }
    }

    private static void printLines( String[] lines) {
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println();
    }

    private static void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println("\n");
    }
}