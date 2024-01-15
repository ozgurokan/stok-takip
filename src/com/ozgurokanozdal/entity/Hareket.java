package com.ozgurokanozdal.entity;

import java.sql.Date;
import java.sql.Time;

public class Hareket {

    private long id;
    private int cari_id;
    private Date tarih;
    private Time saat;
    private int tur;
    private float tutar;



    public Hareket(){

    }

    public Hareket(long id,int cari_id,Date tarih, Time saat,int tur,float tutar){
        this.id = id;
        this.cari_id = cari_id;
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

    public int getCari_id() {
        return cari_id;
    }

    public void setCari_id(int cari_id) {
        this.cari_id = cari_id;
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
