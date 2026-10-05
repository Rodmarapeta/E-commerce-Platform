package e_commerce_platform.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class registerPage extends JFrame implements ActionListener {

    private JLabel lblTitle, lblName, lblEmail, lblPassword, lblConfirmPassword;
    private JTextField txtName, txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnRegister, btnBack;

    registerPage() {

        setTitle("E-Commerce Platform - Register");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("Create Account");
        lblTitle.setBounds(350, 70, 400, 60);
        lblTitle.setFont(lblTitle.getFont().deriveFont(28f));
        add(lblTitle);

        lblName = new JLabel("Name:");
        lblName.setBounds(300, 160, 100, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(400, 160, 300, 35);
        add(txtName);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(300, 215, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(400, 215, 300, 35);
        add(txtEmail);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(300, 270, 100, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(400, 270, 300, 35);
        add(txtPassword);

        lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setBounds(300, 325, 120, 30);
        add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(430, 325, 270, 35);
        add(txtConfirmPassword);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(300, 390, 400, 45);
        add(btnRegister);

        btnBack = new JButton("Back to Login");
        btnBack.setBounds(400, 455, 200, 40);
        add(btnBack);

        btnRegister.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRegister) {

            String username = txtName.getText();
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            if (username.isEmpty() || email.isEmpty()
                    || password.isEmpty() || confirmPassword.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

            } else if (!password.equals(confirmPassword)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Registration successful"
                );
            }

        } else if (e.getSource() == btnBack) {

            loginPage login = new loginPage();
            login.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {

        registerPage register = new registerPage();
        register.setVisible(true);
    }
}