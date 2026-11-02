package e_commerce_platform.ui;

import e_commerce_platform.Order;
import e_commerce_platform.UserSession;
import e_commerce_platform.ui.loginPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class userProfilePage extends JFrame implements ActionListener {

    private JLabel nameLabel, locationLabel, sidebarTitle;
    private JButton backButton, cartButton, orderButton, reviewsButton, shopButton, 
            addressButton, accountButton, paymentButton, logoutButton;
    private JPanel sidebarPanel, contentPanel;


    public userProfilePage() {
        setTitle("User Profile");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(null);
        sidebarPanel.setBounds(25, 25, 230, 715);
        add(sidebarPanel);

        sidebarTitle = new JLabel("USER PROFILE: " + getName());
        sidebarTitle.setBounds(20, 25, 190, 20);
        sidebarPanel.add(sidebarTitle);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 55, 190, 25);
        sidebarPanel.add(nameLabel);

        locationLabel = new JLabel("Location of user");
        locationLabel.setBounds(20, 80, 190, 20);
        locationLabel.setForeground(new Color(100, 110, 120));
        sidebarPanel.add(locationLabel);

        accountButton = new JButton("My Account");
        accountButton.setBounds(20, 140, 190, 45);
        sidebarPanel.add(accountButton);

        paymentButton = new JButton("Payment Method");
        paymentButton.setBounds(20, 200, 190, 45);
        sidebarPanel.add(paymentButton);

        backButton = new JButton("Back to Home");
        backButton.setBounds(580, 25, 190, 30);
        add(backButton);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(275, 25, 495, 715);
        add(contentPanel);

        shopButton = new JButton("Manage MY Shop");
        shopButton.setBounds(0, 55, 235, 190);
        contentPanel.add(shopButton);

        orderButton = new JButton("My Orders");
        orderButton.setBounds(260, 55, 235, 190);
        contentPanel.add(orderButton);

        cartButton = new JButton("My Cart");
        cartButton.setBounds(0, 260, 235, 190);
        contentPanel.add(cartButton);

        addressButton = new JButton("Delivery Address");
        addressButton.setBounds(260, 260, 235, 190);
        contentPanel.add(addressButton);

        reviewsButton = new JButton("My Reviews & Ratings");
        reviewsButton.setBounds(0, 465, 495, 95);
        contentPanel.add(reviewsButton);
        
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(460, 70, 300,30);
        add(logoutButton);

        //Action Listeners
        backButton.addActionListener(this);
        cartButton.addActionListener(this);
        orderButton.addActionListener(this);
        reviewsButton.addActionListener(this);
        shopButton.addActionListener(this);
        addressButton.addActionListener(this);
        accountButton.addActionListener(this);
        paymentButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == backButton)
        {
            homePage home = new homePage();
            home.setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == cartButton)
        {

        }
        else if (e.getSource() == orderButton)
        {
            java.util.List<e_commerce_platform.Order> orders = e_commerce_platform.Order.getPlacedOrders();

            if (orders.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You have no placed orders yet.", "My Orders", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                for (e_commerce_platform.Order ord : orders) {
                    JPanel orderBox = new JPanel(null);
                    orderBox.setPreferredSize(new Dimension(420, 120));
                    orderBox.setBorder(BorderFactory.createTitledBorder("Order #" + ord.getOrderId()));

                    JLabel lblCustomer = new JLabel("Customer: " + ord.getCustomerName());
                    lblCustomer.setBounds(15, 20, 380, 20);
                    orderBox.add(lblCustomer);

                    JLabel lblAddress = new JLabel("Address: " + ord.getDeliveryAddress());
                    lblAddress.setBounds(15, 45, 380, 20);
                    orderBox.add(lblAddress);

                    JLabel lblTotal = new JLabel(String.format("Total: PHP %.2f", ord.getTotalPrice()));
                    lblTotal.setBounds(15, 70, 380, 20);
                    orderBox.add(lblTotal);

                    panel.add(orderBox);
                }

                JScrollPane scrollPane = new JScrollPane(panel);
                scrollPane.setPreferredSize(new Dimension(450, 400));

                JOptionPane.showMessageDialog(this, scrollPane, "My Orders", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource() == shopButton)
        {
            userShop Mobailegends = new userShop();
            Mobailegends.setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == logoutButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                UserSession.clearSession();
                
                loginPage login =  new loginPage();
                login.setVisible(true);
                this.dispose();
            }
        else if (e.getSource() == addressButton)
        {

        }
        else if (e.getSource() == paymentButton)
        {

        }
        else if (e.getSource() == accountButton)
        {

        }
    }

    }

    public static void main(String[] args) {
        userProfilePage lol = new userProfilePage();
        lol.setVisible(true);
    }
}
