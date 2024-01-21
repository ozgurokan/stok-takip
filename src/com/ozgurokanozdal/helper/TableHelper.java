package com.ozgurokanozdal.helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class TableHelper {



    public static void tableConfigIdAndReorderAndSelection(JTable table,int idColumnIndex,boolean isIdHidden,boolean isReorderAllowed,boolean isSelectionSingle){


        table.getTableHeader().setReorderingAllowed(isReorderAllowed);
        if(isIdHidden){
            TableColumn column = table.getColumnModel().getColumn(idColumnIndex);
            column.setMinWidth(0);
            column.setWidth(0);
            column.setMaxWidth(0);
        }
        table.setSelectionMode(isSelectionSingle ? 0:2);

    }
}
