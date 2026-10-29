package e_commerce_platform.ui;

import e_commerce_platform.Cart;
import e_commerce_platform.Product;
import javax.swing.*;
import java.awt.*;

public class CheckOutPage extends JFrame {

    private Cart cart;
    private JTextField nameField;
    private JTextField addressField;
    private JLabel totalLabel;

    public CheckOutPage(Cart cart) {
        this.cart = cart;

        setTitle("E-Commerce Platform - Checkout");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(30, 20, 300, 40);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(30, 90, 150, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 90, 350, 30);
        add(nameField);

        JLabel addressLabel = new JLabel("Delivery Address:");
        addressLabel.setBounds(30, 150, 150, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(180, 150, 350, 30);
        add(addressField);

        JLabel orderLabel = new JLabel("Order Summary:");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        orderLabel.setBounds(30, 210, 200, 30);
        add(orderLabel);

        JTextArea orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setFont(new Font("Arial", Font.PLAIN, 14));

        for (Product product : cart.getCartProducts()) {
            orderArea.append(
                product.getName() + " - PHP "
                + String.format("%.2f", product.getPrice()) + "\n"
            );
        }

        JScrollPane scrollPane = new JScrollPane(orderArea);
        scrollPane.setBounds(30, 250, 500, 90);
        add(scrollPane);

        totalLabel = new JLabel(
            String.format("Total: PHP %.2f", cart.getTotalPrice())
        );
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBounds(30, 350, 250, 30);
        add(totalLabel);

        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(350, 400, 180, 40);
        add(placeOrderButton);

        placeOrderButton.addActionListener(e -> {
            if (nameField.getText().trim().isEmpty()
                    || addressField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter your name and delivery address."
                );

            } else if (cart.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty. Please add a product first."
                );

            } else {

                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Confirm your order for PHP "
                    + String.format("%.2f", cart.getTotalPrice()) + "?",
                    "Confirm Order",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(
                        this,
                        "WOW! Order placed successfully!\n"
                        + "Customer: " + nameField.getText().trim()
                        + "\nDelivery Address: "
                        + addressField.getText().trim()
                    );

                    cart.clearCart();
                    dispose();
                }
            }
        });
    }
}
