package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class deliveryAddressPage extends JFrame implements ActionListener{

    private JLabel lblTitle, lblStreet, lblCity, lblProvince, lblPostal, lblPhone;
    private JTextField txtStreet, txtCity, txtProvince, txtPostal, txtPhone;
    private JButton btnSave, btnBack;

    public deliveryAddressPage() {

        setTitle("Dilivery Address");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lblTitle = new JLabel("Delivery Address Management");
        lblTitle.setBounds(50, 40, 400, 40);
        add(lblTitle);

        lblStreet = new JLabel("Street Address:");
        lblStreet.setBounds(50, 120, 150, 30);
        add(lblStreet);

        txtStreet = new JTextField();
        txtStreet.setBounds(200, 120, 500, 35);
        add(txtStreet);

        lblCity = new JLabel("City / Municipality:");
        lblCity.setBounds(50, 180, 150, 30);
        add(lblCity);

        txtCity = new JTextField();
        txtCity.setBounds(200, 180, 500, 35);
        add(txtCity);

        lblProvince = new JLabel("Province:");
        lblProvince.setBounds(50, 240, 150, 30);
        add(lblProvince);

        txtProvince = new JTextField();
        txtProvince.setBounds(200, 240, 500, 35);
        add(txtProvince);

        lblPostal = new JLabel("Postal Code:");
        lblPostal.setBounds(50, 300, 150, 30);
        add(lblPostal);

        txtPostal = new JTextField();
        txtPostal.setBounds(200, 300, 500, 35);
        add(txtPostal);

        lblPhone = new JLabel("Phone Number:");
        lblPhone.setBounds(50, 360, 150, 30);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(200, 360, 500, 35);
        add(txtPhone);

        btnSave = new JButton("Save Address");
        btnSave.setBounds(200, 450, 220, 45);
        add(btnSave);

        btnBack = new JButton("Back");
        btnBack.setBounds(650, 20, 100, 30);
        add(btnBack);

        btnSave.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){


    }

    public static void main(String[] args){
        deliveryAddressPage lol = new deliveryAddressPage();
        lol.setVisible(true);
    }

}
