package com.ozgurokanozdal.entity;

public class Durum {

    private String kod;
    private String name;

    public Durum(String kod, String name) {
        this.kod = kod;
        this.name = name;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
