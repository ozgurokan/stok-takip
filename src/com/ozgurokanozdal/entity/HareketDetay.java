package com.ozgurokanozdal.entity;

public class HareketDetay {

    private long id;
    private long hareketId;
    private int urunId;
    private float miktar;
    private int birimId;
    private float birimFiyat;
    private float tutar;



    public HareketDetay(){

    }

    public HareketDetay(long id, long hareketId, int urunId, float miktar, int birimId, float birimFiyat, float tutar) {
        this.id = id;
        this.hareketId = hareketId;
        this.urunId = urunId;
        this.miktar = miktar;
        this.birimId = birimId;
        this.birimFiyat = birimFiyat;
        this.tutar = tutar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHareketId() {
        return hareketId;
    }

    public void setHareketId(long hareketId) {
        this.hareketId = hareketId;
    }

    public int getUrunId() {
        return urunId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public float getMiktar() {
        return miktar;
    }

    public void setMiktar(float miktar) {
        this.miktar = miktar;
    }

    public int getBirimId() {
        return birimId;
    }

    public void setBirimId(int birimId) {
        this.birimId = birimId;
    }

    public float getBirimFiyat() {
        return birimFiyat;
    }

    public void setBirimFiyat(float birimFiyat) {
        this.birimFiyat = birimFiyat;
    }

    public float getTutar() {
        return tutar;
    }

    public void setTutar(float tutar) {
        this.tutar = tutar;
    }

    @Override
    public String toString() {
        return "HareketDetay{" +
                "id=" + id +
                ", hareketId=" + hareketId +
                ", urunId=" + urunId +
                ", miktar=" + miktar +
                ", birimId=" + birimId +
                ", birimFiyat=" + birimFiyat +
                ", tutar=" + tutar +
                '}';
    }
}
