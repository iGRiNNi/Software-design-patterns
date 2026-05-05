package visitor;

import transport.Car;
import transport.Motorbike;

public interface Visitor {
    void visit(Car car);

    void visit(Motorbike motorbike);
}
