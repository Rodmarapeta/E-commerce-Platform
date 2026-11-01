package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupportPage extends JFrame implements ActionListener {
    private JPanel headerPanel;
    private JPanel mainPanel;
    private JLabel subtitleLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JTextField nameField;
    private JTextField emailField;
    private JLabel titleLabel;
    

    public SupportPage() {

        setTitle("Customer Support");
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 100);
        headerPanel.setLayout(null);
        add(headerPanel);

        titleLabel = new JLabel("Customer Support");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(40, 25, 350, 50);
        headerPanel.add(titleLabel);

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 100, 1000, 600);
        mainPanel.setLayout(null);
        add(mainPanel);

        subtitleLabel = new JLabel("How Can We Help You?");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        subtitleLabel.setBounds(300, 25, 400, 40);
        mainPanel.add(subtitleLabel);

        nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(150, 110, 150, 30);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(320, 110, 500, 35);
        mainPanel.add(nameField);

        emailLabel = new JLabel("Email Address:");
        emailLabel.setBounds(150, 175, 150, 30);
        mainPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(320, 175, 500, 35);
        mainPanel.add(emailField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {

        SupportPage page = new SupportPage();
        page.setVisible(true);
    }
}