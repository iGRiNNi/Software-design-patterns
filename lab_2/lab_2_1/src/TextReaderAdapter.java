import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TextReaderAdapter implements AutoCloseable {
    private final InputStream inputStream;

    public TextReaderAdapter(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        this.inputStream = inputStream;
    }

    public String[] readText() throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines.toArray(new String[0]);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
