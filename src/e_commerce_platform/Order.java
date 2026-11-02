/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_commerce_platform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodmar
 */
public class Order {
    private int orderId;
    private int userId;
    private List<Product> productList;
    private double totalPrice;
    private String customerName;
    private String deliveryAddress;
    private static int idCount = 1; 
    private static final List<Order> placedOrders = new ArrayList<>();

    public Order(int userId, List<Product> productList, double totalPrice, String customerName, String deliveryAddress) 
    {
        this.orderId = idCount++;
        this.userId = userId;
        this.productList = new ArrayList<>(productList);
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public static void addOrder(Order order) {
        if (order != null) {
            placedOrders.add(order);
        }
    }

    public static List<Order> getPlacedOrders() {
        return placedOrders;
    }
}