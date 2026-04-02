import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TextAdapter implements AutoCloseable{
    private final OutputStream outputStream;

    public TextAdapter(OutputStream outputStream) {
        if (outputStream == null) throw new IllegalArgumentException("OutpuStream cannot be null");
        this.outputStream = outputStream;
    }

    public void printText(String[] text) throws IOException {
        if (text == null) return;

        for (String s : text) {
            if (s == null) {
                s = "null";
            }
            outputStream.write(s.getBytes(StandardCharsets.UTF_16));
            outputStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_16));
        }
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.flush();
        outputStream.close();
    }
}
