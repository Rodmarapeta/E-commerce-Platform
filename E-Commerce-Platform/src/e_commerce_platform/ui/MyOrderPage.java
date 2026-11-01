package e_commerce_platform.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyOrderPage extends JFrame implements ActionListener {

    private JLabel titleLabel, subtitleLabel, sectionLabel;
    private JLabel emptyLabel;
    private JButton backButton, refreshButton;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public MyOrderPage() {

        setTitle("E-Commerce Platform - My Orders");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        MyOrderPage orders = new MyOrderPage();
        orders.setVisible(true);
    }
}