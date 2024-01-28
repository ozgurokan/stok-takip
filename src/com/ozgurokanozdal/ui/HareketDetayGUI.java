package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Cari;
import com.ozgurokanozdal.dto.Item;
import com.ozgurokanozdal.entity.Hareket;
import com.ozgurokanozdal.entity.HareketDetay;
import com.ozgurokanozdal.entity.Urun;
import com.ozgurokanozdal.helper.TableHelper;
import com.ozgurokanozdal.helper.UIDialog;
import com.ozgurokanozdal.helper.UIPages;
import com.ozgurokanozdal.services.CariServis;
import com.ozgurokanozdal.services.HareketDetayServis;
import com.ozgurokanozdal.services.HareketServis;
import com.ozgurokanozdal.services.UrunServis;

import javax.swing.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class HareketDetayGUI extends JFrame {
    private JPanel pnl_bt;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JTextArea lbl_adres1;
    private JPanel pnl_genel;
    private JPanel pnl_adres;
    private JPanel pnl_hareketTur;
    private JTextField fld_telNo;
    private JScrollPane scrll_hareket;
    private JButton btn_ekle;
    private JLabel lbl_tutar;
    private JPanel pnl_tarih;
    private JLabel lbl_tutar_hesaplanmis;
    private JComboBox<Item<Long,String>> cmb_cari;
    private JButton btn_cari_kayit;
    private JLabel lbl_cariKod;
    private JLabel lbl_telNo;
    private JTextPane fld_adres;
    private JTextField fld_tarih;
    private JCheckBox check_bugun;
    private JCheckBox check_suan;
    private JTextField fld_saat;
    private JButton btn_urunEkle;
    private JTable tbl_hareketler;
    private JComboBox<Item<Integer,String>> cmb_hareketTur;
    private JPanel wrapper;

    private DefaultTableModel mdl_hareket_detay;
    private Object[] hareketDetay_row_list;

    public HareketDetayGUI(int logic, long hareket_id){
        add(wrapper);
        setSize(720,1024);
        setTitle("Hareket Detay");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JFrame thisFrame = this;


        // hareket detay tablo +++++++++++++++++++++
        mdl_hareket_detay = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 4 && column < 7;
            }
        };

        Object[] columns_hareketDetay = {"ID","URUN ID","ÜRÜN İSİM","ÜRÜN KOD","BİRİM","MİKTAR","BİRİM FİYAT","TUTAR"};
        mdl_hareket_detay.setColumnIdentifiers(columns_hareketDetay);
        hareketDetay_row_list = new Object[columns_hareketDetay.length];
        tbl_hareketler.setModel(mdl_hareket_detay);

        tbl_hareketler.getTableHeader().setReorderingAllowed(false);
        TableHelper.tableConfigIdAndReorderAndSelection(tbl_hareketler,0,true,false,true);
        tbl_hareketler.getColumnModel().getColumn(1).setMinWidth(0);
        tbl_hareketler.getColumnModel().getColumn(1).setWidth(0);
        tbl_hareketler.getColumnModel().getColumn(1).setMaxWidth(0);
        tbl_hareketler.setAutoCreateRowSorter(true);
        tbl_hareketler.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {

                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    try {
                        if(col ==5 || col == 6){
                            float miktar = Float.parseFloat(tbl_hareketler.getValueAt(row, 5).toString());
                            float birim_fiyat = Float.parseFloat(tbl_hareketler.getValueAt(row, 6).toString());
                            calculateTotalTable(miktar, birim_fiyat, row);
                            calculateTotal();
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("Sayısal bir değer bekleniyor.");
                    }
                }
            }
        });


        // hareket detay tablo ---------------------

        
        if(logic == 1){
            setTitle("Hareket Değiştir");
            loadHareketTBL(hareket_id);
            calculateTotal();
        } else if (logic == 2) {
            setTitle("Hareket Detay");
            loadHareketTBL(hareket_id);
            calculateTotal();
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
        
        check_bugun.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(check_bugun.isSelected()){
                    fld_tarih.setText(String.valueOf(LocalDate.now()));
                }else{
                    fld_tarih.setText(null);
                }
            }
        });

        check_suan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(check_suan.isSelected()){
                    fld_saat.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                }else{
                    fld_saat.setText(null);
                }
            }
        });
        fld_tarih.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(fld_tarih.getText().length() >= 10){
                    e.consume();
                }
            }
        });

        fld_saat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(fld_saat.getText().length() >= 8){
                    e.consume();
                }
            }
        });
        btn_urunEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               UrunSecGUI secilen = (UrunSecGUI) UIPages.newWindow(new UrunSecGUI(),thisFrame);
               secilen.addWindowListener(new WindowAdapter() {
                   @Override
                   public void windowClosed(WindowEvent e) {
                       ArrayList<Long> secilenler = secilen.getSecilen_urunIDleri();
                       loadHareketTBL(secilenler);

                   }
               });



            }
        });

        btn_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(UIDialog.confirm("sure")){

                    Hareket hareket = new Hareket();
                    hareket.setCari_id(cmb_cari.getItemAt(cmb_cari.getSelectedIndex()).getKey());
                    hareket.setTarih(Date.valueOf(fld_tarih.getText()));
                    hareket.setSaat(Time.valueOf(fld_saat.getText()));
                    hareket.setTur(cmb_hareketTur.getItemAt(cmb_hareketTur.getSelectedIndex()).getKey());
                    hareket.setTutar(Float.parseFloat(lbl_tutar_hesaplanmis.getText()));
                    int result = HareketServis.getInstance().create(hareket);
                    if(result > -1){
                        for(int i = 0; i < mdl_hareket_detay.getRowCount(); i++){
                            HareketDetay hareketDetay = new HareketDetay();
                            hareketDetay.setHareketId(result);
                            hareketDetay.setUrunId(Integer.parseInt(mdl_hareket_detay.getValueAt(i,1).toString()));
                            hareketDetay.setBirimId(Integer.parseInt(mdl_hareket_detay.getValueAt(i,4).toString()));
                            hareketDetay.setMiktar(Float.parseFloat(mdl_hareket_detay.getValueAt(i,5).toString()));
                            hareketDetay.setBirimFiyat(Float.parseFloat(mdl_hareket_detay.getValueAt(i,6).toString()));
                            hareketDetay.setTutar(Float.parseFloat(mdl_hareket_detay.getValueAt(i,7).toString()));
                            HareketDetayServis.getInstance().create(hareketDetay);
                        }

                    }


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

    private void loadHareketTur(){
        cmb_hareketTur.removeAllItems();
        cmb_hareketTur.addItem(new Item<Integer,String>(0,"Alış"));
        cmb_hareketTur.addItem(new Item<Integer,String>(1,"Satış"));
        cmb_hareketTur.addItem(new Item<Integer,String>(2,"İade"));
    }

    private void initComboBoxes(){
        loadCariCMB();
        loadHareketTur();
    }

    private void loadHareketTBL(long id){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hareketler.getModel();
        clearModel.setRowCount(0);
        Urun urun;
        int i;
        for(HareketDetay hareketDetay : HareketDetayServis.getInstance().getAllByHareketId(id)){
            i = 0;
            urun = UrunServis.getInstance().getById((long) hareketDetay.getUrunId());
            hareketDetay_row_list[i++] = hareketDetay.getId();
            hareketDetay_row_list[i++] = hareketDetay.getUrunId();
            hareketDetay_row_list[i++] = urun.getIsim();
            hareketDetay_row_list[i++] = urun.getKod();
            hareketDetay_row_list[i++] = urun.getBirimKodId();
            hareketDetay_row_list[i++] = hareketDetay.getMiktar();
            hareketDetay_row_list[i++] = hareketDetay.getBirimFiyat();
            hareketDetay_row_list[i++] = hareketDetay.getTutar();

            mdl_hareket_detay.addRow(hareketDetay_row_list);
        }
    }
    private void loadHareketTBL(ArrayList<Long> ids){
        // {"ID","URUN ID","ÜRÜN İSİM","ÜRÜN KOD","BİRİM","MİKTAR","BİRİM FİYAT","TUTAR"};
        for(Long id : ids){
            Urun urun = UrunServis.getInstance().getById(id);
            hareketDetay_row_list[1] = urun.getId();
            hareketDetay_row_list[2] = urun.getIsim();
            hareketDetay_row_list[3] = urun.getKod();
            hareketDetay_row_list[4] = urun.getBirimKodId();
            hareketDetay_row_list[5] = 0;
            hareketDetay_row_list[6] = 0;
            hareketDetay_row_list[7] = 0F;



            mdl_hareket_detay.addRow(hareketDetay_row_list);
        }
        
    }
    private void calculateTotal(){
        float total = 0F;
        for (int i = 0; i < tbl_hareketler.getRowCount(); i++){
            float amount = Float.parseFloat(tbl_hareketler.getValueAt(i, tbl_hareketler.getColumnCount()-1).toString());
            total += amount;
        }
        lbl_tutar_hesaplanmis.setText(String.valueOf(total));
    }

    private void calculateTotalTable(float miktar, float birim_fiyat, int row) {
        float tutar = miktar * birim_fiyat;
        tbl_hareketler.setValueAt(String.valueOf(tutar), row, 7);
    }


}
