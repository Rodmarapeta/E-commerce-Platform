package e_commerce_platform.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class adminPage extends JFrame implements ActionListener {

    private JLabel titleLabel, welcomeLabel;
    private JButton productsButton, inventoryButton, salesButton, ordersButton, logoutButton,
            homeButton;

    public adminPage() {

        setTitle("E-Commerce Platform - Admin Dashboard");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(40, 30, 400, 50);
        add(titleLabel);

        welcomeLabel = new JLabel("Welcome, Administrator!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setBounds(40, 90, 350, 30);
        add(welcomeLabel);

        productsButton = new JButton("Manage Products");
        productsButton.setBounds(50, 170, 300, 100);
        add(productsButton);

        inventoryButton = new JButton("Inventory Report");
        inventoryButton.setBounds(420, 170, 300, 100);
        add(inventoryButton);

        salesButton = new JButton("Sales Report");
        salesButton.setBounds(50, 310, 300, 100);
        add(salesButton);

        ordersButton = new JButton("Manage Orders");
        ordersButton.setBounds(420, 310, 300, 100);
        add(ordersButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(580, 480, 140, 35);
        add(logoutButton);
        
        homeButton = new JButton("Home Page");
        homeButton.setBounds(580, 30, 100, 30);
        add(homeButton);

        productsButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        salesButton.addActionListener(this);
        ordersButton.addActionListener(this);
        logoutButton.addActionListener(this);
        homeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == productsButton) {
            sellProduct product = new sellProduct();
            product.setVisible(true);

        } else if (e.getSource() == inventoryButton) {
            inventoryReport inventory = new inventoryReport();
            inventory.setVisible(true);

        } else if (e.getSource() == salesButton) {
            salesReport sales = new salesReport();
            sales.setVisible(true);

        } else if (e.getSource() == ordersButton) {
            ManageOrdersPage orders = new ManageOrdersPage();
            orders.setVisible(true);
            dispose();

        } else if (e.getSource() == logoutButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                loginPage login = new loginPage();
                login.setVisible(true);
                dispose();
            }
        }
        else if (e.getSource() == homeButton){
            homePage b = new homePage();
            b.setVisible(true);
            this.dispose();
        } 
    }
    public static void main(String[] args) {
        adminPage a = new adminPage();
        a.setVisible(true);
    }
}