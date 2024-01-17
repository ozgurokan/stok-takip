package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.helper.UIPages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CariHesaplarGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_ekle;
    private JButton btn_incele;
    private JButton btn_degistir;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bot;
    private JTable table1;

    public CariHesaplarGUI(){
        add(wrapper);
        setSize(360,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Cari Hesaplar");
        JFrame thisFrame = this;









        btn_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new CariDetayGUI(),thisFrame);
            }
        });
    }
}
