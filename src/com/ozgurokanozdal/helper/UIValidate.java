package com.ozgurokanozdal.helper;

import javax.swing.JTextField;

public class UIValidate {

    // VALİDATON FİELDS
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }


}
