package com.ozgurokanozdal.entity;

public class Cari {
    private long id;
    private String kod;
    private String isim;
    private String vergiNo;
    private String vergiDairesi;
    private String adres;
    private String tel_no;

    public Cari() {
    }

    public Cari(long id, String kod, String isim, String vergiNo, String vergiDairesi, String adres, String tel_no) {
        this.id = id;
        this.kod = kod;
        this.isim = isim;
        this.vergiNo = vergiNo;
        this.vergiDairesi = vergiDairesi;
        this.adres = adres;
        this.tel_no = tel_no;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getVergiNo() {
        return vergiNo;
    }

    public void setVergiNo(String vergiNo) {
        this.vergiNo = vergiNo;
    }

    public String getVergiDairesi() {
        return vergiDairesi;
    }

    public void setVergiDairesi(String vergiDairesi) {
        this.vergiDairesi = vergiDairesi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTel_no() {
        return tel_no;
    }

    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }
}
