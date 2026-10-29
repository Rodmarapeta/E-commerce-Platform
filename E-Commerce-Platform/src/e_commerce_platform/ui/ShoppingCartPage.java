
package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartPage extends JFrame {

    public ShoppingCartPage() {

        setTitle("E-Commerce Platform - Shopping Cart");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Shopping Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(20, 20, 300, 90);
        add(titleLabel);
        
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBounds(30, 100, 920, 350);
        cartPanel.setBorder(
        BorderFactory.createTitledBorder("Your Cart Items")
         );
        add(cartPanel);

        JLabel productLabel = new JLabel("Product");
        productLabel.setBounds(30, 30, 150, 30);
        cartPanel.add(productLabel);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(350, 30, 100, 30);
        cartPanel.add(priceLabel);

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(500, 30, 100, 30);
        cartPanel.add(quantityLabel);

        JLabel subtotalLabel = new JLabel("Subtotal");
        subtotalLabel.setBounds(650, 30, 100, 30);
        cartPanel.add(subtotalLabel);
        
        JLabel emptyCartLabel = new JLabel("Your shopping cart is empty.");
        emptyCartLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emptyCartLabel.setBounds(300, 150, 350, 30);
        cartPanel.add(emptyCartLabel);
        
        JLabel totalLabel = new JLabel("Total: PHP 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setBounds(690, 480, 250, 40);
        add(totalLabel);
        
        JButton backButton = new JButton("Back to Shop");
        backButton.setBounds(780, 50, 150, 40);
        add(backButton);

        JButton continueButton = new JButton("Continue Shopping");
        continueButton.setBounds(30, 540, 180, 40);
        add(continueButton);

        JButton checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.setBounds(740, 540, 200, 40);
        add(checkoutButton);
        
    }
        
    public static void main(String[] args) {
        ShoppingCartPage cart = new ShoppingCartPage();
        cart.setVisible(true);
    }
}
