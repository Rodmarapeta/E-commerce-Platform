package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userShop extends JFrame implements ActionListener {

    private JLabel headerTitle, nameLabel, locationLabel;
    private JButton backButton, shopButton, salesButton,
            ordersButton, accountButton, reportsButton, inventoryButton;

    public userShop() {

        setTitle("Shop Owner Dashboard");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        headerTitle = new JLabel("SHOP OWNER PORTAL");
        headerTitle.setBounds(30, 20, 300, 30);
        add(headerTitle);

        backButton = new JButton("Back");
        backButton.setBounds(650, 18, 100, 35);
        add(backButton);

        nameLabel = new JLabel("Welcome, Name");
        nameLabel.setBounds(35, 75, 500, 25);
        add(nameLabel);

        locationLabel = new JLabel("Location: Location of user");
        locationLabel.setBounds(35, 100, 500, 20);
        add(locationLabel);

        shopButton = new JButton("Sell Items");
        shopButton.setBounds(30, 155, 720, 100);
        add(shopButton);

        salesButton = new JButton("Generate Sales");
        salesButton.setBounds(30, 275, 350, 130);
        add(salesButton);

        ordersButton = new JButton("Manage Orders");
        ordersButton.setBounds(400, 275, 350, 130);
        add(ordersButton);

        inventoryButton = new JButton("Manage Inventory");
        inventoryButton.setBounds(30, 420, 350, 130);
        add(inventoryButton);

        reportsButton = new JButton("Inventory Reports");
        reportsButton.setBounds(400, 420, 350, 130);
        add(reportsButton);

        accountButton = new JButton("My Account Settings");
        accountButton.setBounds(30, 575, 720, 75);
        add(accountButton);

        //add to actionlistener
        backButton.addActionListener(this);
        reportsButton.addActionListener(this);
        shopButton.addActionListener(this);
        inventoryButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource()== backButton){
            userProfilePage userpPro = new userProfilePage();
            userpPro.setVisible(true);
            this.dispose();
        }
        else if (e.getSource()== reportsButton) {
            inventoryReport valorant = new inventoryReport();
            valorant.setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == shopButton) {
            sellProduct gooditembusseng = new sellProduct();
            gooditembusseng.setVisible(true);
            this.dispose();
        } else if (e.getSource() == inventoryButton) {
            inventory baihubi = new inventory();
            baihubi.setVisible(true);
            this.dispose();
        }


    }

    public static void main(String[] args) {
        userShop bisaya = new userShop();
        bisaya.setVisible(true);
    }

}
