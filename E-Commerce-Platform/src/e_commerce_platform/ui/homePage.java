package e_commerce_platform.ui;

import e_commerce_platform.Cart;
import e_commerce_platform.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class homePage extends JFrame implements ActionListener {

    private JLabel shippingLabel, returnLabel, warrantyLabel, storeLabel, categoryLabel;
    private JPanel upperPanel, mainPanel, categoryPanel, productPanel;
    private JButton myAccountButton, cartButton, supportButton, trackOrderButton,
                    searchButton, backButton, homeButton, shopButton, electronicsButton, kitchenButton,
                    fashionButton, applianceButton;
    private JComboBox<String> comboBox;
    private JTextField searchProduct;
    private List<Product> allProducts = new ArrayList<>();
    Cart cart= new Cart();

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

        shippingLabel = new JLabel("Free Shipping on Orders over 500");
        shippingLabel.setBounds(25, 15, 220, 30);
        upperPanel.add(shippingLabel);

        returnLabel = new JLabel("30 days easy returns");
        returnLabel.setBounds(300, 15, 180, 30);
        upperPanel.add(returnLabel);

        warrantyLabel = new JLabel("1 Year Warranty");
        warrantyLabel.setBounds(520, 15, 150, 30);
        upperPanel.add(warrantyLabel);

        supportButton = new JButton("Support");
        supportButton.setBounds(730, 15, 100, 30);
        upperPanel.add(supportButton);

        trackOrderButton = new JButton("Track Order");
        trackOrderButton.setBounds(835, 15, 120, 30);
        upperPanel.add(trackOrderButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 70, 1000, 125);
        add(mainPanel);

        // Store name
        storeLabel = new JLabel("Store");
        storeLabel.setBounds(25, 20, 180, 60);
        storeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainPanel.add(storeLabel);

        // All Categories Nung Product
        comboBox = new JComboBox<>(allCat);
        comboBox.setBounds(210, 25, 170, 40);
        mainPanel.add(comboBox);

        comboBox.addActionListener(e -> {
            searchProducts();
        });

        // Search bar ng mga product
        searchProduct = new JTextField();
        searchProduct.setBounds(380, 25, 260, 40);
        mainPanel.add(searchProduct);

        searchButton = new JButton("Search");
        searchButton.setBounds(640, 25, 90, 40);
        mainPanel.add(searchButton);
        searchButton.addActionListener(e -> {
            searchProducts();
        });

        // My Account button
        myAccountButton = new JButton("My Account");
        myAccountButton.setBounds(740, 25, 110, 40);
        mainPanel.add(myAccountButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(855, 25, 80, 40);
        mainPanel.add(backButton);

        // Cart button
        cartButton = new JButton("Cart");
        cartButton.setBounds(935, 25, 60, 40);
        mainPanel.add(cartButton);

        // Category Panel

        categoryPanel = new JPanel();
        categoryPanel.setLayout(null);
        categoryPanel.setBounds(0, 195, 1000, 55);
        add(categoryPanel);

        homeButton = new JButton("Home");
        homeButton.setBounds(30, 10, 90, 35);
        categoryPanel.add(homeButton);

        shopButton = new JButton("Shop");
        shopButton.setBounds(130, 10, 90, 35);
        categoryPanel.add(shopButton);

        categoryLabel = new JLabel("Categories:");
        categoryLabel.setBounds(250, 10, 90, 35);
        categoryPanel.add(categoryLabel);

        electronicsButton = new JButton("Electronics");
        electronicsButton.setBounds(340, 10, 110, 35);
        categoryPanel.add(electronicsButton);

        kitchenButton = new JButton("Kitchen and Living");
        kitchenButton.setBounds(455, 10, 150, 35);
        categoryPanel.add(kitchenButton);

        fashionButton = new JButton("Fashion and Apparel");
        fashionButton.setBounds(610, 10, 150, 35);
        categoryPanel.add(fashionButton);

        applianceButton = new JButton("Home Appliances");
        applianceButton.setBounds(765, 10, 130, 35);
        categoryPanel.add(applianceButton);

        electronicsButton.addActionListener(e -> {
            comboBox.setSelectedItem("Electronics");
        });

        kitchenButton.addActionListener(e -> {
            comboBox.setSelectedItem("Kitchen and Living");
        });

        fashionButton.addActionListener(e -> {
            comboBox.setSelectedItem("Fashion and Apparel");
        });

        applianceButton.addActionListener(e -> {
            comboBox.setSelectedItem("Home Appliances");
        });

        // Product Panel

        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 4, 15, 15));

        // Mga Sample Product

        allProducts.add(new Product(1, "Power Bank", 500.00, "Electronics"));
        allProducts.add(new Product(2, "Smart Watch", 1999.00, "Electronics"));
        allProducts.add(new Product(3, "Laptop", 39999.00, "Electronics"));
        allProducts.add(new Product(4, "Headphones", 999.00, "Electronics"));
        allProducts.add(new Product(5, "Iphone 14 Pro Max", 30999.00, "Electronics"));
        allProducts.add(new Product(6, "Charger", 1499.00, "Electronics"));
        allProducts.add(new Product(7, "Air Fryer", 2799.00, "Home Appliances"));
        allProducts.add(new Product(8, "Keyboard", 1499.00, "Electronics"));

        // To Display all products initially

        displayProducts("All Categories");

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setBounds(25, 255, 950, 345);
        add(scrollPane);

        //addtoactionlistener
        myAccountButton.addActionListener(this);
        cartButton.addActionListener(this);
        searchButton.addActionListener(this);
    }

    JPanel panel;
    JLabel productName, productPrice, productCategory;
    JButton orderButton,addCartButton;

    private JPanel createProduct(Product product) {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(200, 220));
        panel.setBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        );

        productName = new JLabel(product.getName());
        productName.setBounds(15, 25, 185, 30);
        productName.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(productName);

        productPrice = new JLabel(String.format("₱%,.2f", product.getPrice()));
        productPrice.setBounds(15, 65, 185, 30);
        productPrice.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(productPrice);

        productCategory = new JLabel(product.getCategory());
        productCategory.setBounds(15, 95, 185, 20);
        panel.add(productCategory);

        orderButton = new JButton("Order");
        orderButton.setBounds(15, 145, 80, 35);
        panel.add(orderButton);

        addCartButton = new JButton("Add to Cart");
        addCartButton.setBounds(100, 145, 90, 35);
        panel.add(addCartButton);
        addCartButton.addActionListener( e ->{
            cart.addProduct(product);

            JOptionPane.showMessageDialog(this, product.getName() + " Added to Cart!");
        });

        return panel;
    }

    private void displayProducts(String selectedCategory) {

        productPanel.removeAll();

        for (Product product : allProducts) {

            if (selectedCategory.equals("All Categories")
                    || product.getCategory().equalsIgnoreCase(selectedCategory)){

                productPanel.add(createProduct(product));
            }
        }

        productPanel.revalidate();
        productPanel.repaint();
    }

    private void searchProducts() {

        String keyword = searchProduct.getText()
                .trim()
                .toLowerCase();

        String selectedCategory =
                (String) comboBox.getSelectedItem();

        productPanel.removeAll();

        for (Product product : allProducts) {

            boolean matchesCategory =
                    selectedCategory.equals("All Categories")
                    || product.getCategory()
                           .equalsIgnoreCase(selectedCategory);

            boolean matchesKeyword =
                    product.getName()
                           .toLowerCase()
                           .contains(keyword);

            if (matchesCategory && matchesKeyword) {
                productPanel.add(createProduct(product));
            }
        }

        productPanel.revalidate();
        productPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== myAccountButton) {
            userProfilePage bisaya = new userProfilePage();
            bisaya.setVisible(true);
            this.dispose();
        }
        else if (e.getSource()== cartButton) {
            ShoppingCartPage ilocano = new ShoppingCartPage(cart);
            ilocano.setVisible(true);
            this.dispose();
        }
        else if (e.getSource()== searchButton) {

        }
        else if (e.getSource()== orderButton) {

        }
        else if (e.getSource()== addCartButton) {

        }
        else if (e.getSource()== productPanel) {

        }

    }

    public static void main(String[] args) {
        homePage tagalog = new homePage();
        tagalog.setVisible(true);
    }


}