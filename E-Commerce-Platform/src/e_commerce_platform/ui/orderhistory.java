package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class orderhistory extends JFrame implements ActionListener {

    JLabel headerTitle, sidetitle, activelbl, activeval, deliveredlbl,
            deliveredval, returnslbl, returnsval;
    JButton backbtn, reportIssuebtn, liveTrackbutton, historybtn,
            returnsbtn, invoicesbtn;
    JSeparator topseparator;
    JPanel sidebarpanel;

    public orderhistory() {

        setTitle("Order History & Tracking");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        headerTitle = new JLabel("LOGISTICS & ORDER TRACKING");
        headerTitle.setBounds(35, 25, 350, 30);
        add(headerTitle);

        backbtn = new JButton("Back");
        backbtn.setBounds(650, 22, 100, 35);
        add(backbtn);

        topseparator = new JSeparator();
        topseparator.setBounds(35, 70, 715, 10);
        add(topseparator);

        sidebarpanel = new JPanel();
        sidebarpanel.setLayout(null);
        sidebarpanel.setBounds(35, 95, 220, 480);

        sidetitle = new JLabel("STATUS SUMMARY");
        sidetitle.setBounds(20, 20, 180, 20);
        sidetitle.setForeground(new Color(100, 110, 120));
        sidebarpanel.add(sidetitle);

        activelbl = new JLabel("Active Shipments");
        activelbl.setBounds(20, 60, 180, 15);
        sidebarpanel.add(activelbl);

        activeval = new JLabel("4 Orders");
        activeval.setBounds(20, 80, 180, 25);
        sidebarpanel.add(activeval);

        deliveredlbl = new JLabel("Delivered");
        deliveredlbl.setBounds(20, 140, 180, 15);
        sidebarpanel.add(deliveredlbl);

        deliveredval = new JLabel("128");
        deliveredval.setBounds(20, 160, 180, 25);
        sidebarpanel.add(deliveredval);

        returnslbl = new JLabel("Returns / Issues");
        returnslbl.setBounds(20, 220, 180, 15);
        sidebarpanel.add(returnslbl);

        returnsval = new JLabel("1 Case");
        returnsval.setBounds(20, 240, 180, 25);
        sidebarpanel.add(returnsval);

        reportIssuebtn = new JButton("Report Issue");
        reportIssuebtn.setBounds(20, 390, 180, 50);
        sidebarpanel.add(reportIssuebtn);
        add(sidebarpanel);

        liveTrackbutton = new JButton("Live Shipment Tracking");
        liveTrackbutton.setBounds(275, 95, 475, 140);
        add(liveTrackbutton);

        historybtn = new JButton("Complete Order History");
        historybtn.setBounds(275, 255, 230, 140);
        add(historybtn);

        returnsbtn = new JButton("Returns & Refunds");
        returnsbtn.setBounds(520, 255, 230, 140);
        add(returnsbtn);

        invoicesbtn = new JButton("Invoices, Receipts & Billing Archive");
        invoicesbtn.setBounds(275, 415, 475, 160);
        add(invoicesbtn);

        backbtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backbtn) {
            userShop a = new userShop();
            a.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new orderhistory().setVisible(true);
        });
    }
}
