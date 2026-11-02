
package e_commerce_platform.ui;

import e_commerce_platform.Cart;
import e_commerce_platform.Product;
import e_commerce_platform.UserSession;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import dao.OrderDao;

public class CheckoutPage extends JFrame implements ActionListener {

    private JLabel titleLabel, nameLabel, addressLabel;
    private JLabel orderLabel, totalLabel;

    private JTextField nameField, addressField;
    private JTextArea orderArea;
    private JButton placeOrderButton;

    private Cart cart;
    private OrderDao orderDao;

    public CheckoutPage(Cart cart) {
        this.cart = cart;
        this.orderDao = new OrderDao();

        setTitle("E-Commerce Platform - Checkout");
        setSize(600, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        titleLabel = new JLabel("Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(30, 20, 300, 40);
        add(titleLabel);

        nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(30, 90, 150, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 90, 350, 30);
        add(nameField);

        addressLabel = new JLabel("Delivery Address:");
        addressLabel.setBounds(30, 150, 150, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(180, 150, 350, 30);
        add(addressField);

        orderLabel = new JLabel("Order Summary:");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        orderLabel.setBounds(30, 210, 200, 30);
        add(orderLabel);

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setFont(new Font("Arial", Font.PLAIN, 14));
        orderArea.setBounds(30, 250, 500, 90);
        add(orderArea);

        for (Product product : cart.getCartProducts()) {
            orderArea.append(
                product.getName() + " - PHP "
                + String.format("%.2f", product.getPrice()) + "\n"
            );
        }

        totalLabel = new JLabel(
            "Total: PHP " + String.format("%.2f", cart.getTotalPrice())
        );
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBounds(30, 350, 250, 30);
        add(totalLabel);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(350, 400, 180, 40);
        add(placeOrderButton);

        placeOrderButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == placeOrderButton) {

            if (nameField.getText().trim().isEmpty()
                    || addressField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter your name and delivery address."
                );

            } else if (cart.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty. Please add a product first."
                );

            } else if (UserSession.getUserId() <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please login first."
                );

            } else {

                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Confirm your order for PHP "
                    + String.format("%.2f", cart.getTotalPrice()) + "?",
                    "Confirm Order",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    boolean saved = orderDao.saveOrder(
                        UserSession.getUserId(),
                        nameField.getText().trim(),
                        addressField.getText().trim(),
                        "Not Provided",
                        "Cash on Delivery",
                        cart
                    );

                    if (saved) {

                        JOptionPane.showMessageDialog(
                            this,
                            "Order placed successfully!\n"
                            + "Customer: " + nameField.getText().trim()
                            + "\nDelivery Address: "
                            + addressField.getText().trim()
                        );

                        cart.clearCart();

                        homePage home = new homePage();
                        home.setVisible(true);

                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                            this,
                            "Failed to save your order. Please try again."
                        );
                    }
                }
            }
        }
    }
}
