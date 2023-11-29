package tech.neckel.jdbc.util;

import tech.neckel.jdbc.database.DatabaseConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertieUtil {
    private static final String PROPERTIES_FILE = "application.properties";

    public static Properties load() {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
