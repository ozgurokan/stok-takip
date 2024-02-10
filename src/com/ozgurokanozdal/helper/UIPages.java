package com.ozgurokanozdal.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UIPages {


    public static void centeredFrame(JFrame frame){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x,y);
        frame.setVisible(true);

    }


    public static JFrame newWindow(JFrame frameChild, JFrame frameParent){
        centeredFrame(frameChild);
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

    public static JFrame redirectPage(JFrame frameChild,JFrame frameParent){

        centeredFrame(frameChild);
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

    public static void changeFieldsEnable(JPanel panel, Boolean isEnabled) {


        Component[] components = panel.getComponents();

        for (Component component : components) {

            if (component instanceof JPanel) {
                changeFieldsEnable((JPanel) component, isEnabled);
            }
            if( component instanceof JTabbedPane){
                Component[] components1 = ((JTabbedPane) component).getComponents();
                for(Component component1 : components1){
                    changeFieldsEnable((JPanel) component1, isEnabled);
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
            } else if (component instanceof  JCheckBox) {
                component.setEnabled(isEnabled);
            } else if (component instanceof  JTextPane) {
                ((JTextPane) component).setEditable(isEnabled);
            }

        }
    }


}
