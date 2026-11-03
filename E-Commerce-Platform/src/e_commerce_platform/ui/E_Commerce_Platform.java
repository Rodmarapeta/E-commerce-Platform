/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package e_commerce_platform.ui;

import e_commerce_platform.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Rodmar
 */
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

            java.awt.EventQueue.invokeLater(() -> {
                new loginPage().setVisible(true);
            });

        } else {
            System.out.println("Unable to connect to database.");
        }
    }
}

