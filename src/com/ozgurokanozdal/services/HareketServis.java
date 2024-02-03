package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.dto.TableHareket;
import com.ozgurokanozdal.entity.Hareket;

import java.sql.PreparedStatement;
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


    public List<TableHareket> getAllForTable(){
        List<TableHareket> hareketler = new ArrayList<>();
        String query = "SELECT hareketler.*, cariler.isim FROM hareketler INNER JOIN cariler ON hareketler.cari_id = cariler.id ";
        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                TableHareket tableHareket = new TableHareket();
                tableHareket.setId(rs.getLong(1));
                tableHareket.setCari_id(rs.getLong(2));
                tableHareket.setTarih(rs.getDate(3));
                tableHareket.setSaat(rs.getTime(4));
                tableHareket.setTur(rs.getInt(5));
                tableHareket.setTutar(rs.getFloat(6));
                tableHareket.setCariAd(rs.getString(7));

                hareketler.add(tableHareket);

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

    public int create(Hareket hareket){
        String query = "INSERT INTO hareketler(cari_id,tarih,saat,tur,tutar) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pr.setLong(1,hareket.getCari_id());
            pr.setDate(2,hareket.getTarih());
            pr.setTime(3,hareket.getSaat());
            pr.setInt(4,hareket.getTur());
            pr.setFloat(5,hareket.getTutar());

            int id = pr.executeUpdate();

            if(id > 0){
                ResultSet generetedID = pr.getGeneratedKeys();
                if(generetedID.next()){
                    return generetedID.getInt(1);
                }
            }
            pr.close();
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
