package e_commerce_platform.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userTrackOrder extends JFrame implements ActionListener {

    private JPanel upperPanel, mainPanel, trackingDetailsPanel;
    private JLabel titleLabel, orderIdLabel, statusHeaderLabel;
    private JTextField orderField;
    private JButton trackButton, backButton;

    private JLabel infoOrderNo, infoDate, infoTotal, infoAddress;

    public userTrackOrder() {
        setTitle("Track Order");
        setSize(1020, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        upperPanel = new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(0, 0, 1000, 70);
        add(upperPanel);

        backButton = new JButton("Back");
        backButton.setBounds(860, 15, 115, 30);
        upperPanel.add(backButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 70, 1000, 100);
        add(mainPanel);

        titleLabel = new JLabel("Track Your Order");
        titleLabel.setBounds(25, 20, 300, 40);
        mainPanel.add(titleLabel);

        orderIdLabel = new JLabel("Order ID:");
        orderIdLabel.setBounds(480, 25, 80, 30);
        mainPanel.add(orderIdLabel);

        orderField = new JTextField();
        orderField.setBounds(560, 25, 290, 35);
        mainPanel.add(orderField);

        trackButton = new JButton("Track");
        trackButton.setBounds(860, 25, 115, 35);
        mainPanel.add(trackButton);

        trackingDetailsPanel = new JPanel();
        trackingDetailsPanel.setLayout(null);
        trackingDetailsPanel.setBounds(25, 185, 950, 400);
        trackingDetailsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        trackingDetailsPanel.setBackground(new Color(250, 250, 250));
        add(trackingDetailsPanel);

        statusHeaderLabel = new JLabel("Enter your Order ID above to check tracking status.");
        statusHeaderLabel.setBounds(30, 25, 600, 30);
        trackingDetailsPanel.add(statusHeaderLabel);

        JPanel summaryBox = new JPanel();
        summaryBox.setLayout(null);
        summaryBox.setBounds(30, 75, 420, 280);
        summaryBox.setBorder(BorderFactory.createTitledBorder("Order Information"));
        summaryBox.setBackground(Color.WHITE);
        trackingDetailsPanel.add(summaryBox);

        infoOrderNo = new JLabel("Order Number: -");
        infoOrderNo.setBounds(20, 40, 380, 25);
        summaryBox.add(infoOrderNo);

        infoDate = new JLabel("Date Placed: -");
        infoDate.setBounds(20, 80, 380, 25);
        summaryBox.add(infoDate);

        infoTotal = new JLabel("Total Amount: -");
        infoTotal.setBounds(20, 120, 380, 25);
        summaryBox.add(infoTotal);

        infoAddress = new JLabel("Shipping Address: -");
        infoAddress.setBounds(20, 160, 380, 50);
        summaryBox.add(infoAddress);

        JPanel timelineBox = new JPanel();
        timelineBox.setLayout(null);
        timelineBox.setBounds(470, 75, 450, 280);
        timelineBox.setBorder(BorderFactory.createTitledBorder("Shipment Progress"));
        trackingDetailsPanel.add(timelineBox);

        setupTimelineStep(timelineBox, 30, 40, "1. Order Placed", true);
        setupTimelineStep(timelineBox, 30, 95, "2. Packed & Ready", true);
        setupTimelineStep(timelineBox, 30, 150, "3. Out for Delivery", true);
        setupTimelineStep(timelineBox, 30, 205, "4. Delivered", true);

        //add action listener
        trackButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private void setupTimelineStep(JPanel parent, int x, int y, String text, boolean isCompleted) {
        JPanel dot = new JPanel();
        dot.setBounds(x, y + 4, 16, 16);
        dot.setBackground(isCompleted ? new Color(34, 139, 34) : Color.LIGHT_GRAY);
        parent.add(dot);

        JLabel label = new JLabel(text);
        label.setBounds(x + 35, y, 300, 25);
        label.setForeground(isCompleted ? Color.BLACK : Color.GRAY);
        parent.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            homePage a = new homePage();
            a.setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == trackButton) {

        }
    }

    public static void main(String[] args) {
        userTrackOrder a = new  userTrackOrder();
        a.setVisible(true);
    }
}
