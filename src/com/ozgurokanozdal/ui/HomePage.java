package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.helper.UIPages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JPanel wrapper;
    private JButton btn_urunList;
    private JButton hareketlerButton;


    public HomePage(){
        add(wrapper);
        setSize(1024,768);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFrame thisFrame = this;



        btn_urunList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.redirectPage(new Urunler(),thisFrame);
            }
        });

        hareketlerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.redirectPage(new Hareketler(),thisFrame);
            }
        });
    }
}
