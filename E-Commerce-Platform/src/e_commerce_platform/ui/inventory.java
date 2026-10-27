package e_commerce_platform.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class inventory extends JFrame implements ActionListener {

    private JLabel lblName,lblId,lblPrice,lblQuantity,lblCategory;
    private JTextField txtId, txtName, txtPrice, txtQuantity, txtCategory;
    private JTable productTable;
    private JButton btnback, btnAdd, btnUpdate , btnRemove, btnClear, btnSelect;
    JScrollPane scrollPane;

    public inventory() {

        setTitle("Inventory Management System");
        setSize(850, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnback = new JButton("Back");
        btnback.setBounds(710, 20, 100, 30);
        add(btnback);

        lblId = new JLabel("Product ID:");
        lblId.setBounds(30, 80, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 80, 180, 25);
        add(txtId);

        lblName = new JLabel("Product Name:");
        lblName.setBounds(30, 120, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 120, 180, 25);
        add(txtName);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(30, 160, 100, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(130, 160, 180, 25);
        add(txtPrice);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(30, 200, 100, 25);
        add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(130, 200, 180, 25);
        add(txtQuantity);

        lblCategory = new JLabel("Category:");
        lblCategory.setBounds(30, 240, 100, 25);
        add(lblCategory);

        txtCategory = new JTextField();
        txtCategory.setBounds(130, 240, 180, 25);
        add(txtCategory);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(30, 290, 80, 30);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 290, 90, 30);
        add(btnUpdate);

        btnRemove = new JButton("Remove");
        btnRemove.setBounds(220, 290, 90, 30);
        add(btnRemove);

        btnClear = new JButton("Clear");
        btnClear.setBounds(120, 330, 90, 30);
        add(btnClear);

        btnSelect = new JButton("Select");
        btnSelect.setBounds(220, 330, 90, 30);
        add(btnSelect);

        //make columns for product info
        String[] columns = {"ID", "Name", "Price", "Quantity", "Category"};
        productTable = new JTable(new Object[0][5], columns);

        scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(340, 80, 470, 430);
        add(scrollPane);

        btnback.addActionListener(this);
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnRemove.addActionListener(this);
        btnClear.addActionListener(this);
        btnSelect.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnAdd) {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            int price = Integer.parseInt(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            String category = txtCategory.getText();

        }
        else if (e.getSource() == btnUpdate) {
            int id = Integer.parseInt(txtId.getText());
        }
        else if (e.getSource() == btnRemove) {
            int id = Integer.parseInt(txtId.getText());
        }
        else if (e.getSource() == btnClear) {
            txtId.setText("");
            txtName.setText("");
            txtPrice.setText("");
            txtQuantity.setText("");
        }
        else if (e.getSource() == btnSelect) {

        }
        else if (e.getSource() == btnback) {
            userShop blonde  = new userShop();
            blonde.setVisible(true);
            this.dispose();
        }

    }

    public static void main(String[] args) {
        inventory frame = new inventory();
        frame.setVisible(true);
    }
}
