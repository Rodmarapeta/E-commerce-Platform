package e_commerce_platform.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import e_commerce_platform.Cart;
import e_commerce_platform.Product;

public class ShoppingCartPage extends JFrame implements ActionListener {

    private Cart cart;
    private JPanel cartPanel;
    private JPanel itemsPanel;

    private JButton backbtn, continueButton, checkoutButton;

    private JLabel productLabel, priceLabel, quantityLabel;
    private JLabel subtotalLabel, emptyCartLabel, totalLabel;

    public ShoppingCartPage(Cart cart) {
        this.cart = cart;

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
        productLabel.setBounds(30, 30, 250, 30);
        cartPanel.add(productLabel);

        priceLabel = new JLabel("Price");
        priceLabel.setBounds(350, 30, 100, 30);
        cartPanel.add(priceLabel);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(500, 30, 100, 30);
        cartPanel.add(quantityLabel);

        subtotalLabel = new JLabel("Subtotal");
        subtotalLabel.setBounds(650, 30, 120, 30);
        cartPanel.add(subtotalLabel);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(
                new BoxLayout(itemsPanel, BoxLayout.Y_AXIS)
        );

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBounds(15, 65, 885, 260);
        cartPanel.add(scrollPane);

        emptyCartLabel = new JLabel("Your shopping cart is empty.");
        emptyCartLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emptyCartLabel.setBounds(300, 170, 350, 30);
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

        backbtn.addActionListener(this);
        continueButton.addActionListener(this);
        checkoutButton.addActionListener(this);

        displayCartItems();
    }

    private void displayCartItems() {
        itemsPanel.removeAll();

        if (cart.isEmpty()) {
            emptyCartLabel.setVisible(true);
        } else {
            emptyCartLabel.setVisible(false);

            for (Product product : cart.getCartProducts()) {

                JPanel row = new JPanel(null);
                row.setPreferredSize(new Dimension(860, 40));
                row.setMinimumSize(new Dimension(860, 40));
                row.setMaximumSize(new Dimension(860, 40));

                JLabel name = new JLabel(product.getName());
                name.setBounds(15, 5, 300, 30);
                row.add(name);

                JLabel price = new JLabel(
                        String.format("PHP %.2f", product.getPrice())
                );
                price.setBounds(335, 5, 130, 30);
                row.add(price);

                JLabel quantity = new JLabel("1");
                quantity.setBounds(485, 5, 100, 30);
                row.add(quantity);

                JLabel subtotal = new JLabel(
                        String.format("PHP %.2f", product.getPrice())
                );
                subtotal.setBounds(635, 5, 130, 30);
                row.add(subtotal);

                JButton removeButton = new JButton("Remove");
                removeButton.setBounds(780, 5, 75, 30);
                row.add(removeButton);

                removeButton.addActionListener(e -> {
                    cart.removeProduct(product);
                    displayCartItems();
                });

                itemsPanel.add(row);
            }
        }

        totalLabel.setText(
                String.format("Total: PHP %.2f", cart.getTotalPrice())
        );

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backbtn
                || e.getSource() == continueButton) {

            homePage home = new homePage();
            home.setVisible(true);
            this.dispose();

        } else if (e.getSource() == checkoutButton) {

            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Your cart is empty. Please add products first."
                );
            } else {
                CheckoutPage checkout = new CheckoutPage(cart);
                checkout.setVisible(true);
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        Cart cart = new Cart();

        // Sample product for testing
        cart.addProduct(
                new Product(1, "Power Bank", 500.00, "Electronics", 1)
        );

        ShoppingCartPage page = new ShoppingCartPage(cart);
        page.setVisible(true);
    }
}
