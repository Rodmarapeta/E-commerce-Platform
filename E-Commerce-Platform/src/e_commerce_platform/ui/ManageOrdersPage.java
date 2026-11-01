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
        // Orders Table
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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
}
    public static void main(String[] args) {

    ManageOrdersPage page = new ManageOrdersPage();
    page.setVisible(true);

}
}
