package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.dto.TableHareket;
import com.ozgurokanozdal.entity.Hareket;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.helper.UIValidate;
import com.ozgurokanozdal.services.HareketServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HareketlerGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bt;
    private JScrollPane scrl_hareket;
    private JTable tbl_hareketler;
    private JButton btn_add;
    private JButton btn_inspect;

    private DefaultTableModel hareket_tbl_model;
    private Object[] hareketler_tbl_rows;

    private Long selected;

    public HareketlerGUI(){
        add(wrapper);
        setTitle("Hareketler");
        setSize(720,960);

        JFrame thisFrame = this;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        // HAREKET TABLOSU AYARLAR VE LISTENER +++++++++++++++
        hareket_tbl_model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] hareket_tbl_cols = {"ID","CARI HESAP", "TARIH", "SAAT","TÜR", "TUTAR"};
        hareket_tbl_model.setColumnIdentifiers(hareket_tbl_cols);
        hareketler_tbl_rows = new Object[hareket_tbl_cols.length];
        loadHareket();


        tbl_hareketler.setModel(hareket_tbl_model);
        tbl_hareketler.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_hareketler.getColumnModel().getColumn(0).setWidth(0);
        tbl_hareketler.getColumnModel().getColumn(0).setMaxWidth(0);



        tbl_hareketler.getTableHeader().setReorderingAllowed(false);


        tbl_hareketler.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_hareketler.setAutoCreateRowSorter(true);
        tbl_hareketler.getSelectionModel().addListSelectionListener(e ->{
            try{
                selected = (long) tbl_hareketler.getValueAt(tbl_hareketler.getSelectedRow(),0);

            }catch (RuntimeException r1 ){

            }
        });

        // HAREKET TABLOSU AYARLAR VE LISTENER ---------------------


        // PNL BT ++++++++++++++++++++++++++++++++++++++++++
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new HareketDetayGUI(0,0),thisFrame);
            }
        });
        btn_inspect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(selected == null){
                    UIDialog.showMessage("Lütfen bir hareket seçin!");
                }else{
                    UIPages.newWindow(new HareketDetayGUI(2,selected),thisFrame);
                }


            }
        });

        // PNL BT ------------------------------------------




    }

    private void loadHareket(){

        //{"ID","CARI NO", "TARIH", "SAAT","TÜR", "TUTAR"};
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hareketler.getModel();
        clearModel.setRowCount(0);
        int i;
        for(TableHareket hareket : HareketServis.getInstance().getAllForTable()){
            i=0;
            hareketler_tbl_rows[i++] = hareket.getId();
            hareketler_tbl_rows[i++] = hareket.getCariAd();
            hareketler_tbl_rows[i++] = hareket.getTarih();
            hareketler_tbl_rows[i++] = hareket.getSaat();
            hareketler_tbl_rows[i++] = hareket.getTur();
            hareketler_tbl_rows[i++] = hareket.getTutar();


            hareket_tbl_model.addRow(hareketler_tbl_rows);
        }

    }
}
