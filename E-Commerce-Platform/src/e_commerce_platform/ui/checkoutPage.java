package e_commerce_platform.ui;

import e_commerce_platform.nonUIclass.Cart;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checkoutPage extends JFrame implements ActionListener{

    private JLabel lblTitle, lblName, lblAddress, lblPhone, lblPaymentMethod, lblTotal;
    private JTextField txtName, txtAddress, txtPhone;
    private JComboBox<String> paymentMethodBox;
    private JButton btnConfirmCheckout, btnBack;
    private Cart cart;

    public checkoutPage() {

        setTitle("Checkout");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lblTitle = new JLabel("Checkout & Payment");
        lblTitle.setBounds(50, 30, 400, 40);
        add(lblTitle);

        lblName = new JLabel("Full Name:");
        lblName.setBounds(50, 100, 150, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 100, 450, 35);
        add(txtName);

        lblAddress = new JLabel("Shipping Address:");
        lblAddress.setBounds(50, 160, 150, 30);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(200, 160, 450, 35);
        add(txtAddress);

        lblPhone = new JLabel("Phone Number:");
        lblPhone.setBounds(50, 220, 150, 30);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(200, 220, 450, 35);
        add(txtPhone);

        lblPaymentMethod = new JLabel("Payment Method:");
        lblPaymentMethod.setBounds(50, 280, 150, 30);
        add(lblPaymentMethod);

        String[] paymentOptions = {"Cash on Delivery (COD)", "Credit/Debit Card", "GCash", "PayPal"};
        paymentMethodBox = new JComboBox<>(paymentOptions);
        add(paymentMethodBox);

        double totalAmount = cart != null ? cart.getTotalPrice() : 0.0;
        lblTotal = new JLabel(String.format("Total Amount to Pay: ₱%,.2f", totalAmount));
        lblTotal.setBounds(50, 360, 400, 40);
        add(lblTotal);

        btnConfirmCheckout = new JButton("Confirm Order");
        btnConfirmCheckout.setBounds(200, 450, 200, 45);
        add(btnConfirmCheckout);

        btnBack = new JButton("Back to Cart");
        btnBack.setBounds(600, 20, 150, 30);
        add(btnBack);

        btnConfirmCheckout.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnBack) {
            ShoppingCartPage cartPage = new ShoppingCartPage();
            cartPage.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args){
        checkoutPage lol = new checkoutPage();
        lol.setVisible(true);
    }

}