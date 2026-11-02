package e_commerce_platform.ui;

import e_commerce_platform.Order;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class salesReport extends JFrame implements ActionListener {

    private JButton backButton, newSaleButton, transactionsButton, analyticsButton,
                    discountsButton, reportsButton;
    private JLabel headerTitle, revlbl, revValuelbl, ordersTitle, ordersValue, aovTitle,
                   aovValue, statusLabel;
                   
    public salesReport() 
    {
        setTitle("Sales Report");
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        headerTitle = new JLabel("SALES MANAGEMENT DASHBOARD");
        headerTitle.setBounds(30, 20, 350, 30);
        add(headerTitle);

        backButton = new JButton("Back");
        backButton.setBounds(650, 18, 100, 35);
        add(backButton);

        revlbl = new JLabel("TOTAL REVENUE");
        revlbl.setBounds(40, 85, 200, 20);
        add(revlbl);

        revValuelbl = new JLabel("PHP 0.00");
        revValuelbl.setBounds(40, 105, 200, 30);
        add(revValuelbl);

        ordersTitle = new JLabel("TOTAL ORDERS");
        ordersTitle.setBounds(280, 85, 200, 20);
        add(ordersTitle);

        ordersValue = new JLabel("0");
        ordersValue.setBounds(280, 105, 200, 30);
        add(ordersValue);

        newSaleButton = new JButton("New Sale / POS Terminal");
        newSaleButton.setBounds(30, 180, 350, 140);
        add(newSaleButton);

        transactionsButton = new JButton("Recent Transactions");
        transactionsButton.setBounds(400, 180, 350, 140);
        add(transactionsButton);

        analyticsButton = new JButton("Sales Analytics & Graphs");
        analyticsButton.setBounds(30, 340, 350, 140);
        add(analyticsButton);

        discountsButton = new JButton("Discounts & Promotions");
        discountsButton.setBounds(400, 340, 350, 140);
        add(discountsButton);

        reportsButton = new JButton("Generate Monthly Sales Report");
        reportsButton.setBounds(30, 510, 720, 100);
        add(reportsButton);

        statusLabel = new JLabel("Last updated: Today at 10:00 AM");
        statusLabel.setBounds(30, 630, 300, 25);
        add(statusLabel);
        
        //add action listeners
        backButton.addActionListener(this);
        newSaleButton.addActionListener(this);
        transactionsButton.addActionListener(this);
        reportsButton.addActionListener(this);
        
        updateSalesDashboard();
    }
    
    private void updateSalesDashboard() {
        List<Order> orders = Order.getPlacedOrders();
        double totalRevenue = 0.0;
        
        for (Order order : orders) {
            totalRevenue += order.getTotalPrice();
        }
        
        revValuelbl.setText(String.format("PHP %.2f", totalRevenue));
        ordersValue.setText(String.valueOf(orders.size()));
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == backButton) {
            userShop a = new userShop();
            a.setVisible(true);
            this.dispose();
        } 
        else if (e.getSource() == reportsButton) {
            List<Order> orders = Order.getPlacedOrders();
            
            updateSalesDashboard();
            
            if (orders.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "No sales data", "Sales Report", JOptionPane.INFORMATION_MESSAGE);
            } 
            else 
            {
                String report = " ";
                double Revenue = 0.0;
                
                for (Order order : orders) 
                {
                    report += "Order ID: #" + order.getOrderId() + "\n"
                            + "Customer: " + order.getCustomerName() + "\n"
                            + "Amount: PHP " + String.format("%.2f", order.getTotalPrice()) + "\n";
                    Revenue += order.getTotalPrice();
                }

                report += "\nTotal Orders: " + orders.size() + "\nTotal Revenue: " + String.format("%.2f", Revenue);

                JTextArea textArea = new JTextArea(report, 20, 45);
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(this, scrollPane, "Generated Sales Report", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource() == newSaleButton) 
        {
            sellProduct sell = new sellProduct();
            sell.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        salesReport obj = new salesReport();
        obj.setVisible(true);
    }
}