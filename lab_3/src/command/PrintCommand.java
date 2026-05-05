package command;

import transport.Transportable;

import java.io.IOException;
import java.io.OutputStream;

public interface PrintCommand {
    void print(Transportable transport, OutputStream outputStream) throws IOException;
}
