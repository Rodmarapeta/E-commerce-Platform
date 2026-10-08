package e_commerce_platform.templates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class inventory extends JFrame implements ActionListener {

    private JLabel lblName,lblId,lblPrice,lblQuantity,lblCategory;
    private JTextField txtId, txtName, txtPrice, txtQuantity, txtCategory;
    private JTable productTable;

    public inventory() {

        setTitle("Inventory Management System");
        setSize(850, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        lblId = new JLabel("Product ID:");
        lblId.setBounds(30, 30, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 30, 180, 25);
        add(txtId);

        lblName = new JLabel("Product Name:");
        lblName.setBounds(30, 70, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 70, 180, 25);
        add(txtName);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(30, 110, 100, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(130, 110, 180, 25);
        add(txtPrice);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(30, 150, 100, 25);
        add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(130, 150, 180, 25);
        add(txtQuantity);

        lblCategory = new JLabel("Category:");
        lblCategory.setBounds(30, 190, 100, 25);
        add(lblCategory);

        txtCategory = new JTextField();
        txtCategory.setBounds(130, 190, 180, 25);
        add(txtCategory);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(30, 240, 80, 30);
        add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 240, 90, 30);
        add(btnUpdate);

        JButton btnRemove = new JButton("Remove");
        btnRemove.setBounds(220, 240, 90, 30);
        add(btnRemove);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(120, 280, 90, 30);
        add(btnClear);

        JButton btnSelect = new JButton("Select");
        btnSelect.setBounds(220, 280, 90, 30);
        add(btnSelect);

        String[] columns = {"ID", "Name", "Price", "Quantity", "Category"};
        productTable = new JTable(new Object[0][5], columns);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(340, 30, 460, 430);
        add(scrollPane);


    }

    @Override
    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args) {
        inventory frame = new inventory();
        frame.setVisible(true);
    }
}
