package se.jensen.yuki.webshop_admin.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private final Properties properties;

    private ConfigurationManager() {
        properties = new Properties();
        try (InputStream input = ClassLoader.getSystemResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getProperty("db.url"), getProperty("db.username"), getProperty("db.password"));
    }
}
