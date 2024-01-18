package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Cari;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.CariServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTable tbl_cariHesaplar;
    private JScrollPane scrl_cariHesaplar;


    DefaultTableModel mdl_cariHesaplar;
    Object[] row_list_cariHesaplar;

    private Integer id;
    public CariHesaplarGUI(){
        add(wrapper);
        setSize(960,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Cari Hesaplar");
        JFrame thisFrame = this;


        mdl_cariHesaplar = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] columns_cariHesaplar = {"ID","KOD","İSİM","VERGİ NO.","VERGİ DAİRESİ", "ADRES","İLETİŞİM NO"};
        mdl_cariHesaplar.setColumnIdentifiers(columns_cariHesaplar);
        tbl_cariHesaplar.setModel(mdl_cariHesaplar);

        row_list_cariHesaplar = new Object[columns_cariHesaplar.length];

        tbl_cariHesaplar.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_cariHesaplar.getColumnModel().getColumn(0).setWidth(0);
        tbl_cariHesaplar.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_cariHesaplar.setSelectionMode(0);
        tbl_cariHesaplar.setAutoCreateRowSorter(true);

        loadCariTBL();
        tbl_cariHesaplar.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        btn_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new CariDetayGUI(0),thisFrame);
            }
        });
    }


    public void loadCariTBL(){
        DefaultTableModel clearModel =(DefaultTableModel) tbl_cariHesaplar.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Cari cari : CariServis.getInstance().getAll(false)){
            i=0;
            row_list_cariHesaplar[i++] = cari.getId();
            row_list_cariHesaplar[i++] = cari.getKod();
            row_list_cariHesaplar[i++] = cari.getIsim();
            row_list_cariHesaplar[i++] = cari.getVergiNo();
            row_list_cariHesaplar[i++] = cari.getVergiDairesi();
            row_list_cariHesaplar[i++] = cari.getAdres();
            row_list_cariHesaplar[i++] = cari.getTel_no();

            mdl_cariHesaplar.addRow(row_list_cariHesaplar);

        }
    }
}
