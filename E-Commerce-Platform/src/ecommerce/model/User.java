package ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private final String id;
    private String name;
    private String email;
    private final List<Order> orderHistory = new ArrayList<>();

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return Collections.unmodifiableList(orderHistory);
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
