package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:students.db";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            if (conn != null) {
                System.out.println("Połączono z bazą danych SQLite.");
            }
            return conn;
        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą danych: " + e.getMessage());
            return null;
        }
    }
}
