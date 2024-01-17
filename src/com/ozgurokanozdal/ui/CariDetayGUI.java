package com.ozgurokanozdal.ui;

import javax.swing.*;

public class CariDetayGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bot;
    private JPanel pnl_mid_detay;
    private JPanel pnl_mid_adres;
    private JTextField textField1;
    private JTextField textField2;
    private JTextPane textPane1;
    private JButton ekleButton;


    public CariDetayGUI(){
        add(wrapper);
        setSize(360,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}
