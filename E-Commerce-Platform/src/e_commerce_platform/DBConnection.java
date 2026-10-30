/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_commerce_platform;

/**
 *
 * @author Rodmar
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void createDatabase() {
        String query = "CREATE DATABASE IF NOT EXISTS e_commerce_db";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);
            System.out.println("E-Commerce database is ready!");

        } catch (SQLException e) {
            System.out.println("Database creation failed!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(
                    URL + "e_commerce_db", USER, PASS);

            System.out.println("Connected to E-Commerce database!");
            return conn;

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
    
     public static void main(String[] args) {

        createDatabase();

        Connection conn = getConnection();

        if (conn != null) {
            System.out.println("Connection test successful!");

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}