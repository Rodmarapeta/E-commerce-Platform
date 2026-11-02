
package e_commerce_platform.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManageOrdersPage extends JFrame implements ActionListener {

    private JPanel headerPanel;
    private JLabel titleLabel;
    private JTable ordersTable;
    private JScrollPane scrollPane;

    private JButton backButton;
    private JButton viewOrderButton;
    private JButton updateStatusButton;

    private DefaultTableModel model;

    public ManageOrdersPage() {

        setTitle("E-Commerce Platform - Manage Orders");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 100);
        headerPanel.setLayout(null);
        add(headerPanel);

        titleLabel = new JLabel("Manage Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(30, 25, 400, 50);
        headerPanel.add(titleLabel);
        
        String[] columns = {
            "Order ID",
            "Customer",
            "Product Name",
            "Total Price",
            "Payment Status",
            "Order Status",
            "Tracking Number"
        };

        model = new DefaultTableModel(columns, 0);
        ordersTable = new JTable(model);

        ordersTable.setFont(new Font("Arial", Font.PLAIN, 12));
        ordersTable.setRowHeight(30);
        ordersTable.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 12)
        );

        scrollPane = new JScrollPane(ordersTable);
        scrollPane.setBounds(20, 140, 950, 350);
        add(scrollPane);

        JLabel emptyLabel = new JLabel("No orders available.");
        emptyLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        emptyLabel.setBounds(30, 500, 300, 30);
        add(emptyLabel);

        backButton = new JButton("Back");
        backButton.setBounds(30, 550, 130, 40);
        backButton.addActionListener(this);
        add(backButton);

        viewOrderButton = new JButton("View Order");
        viewOrderButton.setBounds(600, 550, 150, 40);
        viewOrderButton.addActionListener(this);
        add(viewOrderButton);

        updateStatusButton = new JButton("Update Status");
        updateStatusButton.setBounds(780, 550, 170, 40);
        updateStatusButton.addActionListener(this);
        add(updateStatusButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backButton) {

            userShop shop = new userShop();
            shop.setVisible(true);
            this.dispose();

        } else if (e.getSource() == viewOrderButton) {

            int selectedRow = ordersTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please select an order first."
                );

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Order ID: " + model.getValueAt(selectedRow, 0)
                    + "\nCustomer: " + model.getValueAt(selectedRow, 1)
                    + "\nProduct: " + model.getValueAt(selectedRow, 2)
                    + "\nTotal Price: " + model.getValueAt(selectedRow, 3)
                    + "\nPayment Status: " + model.getValueAt(selectedRow, 4)
                    + "\nOrder Status: " + model.getValueAt(selectedRow, 5)
                    + "\nTracking Number: " + model.getValueAt(selectedRow, 6),
                    "Order Details",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        } else if (e.getSource() == updateStatusButton) {

            int selectedRow = ordersTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please select an order first."
                );

            } else {

                String paymentStatus = model.getValueAt(selectedRow, 4).toString();

                String[] statuses = {
                    "Pending",
                    "Processing",
                    "Ready to Ship",
                    "Shipped",
                    "Delivered"
                };

                String newStatus = (String) JOptionPane.showInputDialog(
                    this,
                    "Select new order status:",
                    "Update Order Status",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    statuses,
                    model.getValueAt(selectedRow, 5)
                );

                if (newStatus != null) {

                    if (newStatus.equals("Ready to Ship")
                            && !paymentStatus.equalsIgnoreCase("Paid")) {

                        JOptionPane.showMessageDialog(
                            this,
                            "This order must be paid first before shipping.\n"
                            + "Exception: COD orders can proceed."
                        );

                    } else {

                        model.setValueAt(newStatus, selectedRow, 5);

                        JOptionPane.showMessageDialog(
                            this,
                            "Order status updated successfully!"
                        );
                    }
                }
            }
        }
    }
}
