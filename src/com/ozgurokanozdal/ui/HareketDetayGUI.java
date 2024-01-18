package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Cari;
import com.ozgurokanozdal.dto.Item;
import com.ozgurokanozdal.entity.HareketDetay;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.CariServis;
import com.ozgurokanozdal.services.HareketDetayServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;

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
    private JLabel lbl_cariKod;
    private JLabel lbl_telNo;
    private JTextArea lbl_adres1;

    private DefaultTableModel mdl_hareket_detay;
    private Object[] hareketDetay_row_list;



    public HareketDetayGUI(int logic, long id){
        add(wrapper);
        setSize(720,1024);
        setTitle("Hareket Detay");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JFrame thisFrame = this;


        // hareket detay tablo +++++++++++++++++++++
        mdl_hareket_detay = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 5;
            }

        };

        Object[] columns_hareketDetay = {"ID","ÜRÜN","MİKTAR","BİRİM","BİRİM FİYAT","TUTAR","DENEME"};
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

        
        if(logic == 1){
            setTitle("Hareket Değiştir");
            loadHareketTBL(id);
            loadHareketInfo(id);
        } else if (logic == 2) {
            setTitle("Hareket Detay");
            loadHareketInfo(id);
            loadHareketTBL(1);
            initComboBoxes();
            UIPages.disableAllFields(wrapper,false);

        }else{
            setTitle("Hareket Ekle");
            initComboBoxes();
        }








        btn_cari_kayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPages.newWindow(new CariDetayGUI(0),thisFrame).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadCariCMB();
                    }
                });
            }
        });


        cmb_cari.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cmb_cari.getSelectedItem() != null){
                    loadCariInfo(cmb_cari.getItemAt(cmb_cari.getSelectedIndex()).getKey());
                }
            }
        });
    }

    private void loadHareketInfo(long id){




    }

    private void loadCariInfo(long id){
        Cari cari = CariServis.getInstance().getOneByID(id);
        fld_adres.setText(cari.getAdres());
        lbl_telNo.setText(cari.getTel_no());
        lbl_cariKod.setText(cari.getKod());
    }

    private void loadCariCMB(){
        cmb_cari.removeAllItems();
        for(Cari cari : CariServis.getInstance().getAll(false)){
            cmb_cari.addItem(new Item<>(cari.getId(), cari.getIsim()));
        }
    }

    private void initComboBoxes(){
        loadCariCMB();
    }

    private void loadHareketTBL(long id){
        DefaultTableModel clearModel =(DefaultTableModel) tbl_hareketler.getModel();
        clearModel.setRowCount(0);
        int i;
        for(HareketDetay hareketDetay : HareketDetayServis.getInstance().getAllByHareketId(id)){
            i = 0;
            hareketDetay_row_list[i++] = hareketDetay.getId();
            hareketDetay_row_list[i++] = hareketDetay.getUrunId();
            hareketDetay_row_list[i++] = hareketDetay.getMiktar();
            hareketDetay_row_list[i++] = hareketDetay.getBirimId();
            hareketDetay_row_list[i++] = hareketDetay.getBirimFiyat();
            hareketDetay_row_list[i++] = hareketDetay.getTutar();
            mdl_hareket_detay.addRow(hareketDetay_row_list);
        }
    }

}
