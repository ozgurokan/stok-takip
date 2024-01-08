package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.dto.TableItemUrun;
import com.ozgurokanozdal.entity.Item;
import com.ozgurokanozdal.entity.KategoriAna;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.KategoriService;
import com.ozgurokanozdal.services.UrunServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Urunler extends JFrame {
    private JPanel wrapper;
    private JButton ekle_btn;
    private JButton duzenle_btn;
    private JButton incele_btn;
    private JPanel urunler_kontrol_pnl;
    private JScrollPane urunler_scrll;
    private JTable urunler_table;
    private JComboBox<Item> cmb_filterAPH;
    private JComboBox<Item> cmb_filterKategori;

    private DefaultTableModel urunler_tbl_mdl;
    private Object[] urunler_row_list;

    private long urun_selected;

    public Urunler(){
        add(wrapper);
        setSize(1200,720);
        setTitle("Urunler");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        JFrame thisFrame = this;
        initComboBoxes();

        System.out.println(this.hashCode());
        System.out.println(thisFrame.hashCode());

        // *************************************************************************
        // URUN TABLE START
        urunler_tbl_mdl = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] columns_urunler = {"ID","DURUM","KOD","STOK ADI", "ANA KATEGORİ","ALT KATEGORİ", "URETICI", "KDV"};
        urunler_tbl_mdl.setColumnIdentifiers(columns_urunler);
        urunler_row_list = new Object[columns_urunler.length];


        urunler_table.setModel(urunler_tbl_mdl);

        // urunler table ayarları
        urunler_table.getTableHeader().setReorderingAllowed(false);
        urunler_table.getColumnModel().getColumn(0).setMinWidth(0);
        urunler_table.getColumnModel().getColumn(0).setWidth(0);
        urunler_table.getColumnModel().getColumn(0).setMaxWidth(0);

        urunler_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        urunler_table.setAutoCreateRowSorter(true);
        urunler_table.getSelectionModel().addListSelectionListener(e ->{
            try{
                urun_selected = (long) urunler_table.getValueAt(urunler_table.getSelectedRow(),0);
            }catch (RuntimeException r1 ){

            }



        });

        // URUN TABLE END
        loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));





        // EVENT LİSTENERS START-
        // ++++ KONTROL BUTTONLARI
        ekle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UIPages.newWindow(new UrunKontrol(0,0),thisFrame).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                    }

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
                UIPages.newWindow(new UrunKontrol(2,urun_selected),thisFrame);
            }
        });


        duzenle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new UrunKontrol(1,urun_selected),thisFrame).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadUrunList(getComboItemKey(cmb_filterAPH),getComboItemKey(cmb_filterKategori));
                    }
                });

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

    public void loadUrunList(int filterDurum,int filterKategoriAna){

        // "ID","DURUM","KOD","STOK ADI", "ANA KATEGORİ","ALT KATEGORİ", "URETICI", "KDV"
        DefaultTableModel clearModel = (DefaultTableModel) urunler_table.getModel();
        clearModel.setRowCount(0);
        int i;
        for(TableItemUrun urun : UrunServis.getInstance().getAllForTable(filterDurum,filterKategoriAna)){
            i=0;
            urunler_row_list[i++] = urun.getId();
            urunler_row_list[i++] = urun.getDurum();
            urunler_row_list[i++] = urun.getKod();
            urunler_row_list[i++] = urun.getIsim();
            urunler_row_list[i++] = urun.getKategoriAna();
            urunler_row_list[i++] = urun.getKategoriAlt();
            urunler_row_list[i++] = urun.getUreticiIsim();
            urunler_row_list[i++] = urun.getKdv();



            urunler_tbl_mdl.addRow(urunler_row_list);
        }

    }

    private void loadFilterDurumCMB(){

        cmb_filterAPH.addItem(new Item(-2,"HEPSI"));
        cmb_filterAPH.addItem(new Item(0,"P"));
        cmb_filterAPH.addItem(new Item(1,"A"));

    }

    private void loadFilterKategoriCMB(){
        cmb_filterKategori.addItem(new Item(-2,"HEPSI"));
        for(KategoriAna kategoriAna : KategoriService.getInstance().getAllKategoriAna()){
            cmb_filterKategori.addItem(new Item(kategoriAna.getId(), kategoriAna.getName()));
        }
    }

    private void initComboBoxes(){
        loadFilterDurumCMB();
        loadFilterKategoriCMB();
    }

    private int getComboItemKey(JComboBox<Item> comboBox){
        return comboBox.getItemAt(comboBox.getSelectedIndex()).getKey();
    }

}

