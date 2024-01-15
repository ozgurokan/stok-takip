package com.ozgurokanozdal.helper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UIPages {


    public static JFrame newWindow(JFrame frameChild,JFrame frameParent){

        frameChild.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                frameParent.setEnabled(false);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                frameParent.setEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                frameParent.setEnabled(true);
            }

            @Override
            public void windowIconified(WindowEvent e) {
                frameParent.setEnabled(false);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                frameParent.setEnabled(false);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                frameParent.setEnabled(false);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                frameParent.setEnabled(false);
            }
        });

        return frameChild;
    }

    public static JFrame redirectPage(JFrame frameChild,JFrame frameParent){

        frameChild.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                frameParent.setVisible(false);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                frameParent.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                frameParent.setVisible(true);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        return frameChild;
    }

    public static void setLayout(){

    }


}
