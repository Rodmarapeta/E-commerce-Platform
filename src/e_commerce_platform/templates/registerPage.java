package e_commerce_platform.templates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerPage extends JFrame implements ActionListener {

    JLabel email, password, username, createacc, l5;
    JButton signupbtn, login, backbtn, helpbtn;
    JTextField emailid, mobilenum, usernameid;
    JPasswordField passwordid;

    // Changed constructor visibility to public
    public registerPage() {

        setTitle("registerPage");
        setResizable(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        //
        email = new JLabel("Email:");
        emailid = new JTextField();
        username = new JLabel("Username:");
        usernameid = new JTextField();
        password = new JLabel("Password:");
        passwordid = new JPasswordField();
        mobilenum = new JTextField();
        createacc = new JLabel("Create Account:");
        l5 = new JLabel("Login Page");
        login = new JButton("Login");
        signupbtn = new JButton("Signup");
        backbtn = new JButton("Back");
        helpbtn = new JButton("Help");

        //SetBounds
        email.setBounds(50, 50, 100, 30);
        emailid.setBounds(160, 50, 200, 30);
        username.setBounds(50, 100, 100, 30);
        usernameid.setBounds(160, 100, 200, 30);
        password.setBounds(50, 150, 100, 30);
        passwordid.setBounds(160, 150, 200, 30);
        createacc.setBounds(50, 200, 120, 30);
        mobilenum.setBounds(160, 200, 200, 30);
        signupbtn.setBounds(50, 270, 100, 30);
        login.setBounds(160, 270, 100, 30);
        backbtn.setBounds(270, 270, 100, 30);
        helpbtn.setBounds(380, 270, 100, 30);

        //ADDtogui
        add(email);
        add(username);
        add(password);
        add(createacc);
        add(emailid);
        add(usernameid);
        add(passwordid);
        add(mobilenum);
        add(signupbtn);
        add(login);
        add(backbtn);
        add(helpbtn);

        //Add Action Listeners
        signupbtn.addActionListener(this);
        backbtn.addActionListener(this);
        helpbtn.addActionListener(this);
        login.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //
        //dito sa signupbtn if nakagawa na ng account dapat matic na sa login page
        if (e.getSource() == signupbtn) {
            String userEmail = emailid.getText();

        } else if (e.getSource() == backbtn) {
            loginPage l = new loginPage();
            l.setVisible(true);
            this.dispose();
        } else if (e.getSource() == helpbtn) {
            JOptionPane.showMessageDialog(this, "Enter your account details to register.");
        } else if (e.getSource() == login) {

        } else {

        }
    }

    public static void main(String[] args){
        registerPage lol = new registerPage();
        lol.setVisible(true);
    }
}
