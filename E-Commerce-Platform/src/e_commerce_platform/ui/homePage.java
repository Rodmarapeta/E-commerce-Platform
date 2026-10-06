package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;

public class homePage extends JFrame {

    private JPanel upperPanel;
    private JPanel mainPanel;
    private JPanel categoryPanel;
    private JPanel navigationPanel;
    private JPanel productPanel;

    private JComboBox<String> comboBox;
    private JTextField searchProduct;

    private String[] allCat = {
        "All Categories",
        "Electronics",
        "Kitchen and Living",
        "Fashion and Apparel",
        "Home Appliances",
        "Personal Care",
        "Groceries and Supermarket",
        "Hardware",
        "Hobbies",
        "Sport and Outdoor"
    };

    homePage() {

        setTitle("E-Commerce Platform - Home");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        upperPanel = new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(0, 0, 1000, 70);
        add(upperPanel);

        JLabel shippingLabel = new JLabel("Free Shipping on Orders over 500");
        shippingLabel.setBounds(25, 15, 220, 30);
        upperPanel.add(shippingLabel);

        JLabel returnLabel = new JLabel("30 days easy returns");
        returnLabel.setBounds(300, 15, 180, 30);
        upperPanel.add(returnLabel);

        JLabel warrantyLabel = new JLabel("1 Year Warranty");
        warrantyLabel.setBounds(520, 15, 150, 30);
        upperPanel.add(warrantyLabel);

        JButton supportButton = new JButton("Support");
        supportButton.setBounds(730, 15, 100, 30);
        upperPanel.add(supportButton);

        JButton trackOrderButton = new JButton("Track Order");
        trackOrderButton.setBounds(835, 15, 120, 30);
        upperPanel.add(trackOrderButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 70, 1000, 125);
        add(mainPanel);

        // Store name 
        JLabel storeLabel = new JLabel("Store");
        storeLabel.setBounds(25, 20, 180, 60);
        storeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainPanel.add(storeLabel);

        // All Categories Nung Product
        comboBox = new JComboBox<>(allCat);
        comboBox.setBounds(210, 25, 170, 40);
        mainPanel.add(comboBox);

        // Search bar ng mga product
        searchProduct = new JTextField();
        searchProduct.setBounds(380, 25, 260, 40);
        mainPanel.add(searchProduct);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(640, 25, 90, 40);
        mainPanel.add(searchButton);

        // My Account button
        JButton myAccountButton = new JButton("My Account");
        myAccountButton.setBounds(740, 25, 110, 40);
        mainPanel.add(myAccountButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(855, 25, 80, 40);
        mainPanel.add(backButton);

        // Cart button
        JButton cartButton = new JButton("Cart");
        cartButton.setBounds(935, 25, 60, 40);
        mainPanel.add(cartButton);
    }

}