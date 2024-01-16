package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.entity.Cari;

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

    public List<Cari> getAll() {
        String query = "SELECT * FROM cariler";
        List<Cari> cariler = new ArrayList<>();

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

    public Cari getOneByID(int id) {
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
}