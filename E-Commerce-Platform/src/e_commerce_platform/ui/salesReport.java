package e_commerce_platform.ui;

import dao.SalesReportDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class salesReport extends JFrame implements ActionListener {

    private JLabel titleLabel, totalSalesLabel, totalOrdersLabel;
    private JButton refreshButton, backButton;
    private SalesReportDao salesDao;

    public salesReport() {
        salesDao = new SalesReportDao();

        setTitle("Sales Report");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Sales Report");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(30, 30, 400, 50);
        add(titleLabel);

        totalSalesLabel = new JLabel();
        totalSalesLabel.setFont(new Font("Arial", Font.BOLD, 22));
        totalSalesLabel.setBounds(50, 150, 400, 50);
        add(totalSalesLabel);

        totalOrdersLabel = new JLabel();
        totalOrdersLabel.setFont(new Font("Arial", Font.BOLD, 22));
        totalOrdersLabel.setBounds(50, 230, 400, 50);
        add(totalOrdersLabel);

        refreshButton = new JButton("Refresh Report");
        refreshButton.setBounds(50, 330, 180, 40);
        add(refreshButton);

        backButton = new JButton("Back");
        backButton.setBounds(250, 330, 120, 40);
        add(backButton);

        refreshButton.addActionListener(this);
        backButton.addActionListener(this);

        displayReport();
    }

    public void displayReport() {

        double totalSales = salesDao.getTotalSales();
        int totalOrders = salesDao.getTotalOrders();
        totalSalesLabel.setText(String.format("Total Sales: PHP %.2f", totalSales));
        totalOrdersLabel.setText("Total Orders: " + totalOrders);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {
            displayReport();
            JOptionPane.showMessageDialog(this, "Sales report updated!");

        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}