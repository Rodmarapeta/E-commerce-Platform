package e_commerce_platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private static final List<Order> placedOrders = new ArrayList<>();

    private int orderId;
    private int userId;
    private String customerName;
    private ArrayList<Product> productList;
    private double totalPrice;

    public Order(int orderNumber, int userNumber) {
        this.orderId = orderNumber;
        this.userId = userNumber;
        this.customerName = "User #" + userNumber;
        this.productList = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public Order(int orderNumber, int userNumber, String customerName, double totalPrice) {
        this.orderId = orderNumber;
        this.userId = userNumber;
        this.customerName = customerName;
        this.productList = new ArrayList<>();
        this.totalPrice = totalPrice;
    }

    public static void addPlacedOrder(Order order) {
        if (order != null) {
            placedOrders.add(order);
        }
    }

    public static List<Order> getPlacedOrders() {
        return Collections.unmodifiableList(placedOrders);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}