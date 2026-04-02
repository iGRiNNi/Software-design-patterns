package main.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;

    private ConfigLoader() {
        properties = new Properties();

        loadProperties();
    }

    private void loadProperties() {
        System.out.println("Загрузка настроек...");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Файл config.properties не найден");
            }

            properties.load(inputStream);
            System.out.println("Настройки загружены!");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении config.properties", e);
        }
    }

    private static class Holder {
        private static final ConfigLoader INSTANCE = new ConfigLoader();
    }

    public static ConfigLoader getInstance() {
        return Holder.INSTANCE;
    }

    public Properties getConfig() {
        return properties;
    }
}

