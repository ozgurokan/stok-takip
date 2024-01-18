package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Cari;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIValidate;
import com.ozgurokanozdal.services.CariServis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CariDetayGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bot;
    private JPanel pnl_mid_detay;
    private JPanel pnl_mid_adres;
    private JTextField fld_kod;
    private JTextField fld_telNo;
    private JTextPane fld_adres;
    private JButton btn_ekle;
    private JTextField fld_isim;
    private JTextField fld_vergiNo;
    private JTextField fld_verigiDairesi;
    private JButton btn_duzenle;


    public CariDetayGUI(int logic){
        add(wrapper);
        setSize(520,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JFrame thisFrame = this;

        if(logic == 1){
            setTitle("Cari Kayıt Düzenle");
            btn_ekle.setVisible(false);
        } else if (logic == 2 ) {
            setTitle("Cari Kayıt İncele");
            btn_ekle.setVisible(false);
            btn_duzenle.setVisible(false);
        }else{
            setTitle("Cari Kayıt Ekle");
            btn_duzenle.setVisible(false);
        }


        btn_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(UIValidate.isFieldEmpty(fld_kod) || UIValidate.isFieldEmpty(fld_isim) || UIValidate.isFieldEmpty(fld_vergiNo)){
                    UIDialog.showMessage("Zorunlu alanları doldurun!");
                }else{
                    Cari cari = new Cari();
                    cari.setKod(fld_kod.getText());
                    cari.setIsim(fld_isim.getText());
                    cari.setVergiNo(fld_vergiNo.getText());
                    cari.setVergiDairesi(fld_verigiDairesi.getText());
                    cari.setAdres(fld_adres.getText());
                    cari.setTel_no(fld_telNo.getText());
                    if(CariServis.getInstance().add(cari)){
                        UIDialog.showMessage("Ekleme Başarılı");
                        thisFrame.setVisible(false);
                        thisFrame.dispose();
                    }else{
                        UIDialog.showMessage("Bir hata oluştu!");
                    }
                }



            }
        });


    }
}
