package com.ozgurokanozdal.helper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UIPages {


    public static void newWindow(JFrame frameChild,JFrame frameParent){

        JFrame frame1 = frameChild;
        frameParent.setEnabled(false);
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameParent.setEnabled(true);
            }
        });
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                frameParent.setEnabled(true);
            }
        });

    }

    public static void setLayout(){

    }


}
