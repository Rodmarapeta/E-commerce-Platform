package e_commerce_platform.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import e_commerce_platform.UserSession;
import e_commerce_platform.ui.loginPage;

public class userProfilePage extends JFrame implements ActionListener{

    private JLabel nameLabel, emailLabel;
    private JButton backButton, cartButton, orderButton, reviewsButton, shopButton, addressButton, accountButton, logoutButton;

    public userProfilePage() {

        setTitle("Profile Page");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        nameLabel = new JLabel("Name: " + UserSession.getUsername());
        nameLabel.setBounds(55, 42, 470, 25);
        add(nameLabel);

        emailLabel = new JLabel("Email: " + UserSession.getEmail());
        emailLabel.setBounds(55, 70, 470, 25);
        add(emailLabel);

        backButton = new JButton("Back to Home Page");
        backButton.setBounds(460, 30, 300, 30);
        add(backButton);
        
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(460, 70, 300,30);
        add(logoutButton);

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
        orderButton.addActionListener(this);
        reviewsButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource()== backButton){
            homePage home = new homePage();
            home.setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == cartButton) {
            JOptionPane.showMessageDialog (this, "Please open the cart from the Home Page.");
        } else if (e.getSource()== shopButton) {
            userShop Mobailegends = new userShop();
            Mobailegends.setVisible(true);
            this.dispose();
        } else if (e.getSource() == logoutButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                UserSession.clearSession();
                
                loginPage login =  new loginPage();
                login.setVisible(true);
                this.dispose();
            }
        }
        else if (e.getSource() == orderButton) {
            MyOrderPage order = new MyOrderPage();
            order.setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == reviewsButton) {
            MyReviewsPage reviews = new MyReviewsPage();
            reviews.setVisible(true);
            this.dispose();

    }
        
    }

}
