/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_commerce_platform;


import java.util.ArrayList;
/**
 *
 * @author Rodmar
 */
public class Order {
    private int orderId;
    private int userId;
    private ArrayList<Product> productList;
    private double totalPrice;
    
    public Order(int orderNumber, int userNumber) {
        orderId = orderNumber;
        userId = userNumber;
        productList = new ArrayList<>();
        totalPrice = 0.0;
        
    }
    
}
