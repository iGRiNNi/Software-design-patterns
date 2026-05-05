package chainOfResponsibility;

import transport.Transportable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InLineTransportPrintHandler implements TransportPrintHandler {
    private TransportPrintHandler nextHandler;
    private final String fileName;

    public InLineTransportPrintHandler(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public void print(Transportable transport) throws TransportPrintHandlerException {
        if (transport.getModelArraySize() <= 3)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Brand: ").append(transport.getBrand());
            var models = transport.getModelsNames();
            var prices = transport.getModelsPrice();
            for (int i = 0; i < transport.getModelArraySize() - 1; i++)
            {
                stringBuilder.append("; Model: ").append(models[i]).append(", price: ")
                        .append(prices[i]);
            }
            stringBuilder.append(".");

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
                writer.println(stringBuilder);
            } catch (IOException e) {
                throw new TransportPrintHandlerException("Ошибка записи в файл: " + e.getMessage());
            }
        }
        else {
            if (nextHandler == null) {
                throw new TransportPrintHandlerException("Следующий обработчик в цепочке TransportPrintHandler не определен!");
            }
            nextHandler.print(transport);
        }
    }

    @Override
    public void setNextHandler(TransportPrintHandler handler) {
        nextHandler = handler;
    }
}
