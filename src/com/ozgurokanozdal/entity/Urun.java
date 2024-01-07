package com.ozgurokanozdal.entity;

public class Urun {

    // required
    private long id;
    private int durum;
    private String kod;
    private String isim;

    // optional
    private int kategoriAnaId;
    private int kategoriAltId;
    private int ureticiKodId;
    private String speKod;
    private int kdv;
    private int odemeTipId;
    private int barkodTipId;
    private int barkodUygulamaTip;
    private String barkodAnaOnEk;
    private int birimKodId;
    private int minimumStokSeviye;
    private String resim1;
    private String resim2;
    private String resim3;
    private String resim4;

    public Urun(){

    }
    public Urun(UrunBuilder urunBuilder){
        this.id = urunBuilder.id;
        this.durum = urunBuilder.durum;
        this.kod = urunBuilder.kod;
        this.isim = urunBuilder.isim;
        this.kategoriAnaId = urunBuilder.kategoriAnaId;
        this.kategoriAltId = urunBuilder.kategoriAltId;
        this.ureticiKodId = urunBuilder.ureticiKodId;
        this.speKod = urunBuilder.speKod;
        this.kdv = urunBuilder.kdv;
        this.odemeTipId = urunBuilder.odemeTipId;
        this.barkodTipId = urunBuilder.barkodTipId;
        this.barkodUygulamaTip = urunBuilder.barkodUygulamaTip;
        this.barkodAnaOnEk = urunBuilder.barkodAnaOnEk;
        this.birimKodId = urunBuilder.birimKodId;
        this.minimumStokSeviye = urunBuilder.minimumStokSeviye;
        this.resim1 = urunBuilder.resim1;
        this.resim2 = urunBuilder.resim2;
        this.resim3 = urunBuilder.resim3;
        this.resim4 = urunBuilder.resim4;
    }

    @Override
    public String toString() {
        return "Urun{" +
                "id=" + id +
                ", durum=" + durum +
                ", kod='" + kod + '\'' +
                ", isim='" + isim + '\'' +
                ", kategoriAnaId=" + kategoriAnaId +
                ", kategoriAltId=" + kategoriAltId +
                ", ureticiKodId=" + ureticiKodId +
                ", speKod='" + speKod + '\'' +
                ", kdv=" + kdv +
                ", odemeTipId=" + odemeTipId +
                ", barkodTipId=" + barkodTipId +
                ", barkodUygulamaTip=" + barkodUygulamaTip +
                ", barkodAnaOnEk='" + barkodAnaOnEk + '\'' +
                ", birimKodId=" + birimKodId +
                ", minimumStokSeviye=" + minimumStokSeviye +
                ", resim1='" + resim1 + '\'' +
                ", resim2='" + resim2 + '\'' +
                ", resim3='" + resim3 + '\'' +
                ", resim4='" + resim4 + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public int getDurum() {
        return durum;
    }

    public String getKod() {
        return kod;
    }

    public String getIsim() {
        return isim;
    }

    public int getKategoriAnaId() {
        return kategoriAnaId;
    }

    public int getKategoriAltId() {
        return kategoriAltId;
    }

    public int getUreticiKodId() {
        return ureticiKodId;
    }

    public String getSpeKod() {
        return speKod;
    }

    public int getKdv() {
        return kdv;
    }

    public int getOdemeTipId() {
        return odemeTipId;
    }

    public int getBarkodTipId() {
        return barkodTipId;
    }

    public int isBarkodUygulamaTip() {
        return barkodUygulamaTip;
    }

    public String getBarkodAnaOnEk() {
        return barkodAnaOnEk;
    }

    public int getBirimKodId() {
        return birimKodId;
    }

    public int getMinimumStokSeviye() {
        return minimumStokSeviye;
    }

    public String getResim1() {
        return resim1;
    }

    public String getResim2() {
        return resim2;
    }

    public String getResim3() {
        return resim3;
    }

    public String getResim4() {
        return resim4;
    }

    public static class UrunBuilder {
        // required
        private long id;
        private int durum;
        private String kod;
        private String isim;

        // optional
        private int kategoriAnaId;
        private int kategoriAltId;
        private int ureticiKodId;
        private String speKod;
        private int kdv;
        private int odemeTipId;
        private int barkodTipId;
        private int barkodUygulamaTip;
        private String barkodAnaOnEk;
        private int birimKodId;
        private int minimumStokSeviye;
        private String resim1;
        private String resim2;
        private String resim3;
        private String resim4;

        public UrunBuilder(int durum,String kod,String isim){
             this.durum = durum;
             this.kod = kod;
             this.isim = isim;
        }

        public UrunBuilder setId(Long id){
            this.id = id;
            return this;
        }

        public UrunBuilder setKategoriAnaId(int kategoriAnaId){
            this.kategoriAnaId = kategoriAnaId;
            return this;
        }

        public UrunBuilder setKategoriAltId(int kategoriAltId){
            this.kategoriAltId = kategoriAltId;
            return this;
        }

        public UrunBuilder setUreticiKodId(int ureticiKodId){
            this.ureticiKodId = ureticiKodId;
            return this;
        }

        public UrunBuilder setSpeKod(String speKod ){
            this.speKod = speKod;
            return this;
        }

        public UrunBuilder setKdv(int kdv){
            this.kdv = kdv;
            return this;
        }

        public UrunBuilder setOdemeTipId(int odemeTip_id){
            this.odemeTipId = odemeTip_id;
            return this;
        }
        public UrunBuilder setBarkodTipId(int barkodTipId){
            this.barkodTipId = barkodTipId;
            return this;
        }
        public UrunBuilder setBarkodUygulamaTip(int barkodUygulamaTip){
            this.barkodUygulamaTip = barkodUygulamaTip;
            return this;
        }

        public UrunBuilder setBarkodAnaOnEk(String barkodAnaOnEk){
            this.barkodAnaOnEk = barkodAnaOnEk;
            return this;
        }

        public UrunBuilder setBirimKodId(int birimKodId){
            this.birimKodId = birimKodId;
            return this;

        }
        public UrunBuilder setMinimumStokSeviye(int minimumStokSeviye){
            this.minimumStokSeviye = minimumStokSeviye;
            return this;

        }

        public UrunBuilder setResim1(String resim1) {
            this.resim1 = resim1;
            return this;
        }

        public UrunBuilder setResim2(String resim2) {
            this.resim2 = resim2;
            return this;
        }

        public UrunBuilder setResim3(String resim3) {
            this.resim3 = resim3;
            return this;
        }

        public UrunBuilder setResim4(String resim4) {
            this.resim4 = resim4;
            return this;

        }

        public Urun build(){
            return new Urun(this);
        }
    }





}
