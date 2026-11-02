package ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String orderId;
    private final String userId;
    private final List<OrderItem> productList;
    private final double subtotal;
    private final double vat;
    private final double shippingFee;
    private final double totalPrice;
    private final String status;
    private final String paymentMethod;
    private final String paymentReference;
    private final ShippingInfo shipping;
    private final LocalDateTime placedAt;

    public Order(String orderId, String userId, List<OrderItem> productList,
                 double subtotal, double vat, double shippingFee, double totalPrice,
                 String status, String paymentMethod, String paymentReference,
                 ShippingInfo shipping, LocalDateTime placedAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.productList = new ArrayList<>(productList);
        this.subtotal = subtotal;
        this.vat = vat;
        this.shippingFee = shippingFee;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentReference = paymentReference;
        this.shipping = shipping;
        this.placedAt = placedAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public List<OrderItem> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getVat() {
        return vat;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public ShippingInfo getShipping() {
        return shipping;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public int itemCount() {
        int count = 0;
        for (OrderItem item : productList) {
            count += item.getQuantity();
        }
        return count;
    }
}
