package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class salesReport extends JFrame implements ActionListener {

    private JButton backButton;

    private JButton btnBack;
    public salesReport() {

        setTitle("Sales Report");
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel headerTitle = new JLabel("SALES MANAGEMENT DASHBOARD");
        headerTitle.setBounds(30, 20, 350, 30);
        add(headerTitle);

        backButton = new JButton("Back");
        backButton.setBounds(650, 18, 100, 35);
        add(backButton);

        JLabel revlbl = new JLabel("TOTAL REVENUE");
        revlbl.setBounds(40, 85, 200, 20);
        add(revlbl);

        JLabel revValuelbl = new JLabel("$12,450.00");
        revValuelbl.setBounds(40, 105, 200, 30);
        add(revValuelbl);

        JLabel ordersTitle = new JLabel("TOTAL ORDERS");
        ordersTitle.setBounds(280, 85, 200, 20);
        add(ordersTitle);

        JLabel ordersValue = new JLabel("348");
        ordersValue.setBounds(280, 105, 200, 30);
        add(ordersValue);

        JLabel aovTitle = new JLabel("AVG ORDER VALUE");
        aovTitle.setBounds(520, 85, 200, 20);
        add(aovTitle);

        JLabel aovValue = new JLabel("$35.77");
        aovValue.setBounds(520, 105, 200, 30);
        add(aovValue);

        JButton newSaleButton = new JButton("New Sale / POS Terminal");
        newSaleButton.setBounds(30, 180, 350, 140);
        add(newSaleButton);

        JButton transactionsButton = new JButton("Recent Transactions");
        transactionsButton.setBounds(400, 180, 350, 140);
        add(transactionsButton);

        JButton analyticsButton = new JButton("Sales Analytics & Graphs");
        analyticsButton.setBounds(30, 340, 350, 140);
        add(analyticsButton);

        JButton discountsButton = new JButton("Discounts & Promotions");
        discountsButton.setBounds(400, 340, 350, 140);
        add(discountsButton);

        JButton reportsButton = new JButton("Generate Monthly Sales Report");
        reportsButton.setBounds(30, 510, 720, 100);
        add(reportsButton);

        JLabel statusLabel = new JLabel("Last updated: Today at 10:00 AM");
        statusLabel.setBounds(30, 630, 300, 25);
        add(statusLabel);

        backButton.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == backButton) {
            userShop a =  new userShop();
            a.setVisible(true);
            this.dispose();
        } else if (e.getSource() == btnBack) {

        }
    }
    

    public static  void main(String[] args) {
        salesReport obj = new salesReport();
        obj.setVisible(true);
    }
}
