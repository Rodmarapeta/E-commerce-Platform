package e_commerce_platform.ui;

import e_commerce_platform.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class E_Commerce_Platform {

    public static void main(String[] args) {

        DBConnection.createDatabase();
        DBConnection.createTables();

        Connection c = DBConnection.getConnection();

        if (c != null) {
            System.out.println("SUCCESS: Java is connected to MySQL!");

            try {
                c.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            loginPage login = new loginPage();
            login.setVisible(true);

        } else {
            System.out.println("Unable to connect to database.");
        }
    }
}