package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.config.KDV;
import com.ozgurokanozdal.entity.*;
import com.ozgurokanozdal.helper.ImageHelper;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIValidate;
import com.ozgurokanozdal.services.ItemService;
import com.ozgurokanozdal.services.KategoriService;
import com.ozgurokanozdal.services.UreticiService;
import com.ozgurokanozdal.services.UrunServis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
    private JButton btn_kaydet;
    private JButton btn_vazgec;
    private JTextField fld_ozelKod;
    private JButton btn_ozelKod_more;
    private JComboBox<Item> cmb_kategoriAna;
    private JComboBox<Item> cmb_ureticiKod;
    private JButton btn_grupKod_more;
    private JButton btn_ureticiKod_more;
    private JComboBox<Item> cmb_odemeTip;
    private JComboBox<Item> cmb_stat;
    private JPanel pnl_kod;
    private JPanel pnl_odeme;
    private JPanel pnl_kdv;



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
    private JPanel pnl_gorsel_image;
    private JPanel pnl_gorsel2_label_wrapper;
    private JPanel pnl_gorsel3_label_wrapper;
    private JPanel pnl_gorsel4_label_wrapper;
    private JButton btn_gorsel2_sil;
    private JButton btn_gorsel3_sil;
    private JButton btn_gorsel4_sil;
    private JButton btn_gorsel2_degistir;
    private JButton btn_gorsel3_degistir;
    private JButton btn_gorsel4_degistir;
    private JLabel lbl_gorsel2;
    private JLabel lbl_gorsel1;
    private JLabel lbl_gorsel3;
    private JLabel lbl_gorsel4;

    private String image1 = "";
    private String image2 = "";
    private String image3 = "";
    private String image4 = "";


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
            setTitle("Ürün Güncelle");
            loadProdInfo(id);
        } else if (logic == 2) {
            setTitle("Ürün İncele");
            loadProdInfo(id);
            setPanelEnabled(wrapper,false);
            setPanelEnabled(pnl_alt,true);
            btn_kaydet.setVisible(false);

        }else{
            setTitle("Ürün Ekle");
        }
        




        // *********** PNL UST START **************


        // *********** PNL UST END   **************

        //************ PNL ALT START **************
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
                            .setBarkodUygulamaTip(0).setBirimKodId(0)
                            .setMinimumStokSeviye(0).setResim1(image1).setResim2(image2).setResim3(image3).setResim4(image4).build();

                    System.out.println(urunToSave.toString());
                    UrunServis.getInstance().create(urunToSave);
                    UIDialog.showMessage("done");
                    dispose();
                    }catch (RuntimeException a){
                        UIDialog.showMessage("error");
                        throw new RuntimeException(a);
                    }
                }




            }
        });

        //************ PNL ALT END **************

        cmb_kategoriAna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadKategoriAltCMB(getComboItemKey(cmb_kategoriAna));
            }
        });


        // GÖRSELLER
        btn_gorsel1_degistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image1 =  showImage(lbl_gorsel1,imageChooser(),"big");
                System.out.println(image1);
            }
        });
        btn_gorsel1_sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteImage(lbl_gorsel1,image1);
                System.out.println(image1);
            }
        });

        btn_gorsel2_degistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image2 = showImage(lbl_gorsel2,imageChooser(),"big");
            }
        });

        btn_gorsel3_degistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               image3 = showImage(lbl_gorsel3,imageChooser(),"big");
            }
        });

        btn_gorsel4_degistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               image4 = showImage(lbl_gorsel4,imageChooser(),"big");
            }
        });
    }

    private void loadProdInfo(long id){
        Urun urun = UrunServis.getInstance().getById(id);
        System.out.println(urun.toString());
        fld_kod.setText(urun.getKod());
        fld_aciklama.setText(urun.getIsim());
        fld_ozelKod.setText(urun.getSpeKod());

        loadKategoriAltCMB(urun.getKategoriAnaId());

        cmb_stat.setSelectedIndex(urun.getDurum());
        findAndSetSelectedItemCMB(urun.getKategoriAnaId(),cmb_kategoriAna);
        findAndSetSelectedItemCMB(urun.getKategoriAltId(),cmb_kategoriAlt);
        findAndSetSelectedItemCMB(urun.getOdemeTipId(),cmb_odemeTip);
        findAndSetSelectedItemCMB(urun.getUreticiKodId(),cmb_ureticiKod);
        findAndSetSelectedItemCMB(urun.getBarkodTipId(),cmb_barkodTip);
        findAndSetSelectedItemCMB(urun.getKdv(),cmb_kdvAlis);

        showImage(urun_fotograf_1,urun.getResim1(),"small");
        showImage(urun_fotograf_2,urun.getResim2(),"small");
        showImage(lbl_gorsel1,urun.getResim1(),"big");
        showImage(lbl_gorsel2,urun.getResim2(),"big");
        showImage(lbl_gorsel3,urun.getResim3(),"big");
        showImage(lbl_gorsel4,urun.getResim4(),"big");




    }




    // LOAD COMBO BOX+++++++++++++++++++++++
    private void findAndSetSelectedItemCMB(int id,JComboBox<Item> comboBox){

        for(int i = 0; i< comboBox.getItemCount(); i++){
            if(comboBox.getItemAt(i).getKey() == id){
                comboBox.setSelectedIndex(i);
                break;
            }
        }

    }
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

        cmb_stat.addItem(new Item(0,"PASIF"));
        cmb_stat.addItem(new Item(1,"AKTIF"));
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
        loadKategoriAltCMB(getComboItemKey(cmb_kategoriAna));
    }

    private int getComboItemKey(JComboBox<Item> comboBox){
        return comboBox.getItemAt(comboBox.getSelectedIndex()).getKey();
    }
    // LOAD COMBO BOX--------------------

    // IMAGE LOAD DELETE ++++++++++++++++
    private String imageChooser(){
        return ImageHelper.imageChooser();
    }
    private String showImage(JLabel lbl,String imagePath,String type){
        try{
            switch (type) {
                case "big" -> lbl.setIcon(new ImageIcon(ImageHelper.resizeImage(imagePath)));
                case "small" -> lbl.setIcon(new ImageIcon(ImageHelper.resizeImage(imagePath, 100, 100)));
            }

        }catch (IOException ex){

        }
        return imagePath;
    }

    private void deleteImage(JLabel lbl,String imagePath){
        lbl.setIcon(null);
        imagePath = "";
    }

    // IMAGE LOAD DELETE ----------------
    private void setPanelEnabled(JPanel panel, Boolean isEnabled) {


        Component[] components = panel.getComponents();

        for (Component component : components) {

            if (component instanceof JPanel) {
                setPanelEnabled((JPanel) component, isEnabled);
            }
            if( component instanceof JTabbedPane){
                Component[] components1 = ((JTabbedPane) component).getComponents();
                for(Component component1 : components1){
                    setPanelEnabled((JPanel) component1, isEnabled);
                }
            }
            if(component instanceof JTextField){
                ((JTextField) component).setEditable(isEnabled);
            }
            if(component instanceof JComboBox){
                component.setEnabled(isEnabled);
            }
            if(component instanceof JButton){
                component.setEnabled(isEnabled);
            }
            if(component instanceof JRadioButton){
                component.setEnabled(isEnabled);
            }

        }
    }

}
