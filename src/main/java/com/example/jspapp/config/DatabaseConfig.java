package com.example.jspapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_smis_db";
    private static final String DB_USERNAME = "username";
    private static final String DB_PASSWORD = "password";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (Connection.class) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                } catch (ClassNotFoundException e) {
                    throw new SQLException("Database driver not active", e);
                }
            }
        }
        return connection;
    }
}
