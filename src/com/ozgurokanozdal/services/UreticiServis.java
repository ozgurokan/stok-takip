package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.entity.Uretici;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UreticiServis {

    private static UreticiServis ureticiServis;


    private UreticiServis(){

    }
    public static UreticiServis getInstance(){
        if(ureticiServis == null){
             ureticiServis = new UreticiServis();
        }
        return ureticiServis;
    }

    public List<Uretici> getAllUretici() {
        String query = "SELECT * FROM uretici";
        List<Uretici> items = new ArrayList<>();

        Statement pr = null;
        try {
            pr = DBConnector.getInstance().createStatement();
            ResultSet rs = pr.executeQuery(query);
            while(rs.next()){
                Uretici uretici = new Uretici(rs.getInt(1),rs.getString(2), rs.getString(3));
                items.add(uretici);

            }
            pr.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

}
