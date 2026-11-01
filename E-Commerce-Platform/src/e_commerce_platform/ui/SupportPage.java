package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupportPage extends JFrame implements ActionListener {
    private JPanel headerPanel;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {

        SupportPage page = new SupportPage();
        page.setVisible(true);
    }
}