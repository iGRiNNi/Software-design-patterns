package visitor;

import transport.Car;
import transport.Motorbike;

public class PrintVisitor implements Visitor {

    @Override
    public void visit(Car car) {
        System.out.println(buildInlineText(car));
    }

    @Override
    public void visit(Motorbike motorbike) {
        System.out.println(buildColumnText(motorbike));
    }

    private String buildInlineText(Car car) {
        StringBuilder sb = new StringBuilder();

        sb.append("Brand: ").append(car.getBrand());

        String[] models = car.getModelsNames();
        double[] prices = car.getModelsPrice();

        for (int i = 0; i < models.length; i++) {
            sb.append("; Model: ")
                    .append(models[i])
                    .append(", price: ")
                    .append(prices[i]);
        }

        sb.append(".");

        return sb.toString();
    }

    private String buildColumnText(Motorbike motorbike) {
        StringBuilder sb = new StringBuilder();

        sb.append("Brand: ")
                .append(motorbike.getBrand())
                .append(System.lineSeparator());

        String[] models = motorbike.getModelsNames();
        double[] prices = motorbike.getModelsPrice();

        for (int i = 0; i < models.length; i++) {
            sb.append("Model: ")
                    .append(models[i])
                    .append(", price: ")
                    .append(prices[i])
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
