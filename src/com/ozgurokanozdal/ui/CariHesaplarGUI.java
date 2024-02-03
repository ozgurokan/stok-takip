package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Cari;
import com.ozgurokanozdal.helper.TableHelper;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.CariServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    private long selectedId;
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

        TableHelper.tableConfigIdAndReorderAndSelection(tbl_cariHesaplar,0,true,false,true);
        tbl_cariHesaplar.setAutoCreateRowSorter(true);

        tbl_cariHesaplar.getSelectionModel().addListSelectionListener(e -> {

            try{
                selectedId = (long) tbl_cariHesaplar.getValueAt(tbl_cariHesaplar.getSelectedRow(),0);
            }catch (RuntimeException r){

            }


        });

        loadCariTBL();

        btn_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new CariDetayGUI(0,0),thisFrame).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        loadCariTBL();
                    }
                });
            }
        });

        btn_degistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new CariDetayGUI(1, selectedId),thisFrame).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        loadCariTBL();
                    }
                });
            }
        });

        btn_incele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new CariDetayGUI(2, selectedId),thisFrame);
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
