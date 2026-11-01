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
 
        titleLabel = new JLabel("My Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setBounds(40, 20, 400, 40);

        sectionLabel = new JLabel("Orders");
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 22));
        sectionLabel.setBounds(60, 40, 800, 90);
        add(sectionLabel);
        // Table Columns
        String[] columns = {
            "Product Name",
            "Deliver Date",
            "Total Price",
            "Order Status"
};

        tableModel = new DefaultTableModel(columns, 0) {

    @Override
            public boolean isCellEditable(int row, int column) {
            return false;
    }
};
        
        orderTable = new JTable(tableModel);
        orderTable.setRowHeight(45);
        orderTable.setFont(new Font("Arial", Font.PLAIN, 13));

        scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(40, 150, 900, 280);
        add(scrollPane);

        emptyLabel = new JLabel(
        "No current orders yet."
    );
        backButton = new JButton("Back to Profile");
        backButton.setBounds(40, 450, 160, 40);
        add(backButton);

        refreshButton = new JButton("Refresh Orders");
        refreshButton.setBounds(780, 450, 160, 40);
        add(refreshButton);
        
        backButton.addActionListener(this);
        refreshButton.addActionListener(this);
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {

    if (e.getSource() == backButton) {
        userProfilePage profile = new userProfilePage();
        profile.setVisible(true);
        this.dispose();
    }
    else if (e.getSource() == refreshButton) {

        JOptionPane.showMessageDialog(
            this,
            "Order refresh will be connected to the database."
        );
    }
}
    public static void main(String[] args) {
        MyOrderPage orders = new MyOrderPage();
        orders.setVisible(true);
    }
}