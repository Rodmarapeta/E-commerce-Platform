package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCartPage extends JFrame implements ActionListener {

    private JPanel cartPanel;
    private JButton backbtn, continueButton, checkoutButton;
    private JLabel productLabel, priceLabel, quantityLabel, subtotalLabel, emptyCartLabel, totalLabel;

    public ShoppingCartPage() {

        setTitle("E-Commerce Platform - Shopping Cart");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Shopping Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(30, 20, 300, 50);
        add(titleLabel);

        backbtn = new JButton("Back to Home Page");
        backbtn.setBounds(800, 35, 150, 30);
        add(backbtn);

        cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBounds(30, 100, 920, 350);
        cartPanel.setBorder(
                BorderFactory.createTitledBorder("Your Cart Items")
        );
        add(cartPanel);

        productLabel = new JLabel("Product");
        productLabel.setBounds(30, 30, 150, 30);
        cartPanel.add(productLabel);

        priceLabel = new JLabel("Price");
        priceLabel.setBounds(350, 30, 100, 30);
        cartPanel.add(priceLabel);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(500, 30, 100, 30);
        cartPanel.add(quantityLabel);

        subtotalLabel = new JLabel("Subtotal");
        subtotalLabel.setBounds(650, 30, 100, 30);
        cartPanel.add(subtotalLabel);

        emptyCartLabel = new JLabel("Your shopping cart is empty.");
        emptyCartLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emptyCartLabel.setBounds(300, 150, 350, 30);
        cartPanel.add(emptyCartLabel);

        totalLabel = new JLabel("Total: PHP 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setBounds(690, 480, 250, 40);
        add(totalLabel);

        continueButton = new JButton("Continue Shopping");
        continueButton.setBounds(30, 540, 180, 40);
        add(continueButton);

        checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.setBounds(740, 540, 200, 40);
        add(checkoutButton);
        
        checkoutButton.addActionListener(e -> {
            PaymentPage payment = new PaymentPage();
            payment.setVisible(true);
            dispose();
        });


        //addtoactionlistener
        backbtn.addActionListener(this);
        continueButton.addActionListener(this);
        checkoutButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backbtn ){
            homePage japanese  = new homePage();
            japanese.setVisible(true);
            this.dispose();
        }
        else if (e.getSource()== continueButton) {
            homePage baddies  = new homePage();
            baddies.setVisible(true);
            this.dispose();
        }
    }


    public static void main(String[] args) {
        ShoppingCartPage cart = new ShoppingCartPage();
        cart.setVisible(true);
    }
}