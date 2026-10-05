package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homePage extends JFrame {

    // Row 1
    private JButton supportbtn, trackorderbtn;
    private JLabel frstlbl, scnd, trhd;

    // Row 2
    private JLabel logo;
    private JButton searchbtn, myaccountbtn, backbtn, userProfilebtn;
    private JTextField searchproduct;

    // Category
    private String[] allCat = {
        "Electronics",
        "Kitchen and Living",
        "Fashion and Apparel",
        "Home Appliances",
        "Personal Care",
        "Groceries and Supermarket",
        "Hardware",
        "Hobbies",
        "Sport and Outdoor"
    };

    private JComboBox<String> comboBox;

    private JPanel upperPanel, navigationPanel, productPanel;

    homePage() {

        setTitle("E-Commerce Platform - Home");
        setLayout(null);
        setSize(1000, 650);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        upperPanel = new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(0, 0, 1000, 110);
        add(upperPanel);

        // Row 1
        frstlbl = new JLabel("Free Shipping order over 500");
        scnd = new JLabel("30 days easy return");
        trhd = new JLabel("Warranty");

        supportbtn = new JButton("Support");
        trackorderbtn = new JButton("Track Order");

        frstlbl.setBounds(20, 10, 200, 30);
        scnd.setBounds(250, 10, 200, 30);
        trhd.setBounds(470, 10, 150, 30);

        supportbtn.setBounds(680, 10, 120, 30);
        trackorderbtn.setBounds(820, 10, 140, 30);

        // Add Row 1
        upperPanel.add(frstlbl);
        upperPanel.add(scnd);
        upperPanel.add(trhd);
        upperPanel.add(supportbtn);
        upperPanel.add(trackorderbtn);

        navigationPanel = new JPanel();
        navigationPanel.setLayout(null);
        navigationPanel.setBounds(0, 110, 1000, 100);
        add(navigationPanel);

        logo = new JLabel("Store 📦");
        logo.setBounds(20, 20, 150, 30);
        navigationPanel.add(logo);

        comboBox = new JComboBox<>(allCat);
        comboBox.setBounds(180, 20, 180, 30);
        comboBox.setEditable(false);
        navigationPanel.add(comboBox);

        searchproduct = new JTextField();
        searchproduct.setBounds(380, 20, 220, 30);
        navigationPanel.add(searchproduct);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(610, 20, 100, 30);
        navigationPanel.add(searchbtn);

        myaccountbtn = new JButton("My Account");
        myaccountbtn.setBounds(720, 20, 120, 30);
        navigationPanel.add(myaccountbtn);

        backbtn = new JButton("Back");
        backbtn.setBounds(850, 20, 100, 30);
        navigationPanel.add(backbtn);

        productPanel = new JPanel();
        productPanel.setLayout(null);
        productPanel.setBounds(0, 210, 1000, 400);
        add(productPanel);

    }

}