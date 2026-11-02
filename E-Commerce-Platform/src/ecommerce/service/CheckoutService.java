package ecommerce.service;

import ecommerce.config.Config;
import ecommerce.model.Cart;
import ecommerce.model.Order;
import ecommerce.model.OrderItem;
import ecommerce.model.ShippingInfo;
import ecommerce.model.User;
import ecommerce.repository.OrderRepository;
import ecommerce.util.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Checkout pipeline: quote totals, charge payment, build the order,
 * persist it, attach it to the user's history, and empty the cart.
 */
public class CheckoutService {
    public record Quote(double subtotal, double vat, double shipping, double total) {
    }

    public enum PaymentMethod {
        CARD("Credit / Debit Card"),
        COD("Cash on Delivery");

        private final String label;

        PaymentMethod(String label) {
            this.label = label;
        }

        public String label() {
            return label;
        }
    }

    public static class CheckoutException extends Exception {
        public CheckoutException(String message) {
            super(message);
        }
    }

    private final OrderRepository orders;
    private final PaymentService payments;

    public CheckoutService(OrderRepository orders, PaymentService payments) {
        this.orders = orders;
        this.payments = payments;
    }

    public Quote quote(Cart cart) {
        double subtotal = Util.round(cart.subtotal());
        double vat = Util.round(subtotal * Config.VAT_RATE);
        double shipping = subtotal == 0 || subtotal >= Config.FREE_SHIPPING_THRESHOLD ? 0 : Config.SHIPPING_FEE;
        double total = Util.round(subtotal + vat + shipping);
        return new Quote(subtotal, vat, shipping, total);
    }

    public Order placeOrder(User user, Cart cart, ShippingInfo shipping, PaymentMethod method,
                            PaymentService.CardDetails card) throws CheckoutException {
        if (user == null) {
            throw new CheckoutException("Please sign in before checking out.");
        }
        if (cart == null || cart.isEmpty()) {
            throw new CheckoutException("Your cart is empty.");
        }
        if (shipping == null) {
            throw new CheckoutException("Shipping details are missing.");
        }
        Quote quote = quote(cart);
        PaymentService.PaymentResult payment = method == PaymentMethod.CARD
                ? payments.charge(quote.total(), card)
                : payments.chargeOnDelivery(quote.total());
        if (!payment.approved()) {
            throw new CheckoutException(payment.message());
        }

        List<OrderItem> items = new ArrayList<>();
        for (Cart.Line line : cart.getLines()) {
            items.add(new OrderItem(line.getProduct().getId(), line.getProduct().getName(),
                    line.getProduct().getPrice(), line.getQuantity()));
        }
        String status = method == PaymentMethod.CARD ? "PAID" : "TO SHIP (COD)";
        Order order = new Order(orders.nextOrderId(), user.getId(), items,
                quote.subtotal(), quote.vat(), quote.shipping(), quote.total(),
                status, method.label(), payment.reference(), shipping, LocalDateTime.now());
        try {
            orders.save(order);
        } catch (RuntimeException e) {
            throw new CheckoutException("Payment approved but the order could not be saved. " + e.getMessage());
        }
        user.addOrder(order);
        cart.clear();
        return order;
    }
}
