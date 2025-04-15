package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost:5432/OOP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL successfully!\n----------------");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return conn;
    }
}

