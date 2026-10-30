package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.ProductDao;
import e_commerce_platform.Product;

public class sellProduct extends JFrame implements ActionListener{

    JPanel contentPane;
    JLabel productName, productPrice, productcategory, productQuantity;
    JTextField prdctName, prdctPrice, prdctCategory, prdctQuantity;
    public JComboBox<String> comboBox;
    JButton sellProduct, mngeInventory, backButton;

    public String[] allCat = {
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

    public sellProduct() {

        setTitle("USER SHOP");
        setLayout(null);
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        backButton = new JButton("Back");
        backButton.setBounds(680, 20, 100, 30);
        add(backButton);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 800, 800);
        add(contentPane);

        productName = new JLabel("Product Name: ");
        prdctName = new JTextField();

        productPrice = new JLabel("Product Price: ");
        prdctPrice = new JTextField();

        productQuantity = new JLabel("Product Quantity: ");
        prdctQuantity = new JTextField();

        productcategory = new JLabel("Product Category: ");
        comboBox = new JComboBox<>(allCat);
        comboBox.setBounds(80, 340, 350, 40);
        contentPane.add(comboBox);

        sellProduct = new JButton("Sell Product");

        productName.setBounds(80, 80, 130, 40);
        prdctName.setBounds(220, 80, 350, 40);
        productPrice.setBounds(80, 210, 130, 40);
        prdctPrice.setBounds(220, 210, 350, 40);
        productQuantity.setBounds(80, 275, 130, 40);
        prdctQuantity.setBounds(220, 275, 350, 40);
        productcategory.setBounds(80, 340, 130, 40);
        sellProduct.setBounds(150, 620, 500, 50);

        contentPane.add(productName);
        contentPane.add(productPrice);
        contentPane.add(productcategory);
        contentPane.add(productQuantity);
        contentPane.add(prdctName);
        contentPane.add(prdctPrice);
        contentPane.add(sellProduct);
        contentPane.add(prdctQuantity);

        sellProduct.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){    
    if (e.getSource() == sellProduct) {

    String name = prdctName.getText().trim();
    String priceText = prdctPrice.getText().trim();
    String quantityText = prdctQuantity.getText().trim();
    String category = comboBox.getSelectedItem().toString();

    if (name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()
            || category.equals("All Categories")) {

        JOptionPane.showMessageDialog(this, "Please fill all fields.");
        return;
    }

    try {
        double price = Double.parseDouble(priceText);
        int quantity = Integer.parseInt(quantityText);

        if (price <= 0 || quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Price must be greater than 0 and quantity cannot be negative.");
            return;
        }

        Product product = new Product(0, name, price, category, quantity);

        ProductDao dao = new ProductDao();

        if (dao.addProduct(product)) {
            JOptionPane.showMessageDialog(this, "Product added successfully!");

            prdctName.setText("");
            prdctPrice.setText("");
            prdctQuantity.setText("");
            comboBox.setSelectedIndex(0);

        } else {
            JOptionPane.showMessageDialog(this, "Failed to add product.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid price and quantity.");
    }
}

    }

    public static void main(String[] args) {
        sellProduct frame = new sellProduct();
        frame.setVisible(true);
    }
}
