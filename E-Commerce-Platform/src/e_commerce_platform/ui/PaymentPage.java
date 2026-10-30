package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;
import e_commerce_platform.Cart;

public class PaymentPage extends JFrame {

    private Cart cart;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField contactField;

    private JRadioButton codButton;
    private JRadioButton gcashButton;
    private JRadioButton cardButton;

    public PaymentPage(Cart cart) {
    this.cart = cart;

        setTitle("E-Commerce Platform - Checkout");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 1000, 75);
        add(headerPanel);

        JLabel titleLabel = new JLabel("CHECKOUT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(30, 15, 500, 45);
        headerPanel.add(titleLabel);

        JLabel shippingTitle = new JLabel("Shipping Information");
        shippingTitle.setFont(new Font("Arial", Font.BOLD, 20));
        shippingTitle.setBounds(40, 100, 350, 30);
        add(shippingTitle);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(40, 150, 130, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 150, 330, 30);
        add(nameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(40, 195, 130, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(170, 195, 330, 30);
        add(addressField);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setBounds(40, 240, 130, 30);
        add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(170, 240, 330, 30);
        add(contactField);

        JLabel paymentTitle = new JLabel("Select Payment Method");
        paymentTitle.setFont(new Font("Arial", Font.BOLD, 20));
        paymentTitle.setBounds(40, 310, 350, 30);
        add(paymentTitle);

        codButton = new JRadioButton("Cash on Delivery");
        codButton.setBounds(40, 355, 200, 30);

        gcashButton = new JRadioButton("GCash");
        gcashButton.setBounds(40, 390, 200, 30);

        cardButton = new JRadioButton("Credit / Debit Card");
        cardButton.setBounds(40, 425, 200, 30);

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(codButton);
        paymentGroup.add(gcashButton);
        paymentGroup.add(cardButton);

        add(codButton);
        add(gcashButton);
        add(cardButton);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(null);
        summaryPanel.setBounds(570, 110, 360, 350);
        add(summaryPanel);

        JLabel summaryTitle = new JLabel("Order Summary");
        summaryTitle.setFont(new Font("Arial", Font.BOLD, 21));
        summaryTitle.setBounds(20, 20, 300, 35);
        summaryPanel.add(summaryTitle);

        JLabel subtotalLabel = new JLabel("Subtotal:");
        subtotalLabel.setBounds(20, 90, 150, 30);
        summaryPanel.add(subtotalLabel);

        JLabel subtotalValue = new JLabel("PHP 0.00");
        subtotalValue.setBounds(230, 90, 120, 30);
        summaryPanel.add(subtotalValue);

        JLabel shippingLabel = new JLabel("Shipping Fee:");
        shippingLabel.setBounds(20, 140, 150, 30);
        summaryPanel.add(shippingLabel);

        JLabel shippingValue = new JLabel("PHP 0.00");
        shippingValue.setBounds(230, 140, 120, 30);
        summaryPanel.add(shippingValue);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 200, 320, 10);
        summaryPanel.add(separator);

        JLabel totalLabel = new JLabel("Total Payment:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 17));
        totalLabel.setBounds(20, 230, 180, 35);
        summaryPanel.add(totalLabel);

        JLabel totalValue = new JLabel("PHP 0.00");
        totalValue.setFont(new Font("Arial", Font.BOLD, 17));
        totalValue.setBounds(220, 230, 130, 35);
        summaryPanel.add(totalValue);

        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.setBounds(570, 490, 360, 45);
        confirmButton.setFocusPainted(false);
        add(confirmButton);

        confirmButton.addActionListener(e -> {

            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String contact = contactField.getText().trim();

            if (name.isEmpty() || address.isEmpty()
                    || contact.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please complete all shipping information.");
                return;
            }
            if (!contact.matches("[0-9]{11}")) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid 11-digit contact number.");
                return;
            }
            if (paymentGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a payment method.");
                return;
            }

            String method = "";

            if (codButton.isSelected()) {
                method = "Cash on Delivery";
            } else if (gcashButton.isSelected()) {
                method = "GCash";
            } else if (cardButton.isSelected()) {
                method = "Credit / Debit Card";
            }
            JOptionPane.showMessageDialog(this,
                    "Order Submitted Successfully!"
                    + "\nCustomer: " + name
                    + "\nPayment Method: " + method);
        });

        JButton backButton = new JButton("Back to Cart");
        backButton.setBounds(570, 545, 360, 35);
        add(backButton);

        backButton.addActionListener(e -> {
        ShoppingCartPage cartPage = new ShoppingCartPage(this.cart);
            cartPage.setVisible(true);
            dispose();
});
        
    }
        public static void main(String[] args) {
        ShoppingCartPage cart = new ShoppingCartPage();
        cart.setVisible(true);
    }
}