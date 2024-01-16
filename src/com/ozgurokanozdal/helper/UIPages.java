package com.ozgurokanozdal.helper;

import javax.swing.*;
import java.awt.*;
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

        frameChild.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
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

        });

        return frameChild;
    }

    public static void setLayout(){

    }

    public static void disableAllFields(JPanel panel, Boolean isEnabled) {


        Component[] components = panel.getComponents();

        for (Component component : components) {

            if (component instanceof JPanel) {
                disableAllFields((JPanel) component, isEnabled);
            }
            if( component instanceof JTabbedPane){
                Component[] components1 = ((JTabbedPane) component).getComponents();
                for(Component component1 : components1){
                    disableAllFields((JPanel) component1, isEnabled);
                }
            }
            if(component instanceof JTextField){
                ((JTextField) component).setEditable(isEnabled);
            }
            else if(component instanceof JComboBox){
                component.setEnabled(isEnabled);
            }
            else if(component instanceof JButton){
                component.setEnabled(isEnabled);
            }
            else if(component instanceof JRadioButton){
                component.setEnabled(isEnabled);
            }

        }
    }


}
