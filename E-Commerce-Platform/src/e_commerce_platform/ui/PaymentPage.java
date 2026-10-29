
package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;

public class PaymentPage extends JFrame {

    private JTextField nameField;
    private JTextField addressField;
    private JTextField contactField;

    private JRadioButton codButton;
    private JRadioButton gcashButton;
    private JRadioButton cardButton;

    public PaymentPage() {

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

    }
}