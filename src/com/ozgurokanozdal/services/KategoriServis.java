package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.entity.KategoriAlt;
import com.ozgurokanozdal.entity.KategoriAna;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KategoriServis {

    private static KategoriServis kategoriServis;

    private KategoriServis(){

    }
    public static KategoriServis getInstance(){
        if(kategoriServis == null){
             kategoriServis = new KategoriServis();
        }
        return kategoriServis;
    }

    public List<KategoriAna> getAllKategoriAna() {
        String query = "SELECT * FROM kategori_ana";
        List<KategoriAna> items = new ArrayList<>();

        Statement pr = null;
        try {
            pr = DBConnector.getInstance().createStatement();
            ResultSet rs = pr.executeQuery(query);
            while(rs.next()){
                KategoriAna kategoriAna = new KategoriAna(rs.getInt(1),rs.getString(2));
                items.add(kategoriAna);

            }
            pr.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    public List<KategoriAlt> getAllKategoriAltByParentId(int parentId) {
        String query = "SELECT * FROM kategori_alt WHERE parent_id =" + parentId;
        List<KategoriAlt> items = new ArrayList<>();

        Statement pr = null;
        try {
            pr = DBConnector.getInstance().createStatement();
            ResultSet rs = pr.executeQuery(query);
            while(rs.next()){
                KategoriAlt kategoriAlt = new KategoriAlt(rs.getInt(1),rs.getString(2),rs.getInt(3));
                items.add(kategoriAlt);

            }
            pr.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    public KategoriAna getOneKategoriAna(int id){
        String query = "SELECT * FROM kategori_ana WHERE id = "+id;
        Statement st = null;
        KategoriAna kategoriAna = null;
        try{
            st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                kategoriAna = new KategoriAna(rs.getInt(1),rs.getString(2));

            }
            st.close();
            rs.close();
            return kategoriAna;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
