package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.dto.TabloUrun;
import com.ozgurokanozdal.dto.Item;
import com.ozgurokanozdal.entity.KategoriAna;
import com.ozgurokanozdal.helper.TableHelper;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.KategoriServis;
import com.ozgurokanozdal.services.UrunServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class UrunlerGUI extends JFrame {
    private JPanel wrapper;
    private JButton ekle_btn;
    private JButton duzenle_btn;
    private JButton incele_btn;
    private JPanel urunler_kontrol_pnl;
    private JScrollPane urunler_scrll;
    private JTable urunler_table;
    private JComboBox<Item<Integer,String>> cmb_filterAPH;
    private JComboBox<Item<Integer,String>> cmb_filterKategori;
    private JButton btn_select;

    private DefaultTableModel urunler_tbl_mdl;
    private Object[] urunler_row_list;

    private Long urun_selected;


    // LOGIC - 0 -> normal
    // LOGIC - 1 -> hareket detaydan ürün seçme.
    public UrunlerGUI(int logic){
        add(wrapper);
        setSize(1200,720);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFrame thisFrame = this;

        initComboBoxes();

        // *************************************************************************
        // URUN TABLE START
        urunler_tbl_mdl = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] columns_urunler = {"ID","DURUM","KOD","STOK ADI", "ANA KATEGORİ","ALT KATEGORİ", "URETICI", "KDV","STOK"};
        urunler_tbl_mdl.setColumnIdentifiers(columns_urunler);
        urunler_row_list = new Object[columns_urunler.length];

        urunler_table.setModel(urunler_tbl_mdl);

        // urunler table ayarları
        TableHelper.tableConfigIdAndReorderAndSelection(urunler_table,0,true,false,true);
        urunler_table.setAutoCreateRowSorter(true);

        urunler_table.getSelectionModel().addListSelectionListener(e ->{
            try{
                urun_selected = (long) urunler_table.getValueAt(urunler_table.getSelectedRow(),0);

            }catch (RuntimeException r1 ){

            }
        });


        // kullanım amacı için
        if(logic == 0){
            setTitle("Urunler");
            btn_select.setVisible(false);
        } else if(logic == 1){
            prepareToSelectMod();
        }

        // URUN TABLE END
        loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));





        // EVENT LİSTENERS START-
        // ++++ KONTROL BUTTONLARI
        ekle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UIPages.newWindow(new UrunKontrolGUI(0,0),thisFrame).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                    }
                });
            }
        });

        incele_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(urun_selected == null){
                    UIDialog.showMessage("Lütfen bir ürün seçin!");
                }else{
                    UIPages.newWindow(new UrunKontrolGUI(2,urun_selected),thisFrame).addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                        }
                    });
                }

            }
        });


        duzenle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(urun_selected == null){
                    UIDialog.showMessage("Lütfen bir ürün seçin!");
                }else{
                    UIPages.newWindow(new UrunKontrolGUI(1,urun_selected),thisFrame).addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                        }
                    });
                }

            }
        });
        // ---- KONTROL BUTTONLARI

        cmb_filterAPH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                }catch (Exception e1){
                    UIDialog.showMessage("error");
                    System.out.println(e1.getMessage());
                }

            }
        });

        cmb_filterKategori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                }catch (Exception e2){
                    UIDialog.showMessage("error");
                    System.out.println(e2.getMessage());
                }

            }
        });


        // EVENT LİSTENERS END-
    }

    private void loadUrunList(int filterDurum,int filterKategoriAna){

        // "ID","DURUM","KOD","STOK ADI", "ANA KATEGORİ","ALT KATEGORİ", "URETICI", "KDV", "STOK"
        DefaultTableModel clearModel = (DefaultTableModel) urunler_table.getModel();
        clearModel.setRowCount(0);
        int i;
        for(TabloUrun urun : UrunServis.getInstance().getAllForTable(filterDurum,filterKategoriAna)){
            i=0;
            urunler_row_list[i++] = urun.getId();
            urunler_row_list[i++] = urun.getDurum();
            urunler_row_list[i++] = urun.getKod();
            urunler_row_list[i++] = urun.getIsim();
            urunler_row_list[i++] = urun.getKategoriAna();
            urunler_row_list[i++] = urun.getKategoriAlt();
            urunler_row_list[i++] = urun.getUreticiIsim();
            urunler_row_list[i++] = urun.getKdv();
            urunler_row_list[i++] = urun.getStok_bilgi();



            urunler_tbl_mdl.addRow(urunler_row_list);
        }

    }

    private void loadFilterDurumCMB(){

        cmb_filterAPH.addItem(new Item<Integer,String>(-2,"HEPSI"));
        cmb_filterAPH.addItem(new Item<Integer,String>(0,"P"));
        cmb_filterAPH.addItem(new Item<Integer,String>(1,"A"));

    }

    private void loadFilterKategoriCMB(){
        cmb_filterKategori.addItem(new Item<Integer,String>(-2,"HEPSI"));
        for(KategoriAna kategoriAna : KategoriServis.getInstance().getAllKategoriAna()){
            cmb_filterKategori.addItem(new Item<Integer,String>(kategoriAna.getId(), kategoriAna.getName()));
        }
    }

    private void initComboBoxes(){
        loadFilterDurumCMB();
        loadFilterKategoriCMB();
    }

    private int getComboItemKey(JComboBox<Item<Integer,String>> comboBox){
        return comboBox.getItemAt(comboBox.getSelectedIndex()).getKey();
    }


    // Hareket detay sayfasından gelindiğinde yapılacak ayarlamalar.
    private void prepareToSelectMod(){
        setTitle("Ürün Seç");
        incele_btn.setVisible(false);
        duzenle_btn.setVisible(false);
        ekle_btn.setVisible(false);
        cmb_filterAPH.setVisible(false);
        // pasif durumdaki ürünlerin görünmemesi lazım.
        loadUrunList(getComboItemKey(cmb_filterKategori),1);
    }


}

