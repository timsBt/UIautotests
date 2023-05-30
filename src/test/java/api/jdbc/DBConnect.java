package api.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.PropertiesUtils.valueProperties;

public final class DBConnect {

    private DBConnect() {
    }

    /**
     * Метод для подключения к базе данных.
     *
     * @return подключение.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(valueProperties("urlJdbc"),
                    valueProperties("usernameJdbc"),
                    valueProperties("passwordJdbc"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
