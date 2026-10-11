package e_commerce_platform.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class userProfilePage extends JFrame  {

    private JLabel lblTitle, lblUsername, lblEmail;
    private JTextField txtUsername, txtEmail;
    private JButton btnSave, btnBack;

    userProfilePage() {

        setTitle("E-Commerce Platform - User Profile");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("User Profile");
        lblTitle.setBounds(350, 80, 400, 60);
        lblTitle.setFont(lblTitle.getFont().deriveFont(28f));
        add(lblTitle);

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(300, 190, 100, 30);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(400, 190, 300, 35);
        add(txtUsername);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(300, 250, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(400, 250, 300, 35);
        add(txtEmail);

        btnSave = new JButton("Save Changes");
        btnSave.setBounds(300, 330, 400, 45);
        add(btnSave);

        btnBack = new JButton("Back to Home");
        btnBack.setBounds(400, 400, 200, 40);
        add(btnBack);

    }

}