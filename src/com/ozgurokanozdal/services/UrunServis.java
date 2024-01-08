package com.ozgurokanozdal.services;

import com.ozgurokanozdal.config.DBConnector;
import com.ozgurokanozdal.dto.TableItemUrun;
import com.ozgurokanozdal.entity.Urun;
import com.ozgurokanozdal.interfaces.IServis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UrunServis implements IServis<Urun> {


    public static UrunServis urunServis = new UrunServis();


    private UrunServis(){

    }
    public static UrunServis getInstance(){
        return urunServis;
    }


    @Override
    public List<Urun> getAll() {
        String query = "SELECT * FROM urunler";
        List<Urun> urunler = new ArrayList<>();

        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Urun urun = new Urun.UrunBuilder(rs.getString(2),rs.getString(3),rs.getString(4)).setId(rs.getLong(1))
                        .setKategoriAnaId(rs.getInt(5)).setKategoriAltId(rs.getInt(6)).setUreticiKodId(rs.getInt(7))
                        .setSpeKod(rs.getString(8)).setKdv(rs.getInt(9)).setOdemeTipId(rs.getInt(10)).setBarkodTipId(rs.getInt(11))
                        .setBarkodUygulamaTip(rs.getInt(12)).setBarkodAnaOnEk(rs.getString(13)).setBirimKodId(rs.getInt(14))
                        .setMinimumStokSeviye(rs.getInt(15)).build();
                urunler.add(urun);

            }
            st.close();
            rs.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return urunler;
    }

    public List<TableItemUrun> getAllForTable(int filterDurum,int filterKategori){

        String filterDurumSTR = filterDurum == 1 ? "A" : "P";

        String query = "SELECT urunler.id,durum,urunler.kod,isim,kategori_ana_isim,kategori_alt_isim,uretici.name,kdv FROM stok_takip.urunler "+
                "INNER JOIN kategori_alt ON kategori_alt_id = kategori_alt.id "+
                "INNER JOIN kategori_ana ON kategori_ana_id = kategori_ana.id "+
                "INNER JOIN uretici On uretici_id = uretici.id";

        StringBuilder QUERY = new StringBuilder(query);

        String durumFilterQuery = " WHERE durum = '" + filterDurumSTR + "'";
        String kategoriFilterQuery = " WHERE kategori_ana_id = " + filterKategori;
        String kategoriAndDurumFilterQuery = durumFilterQuery + " AND " + kategoriFilterQuery.replace("WHERE","");

        if(filterDurum > -2 && filterKategori > -2){
            QUERY.append(kategoriAndDurumFilterQuery);
        } else if (filterDurum > -2) {
            QUERY.append(durumFilterQuery);
        } else if (filterKategori > -2) {
            QUERY.append(kategoriFilterQuery);
        }
//        System.out.println(QUERY.toString());

        List<TableItemUrun> urunler = new ArrayList<>();

        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(QUERY.toString());

            while(rs.next()){
                int i = 1;
                TableItemUrun tableItemUrun = new TableItemUrun(
                        rs.getInt(i++),rs.getString(i++),
                        rs.getString(i++),rs.getString(i++),rs.getString(i++),
                        rs.getString(i++),rs.getString(i++),rs.getInt(i++)
                );
                urunler.add(tableItemUrun);

            }
            st.close();
            rs.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return urunler;

    }

    @Override
    public boolean create(Urun entity) {
        String query = "INSERT INTO urunler " +
                "(durum,kod,isim,kategori_ana_id,kategori_alt_id,uretici_id,spe_kod,kdv,odeme_tip_id,barkod_tip_id,barkod_uygulama_tipi,barkod_ana_on_ek,birim_kod_id,MINIMUM_STOK_SEVIYE,RESIM_1,RESIM_2,RESIM_3,RESIM_4) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            int i = 1;
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(i++,entity.getDurum());
            pr.setString(i++,entity.getKod());
            pr.setString(i++,entity.getIsim());
            pr.setInt(i++,entity.getKategoriAnaId());
            pr.setInt(i++,entity.getKategoriAltId());
            pr.setInt(i++,entity.getUreticiKodId());
            pr.setString(i++,entity.getSpeKod());
            pr.setInt(i++,entity.getKdv());
            pr.setInt(i++,entity.getOdemeTipId());
            pr.setInt(i++,entity.getBarkodTipId());
            pr.setInt(i++,entity.isBarkodUygulamaTip());
            pr.setString(i++,entity.getBarkodAnaOnEk());
            pr.setInt(i++,entity.getBirimKodId());
            pr.setInt(i++,entity.getMinimumStokSeviye());
            pr.setString(i++,entity.getResim1());
            pr.setString(i++,entity.getResim2());
            pr.setString(i++,entity.getResim3());
            pr.setString(i++,entity.getResim4());

            int response = pr.executeUpdate();
            return response != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public Urun getById(Long id) {
        String query = "SELECT * FROM urunler WHERE ID = "+id;
        Urun urun = new Urun();
        try{
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                Urun urun1 = new Urun.UrunBuilder(rs.getString(2),rs.getString(3),rs.getString(4)).setId(rs.getLong(1))
                        .setKategoriAnaId(rs.getInt(5)).setKategoriAltId(rs.getInt(6)).setUreticiKodId(rs.getInt(7))
                        .setSpeKod(rs.getString(8)).setKdv(rs.getInt(9)).setOdemeTipId(rs.getInt(10)).setBarkodTipId(rs.getInt(11))
                        .setBarkodUygulamaTip(rs.getInt(12)).setBarkodAnaOnEk(rs.getString(13)).setBirimKodId(rs.getInt(14))
                        .setMinimumStokSeviye(rs.getInt(15))
                        .setResim1(rs.getString(16)).setResim2(rs.getString(17)).setResim3(rs.getString(18))
                        .setResim4(rs.getString(19))
                                .build();
                st.close();
                rs.close();
                urun = urun1;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return urun;
    }

    @Override
    public boolean updateById(Long id,Urun entity) {
        String query = "UPDATE urunler SET " +
                "durum = ?,kod = ?,isim = ?,kategori_ana_id = ?,kategori_alt_id = ?,uretici_id = ?,spe_kod = ?,kdv = ?,odeme_tip_id = ?, " +
                "barkod_tip_id = ? ,barkod_uygulama_tipi = ?, " +
                "barkod_ana_on_ek = ?,birim_kod_id = ?,MINIMUM_STOK_SEVIYE = ?,RESIM_1 = ?,RESIM_2 = ?,RESIM_3 = ?,RESIM_4 = ? WHERE ID =" + id;

        try{
            int i = 1;
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(i++,entity.getDurum());
            pr.setString(i++,entity.getKod());
            pr.setString(i++,entity.getIsim());
            pr.setInt(i++,entity.getKategoriAnaId());
            pr.setInt(i++,entity.getKategoriAltId());
            pr.setInt(i++,entity.getUreticiKodId());
            pr.setString(i++,entity.getSpeKod());
            pr.setInt(i++,entity.getKdv());
            pr.setInt(i++,entity.getOdemeTipId());
            pr.setInt(i++,entity.getBarkodTipId());
            pr.setInt(i++,entity.isBarkodUygulamaTip());
            pr.setString(i++,entity.getBarkodAnaOnEk());
            pr.setInt(i++,entity.getBirimKodId());
            pr.setInt(i++,entity.getMinimumStokSeviye());
            pr.setString(i++,entity.getResim1());
            pr.setString(i++,entity.getResim2());
            pr.setString(i++,entity.getResim3());
            pr.setString(i++,entity.getResim4());

            int response = pr.executeUpdate();
            return response != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {

        return false;
    }


}
