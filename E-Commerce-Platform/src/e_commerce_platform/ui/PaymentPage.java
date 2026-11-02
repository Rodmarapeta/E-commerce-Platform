package e_commerce_platform.ui;

import e_commerce_platform.Cart;
import e_commerce_platform.CheckoutLauncher;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Opens the full checkout flow and clears the cart
 * after an order is successfully placed.
 */
public class PaymentPage extends JFrame {

    private final Cart cart;

    public PaymentPage(Cart cart) {
        this.cart = cart;

        setTitle("E-Commerce Platform - Checkout");
        setUndecorated(true);
        setSize(1, 1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {

                ecommerce.model.Order order =
                    CheckoutLauncher.openCheckout(
                        PaymentPage.this,
                        PaymentPage.this.cart
                    );

                if (order != null) {
                    PaymentPage.this.cart.clearCart();
                }

                dispose();
            }
        });
    }
}
