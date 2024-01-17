package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Cari;
import com.ozgurokanozdal.dto.Item;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.CariServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    private DefaultTableModel mdl_hareket_detay;
    private Object[] hareketDetay_row_list;



    public HareketDetayGUI(int logic, long id){
        add(wrapper);
        setSize(720,1024);
        setTitle("Hareket Detay");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        // hareket detay tablo +++++++++++++++++++++
        mdl_hareket_detay = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        Object[] columns_hareketDetay = {"ID","ÜRÜN","MİKTAR","BİRİM","BİRİM FİYAT","TUTAR"};
        mdl_hareket_detay.setColumnIdentifiers(columns_hareketDetay);

        hareketDetay_row_list = new Object[columns_hareketDetay.length];
        tbl_hareketler.setModel(mdl_hareket_detay);
        tbl_hareketler.getTableHeader().setReorderingAllowed(false);
        tbl_hareketler.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_hareketler.getColumnModel().getColumn(0).setWidth(0);
        tbl_hareketler.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_hareketler.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_hareketler.setAutoCreateRowSorter(true);
        // hareket detay tablo ---------------------







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
