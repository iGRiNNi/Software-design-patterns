package chainOfResponsibility;

import transport.Transportable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InColumnTransportPrintHandler implements TransportPrintHandler {
    TransportPrintHandler nextHandler;
    private final String fileName;

    public InColumnTransportPrintHandler(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public void print(Transportable transport) throws TransportPrintHandlerException {
        if (transport.getModelArraySize() > 3)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Brand: ").append(transport.getBrand()).append(System.lineSeparator());
            var models = transport.getModelsNames();
            var prices = transport.getModelsPrice();
            for (int i = 0; i < models.length; i++) {
                stringBuilder.append("Model: ").append(models[i])
                        .append(", price: ").append(prices[i])
                        .append(System.lineSeparator());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
                writer.println(stringBuilder);
            } catch (IOException e) {
                throw new TransportPrintHandlerException("Ошибка записи в файл: " + e.getMessage());
            }
        }
        else {
            if (nextHandler == null)
            {
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
