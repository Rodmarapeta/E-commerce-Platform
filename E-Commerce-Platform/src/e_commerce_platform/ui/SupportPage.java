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
    private JLabel orderLabel;
    private JTextField orderField;
    private JLabel messageLabel;
    private JTextArea messageArea;
    private JScrollPane messageScroll;
    private JButton submitButton;
    private JButton clearButton;
    private JButton backButton;
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

        orderLabel = new JLabel("Order ID:");
        orderLabel.setBounds(150, 240, 150, 30);
        mainPanel.add(orderLabel);

        orderField = new JTextField();
        orderField.setBounds(320, 240, 500, 35);
        mainPanel.add(orderField);
        // Message
        messageLabel = new JLabel("Your Message:");
        messageLabel.setBounds(150, 305, 150, 30);
        mainPanel.add(messageLabel);

        messageArea = new JTextArea();
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        messageScroll = new JScrollPane(messageArea);
        messageScroll.setBounds(320, 305, 500, 150);
        mainPanel.add(messageScroll);

        submitButton = new JButton("Submit Request");
        submitButton.setBounds(320, 485, 160, 40);
        submitButton.addActionListener(this);
        mainPanel.add(submitButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(500, 485, 120, 40);
        clearButton.addActionListener(this);
        mainPanel.add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(40, 530, 120, 40);
        backButton.addActionListener(this);
        mainPanel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {

        SupportPage page = new SupportPage();
        page.setVisible(true);
    }
}