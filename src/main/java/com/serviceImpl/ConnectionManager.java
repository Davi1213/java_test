package main.java.com.serviceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author louwei
 * @ClassName: ConnectionManager
 * @Description:
 */

public class ConnectionManager {
    private static Connection connection = null;

    public static Connection openConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
