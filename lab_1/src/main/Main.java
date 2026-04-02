package main;

import main.config.ConfigLoader;
import main.config.ConfigLoaderClassic;
import main.exception.DuplicateModelNameException;
import main.exception.NoSuchModelNameException;
import main.factory.MotorbikeFactory;
import main.transport.TransportUtils;
import main.transport.Transportable;

public class Main {
    public static void main(String[] args) {
        ConfigLoader loader1 = ConfigLoader.getInstance();
        ConfigLoader loader2 = ConfigLoader.getInstance();

        System.out.println("Порт БД: " + loader1.getConfig().getProperty("db.port"));
        System.out.println("Имя программы: " + loader2.getConfig().getProperty("app.name"));
        System.out.println("Ссылки на ConfigLoader совпадают: " + (loader1 == loader2));
        System.out.println("Ссылки на Properties совпадают: " + (loader1.getConfig() == loader2.getConfig()));
        System.out.println();
        System.out.println("Классический Singleton:");
        ConfigLoaderClassic classic1 = ConfigLoaderClassic.getInstance();
        ConfigLoaderClassic classic2 = ConfigLoaderClassic.getInstance();
        System.out.println("Имя программы: " + classic1.getConfig().getProperty("app.name"));
        System.out.println("Ссылки на ConfigLoaderClassic совпадают: " + (classic1 == classic2));
        System.out.println("Ссылки на Properties совпадают: " + (classic1.getConfig() == classic2.getConfig()));
        System.out.println();

        Transportable t1 = TransportUtils.createInstance("BMW", 5);
        System.out.println("Создан объект: " + t1.getClass().getSimpleName());
        System.out.println(t1);
        System.out.println();

        TransportUtils.setTransportFactory(new MotorbikeFactory());
        Transportable t2 = TransportUtils.createInstance("Harley-Davidson", 3);
        System.out.println("Создан объект: " + t2.getClass().getSimpleName());
        System.out.println(t2);
        System.out.println();

        try {
            Transportable t1Clone = (Transportable) t1.clone();
            System.out.println("До изменений:");
            System.out.println("Оригинал:\n" + t1);
            System.out.println("Клон:\n" + t1Clone);
            System.out.println("Объекты равны: " + t1.equals(t1Clone));
            System.out.println("Ссылки совпадают: " + (t1 == t1Clone));
            System.out.println();

            t1Clone.setModelName("2", "6");
            t1Clone.modelModifPriceByName(4444.444444, "1");
            t1Clone.addModel("FFFF", 222);

            System.out.println("После изменения клона:");
            System.out.println("Оригинал:\n" + t1);
            System.out.println("Клон:\n" + t1Clone);
            System.out.println("Объекты равны: " + t1.equals(t1Clone));
            System.out.println();
            System.out.println("--------------");
            Transportable t2Clone = (Transportable) t2.clone();

            System.out.println("До изменений:");
            System.out.println("Оригинал:\n" + t2);
            System.out.println("Клон:\n" + t2Clone);
            System.out.println("Объекты равны: " + t2.equals(t2Clone));
            System.out.println("Ссылки совпадают: " + (t2 == t2Clone));
            System.out.println();

            t2Clone.setModelName("2", "6");
            t2Clone.modelModifPriceByName(4444.444444, "1");
            t2Clone.addModel("FFFF", 222);

            System.out.println("После изменения клона:");
            System.out.println("Оригинал:\n" + t2);
            System.out.println("Клон:\n" + t2Clone);
            System.out.println("Объекты равны: " + t2.equals(t2Clone));

        } catch (NoSuchModelNameException | DuplicateModelNameException e) {
            System.err.println("Ошибка логики моделей: " + e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.err.println("Ошибка: Клонирование не поддерживается!");
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}