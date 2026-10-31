/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package e_commerce_platform.ui;

import e_commerce_platform.DBConnection;
import javax.swing.JOptionPane;
import e_commerce_platform.ProductTable;

/**
 *
 * @author Rodmar
 */
public class E_Commerce_Platform {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DBConnection.getConnection();
        ProductTable.createTable();
        
        loginPage frame = new loginPage();
        frame.setVisible(true);
                
    }
    
}
