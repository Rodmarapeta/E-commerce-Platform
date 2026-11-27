/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_commerce_platform.ui;

import e_commerce_platform.ProductReview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ratingProduct extends JFrame implements ActionListener{
    
    JButton rateButton;
    JLabel lbl1,productJLabel, productpriceJLabel,productquantityJLabel,productCategoryJLabel;
    JTextArea reviewtxt;
    
    public ratingProduct{
        
        setSize(800,800);
        setTitle("Rating product");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        
        lbl1 = new JLabel("Rate product" + getName());
        lbl1.setBounds(30, 30, 600, 50);
        add(lbl1);
        
        
        
        
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        String[] ratings = {"1", "2", "3", "4", "5"};
        String selectedRating = (String) 
        JOptionPane.showInputDialog(this, "Select Your Rating (1-5 Stars):", 
        "Rate Product", JOptionPane.QUESTION_MESSAGE, null, ratings, ratings[4]);

            if (selectedRating != null) {
                int stars = Integer.parseInt(selectedRating);

                
            }
    }
    
    public static void main(String[] args) {
        ratingProduct a = new ratingProduct();
        a.setVisible(true);
    }
}
