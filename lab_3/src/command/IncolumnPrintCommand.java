package command;

import transport.Transportable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class IncolumnPrintCommand implements PrintCommand {

    @Override
    public void print(Transportable transport, OutputStream outputStream) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("Brand: ")
                .append(transport.getBrand())
                .append(System.lineSeparator());

        String[] models = transport.getModelsNames();
        double[] prices = transport.getModelsPrice();

        for (int i = 0; i < models.length; i++) {
            sb.append("Model: ")
                    .append(models[i])
                    .append(", price: ")
                    .append(prices[i])
                    .append(System.lineSeparator());
        }

        outputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}
