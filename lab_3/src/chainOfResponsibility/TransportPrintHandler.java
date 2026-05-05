package chainOfResponsibility;

import transport.Transportable;

public interface TransportPrintHandler {

    void print(Transportable transport) throws TransportPrintHandlerException;

    void setNextHandler(TransportPrintHandler handler);
}
