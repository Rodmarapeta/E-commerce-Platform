package e_commerce_platform.ui;

import dao.OrderDao;
import e_commerce_platform.Cart;
import e_commerce_platform.Product;
import e_commerce_platform.UserSession;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ordercheckout extends JFrame implements ActionListener {

    private JLabel titleLabel, nameLabel, addressLabel, quantityLabel, paymentLabel,orderLabel, totalLabel;
    private JTextField nameField, addressField;
    private JSpinner quantitySpinner;
    private JComboBox<String> paymentCombo;
    private JTextArea orderArea;
    private JButton placeOrderButton;

    private Cart cart;
    private Product singleProduct;
    private OrderDao orderDao;

    public ordercheckout(Product selectedProduct) {
        this.singleProduct = selectedProduct;
        this.cart = new Cart();
        if (selectedProduct != null) {
            this.cart.addProduct(selectedProduct);
        }
        this.orderDao = new OrderDao();
        initComponents();
        refreshOrderSummary();
    }

    public ordercheckout(Cart cart) {
        this.cart = (cart != null) ? cart : new Cart();
        if (!this.cart.isEmpty() && this.cart.getCartProducts().size() == 1) {
            this.singleProduct = this.cart.getCartProducts().get(0);
        }
        this.orderDao = new OrderDao();
        initComponents();
        refreshOrderSummary();
    }

    private void initComponents() {
        setTitle("Order Checkout");
        setSize(600, 610);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        titleLabel = new JLabel("Order Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(30, 20, 300, 40);
        add(titleLabel);

        nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(30, 80, 150, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 80, 350, 30);
        add(nameField);

        addressLabel = new JLabel("Delivery Address:");
        addressLabel.setBounds(30, 125, 150, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(180, 125, 350, 30);
        add(addressField);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(30, 170, 150, 30);
        add(quantityLabel);

        SpinnerNumberModel qtyModel = new SpinnerNumberModel(1, 1, 100, 1);
        quantitySpinner = new JSpinner(qtyModel);
        quantitySpinner.setBounds(180, 170, 100, 30);
        quantitySpinner.addChangeListener(e -> refreshOrderSummary());
        add(quantitySpinner);

        paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(30, 215, 150, 30);
        add(paymentLabel);

        String[] paymentMethods = {
            "Cash on Delivery",
            "GCash / E-Wallet",
            "Bank Transfer",
            "Credit / Debit Card"
        };
        paymentCombo = new JComboBox<>(paymentMethods);
        paymentCombo.setBounds(180, 215, 220, 30);
        add(paymentCombo);

        orderLabel = new JLabel("Order Summary:");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        orderLabel.setBounds(30, 260, 200, 30);
        add(orderLabel);

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setFont(new Font("Arial", Font.PLAIN, 14));
        orderArea.setBounds(30, 295, 500, 110);
        add(orderArea);

        totalLabel = new JLabel("Total: PHP 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBounds(30, 420, 350, 30);
        add(totalLabel);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(350, 480, 180, 40);
        placeOrderButton.addActionListener(this);
        add(placeOrderButton);
    }

    private int getSelectedQuantity() {
        return (Integer) quantitySpinner.getValue();
    }

    private double getComputedTotal() {
        int qty = getSelectedQuantity();
        if (singleProduct != null) {
            return singleProduct.getPrice() * qty;
        }
        return cart.getTotalPrice() * qty;
    }

    private void refreshOrderSummary() {
        orderArea.setText("");
        int qty = getSelectedQuantity();

        if (singleProduct != null) {
            double lineTotal = singleProduct.getPrice() * qty;
            orderArea.append(
                singleProduct.getName()
                + " | Unit: PHP " + String.format("%.2f", singleProduct.getPrice())
                + " | Qty: " + qty
                + " | Subtotal: PHP " + String.format("%.2f", lineTotal) + "\n"
            );
        } else {
            for (Product product : cart.getCartProducts()) {
                double lineTotal = product.getPrice() * qty;
                orderArea.append(
                    product.getName()
                    + " | Unit: PHP " + String.format("%.2f", product.getPrice())
                    + " | Qty: " + qty
                    + " | Subtotal: PHP " + String.format("%.2f", lineTotal) + "\n"
                );
            }
        }

        totalLabel.setText("Total: PHP " + String.format("%.2f", getComputedTotal()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == placeOrderButton) {
            String customerName = nameField.getText().trim();
            String deliveryAddress = addressField.getText().trim();
            String paymentMethod = (String) paymentCombo.getSelectedItem();
            int qty = getSelectedQuantity();
            double total = getComputedTotal();

            if (customerName.isEmpty() || deliveryAddress.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name and delivery address.");
            } else if (cart.isEmpty() && singleProduct == null) {
                JOptionPane.showMessageDialog(this, "No product selected. Please choose a product first.");
            } else if (UserSession.getUserId() <= 0) {
                JOptionPane.showMessageDialog(this, "Please login first.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Confirm order for " + qty + " item(s)\n"
                    + "Payment Method: " + paymentMethod + "\n"
                    + "Deliver To: " + deliveryAddress + "\n"
                    + "Total: PHP " + String.format("%.2f", total) + "?",
                    "Confirm Order",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean saved = orderDao.saveOrder(
                        UserSession.getUserId(),
                        customerName,
                        deliveryAddress,
                        "Qty: " + qty,
                        paymentMethod,
                        cart
                    );

                    if (saved) {
                        JOptionPane.showMessageDialog(
                            this,
                            "Order placed successfully!\n"
                            + "Customer: " + customerName
                            + "\nDelivery Address: " + deliveryAddress
                            + "\nQuantity: " + qty
                            + "\nPayment Method: " + paymentMethod
                            + "\nTotal: PHP " + String.format("%.2f", total)
                        );

                        cart.clearCart();

                        homePage home = new homePage();
                        home.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to save your order. Please try again.");
                    }
                }
            }
        }
    }
}