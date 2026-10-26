package e_commerce_platform.nonUIclass;

import java.util.List;

public class order {
    private String orderId;
    private String userId;
    private List<Product> productList;
    private double totalPrice;
    private int quantity;

    public order(String orderId, String userId, List<Product> productList, double totalPrice) {
        orderId = orderId;
        userId = userId;
        productList = productList;
        totalPrice = totalPrice;
    }

    public String getOrderId(){
        return orderId;
    }
    public String getUserId(){
        return userId;
    }
    public List<Product> getProductList(){
        return productList;
    }
    public double getTotalPrice(){
        return totalPrice;
    }
}
