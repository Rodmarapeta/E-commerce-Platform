package dao;

import e_commerce_platform.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagesOrderDao {

    public List<Object[]> getAllOrders() {

        List<Object[]> orders = new ArrayList<>();

        String query = "SELECT order_id, customer_name, address, " + "payment_method, total_price, order_date, order_status "
                + "FROM orders ORDER BY order_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {

                Object[] row = {
                    result.getInt("order_id"),
                    result.getString("customer_name"),
                    result.getString("address"),
                    result.getString("payment_method"),
                    result.getDouble("total_price"),
                    result.getTimestamp("order_date"),
                    result.getString("order_status")
                };

                orders.add(row);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

         return orders;
    }

    public boolean updateOrderStatus(int orderId, String newStatus) {

        String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Failed to update order status: " + e.getMessage());
            return false;
        }
    }
}