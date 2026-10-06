package e_commerce_platform.templates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class userProfilePage extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JButton backbtn;

    public userProfilePage(){

        setTitle("USER PROFILE");
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);

        contentPane = new JPanel();
        contentPane.setBounds(0,0,800,800);
        contentPane.setLayout(null);
        add(contentPane);

        backbtn = new JButton("Back");
        backbtn.setBounds(680, 10, 100, 30);

        //styles


        //add to gui
        contentPane.add(backbtn);

        //add to actionlistener
        backbtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource()==backbtn){

            homePage home = new homePage();
            home.setVisible(true);
            this.dispose();
        }
        else if(e.getSource()==backbtn){

        }
    }

    public static void main(String[] args){
        userProfilePage lol = new userProfilePage();
        lol.setVisible(true);
    }

}
