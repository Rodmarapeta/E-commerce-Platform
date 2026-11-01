package e_commerce_platform.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MyReviewsPage extends JFrame implements ActionListener {

    private JPanel headerPanel;
    private JLabel titleLabel;
    private JTable reviewsTable;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JButton writeReviewButton;

    public MyReviewsPage() {

        setTitle("E-Commerce Platform - My Reviews");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 100);
        headerPanel.setLayout(null);
        add(headerPanel);
        
        titleLabel = new JLabel("My Reviews");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(30, 25, 300, 50);
        headerPanel.add(titleLabel);
    }
    @Override
        public void actionPerformed(ActionEvent e) {
    }
    public static void main(String[] args) {
        MyReviewsPage reviews = new MyReviewsPage();
        reviews.setVisible(true);
}
}