package com.ozgurokanozdal.helper;

import javax.swing.*;

public class UIDialog {

    public static void showMessage(String str) {
        optionPageTR();
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Lütfen Tüm Alanları Doldurun!";
                title = "HATA";
            }
            case "done" -> {
                msg = "İşlem Başarılı!";
                title = "Başarılı!";
            }
            case "error" -> {
                msg = "Bir Hata Oluştu!";
                title = "HATA!";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {

        String msg;
        switch (str) {
            case "sure":
                msg = "Emin misinz?";
                break;
            default:
                msg = str;
        }

        return JOptionPane.showConfirmDialog(null, msg, "Doğrulama", JOptionPane.YES_NO_OPTION) == 0;

    }

    public static void optionPageTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }
}
