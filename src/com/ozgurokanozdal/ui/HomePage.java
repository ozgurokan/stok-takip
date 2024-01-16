package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.helper.UIPages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JPanel wrapper;
    private JButton btn_urunList;
    private JButton hareketlerButton;
    private JButton cariHesaplarButton;


    public HomePage(){
        add(wrapper);
        setSize(360,480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFrame thisFrame = this;



        btn_urunList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.redirectPage(new UrunlerGUI(),thisFrame);
            }
        });

        hareketlerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.redirectPage(new HareketlerGUI(),thisFrame);
            }
        });
    }
}
