package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.entity.HareketDetay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HareketDetayServis {

    private static HareketDetayServis hareketDetayServis;

    private HareketDetayServis(){

    }

    public static HareketDetayServis getInstance(){
        if(hareketDetayServis == null){
            hareketDetayServis = new HareketDetayServis();
        }
        return hareketDetayServis;
    }

    public List<HareketDetay> getAllByHareketId(long hareketId){
        String query = "SELECT hareket_detay.* ,urunler.isim FROM hareket_detay\n" +
                "INNER JOIN urunler ON urun_id = urunler.ID\n" +
                "WHERE hareket_detay.hareket_id = " + hareketId;
        List<HareketDetay> hareketDetayList = new ArrayList<>();

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                hareketDetayList.add(new HareketDetay(rs.getLong(1),rs.getLong(2),rs.getInt(3),
                        rs.getFloat(4),rs.getInt(5),rs.getFloat(6),rs.getFloat(7)));
            }
            st.close();
            rs.close();

            return hareketDetayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean create(HareketDetay hareketDetay){
//        String query = "INSERT INTO hareket_detay"
        return false;
    }

}
