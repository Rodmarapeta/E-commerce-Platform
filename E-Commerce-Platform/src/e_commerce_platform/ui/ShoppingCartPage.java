
package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartPage extends JFrame {

    public ShoppingCartPage() {

        setTitle("E-Commerce Platform - Shopping Cart");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Shopping Cart Title
        JLabel titleLabel = new JLabel("Shopping Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(30, 20, 300, 50);
        add(titleLabel);
    }

    public static void main(String[] args) {
        ShoppingCartPage cart = new ShoppingCartPage();
        cart.setVisible(true);
    }
}
