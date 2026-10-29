/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import e_commerce_platform.Product;
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
public class ProductDao {

    public boolean addProduct(Product product) {

        String sql = "INSERT INTO products (name, price, category) "
                   + "VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, product.getName());
                stmt.setDouble(2, product.getPrice());
                stmt.setString(3, product.getCategory());

                stmt.executeUpdate();
                System.out.println("Product added successfully!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT id, name, price, category FROM products";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return products;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category")
                    );

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
