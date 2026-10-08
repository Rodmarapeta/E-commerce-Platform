package e_commerce_platform.templates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userShop extends JFrame implements ActionListener {

    private JPanel usershoppane;
    private JLabel myshop;
    private JButton backbtn, addproduct, removeproduct,
            shopOrders, sales, inventoryReport, manageinventory;

    public userShop() {

        setTitle("USER SHOP");
        setLayout(null);
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        usershoppane = new JPanel();
        usershoppane.setLayout(null);
        usershoppane.setBounds(0,0,800,800);
        add(usershoppane);

        myshop = new JLabel("MY SHOP");
        myshop.setBounds(10, 10, 300, 30);

        backbtn = new JButton("Back");
        backbtn.setBounds(680, 10, 100, 30);

        manageinventory = new JButton("Manage Inventory");
        manageinventory.setBounds(680, 10, 100, 30);

        //styles

        //add gui
        usershoppane.add(myshop);
        usershoppane.add(backbtn);
        usershoppane.add(manageinventory);

        //
        backbtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource()==backbtn){
            
            userProfilePage userpPro = new userProfilePage();
            userpPro.setVisible(true);
            this.dispose();
        }


    }

    public static void main(String[] args) {
        userShop bisaya = new userShop();
        bisaya.setVisible(true);
    }

}
