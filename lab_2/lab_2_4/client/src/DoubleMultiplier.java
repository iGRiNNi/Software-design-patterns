import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DoubleMultiplier implements MultiplicationService {
    private final String host;
    private final int port;

    public DoubleMultiplier(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public double multiply(double a, double b) throws IOException {
        try (Socket socket = new Socket(host, port);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {

            output.writeDouble(a);
            output.writeDouble(b);
            output.flush();

            return input.readDouble();
        }
    }
}
