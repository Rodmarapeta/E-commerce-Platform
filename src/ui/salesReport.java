package ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class salesReport extends JFrame {

    public salesReport() {

        setTitle("Sales Report");
        setSize(1000, 1000);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



    }
    public void actionPerformed(ActionEvent e) {

    }
    public static  void main(String[] args) {
        inventoryReport obj = new inventoryReport();
        obj.setVisible(true);
    }
}
