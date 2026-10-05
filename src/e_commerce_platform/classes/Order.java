
package e_commerce_platform.classes;


import java.util.ArrayList;

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
