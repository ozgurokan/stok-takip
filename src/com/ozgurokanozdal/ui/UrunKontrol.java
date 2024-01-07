package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.config.KDV;
import com.ozgurokanozdal.entity.*;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIValidate;
import com.ozgurokanozdal.services.ItemService;
import com.ozgurokanozdal.services.KategoriService;
import com.ozgurokanozdal.services.UreticiService;
import com.ozgurokanozdal.services.UrunServis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UrunKontrol extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_pnl;
    private JPanel pnl_genel;
    private JPanel pnl_birim;
    private JPanel pnl_ust;
    private JPanel pnl_alt;
    private JPanel pnl_ozellik;
    private JTextField fld_kod;
    private JTextField fld_aciklama;
    private JButton btn_kod_more;
    private JButton button2;
    private JButton button3;
    private JButton btn_kaydet;
    private JButton btn_vazgec;
    private JTextField fld_ozelKod;
    private JButton btn_ozelKod_more;
    private JTextField fld_yetkiKod;
    private JComboBox<Item> cmb_kategoriAna;
    private JComboBox<Item> cmb_ureticiKod;
    private JButton btn_grupKod_more;
    private JButton btn_ureticiKod_more;
    private JComboBox<Item> cmb_odemeTip;
    private JComboBox<Item> cmb_stat;
    private JPanel pnl_kod;
    private JPanel pnl_odeme;
    private JPanel pnl_kdv;
    private JTextField fld_rafOmru;
    private JTextField fld_kdv_alis;
    private JTextField fld_kdv_satis;
    private JTextField fld_kdv_iade;
    private JPanel pnl_ust_sol;
    private JPanel pnl_ust_sag;
    private JPanel pnl_fotograf_1;
    private JPanel pnl_fotograf_2;
    private JPanel pnl_barkod;
    private JLabel urun_fotograf_2;
    private JLabel urun_fotograf_1;
    private JButton btn_aciklama_more;
    private JComboBox<Item> cmb_barkodTip;
    private JComboBox<Item> cmb_kdvAlis;
    private JComboBox<Item> cmb_kdvSatis;
    private JComboBox<Item> cmb_kdvIade;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JComboBox<Item> cmb_kategoriAlt;
    private JButton btn_gorsel1_sil;
    private JButton btn_gorsel1_degistir;
    private JPanel pnl_gorsel1;
    private JPanel pnl_gorseller;
    private JPanel pnl_gorsel2;
    private JPanel pnl_gorsel3;
    private JPanel pnl_gorsel4;
    private JPanel pnl_gorsel1_label_wrapper;
    private JPanel pnl_gorsel2_label_wrapper;
    private JPanel pnl_gorsel3_label_wrapper;
    private JPanel pnl_gorsel4_label_wrapper;
    private JButton btn_gorsel2_sil;
    private JButton btn_gorsel3_sil;
    private JButton btn_gorsel4_sil;
    private JButton btn_gorsel2_degistir;
    private JButton btn_gorsel3_degistir;
    private JButton btn_gorsel4_degistir;


    // ++++++ ile başlayan yorum satırları alt sınırların başlangıcı
    // ------- ile başlayanlar alt sınırların bitişi

    // 0 --> save new prod
    // 1 --> inspect prod
    // 2 --> update prod
    public UrunKontrol(int logic, long id){


        add(wrapper);
        setSize(900,600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        initComboBoxes();
        cmb_odemeTip.setSelectedIndex(2);
        if(logic == 1){
            getProdInfo(id);
            setTitle("Ürün Güncelle");
        } else if (logic == 2) {
            getProdInfo(id);
            disableAllFields();
            setTitle("Ürün İncele");
        }else{
            setTitle("Ürün Ekle");
        }
        




        // *********** PNL UST START **************
        //++++++++++ kod_more event listener

        //---------- kod_more event listener

        // *********** PNL UST END   **************


        btn_kaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(UIValidate.isFieldEmpty(fld_aciklama) || UIValidate.isFieldEmpty(fld_kod)){
                    UIDialog.showMessage("Lütfen zorunlu alanları doldurun.");
                }else{
                    try{

                    Urun urunToSave = new Urun.UrunBuilder(getComboItemKey(cmb_stat),fld_kod.getText(),fld_aciklama.getText())
                            .setKategoriAnaId(getComboItemKey(cmb_kategoriAna)).setKategoriAltId(getComboItemKey(cmb_kategoriAlt))
                            .setUreticiKodId(getComboItemKey(cmb_ureticiKod))
                            .setSpeKod(fld_ozelKod.getText()).setKdv(getComboItemKey(cmb_kdvAlis)).setOdemeTipId(getComboItemKey(cmb_odemeTip))
                            .setBarkodTipId(getComboItemKey(cmb_barkodTip))
                            .setBarkodUygulamaTip(0).setBarkodAnaOnEk("Burası boş dön").setBirimKodId(0)
                            .setMinimumStokSeviye(0).build();
                    UrunServis.getInstance().create(urunToSave);
                    UIDialog.showMessage("done");
                    }catch (RuntimeException a){
                        UIDialog.showMessage("error");
                    }
                }




            }
        });

        cmb_kategoriAna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadKategoriAltCMB(getComboItemKey(cmb_kategoriAna));
            }
        });
    }

    private void getProdInfo(long id){
        Urun urun = UrunServis.getInstance().getById(id);
        // fill fields

    }

    private void disableAllFields(){


    }


    // LOAD COMBO BOX+++++++++++++++++++++++
    private void loadOdemeTipCMB(){
        cmb_odemeTip.removeAllItems();
        for(OdemeTip odemeTip : ItemService.getInstance().getAllOdemeTip()){
            cmb_odemeTip.addItem(new Item(odemeTip.getId(), odemeTip.getName()));
        }
        cmb_odemeTip.setSelectedItem(2);
    }

    private void loadKDVCMB(){

        for(Item item : KDV.kdvList()){
            cmb_kdvAlis.addItem(item);
            cmb_kdvSatis.addItem(item);
            cmb_kdvIade.addItem(item);
        }

    }

    private void loadStatuCMB(){
        cmb_stat.addItem(new Item(1,"AKTIF"));
        cmb_stat.addItem(new Item(0,"PASIF"));

    }

    private void loadBarkodTipCMB(){
        cmb_barkodTip.removeAllItems();

        for(BarkodTip barkodTip : ItemService.getInstance().getAllBarkodTip()){
            cmb_barkodTip.addItem(new Item(barkodTip.getId(), barkodTip.getName()));
        }
    }
    private void loadKategoriAnaCMB(){
        cmb_kategoriAna.removeAllItems();

        for(KategoriAna kategoriAna : KategoriService.getInstance().getAllKategoriAna()){
            cmb_kategoriAna.addItem(new Item(kategoriAna.getId(), kategoriAna.getName()));
        }
    }

    private void loadKategoriAltCMB(int parentId){
        cmb_kategoriAlt.removeAllItems();
        for(KategoriAlt kategoriAlt : KategoriService.getInstance().getAllKategoriAltByParentId(parentId)){
            cmb_kategoriAlt.addItem(new Item(kategoriAlt.getId(), kategoriAlt.getName()));
        }
    }
    private void loadUreticiCMB(){
        cmb_ureticiKod.removeAllItems();
        for(Uretici uretici : UreticiService.getInstance().getAllUretici()){
            cmb_ureticiKod.addItem(new Item(uretici.getId(), uretici.getName()));
        }
    }

    private void initComboBoxes(){
        loadOdemeTipCMB();
        loadBarkodTipCMB();
        loadKategoriAnaCMB();
        loadUreticiCMB();
        loadKDVCMB();
        loadStatuCMB();
    }

    private int getComboItemKey(JComboBox<Item> comboBox){
        return comboBox.getItemAt(comboBox.getSelectedIndex()).getKey();
    }
    // LOAD COMBO BOX--------------------


}
