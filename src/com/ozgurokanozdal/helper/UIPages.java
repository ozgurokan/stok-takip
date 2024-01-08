package com.ozgurokanozdal.helper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UIPages {


    public static JFrame newWindow(JFrame frameChild,JFrame frameParent){

        frameParent.setEnabled(false);
        frameChild.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameParent.setEnabled(true);
            }
        });
        frameChild.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                frameParent.setEnabled(true);
            }
        });

        return frameChild;
    }

    public static void setLayout(){

    }


}
