package e_commerce_platform.templates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class homePage extends JFrame implements ActionListener{

    //jpanels
    JPanel topnavbar, productdiplaypanel;

    //1row
    private JButton supportbtn,trackorderbtn;
    private JLabel frstlbl, scnd, trhd, label;

    //2row
    private JLabel logo,frthlbl, sthlbl;
    private String[] allCat = {"electronic","kitchenAndliving","fashionAndapparel","homeAppliances","personalCare","groceriesAndsupermarket","hardware","hobbies",
            "sportAndoutdoor"};
    private JButton searchbtn, myaccountbtn, backbtn, userProfilebtn;
    private JTextField sreachproduct;
    private JPanel uppane, midpane, lowpane;

    public homePage(){
        setLayout(null);
        setSize(1920,1080);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //topnavbar
        topnavbar = new JPanel();
        topnavbar.setLayout(null);
        topnavbar.setBounds(0,0,1920,1080);
        
        frstlbl=new JLabel("Free Shipping order over 500");
        scnd=new JLabel("30 days easy return");
        trhd=new JLabel("warranty");
        supportbtn = new JButton("Support");
        trackorderbtn = new JButton("Track Order");

        //2row
        logo=new JLabel("Store\uD83D\uDCE6");
        backbtn = new JButton("Back");
        JComboBox<String> comboBox = new JComboBox<>(allCat);
        JLabel label = new JLabel("Selected: " + comboBox.getSelectedItem());
        comboBox.setEditable(false);

        //1row
        frstlbl.setBounds(10,5,200,30);
        scnd.setBounds(347,5,200,30);
        trhd.setBounds(694,5,200,30);
        supportbtn.setBounds(1218,5,150,30);
        trackorderbtn.setBounds(1378,5,150,30);

        //2row
        logo.setBounds(10,40,200,30);
        label.setBounds(210,40,200,30);


        //styles
        supportbtn.setContentAreaFilled(false);
        supportbtn.setOpaque(false);
        supportbtn.setBorderPainted(true);
        supportbtn.setFocusPainted(false);

        trackorderbtn.setContentAreaFilled(false);
        trackorderbtn.setOpaque(false);
        trackorderbtn.setBorderPainted(true);
        trackorderbtn.setFocusPainted(false);

        backbtn.setContentAreaFilled(false);
        backbtn.setOpaque(false);
        backbtn.setBorderPainted(true);
        backbtn.setFocusPainted(false);

        //addtogui

        add(topnavbar);

        //1row
        topnavbar.add(frstlbl);
        topnavbar.add(scnd);
        topnavbar.add(trhd);
        topnavbar.add(supportbtn);
        topnavbar.add(trackorderbtn);

        //2row
        topnavbar.add(backbtn);
        topnavbar.add(logo);
        topnavbar.add(label);
        topnavbar.add(comboBox);

        supportbtn.addActionListener(this);
        trackorderbtn.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e){


    }

    public static void main(String[] args){
        homePage homePage = new homePage();
        homePage.setVisible(true);
    }
}
