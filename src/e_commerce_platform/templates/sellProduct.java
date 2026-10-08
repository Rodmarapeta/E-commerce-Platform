package e_commerce_platform.templates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sellProduct extends JFrame implements ActionListener{

    JPanel contentPane;
    JLabel productName, productId, productPrice, productcategory, productQuantity;
    JTextField prdctName, prdctPrice, prdctCategory, prdctId, prdctQuantity;
    JComboBox prdctcategory;
    JButton sellProduct, mngeInventory;

    public sellProduct() {

        setTitle("USER SHOP");
        setLayout(null);
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 800, 800);
        add(contentPane);

        productName = new JLabel("Product Name: ");
        prdctName = new JTextField();

        productId = new JLabel("Product ID: ");
        prdctId = new JTextField();

        productPrice = new JLabel("Product Price: ");
        prdctPrice = new JTextField();

        productQuantity = new JLabel("Product Quantity: ");
        prdctQuantity = new JTextField();

        productcategory = new JLabel("Product Category: ");
        prdctCategory = new JTextField();

        sellProduct = new JButton("Sell Product");


        productName.setBounds(80, 80, 130, 40);
        prdctName.setBounds(220, 80, 350, 40);

        productId.setBounds(80, 145, 130, 40);
        prdctId.setBounds(220, 145, 350, 40);

        productPrice.setBounds(80, 210, 130, 40);
        prdctPrice.setBounds(220, 210, 350, 40);

        productQuantity.setBounds(80, 275, 130, 40);
        prdctQuantity.setBounds(220, 275, 350, 40);

        productcategory.setBounds(80, 340, 130, 40);
        prdctCategory.setBounds(220, 340, 350, 40);

        sellProduct.setBounds(150, 620, 500, 50);

        contentPane.add(productName);
        contentPane.add(productId);
        contentPane.add(productPrice);
        contentPane.add(productcategory);
        contentPane.add(prdctName);
        contentPane.add(prdctPrice);
        contentPane.add(prdctCategory);
        contentPane.add(prdctId);
        contentPane.add(sellProduct);
        contentPane.add(prdctQuantity);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == sellProduct){
            String productName = prdctName.getText();
            String productId = prdctId.getText();
            String productPrice = prdctPrice.getText();
            String productCategory = prdctCategory.getText();
            String productQuantity = prdctQuantity.getText();

            if( productName.isEmpty()|| productId.isEmpty()|| productPrice.isEmpty()|| productCategory.isEmpty()|| productQuantity.isEmpty()){

            }
            else{

            }
        }
    }

    public static void main(String[] args) {

        sellProduct frame = new sellProduct();
        frame.setVisible(true);
    }
}
