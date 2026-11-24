package e_commerce_platform.ui;

import dao.SalesReportDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class salesReport extends JFrame implements ActionListener {

    private JLabel headerTitle, revlbl, revValuelbl, ordersTitle, ordersValue;
    private JButton refreshButton, backButton;
    private SalesReportDao salesDao;

    public salesReport() {

        salesDao = new SalesReportDao();

        setTitle("Sales Report");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        headerTitle = new JLabel("SALES MANAGEMENT DASHBOARD");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        headerTitle.setBounds(30, 30, 500, 40);
        add(headerTitle);

        backButton = new JButton("Back");
        backButton.setBounds(650, 30, 100, 35);
        add(backButton);

        revlbl = new JLabel("TOTAL REVENUE");
        revlbl.setFont(new Font("Arial", Font.BOLD, 16));
        revlbl.setBounds(50, 130, 200, 30);
        add(revlbl);

        revValuelbl = new JLabel();
        revValuelbl.setFont(new Font("Arial", Font.BOLD, 26));
        revValuelbl.setBounds(50, 165, 300, 50);
        add(revValuelbl);

        ordersTitle = new JLabel("TOTAL ORDERS");
        ordersTitle.setFont(new Font("Arial", Font.BOLD, 16));
        ordersTitle.setBounds(420, 130, 200, 30);
        add(ordersTitle);

        ordersValue = new JLabel();
        ordersValue.setFont(new Font("Arial", Font.BOLD, 26));
        ordersValue.setBounds(420, 165, 200, 50);
        add(ordersValue);

        refreshButton = new JButton("Refresh Report");
        refreshButton.setBounds(50, 280, 180, 45);
        add(refreshButton);

        refreshButton.addActionListener(this);
        backButton.addActionListener(this);

        displayReport();
    }

    public void displayReport() {

        double totalSales = salesDao.getTotalSales();
        int totalOrders = salesDao.getTotalOrders();

        revValuelbl.setText(String.format("PHP %.2f", totalSales));
        ordersValue.setText(String.valueOf(totalOrders));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == refreshButton) {

            displayReport();

            JOptionPane.showMessageDialog(
                    this,
                    "Sales report updated!"
            );

        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}
