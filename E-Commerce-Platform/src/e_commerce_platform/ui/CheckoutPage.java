package e_commerce_platform.ui;

import e_commerce_platform.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutPage extends JFrame implements ActionListener {

    private final Cart cart;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JComboBox<String> paymentBox;
    private JTextArea orderArea;

    private JButton backButton;
    private JButton placeOrderButton;

    public CheckoutPage(Cart cart) {
        this.cart = cart;

        setTitle("E-Commerce Platform - Checkout");
        setSize(800, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(30, 15, 300, 40);
        add(titleLabel);

        JLabel customerLabel = new JLabel("Customer Information");
        customerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        customerLabel.setBounds(30, 65, 250, 30);
        add(customerLabel);

        addLabel("Full Name:", 30, 110);
        nameField = addTextField(150, 110);

        addLabel("Delivery Address:", 30, 155);
        addressField = addTextField(150, 155);

        addLabel("Phone Number:", 30, 200);
        phoneField = addTextField(150, 200);

        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(30, 245, 120, 30);
        add(paymentLabel);

        paymentBox = new JComboBox<>(new String[]{
            "Cash on Delivery",
            "Online Payment"
        });
        paymentBox.setBounds(150, 245, 250, 30);
        add(paymentBox);

        JLabel orderLabel = new JLabel("Order Summary");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        orderLabel.setBounds(430, 65, 250, 30);
        add(orderLabel);

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        orderArea.setText(buildOrderSummary());

        JScrollPane scrollPane = new JScrollPane(orderArea);
        scrollPane.setBounds(430, 105, 330, 300);
        add(scrollPane);

        JLabel totalLabel = new JLabel(
            String.format("Total: PHP %.2f", cart.getTotalPrice())
        );
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setBounds(430, 420, 330, 35);
        add(totalLabel);

        backButton = new JButton("Back to Cart");
        backButton.setBounds(30, 530, 150, 40);
        add(backButton);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(580, 530, 180, 40);
        add(placeOrderButton);

        backButton.addActionListener(this);
        placeOrderButton.addActionListener(this);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 30);
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 250, 30);
        add(field);
        return field;
    }

    private String buildOrderSummary() {
        StringBuilder summary = new StringBuilder();

        if (cart == null || cart.isEmpty()) {
            return "Your cart is empty.\nPlease add products first.";
        }

        for (Product product : cart.getCartProducts()) {
            summary.append(product.getName())
                   .append("\n  PHP ")
                   .append(String.format("%.2f", product.getPrice()))
                   .append("\n\n");
        }

        return summary.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
        }

        if (e.getSource() == placeOrderButton) {
            if (cart == null || cart.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty. Please add products first."
                );
                return;
            }

            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please complete all customer information."
                );
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                this,
                "Customer: " + name
                    + "\nAddress: " + address
                    + "\nPayment: " + paymentBox.getSelectedItem()
                    + "\nTotal: PHP "
                    + String.format("%.2f", cart.getTotalPrice())
                    + "\n\nConfirm your order?",
                "Confirm Order",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                    this,
                    "Checkout confirmed!\n"
                    + "Your order is ready for backend integration."
                );

                // SQL/order saving will be integrated later.
                cart.clearCart();
                dispose();
            }
        }
    }
}
