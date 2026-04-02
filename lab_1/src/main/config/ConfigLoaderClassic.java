package main.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoaderClassic {

    private static ConfigLoaderClassic instance;
    private final Properties properties;

    private ConfigLoaderClassic() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        System.out.println("Загрузка настроек Classic...");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Файл config.properties не найден");
            }

            properties.load(inputStream);
            System.out.println("Настройки загружены Classic!");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении config.properties", e);
        }
    }

    public static synchronized ConfigLoaderClassic getInstance() {
        if (instance == null) {
            instance = new ConfigLoaderClassic();
        }
        return instance;
    }

    public Properties getConfig() {
        return properties;
    }
}
