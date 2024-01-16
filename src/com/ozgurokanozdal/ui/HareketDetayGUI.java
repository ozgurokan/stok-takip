package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Cari;
import com.ozgurokanozdal.dto.Item;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.CariServis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HareketDetayGUI extends JFrame {


    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bt;
    private JTable tbl_hareketler;
    private JPanel pnl_genel;
    private JPanel pnl_adres;
    private JTextPane fld_adres;
    private JTextField fld_tarih;
    private JTextField fld_telNo;
    private JScrollPane scrll_hareket;
    private JButton btn_ekle;
    private JLabel lbl_tutar;
    private JLabel lbl_tutar_hesaplanmis;
    private JComboBox<Item<Long,String>> cmb_cari;
    private JButton btn_cari_kayit;


    public HareketDetayGUI(int logic, long id){
        add(wrapper);
        setSize(720,1024);
        setTitle("Hareket Detay");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        JFrame thisFrame = this;
        if(logic == 1){
            setTitle("Hareket Değiştir");
            loadHareketInfo(id);
        } else if (logic == 2) {
            setTitle("Hareket Detay");
            loadHareketInfo(id);
            UIPages.disableAllFields(wrapper,false);

        }else{
            setTitle("Hareket Ekle");
            initComboBoxes();
        }








        btn_cari_kayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new CariDetayGUI(),thisFrame);
            }
        });



    }

    private void loadHareketInfo(long id){




    }

    private void loadCariCMB(){
        for(Cari cari : CariServis.getInstance().getAll()){
            cmb_cari.addItem(new Item<>(cari.getId(), cari.getIsim()));
        }
    }

    private void initComboBoxes(){
        loadCariCMB();
    }


}
