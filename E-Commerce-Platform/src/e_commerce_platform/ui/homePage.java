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
        
        cartButton.addActionListener(e -> {
    ShoppingCartPage cart = new ShoppingCartPage();
    cart.setVisible(true);
});
        
        // Category Panel 
        
        categoryPanel = new JPanel();
        categoryPanel.setLayout(null);
        categoryPanel.setBounds(0, 195, 1000, 55);
        add(categoryPanel);

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(30, 10, 90, 35);
        categoryPanel.add(homeButton);

        JButton shopButton = new JButton("Shop");
        shopButton.setBounds(130, 10, 90, 35);
        categoryPanel.add(shopButton);

        JLabel categoryLabel = new JLabel("Categories:");
        categoryLabel.setBounds(250, 10, 90, 35);
        categoryPanel.add(categoryLabel);

        JButton electronicsButton = new JButton("Electronics");
        electronicsButton.setBounds(340, 10, 110, 35);
        categoryPanel.add(electronicsButton);

        JButton kitchenButton = new JButton("Kitchen and Living");
        kitchenButton.setBounds(455, 10, 150, 35);
        categoryPanel.add(kitchenButton);

        JButton fashionButton = new JButton("Fashion and Apparel");
        fashionButton.setBounds(610, 10, 150, 35);
        categoryPanel.add(fashionButton);

        JButton applianceButton = new JButton("Home Appliances");
        applianceButton.setBounds(765, 10, 130, 35);
        categoryPanel.add(applianceButton);
        
        // Product Panel
        
        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 4, 15, 15));

        // Mga Sample Product 
        
        JPanel product1 = createProduct("Power Bank", "₱500");
        JPanel product2 = createProduct("Smart Watch", "₱1,999");
        JPanel product3 = createProduct("Laptop", "₱39,999");
        JPanel product4 = createProduct("Headphones", "₱999");
        JPanel product5 = createProduct("Iphone 14 Pro Max", "₱30,999");
        JPanel product6 = createProduct("Charger", "₱1,499");
        JPanel product7 = createProduct("Air Fryer", "₱2,799");
        JPanel product8 = createProduct("Keyboard", "₱1,499");

        productPanel.add(product1);
        productPanel.add(product2);
        productPanel.add(product3);
        productPanel.add(product4);
        productPanel.add(product5);
        productPanel.add(product6);
        productPanel.add(product7);
        productPanel.add(product8);
        
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setBounds(25, 255, 950, 345);
        add(scrollPane);
    }

    private JPanel createProduct(String name, String price) {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(200, 220));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel productName = new JLabel(name);
        productName.setBounds(15, 25, 170, 30);
        productName.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(productName);

        JLabel productPrice = new JLabel(price);
        productPrice.setBounds(15, 65, 170, 30);
        productPrice.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(productPrice);

        JButton orderButton = new JButton("Order");
        orderButton.setBounds(15, 120, 80, 35);
        panel.add(orderButton);

        JButton addCartButton = new JButton("Add to Cart");
        addCartButton.setBounds(100, 120, 90, 35);
        panel.add(addCartButton);

        return panel;
    }
    
            }
