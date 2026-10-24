package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class userProfilePage extends JFrame implements ActionListener{

    private JLabel nameLabel, locationLabel;
    private JButton backButton, cartButton, orderButton, reviewsButton, shopButton, addressButton, accountButton;

    public userProfilePage() {

        setTitle("Profile Page");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(55, 42, 470, 25);
        add(nameLabel);

        locationLabel = new JLabel("Location of user");
        locationLabel.setBounds(55, 70, 470, 25);
        add(locationLabel);

        backButton = new JButton("Back to Home Page");
        backButton.setBounds(460, 30, 300, 30);
        add(backButton);

        cartButton = new JButton("My Cart");
        cartButton.setBounds(40, 150, 340, 160);
        add(cartButton);

        orderButton = new JButton("My Order");
        orderButton.setBounds(420, 150, 340, 160);
        add(orderButton);

        reviewsButton = new JButton("My reviews");
        reviewsButton.setBounds(40, 340, 340, 160);
        add(reviewsButton);

        shopButton = new JButton("Manage My shop");
        shopButton.setBounds(420, 340, 340, 160);
        add(shopButton);

        addressButton = new JButton("Delivery address");
        addressButton.setBounds(40, 530, 340, 160);
        add(addressButton);

        accountButton = new JButton("My account");
        accountButton.setBounds(420, 530, 340, 160);
        add(accountButton);

        backButton.addActionListener(this);
        cartButton.addActionListener(this);
        shopButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource()== backButton){
            homePage home = new homePage();
            home.setVisible(true);
            this.dispose();
        }
        else if(e.getSource()== cartButton){
            ShoppingCartPage chinesebaddie = new ShoppingCartPage();
            chinesebaddie.setVisible(true);
            this.dispose();
        } else if (e.getSource()== shopButton) {
            userShop Mobailegends = new userShop();
            Mobailegends.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args){
        userProfilePage lol = new userProfilePage();
        lol.setVisible(true);
    }

}
