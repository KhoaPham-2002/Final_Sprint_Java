package org.keyin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // You only need to change the name of the database in the URL, unless PG runs on another port on your system. Default port is 5432 
    private static final String URL = "jdbc:postgresql://localhost:5432/Final_Sprint_Java";
//    By default the username is postgres and the password is what ever you set it to be. I usually keep mine simple
    private static final String USER = "postgres";
    private static final String PASSWORD = "Loan1973.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Failed to make a connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
