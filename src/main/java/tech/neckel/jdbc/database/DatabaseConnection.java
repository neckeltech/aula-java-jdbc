package tech.neckel.jdbc.database;

import org.springframework.context.annotation.Configuration;
import tech.neckel.jdbc.util.PropertieUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws DbException {
        if (connection == null) {
            try {
                Properties properties = PropertieUtil.load();

                connection = DriverManager.getConnection(
                        properties.getProperty("jdbc.url"),
                        properties.getProperty("jdbc.username"),
                        properties.getProperty("jdbc.password")
                );
            } catch (SQLException e) {
                throw new DbException(e);
            }
        }
        return connection;
    }
}
