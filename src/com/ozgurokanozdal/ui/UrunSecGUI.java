package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.dto.TabloUrun;
import com.ozgurokanozdal.helper.TableHelper;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIValidate;
import com.ozgurokanozdal.services.UrunServis;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.ArrayList;

public class UrunSecGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_urunler;
    private JPanel pnl_secilenler;
    private JPanel pnl_bot;
    private JLabel lbl_ara;
    private JTextField fld_ara;
    private JTable tbl_urunler;
    private JTable tbl_secilenler;
    private JScrollPane scrl_urunler;
    private JScrollPane scrl_secilenler;
    private JButton btn_kaydet;

    private DefaultTableModel mdl_urunler;
    private Object[] row_list_urunler;

    private DefaultTableModel mdl_secilenler;
    private Object[] row_list_secilenler;

    private ArrayList<Long> secilen_urunIDleri = new ArrayList<>();

    private Long urun_selected;
    public UrunSecGUI(){
        add(wrapper);
        setTitle("Ürün Seç");
        setSize(720,640);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UrunSecGUI thisFrame = this;

        Object[] columns_tables = {"ID","DURUM","KOD","STOK ADI", "ANA KATEGORİ","ALT KATEGORİ", "URETICI", "KDV"};

        // URUNLER TABLOSU
        mdl_urunler = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        mdl_urunler.setColumnIdentifiers(columns_tables);
        row_list_urunler = new Object[columns_tables.length];

        tbl_urunler.setModel(mdl_urunler);

        // urunler table ayarları
        TableHelper.tableConfigIdAndReorderAndSelection(tbl_urunler,0,false,false,true);
//        tbl_urunler.setAutoCreateRowSorter(true);
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(mdl_urunler);
        tbl_urunler.setRowSorter(tableRowSorter);



        loadUrunList(1,-2);

        // SEÇİLENLER TABLOSU
        mdl_secilenler = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        mdl_secilenler.setColumnIdentifiers(columns_tables);
        row_list_secilenler = new Object[columns_tables.length];

        tbl_secilenler.setModel(mdl_secilenler);

        TableHelper.tableConfigIdAndReorderAndSelection(tbl_secilenler,0,false,false,true);



        // TABLO LISTENER
        tbl_urunler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    for(int i = 0; i<tbl_secilenler.getColumnModel().getColumnCount(); i++){
                        row_list_secilenler[i] = tbl_urunler.getValueAt(tbl_urunler.getSelectedRow(),i);
                    }
                    secilen_urunIDleri.add((Long) tbl_urunler.getValueAt(tbl_urunler.getSelectedRow(),0));
                    mdl_secilenler.addRow(row_list_secilenler);
                    mdl_urunler.removeRow(tbl_urunler.getSelectedRow());
                }
            }
        });
        tbl_secilenler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    for(int i = 0; i<tbl_urunler.getColumnModel().getColumnCount(); i++){
                        row_list_urunler[i] = tbl_secilenler.getValueAt(tbl_secilenler.getSelectedRow(),i);
                    }
                    secilen_urunIDleri.remove((Long) tbl_secilenler.getValueAt(tbl_secilenler.getSelectedRow(),0));
                    mdl_urunler.addRow(row_list_urunler);
                    mdl_secilenler.removeRow(tbl_secilenler.getSelectedRow());



                }
            }
        });

        fld_ara.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable(){
                tableRowSorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                    @Override
                    public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                        String name = entry.getValue(3).toString().toLowerCase();
                        String kod = entry.getValue(2).toString().toLowerCase();
                        String searchText =  fld_ara.getText().toLowerCase();
                        return name.contains(searchText) || kod.contains(searchText);
                    }
                });
            }
        });

        btn_kaydet.addActionListener(e -> {
            thisFrame.dispatchEvent(new WindowEvent(thisFrame,WindowEvent.WINDOW_CLOSING));
        });
    }


    private void loadUrunList(int filterDurum,int filterKategoriAna){

        // "ID","DURUM","KOD","STOK ADI", "ANA KATEGORİ","ALT KATEGORİ", "URETICI", "KDV"
        DefaultTableModel clearModel = (DefaultTableModel) tbl_urunler.getModel();
        clearModel.setRowCount(0);
        int i;
        for(TabloUrun urun : UrunServis.getInstance().getAllForTable(filterDurum,filterKategoriAna)){
            i=0;
            row_list_urunler[i++] = urun.getId();
            row_list_urunler[i++] = urun.getDurum();
            row_list_urunler[i++] = urun.getKod();
            row_list_urunler[i++] = urun.getIsim();
            row_list_urunler[i++] = urun.getKategoriAna();
            row_list_urunler[i++] = urun.getKategoriAlt();
            row_list_urunler[i++] = urun.getUreticiIsim();
            row_list_urunler[i++] = urun.getKdv();



            mdl_urunler.addRow(row_list_urunler);
        }

    }

    public ArrayList<Long> getSecilen_urunIDleri() {
        return this.secilen_urunIDleri;
    }


}
