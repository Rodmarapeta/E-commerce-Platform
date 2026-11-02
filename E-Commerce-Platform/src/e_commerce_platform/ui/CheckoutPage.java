package e_commerce_platform.ui;

import e_commerce_platform.Cart;
import e_commerce_platform.CheckoutLauncher;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** Opened by "Proceed to Checkout" in the shopping cart. */
public class CheckoutPage extends JFrame {

    private final Cart cart;

    public CheckoutPage(Cart cart) {
        this.cart = cart;

        setTitle("E-Commerce Platform - Checkout");
        setUndecorated(true);
        setSize(1, 1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                ecommerce.model.Order order = CheckoutLauncher.openCheckout(CheckoutPage.this, CheckoutPage.this.cart);
                if (order != null) {
                    CheckoutPage.this.cart.clearCart();
                }
                dispose();
            }
        });
    }
}
