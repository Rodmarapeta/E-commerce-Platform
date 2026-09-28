package e_commerce_platform.templates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class userProfilePage extends JFrame implements ActionListener{

    private JPanel contentPane;
//    private Jlabel dispalyUserid;
    private userProfilePage(){
        setTitle("registerPage");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,1000);
        setVisible(true);

//        dispalyUserid = usernameid;

    }

    @Override
    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args){
        userProfilePage lol = new userProfilePage();
        lol.setVisible(true);
    }

}
