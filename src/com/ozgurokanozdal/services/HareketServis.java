package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.entity.Hareket;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HareketServis {

    public static  HareketServis hareketServis = new HareketServis();


    private HareketServis(){

    }
    public HareketServis getInstance(){
        return hareketServis;
    }




    public static List<Hareket> getAll(){
        List<Hareket> hareketler = new ArrayList<>();
        String query = "SELECT * FROM hareketler";
        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            Hareket hareket = new Hareket();
            while(rs.next()){
                hareket.setId(rs.getInt(1));
                hareket.setCari_id(rs.getInt(2));
                hareket.setTarih(rs.getDate(3));
                hareket.setSaat(rs.getTime(4));
                hareket.setTur(rs.getInt(5));
                hareket.setTutar(rs.getFloat(6));

                hareketler.add(hareket);

            }

            return hareketler;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
