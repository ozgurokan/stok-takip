package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.entity.Cari;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CariServis {


    private static CariServis cariServis;

    private CariServis() {

    }

    public static CariServis getInstance() {
        if (cariServis == null) {
            cariServis = new CariServis();
        }
        return cariServis;
    }

    public List<Cari> getAll(boolean asc) {
        String query = "SELECT * FROM cariler";
        List<Cari> cariler = new ArrayList<>();
        if(asc){
            query += " ORDER BY isim ASC";
        }
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cari cari = new Cari(
                        rs.getLong(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getString(7)
                );

                cariler.add(cari);
            }

            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cariler;
    }

    public Cari getOneByID(long id) {
        String query = "SELECT * FROM cariler WHERE id = " + id;
        Cari cari = new Cari();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                cari.setId(rs.getLong(1));
                cari.setKod(rs.getString(2));
                cari.setIsim(rs.getString(3));
                cari.setVergiNo(rs.getString(4));
                cari.setVergiDairesi(rs.getString(5));
                cari.setAdres(rs.getString(6));
                cari.setTel_no(rs.getString(7));
            }

            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cari;
    }

    public boolean add(Cari cari){
        String query = "INSERT INTO cariler(kod,isim,vergi_no,vergi_dairesi,adres,tel_no) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, cari.getKod());
            pr.setString(2, cari.getIsim());
            pr.setString(3,cari.getVergiNo());
            pr.setString(4, cari.getVergiDairesi());
            pr.setString(5, cari.getAdres());
            pr.setString(6, cari.getTel_no());

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}