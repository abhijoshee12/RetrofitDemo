package com.example.myapi;

public class DataModel {
    private int id;
    private String judul, penulis, tahun;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTahun() {return tahun;}

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
