package main;

import main.exception.DuplicateModelNameException;
import main.exception.NoSuchModelNameException;
import main.transport.Car;
import main.transport.Transportable;
import main.transport.TransportUtils;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Transportable car = new Car("BMW", 0);
        Transportable syncCar = TransportUtils.synchronizedTransport(car);
        //Transportable syncCar = car;

        int threadCount = 5;
        int modelsPerThread = 20;

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                for (int j = 0; j < modelsPerThread; j++) {
                    String modelName = "Model-" + threadIndex + "-" + j;
                    double price = 100000 + j;

                    try {
                        syncCar.addModel(modelName, price);
                        System.out.println(Thread.currentThread().getName()
                                + " added " + modelName);
                    } catch (DuplicateModelNameException e) {
                        System.out.println(Thread.currentThread().getName()
                                + " duplicate: " + modelName);
                    }
                }
            }, "Thread-" + i);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        int expected = threadCount * modelsPerThread;
        int actual = syncCar.getModelArraySize();

        System.out.println("\nExpected models count: " + expected);
        System.out.println("Actual models count:   " + actual);

        if (expected == actual) {
            System.out.println("Decorator works correctly.");
        } else {
            System.out.println("Something went wrong.");
        }
    }
}