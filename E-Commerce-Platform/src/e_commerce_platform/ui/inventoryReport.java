package e_commerce_platform.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inventoryReport extends JFrame implements ActionListener{

    JLabel store, productID, productName, productPrice, productQty, productCatgy, lblReportsHeader;
    JTextField txtProductID, txtProductName, txtProductPrice, txtProductQty, txtproductCatgy;
    JButton btnBack, AddProduct, btnInventoryReport, btnUserReport, btnSalesReport;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JTable reportTable;

    public inventoryReport() {
        setTitle("Inventory Report");
        setSize(1000, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        store = new JLabel("Store");
        store.setBounds(50, 5, 100, 100);
        add(store);

        btnBack = new JButton("Back");
        btnBack.setBounds(880, 20, 80, 30);
        add(btnBack);

        productID = new JLabel("Product ID:");
        productID.setBounds(50, 100, 90, 25);
        add(productID);

        txtProductID = new JTextField();
        txtProductID.setBounds(135, 100, 160, 25);
        add(txtProductID);

        productName = new JLabel("Name:");
        productName.setBounds(320, 100, 60, 25);
        add(productName);

        txtProductName = new JTextField();
        txtProductName.setBounds(375, 100, 220, 25);
        add(txtProductName);

        productCatgy = new JLabel("Category:");
        productCatgy.setBounds(620, 100, 75, 25);
        add(productCatgy);

        txtproductCatgy = new JTextField();
        txtproductCatgy.setBounds(695, 100, 245, 25);
        add(txtproductCatgy);

        productPrice = new JLabel("Price :");
        productPrice.setBounds(50, 135, 90, 25);
        add(productPrice);

        txtProductPrice = new JTextField();
        txtProductPrice.setBounds(135, 135, 160, 25);
        add(txtProductPrice);

        productQty = new JLabel("Stock:");
        productQty.setBounds(320, 135, 60, 25);
        add(productQty);

        txtProductQty = new JTextField();
        txtProductQty.setBounds(375, 135, 220, 25);
        add(txtProductQty);

        AddProduct = new JButton("Add Product");
        AddProduct.setBounds(695, 133, 245, 28);
        add(AddProduct);

        lblReportsHeader = new JLabel("Report Views");
        lblReportsHeader.setBounds(50, 180, 900, 25);
        add(lblReportsHeader);

        btnInventoryReport = new JButton("View Inventory Report");
        btnInventoryReport.setBounds(50, 215, 280, 35);
        add(btnInventoryReport);

        btnSalesReport = new JButton("View Sales Report");
        btnSalesReport.setBounds(360, 215, 280, 35);
        add(btnSalesReport);

        btnUserReport = new JButton("View User Report");
        btnUserReport.setBounds(670, 215, 270, 35);
        add(btnUserReport);

        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        scrollPane = new JScrollPane(reportTable);
        scrollPane.setBounds(50, 265, 890, 490);
        add(scrollPane);


        //add to actionlistner
        btnBack.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBack) {
            userShop user = new userShop();
            user.setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == btnInventoryReport) {

        }
    }
    public static  void main(String[] args) {
        inventoryReport obj = new inventoryReport();
        obj.setVisible(true);
    }
}
