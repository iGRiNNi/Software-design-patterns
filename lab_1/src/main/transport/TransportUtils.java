package main.transport;

import main.exception.DuplicateModelNameException;
import main.exception.NoSuchClassException;
import main.factory.CarFactory;
import main.factory.TransportFactory;

import java.io.*;

public class TransportUtils {

    private static TransportFactory factory = new CarFactory();

    public static void setTransportFactory(TransportFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        TransportUtils.factory = factory;
    }

    public static Transportable createInstance(String name, int size)
    {
        return factory.createInstance(name, size);
    }

    public static double arifmethicMean(Transportable type) {         //возвращение среднего арифметического цен моделей для заданного Транспортного средства
        double[] x = type.getModelsPrice();
        double y = 0;
        for (Double i : x) {
            y = y + i;
        }
        y = y / x.length;
        return y;
    }

    public static void printModelsNames(Transportable type) {                              //Вывод на экран всех моделей для заданного Транспортного средства.
        String[] x = type.getModelsNames();
        for(int i = 0; i < x.length; i++){
            System.out.println(i + ". " + x[i]);
        }
    }

    public static void printModelsPrices(Transportable type) {         //Вывод на экран всех цен на модели для заданного Транспортного средства.
        double[] x = type.getModelsPrice();
        for(int i = 0; i < x.length; i++){
            System.out.println(i + ". " + x[i]);
        }
    }

    public static void outputTransportable(Transportable v, OutputStream out) throws IOException {          // Запись информации о транспортном средстве в байтовый поток (использован DataOutputStream).
        DataOutputStream output = new DataOutputStream(out);
        output.writeInt(v.getClass().getName().length());      // Записываем длину названия класса.
        output.write(v.getClass().getName().getBytes());       // Записываем название класса.
        //output.writeUTF(v.getClass().getName());
        output.writeInt(v.getBrand().length());                                         // Записываем длину названия бренда.
        output.write(v.getBrand().getBytes());                    // Записываем бренд.
        output.writeInt(v.getModelArraySize());                                         // Записываем длину размера моделей.
        String[] models = v.getModelsNames();
        for(int i = 0; i < v.getModelArraySize(); i++){
            output.writeInt(models[i].length());                                // Записываем длину названия модели.
            output.write(models[i].getBytes());                   // Записываем модель.
        }
        double[] prices = v.getModelsPrice();
        for(int i = 0; i < v.getModelArraySize(); i++){
            output.writeDouble(prices[i]);                                              // Записываем цену.
        }
    }

    public static Transportable createTransportableByType(String str) throws NoSuchClassException {
        Transportable v;
        if(str.equals("Car")) {
            v = new Car();            // Нужно пустой конструкт добавлять?
        }
        else if(str.equals("Motorbike")) {
            v = new Motorbike();
        }
        else {
            throw new NoSuchClassException(str);           // Или просто вернуть null?
        }
        return v;
    }

    public static Transportable inputTransportable(InputStream in) throws IOException, DuplicateModelNameException, NoSuchClassException {         // Чтение информации о транспортном средстве из байтового потока (использовать DataInputStream).
        DataInputStream input = new DataInputStream(in);
        byte[] b = new byte[input.readInt()];
        input.read(b);
        String str = new String(b);
        /*
        Transportable v;
        if(str.equals("Car")) {
            v = new Car();            // Нужно пустой конструкт добавлять?
        }
        else if(str.equals("Motorbike")){
                v = new Motorbike();
        }
        else {
            throw new NoSuchClassException(b.toString());           // Или по другому сделать?
        }
         */
        Transportable v = createTransportableByType(str);
        b = new byte[input.readInt()];
        input.read(b);
        String brand = new String(b, 0, b.length);
        v.setBrand(brand);
        //b = new byte[input.readInt()];
        int c = input.readInt();
        String[] models = new String[c];
        for(int i = 0; i < c; i++) {
            b = new byte[input.readInt()];
            input.read(b);
            String model = new String(b);
            models[i] = model;
        }
        for (int i = 0; i < c; i++) {
            v.addModel(models[i], input.readDouble());
        }
        return v;
    }

    public static void writeTransportable(Transportable v, Writer out) {            // Запись информации о транспортном средстве в символьный поток (использован PrintWriter).
        PrintWriter pw = new PrintWriter(out);
        pw.println(v.getClass().getName());
        pw.println(v.getBrand());
        pw.println(v.getModelArraySize());

        String[] models = v.getModelsNames();
        for(int i = 0; i < v.getModelArraySize(); i++) {
            pw.println(models[i]);
        }

        double[] prices = v.getModelsPrice();
        for(int i = 0; i < v.getModelArraySize(); i++) {
            pw.println(prices[i]);
        }
        pw.flush();
    }

    public static Transportable readTransportable(Reader in) throws IOException, DuplicateModelNameException, NoSuchClassException {              // Чтение информации о транспортном средстве из символьного потока (использован BufferedReader или StreamTokenizer).
        BufferedReader reader = new BufferedReader(in);
        String line;
        line = reader.readLine();
        /*
        Transportable v;
        if(line.equals("Car")) {
            v = new Car();
        }
        else if(line.equals("Motorbike")) {
            v = new Motorbike();
        }
        else {
            throw new NoSuchClassException(line);
        }
         */
        Transportable v = createTransportableByType(line);
        line = reader.readLine();
        v.setBrand(line);
        int c = Integer.parseInt(reader.readLine());
        String[] models = new String[c];
        //line = reader.readLine();
        for(int i = 0; i < c; i++) {
            models[i] = reader.readLine();
        }
        for(int i = 0; i < c; i++) {
            v.addModel(models[i], Double.parseDouble(reader.readLine()));
        }
        return v;
    }
}
