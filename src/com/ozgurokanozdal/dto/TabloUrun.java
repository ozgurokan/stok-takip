package com.ozgurokanozdal.dto;

public class TabloUrun {

    private long id;
    private String durum;
    private String kod;
    private String isim;
    private String kategoriAna;
    private String kategoriAlt;
    private String ureticiIsim;
    private int kdv;
    private float stok_bilgi;

    public TabloUrun() {
    }

    public TabloUrun(long id, String durum, String kod, String isim, String kategoriAna, String kategoriAlt, String ureticiIsim, int kdv,float stok_bilgi) {
        this.id = id;
        this.durum = durum;
        this.kod = kod;
        this.isim = isim;
        this.kategoriAna = kategoriAna;
        this.kategoriAlt = kategoriAlt;
        this.ureticiIsim = ureticiIsim;
        this.kdv = kdv;
        this.stok_bilgi = stok_bilgi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getKategoriAna() {
        return kategoriAna;
    }

    public void setKategoriAna(String kategoriAna) {
        this.kategoriAna = kategoriAna;
    }

    public String getKategoriAlt() {
        return kategoriAlt;
    }

    public void setKategoriAlt(String kategoriAlt) {
        this.kategoriAlt = kategoriAlt;
    }

    public String getUreticiIsim() {
        return ureticiIsim;
    }

    public void setUreticiIsim(String ureticiIsim) {
        this.ureticiIsim = ureticiIsim;
    }

    public int getKdv() {
        return kdv;
    }

    public void setKdv(int kdv) {
        this.kdv = kdv;
    }

    public float getStok_bilgi() {
        return stok_bilgi;
    }

    public void setStok_bilgi(float stok_bilgi) {
        this.stok_bilgi = stok_bilgi;
    }
}
