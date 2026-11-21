/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import e_commerce_platform.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Rodmar
 */
public class SalesReportDao {
   
    public double getTotalSales() {
        double totalSales = 0;
        String query = "SELECT SUM(total_price) AS total_sales FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                totalSales = result.getDouble("total_sales");
            }

        } catch (SQLException e) {
            System.out.println("Failed to get total sales!");
            e.printStackTrace();
        }
        return totalSales;
    }

    public int getTotalOrders() {
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS total_orders FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                totalOrders = result.getInt("total_orders");
            }

        } catch (SQLException e) {
            System.out.println("Failed to get total orders!");
            e.printStackTrace();
        }
        return totalOrders;
    }
}