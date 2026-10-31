package e_commerce_platform.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import dao.ProductDao;
import e_commerce_platform.Product;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class inventory extends JFrame implements ActionListener {

    private JLabel lblName,lblId,lblPrice,lblQuantity,lblCategory;
    private JTextField txtId, txtName, txtPrice, txtQuantity, txtCategory;
    private JTable productTable;
    private DefaultTableModel tableModel;
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

        tableModel = new DefaultTableModel(columns, 0) {
         @Override
          public boolean isCellEditable(int row, int column) {
          return false;
           }
        };
        
        productTable = new JTable(tableModel);

        scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(340, 80, 470, 430);
        add(scrollPane);

        btnback.addActionListener(this);
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnRemove.addActionListener(this);
        btnClear.addActionListener(this);
        btnSelect.addActionListener(this);
        
        loadProducts();
    }
    
    public void loadProducts() {
    ProductDao dao = new ProductDao();
    List<Product> products = dao.getAllProducts();
    tableModel.setRowCount(0);
    for (Product product : products) {

        Object[] row = {
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getQuantity(),
            product.getCategory()
        };

        tableModel.addRow(row);
    }
}

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnAdd) {

        try {

        String name = txtName.getText().trim();
        double price = Double.parseDouble(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String category = txtCategory.getText().trim();

        if (name.isEmpty() || category.isEmpty() || price <= 0 || quantity < 0) {
            JOptionPane.showMessageDialog(this, "Please enter valid product details.");
            return;
        }

        Product product = new Product(0, name, price, category, quantity);
        ProductDao dao = new ProductDao();

        if (dao.addProduct(product)) {
            JOptionPane.showMessageDialog(this, "Product added successfully!");

            loadProducts();

            txtId.setText("");
            txtName.setText("");
            txtPrice.setText("");
            txtQuantity.setText("");
            txtCategory.setText("");

        } else {
            JOptionPane.showMessageDialog(this, "Failed to add product.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid price and quantity.");
    }
}
         else if (e.getSource() == btnUpdate) {

        try {

        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText().trim();
        double price = Double.parseDouble(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String category = txtCategory.getText().trim();

        if (name.isEmpty() || category.isEmpty() || price <= 0 || quantity < 0) {
            JOptionPane.showMessageDialog(this, "Please enter valid product details.");
            return;
        }

        Product product = new Product(id, name, price, category, quantity);
        ProductDao dao = new ProductDao();
        if (dao.updateProduct(product)) {
            JOptionPane.showMessageDialog(this, "Product updated successfully!");

            loadProducts();

        } else {
            JOptionPane.showMessageDialog(this, "Product update failed.");

        }

    }   catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid ID, price, and quantity.");
    }
}
        else if (e.getSource() == btnRemove) {

        try {

        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a product first.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this product?", "Confirm Removal", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            ProductDao dao = new ProductDao();

            if (dao.deleteProduct(id)) {
                JOptionPane.showMessageDialog(this, "Product removed successfully!");
                loadProducts();

                txtId.setText("");
                txtName.setText("");
                txtPrice.setText("");
                txtQuantity.setText("");
                txtCategory.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Product removal failed.");
            }
        }

    } catch (NumberFormatException ex) {JOptionPane.showMessageDialog(this, "Invalid Product ID.");
    }
}
        else if (e.getSource() == btnClear) {
            txtId.setText("");
            txtName.setText("");
            txtPrice.setText("");
            txtQuantity.setText("");
            txtCategory.setText("");
            
            productTable.clearSelection();
        }
        
        else if (e.getSource() == btnSelect) {
        int selectedRow = productTable.getSelectedRow();

        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a product from the table.");
        }
        
        else {

        txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
        txtPrice.setText(tableModel.getValueAt(selectedRow, 2).toString());
        txtQuantity.setText(tableModel.getValueAt(selectedRow, 3).toString());
        txtCategory.setText(tableModel.getValueAt(selectedRow, 4).toString());

    }
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
