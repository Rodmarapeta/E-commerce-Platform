package e_commerce_platform;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductTable {

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products ("
        + "id INT AUTO_INCREMENT PRIMARY KEY, "
        + "name VARCHAR(100) NOT NULL, "
        + "price DOUBLE NOT NULL, "
        + "category VARCHAR(100) NOT NULL, "
        + "quantity INT NOT NULL DEFAULT 0"
        + ")";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                System.out.println("No database connection.");
                return;
            }

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Products table created successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to create products table!");
            e.printStackTrace();
        }
    }
}
