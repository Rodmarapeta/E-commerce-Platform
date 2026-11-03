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
    public static void createTables() {

    String[] tables = {

        "CREATE TABLE IF NOT EXISTS users ("
        + "id INT AUTO_INCREMENT PRIMARY KEY, "
        + "username VARCHAR(50) NOT NULL UNIQUE, "
        + "email VARCHAR(100) NOT NULL UNIQUE, "
        + "password VARCHAR(255) NOT NULL"
        + ")",

        "CREATE TABLE IF NOT EXISTS products ("
        + "id INT AUTO_INCREMENT PRIMARY KEY, "
        + "name VARCHAR(100) NOT NULL, "
        + "price DECIMAL(10,2) NOT NULL, "
        + "category VARCHAR(100) NOT NULL, "
        + "quantity INT NOT NULL DEFAULT 0"
        + ")",

        "CREATE TABLE IF NOT EXISTS orders ("
        + "order_id INT AUTO_INCREMENT PRIMARY KEY, "
        + "user_id INT NOT NULL, "
        + "customer_name VARCHAR(100) NOT NULL, "
        + "address VARCHAR(255) NOT NULL, "
        + "contact VARCHAR(30) NOT NULL, "
        + "payment_method VARCHAR(50) NOT NULL, "
        + "total_price DECIMAL(10,2) NOT NULL, "
        + "order_status VARCHAR(50) DEFAULT 'Pending', "
        + "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
        + ")",

        // ORDER ITEMS TABLE
        "CREATE TABLE IF NOT EXISTS order_items ("
        + "item_id INT AUTO_INCREMENT PRIMARY KEY, "
        + "order_id INT NOT NULL, "
        + "product_id INT NOT NULL, "
        + "product_name VARCHAR(100) NOT NULL, "
        + "price DECIMAL(10,2) NOT NULL, "
        + "quantity INT NOT NULL, "
        + "subtotal DECIMAL(10,2) NOT NULL"
        + ")"
    };

    try (Connection conn = getConnection()) {

        if (conn == null) {
            System.out.println("Cannot create tables. Database connection failed.");
            return;
        }

        try (Statement stmt = conn.createStatement()) {

            for (String sql : tables) {
                stmt.executeUpdate(sql);
            }

            System.out.println("All database tables are ready!");
        }

    } catch (SQLException e) {
        System.out.println("Table creation failed!");
        e.printStackTrace();
    }
 } 
    public static void initializeDatabase() {
    createDatabase();
    createTables();
}
    
}