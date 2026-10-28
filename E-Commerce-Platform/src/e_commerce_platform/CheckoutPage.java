package e_commerce_platform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutPage extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JButton backButton;
    private JButton placeOrderButton;

    public CheckoutPage() {
        setTitle("E-Commerce Platform - Checkout");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(30, 20, 300, 50);
        add(titleLabel);

        backButton = new JButton("Back");
        backButton.setBounds(30, 500, 100, 35);
        backButton.addActionListener(this);
        add(backButton);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(620, 500, 140, 35);
        placeOrderButton.addActionListener(this);
        add(placeOrderButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
        } else if (e.getSource() == placeOrderButton) {
            JOptionPane.showMessageDialog(
                this,
                "Checkout GUI is ready for the next step!"
            );
        }
    }

    public static void main(String[] args) {
        CheckoutPage checkout = new CheckoutPage();
        checkout.setVisible(true);
    }
}
