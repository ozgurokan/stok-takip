package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.entity.BarkodTip;
import com.ozgurokanozdal.entity.OdemeTip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemServis {

    public static  ItemServis itemServis = new ItemServis();


    private ItemServis(){

    }
    public static ItemServis getInstance(){
        return itemServis;
    }


    public List<OdemeTip> getAllOdemeTip() {
        String query = "SELECT * FROM odeme_tip";
        List<OdemeTip> items = new ArrayList<>();

        Statement pr = null;
        try {
            pr = DBConnector.getInstance().createStatement();
            ResultSet rs = pr.executeQuery(query);
            while(rs.next()){
                OdemeTip odemeTip = new OdemeTip(rs.getInt(1),rs.getString(3),rs.getString(2));
                items.add(odemeTip);

            }
            pr.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }


    // BURAYA POLYMORPHISM UYGULAYIP TEK METHODA DÜŞÜREBİLİRİM. NASIL İBLİYO MUSUN VERİLEN BİR KEY'E GÖRE FARKLLI INSTENCELAR OLUŞTURURUM. BURAYA DÖN ! VE DÜŞÜN.
    public List<BarkodTip> getAllBarkodTip(){
        String query = "SELECT * FROM barkod_tip";
        List<BarkodTip> items = new ArrayList<>();

        Statement pr = null;
        try {
            pr = DBConnector.getInstance().createStatement();
            ResultSet rs = pr.executeQuery(query);
            while(rs.next()){
                BarkodTip barkodTip = new BarkodTip(rs.getInt(1),rs.getString(3),rs.getString(2));
                items.add(barkodTip);

            }
            pr.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}
