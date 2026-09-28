
package e_commerce_platform.templates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPage extends JFrame implements ActionListener {

    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername, txtPassword;
    private JButton btnLogin, noAccount, rpage;

    public loginPage() {
        setTitle("Login Page");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800,800);

        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        btnLogin = new JButton("Login");
        noAccount = new JButton("No Account? Sign up");
        rpage = new JButton("Register");

        //location
        lblUsername.setBounds(40, 20, 320, 25);
        txtUsername.setBounds(40, 45, 320, 35);
        lblPassword.setBounds(40, 95, 320, 25);
        txtPassword.setBounds(40, 120, 320, 35);
        btnLogin.setBounds(40, 175, 320, 40);
        noAccount.setBounds(40, 230, 180, 30);
        rpage.setBounds(240, 230, 120, 30);

        //styles
        btnLogin.setContentAreaFilled(false);
        btnLogin.setOpaque(false);
        btnLogin.setBorderPainted(true);
        btnLogin.setFocusPainted(false);

        noAccount.setContentAreaFilled(false);
        noAccount.setOpaque(false);
        noAccount.setBorderPainted(false);
        noAccount.setFocusPainted(false);

        rpage.setContentAreaFilled(false);
        rpage.setOpaque(false);
        rpage.setBorderPainted(false);
        rpage.setFocusPainted(false);

        //addToGUI
        add(lblUsername);
        add(lblPassword);
        add(txtUsername);
        add(txtPassword);
        add(btnLogin);
        add(rpage);
        add(noAccount);

        //addActionListener
        btnLogin.addActionListener(this);
        noAccount.addActionListener(this);
        rpage.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnLogin){
            String username = txtUsername.getText();
            String password = txtPassword.getText();
        } else{
        registerPage rg = new registerPage();
        rg.setVisible(true);
        this.dispose();
        }

    }

    public static void main(String[] args) {
        loginPage lol = new loginPage();
        lol.setVisible(true);
    }
}
