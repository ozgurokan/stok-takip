package com.ozgurokanozdal.ui;

import com.ozgurokanozdal.entity.Uretici;
import com.ozgurokanozdal.helper.TableHelper;
import com.ozgurokanozdal.services.UreticiServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UreticilerGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bot;
    private JScrollPane scrl_uretici;
    private JTable tbl_uretici;
    private JButton btn_ekle;
    private JButton btn_incele;
    private JButton btn_degistir;

    private DefaultTableModel mdl_uretici;
    private Object[] row_list_uretici;


    public UreticilerGUI(){
        add(wrapper);
        setSize(680,540);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        mdl_uretici = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] columns_uretici = {"ID","ISIM","KOD"};
        mdl_uretici.setColumnIdentifiers(columns_uretici);
        row_list_uretici = new Object[columns_uretici.length];

        tbl_uretici.setModel(mdl_uretici);
        TableHelper.tableConfigIdAndReorderAndSelection(tbl_uretici,0,true,false,true);
        loadUrticiTBL();







    }

    public void loadUrticiTBL(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_uretici.getModel();
        clearModel.setRowCount(0);

        int i;
        for(Uretici uretici : UreticiServis.getInstance().getAllUretici()){
            i = 0;
            row_list_uretici[i++] = uretici.getId();
            row_list_uretici[i++] = uretici.getName();
            row_list_uretici[i++] = uretici.getCode();

            mdl_uretici.addRow(row_list_uretici);
        }
    }
}
