package e_commerce_platform.templates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class loginPage extends JFrame implements ActionListener {

    private JLabel lblTitle, lblEmail, lblPassword, lblNoAccount;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnSignUp;

    public loginPage() {

        setTitle("E-Commerce Platform - Login");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("E-Commerce Platform");
        lblTitle.setBounds(330, 80, 400, 60);
        lblTitle.setFont(lblTitle.getFont().deriveFont(28f));
        add(lblTitle);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(300, 190, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(400, 190, 300, 35);
        add(txtEmail);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(300, 250, 100, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(400, 250, 300, 35);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(300, 320, 400, 45);
        add(btnLogin);

        lblNoAccount = new JLabel("No Account?");
        lblNoAccount.setBounds(400, 385, 100, 30);
        add(lblNoAccount);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(530, 380, 170, 40);
        add(btnSignUp);

        btnLogin.addActionListener(this);
        btnSignUp.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogin) {

            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());

            if (!email.isEmpty() && !password.isEmpty()) {

                homePage home = new homePage();
                home.setVisible(true);
                this.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter email and password first",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else if (e.getSource() == btnSignUp) {

            registerPage register = new registerPage();
            register.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {

        loginPage login = new loginPage();
        login.setVisible(true);
    }
}
