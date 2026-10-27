package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userProductRatings extends JFrame implements ActionListener{



    public userProductRatings() {

        setTitle("My Product Ratings");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


    }

    @Override
    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args){
        userProductRatings lol = new userProductRatings();
        lol.setVisible(true);
    }

}