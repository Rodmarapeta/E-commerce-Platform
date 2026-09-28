/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_commerce_platform;


import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private ArrayList<Order> orderHistory;
    
    
    public User(int userId, String userName, String userEmail) {
        id = userId;
        name = userName;
        email = userEmail;
        orderHistory = new ArrayList<>();
        
        
    }
}
