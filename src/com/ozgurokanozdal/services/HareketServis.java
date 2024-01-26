package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.dto.TableHareket;
import com.ozgurokanozdal.entity.Hareket;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HareketServis {

    private static  HareketServis hareketServis;


    private HareketServis(){

    }
    public static HareketServis getInstance(){
        if(hareketServis == null){
            hareketServis = new HareketServis();
        }
        return hareketServis;
    }




    public List<TableHareket> getAll(){
        List<TableHareket> hareketler = new ArrayList<>();
        String query = "SELECT hareketler.*, cariler.isim FROM hareketler INNER JOIN cariler ON hareketler.cari_id = cariler.id ";
        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            TableHareket hareket = new TableHareket();
            while(rs.next()){
                hareket.setId(rs.getInt("id"));
                hareket.setCariAd(rs.getString("isim"));
                hareket.setTarih(rs.getDate("tarih"));
                hareket.setSaat(rs.getTime("saat"));
                hareket.setTutar(rs.getFloat("tutar"));


                hareketler.add(hareket);

            }
            rs.close();
            st.close();
            return hareketler;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Hareket getOneByID(long id){
        Hareket hareket = new Hareket();
        String query = "SELECT * FROM hareketler WHERE id = " + id;
        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                hareket.setId(rs.getInt(1));
                hareket.setCari_id(rs.getInt(2));
                hareket.setTarih(rs.getDate(3));
                hareket.setSaat(rs.getTime(4));
                hareket.setTur(rs.getInt(5));
                hareket.setTutar(rs.getFloat(6));
            }
            st.close();
            rs.close();
            return hareket;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean create(){
        return true;
    }



}
