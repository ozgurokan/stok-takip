package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.helper.UIPages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JPanel wrapper;
    private JButton btn_urunList;
    private JButton btn_hareketler;
    private JButton btn_cariHesaplar;


    public HomePage(){
        add(wrapper);
        setSize(360,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFrame thisFrame = this;
        UIPages.centeredFrame(thisFrame);



        btn_urunList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.redirectPage(new UrunlerGUI(),thisFrame);
            }
        });

        btn_hareketler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.redirectPage(new HareketlerGUI(),thisFrame);
            }
        });

        btn_cariHesaplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.redirectPage(new CariHesaplarGUI(),thisFrame);
            }
        });

    }
}
