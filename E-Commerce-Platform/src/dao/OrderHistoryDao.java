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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodmar
 */
public class OrderHistoryDao {
    
    public List<String> getOrderHistory(int userId) {
        List<String> orders = new ArrayList<>();
        String query = "SELECT order_id, total_price, order_status, order_date " + "FROM orders WHERE user_id = ? " + "ORDER BY order_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                String order = "Order ID: " + result.getInt("order_id") + "\nTotal: PHP " + String.format("%.2f", result.getDouble("total_price"))
                        + "\nStatus: " + result.getString("order_status") + "\nDate: " + result.getString("order_date");

                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve order history!");
            e.printStackTrace();
        }
        return orders;
    }
}
