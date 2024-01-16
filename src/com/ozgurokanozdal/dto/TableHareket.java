package com.ozgurokanozdal.dto;

import java.sql.Date;
import java.sql.Time;

public class TableHareket {
    private long id;
    private String cariAd;
    private Date tarih;
    private Time saat;
    private int tur;
    private float tutar;

    public TableHareket(){

    }
    public TableHareket(long id, String cariAd, Date tarih, Time saat, int tur, float tutar) {
        this.id = id;
        this.cariAd = cariAd;
        this.tarih = tarih;
        this.saat = saat;
        this.tur = tur;
        this.tutar = tutar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCariAd() {
        return cariAd;
    }

    public void setCariAd(String cariAd) {
        this.cariAd = cariAd;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public Time getSaat() {
        return saat;
    }

    public void setSaat(Time saat) {
        this.saat = saat;
    }

    public int getTur() {
        return tur;
    }

    public void setTur(int tur) {
        this.tur = tur;
    }

    public float getTutar() {
        return tutar;
    }

    public void setTutar(float tutar) {
        this.tutar = tutar;
    }
}
