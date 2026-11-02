/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import e_commerce_platform.DBConnection;
import e_commerce_platform.Cart;
import e_commerce_platform.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodmar
 */
public class OrderDao {

    public boolean saveOrder(int userId, String customerName, String address, String contact, String paymentMethod, Cart cart) {
        Connection conn = null;
        PreparedStatement orderStatement = null;
        PreparedStatement itemStatement = null;
        ResultSet result = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null || cart.isEmpty()) {
                return false;
            }
            conn.setAutoCommit(false);

            String orderQuery = "INSERT INTO orders " + "(user_id, customer_name, address, contact, "
                    + "payment_method, total_price, order_status) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            orderStatement = conn.prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            orderStatement.setInt(1, userId);
            orderStatement.setString(2, customerName);
            orderStatement.setString(3, address);
            orderStatement.setString(4, contact);
            orderStatement.setString(5, paymentMethod);
            orderStatement.setDouble(6, cart.getTotalPrice());
            orderStatement.setString(7, "Completed");

            orderStatement.executeUpdate();
            
            result = orderStatement.getGeneratedKeys();
            
            if (result.next()) {
                int orderId = result.getInt(1);
                String itemQuery = "INSERT INTO order_items " + "(order_id, product_id, product_name, "
                        + "price, quantity, subtotal) " + "VALUES (?, ?, ?, ?, ?, ?)";

                itemStatement = conn.prepareStatement(itemQuery);

                for (Product product : cart.getCartProducts()) {

                    itemStatement.setInt(1, orderId);
                    itemStatement.setInt(2, product.getId());
                    itemStatement.setString(3, product.getName());
                    itemStatement.setDouble(4, product.getPrice());
                    itemStatement.setInt(5, 1);
                    itemStatement.setDouble(6, product.getPrice());

                    itemStatement.executeUpdate();
                }

                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException ex) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException error) {
                System.out.println(error.getMessage());
            }
            System.out.println(ex.getMessage());
            return false;
            
        } finally {

            try {
                if (result != null) {
                    result.close();
                }
                if (orderStatement != null) {
                    orderStatement.close();
                }
                if (itemStatement != null) {
                    itemStatement.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
