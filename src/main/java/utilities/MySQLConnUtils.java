package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnUtils {
    public static Connection getMySQLConnection() {
        String hostName = "localhost";
        String dbName = "orangehrm5";
        String userName = "root";
        String password = "";
        return getMySQLConnection(hostName, dbName, userName, password);
    }
    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
        Connection conn = null;
        try {
            String connectionURL = "jdbc:mysql://" + hostName + ":3309/" + dbName;
            conn = DriverManager.getConnection(connectionURL, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
