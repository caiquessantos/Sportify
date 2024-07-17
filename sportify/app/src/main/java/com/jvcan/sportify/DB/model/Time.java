package com.jvcan.sportify.DB.model;

public class Time {
    private  int id_time;
    private String nome_time;
    private String logo_time;

    public Time(String nome_time, String logo_time) {
        this.nome_time = nome_time;
        this.logo_time = logo_time;
    }

    public int getId_time() {
        return id_time;
    }

    public void setId_time(int id_time) {
        this.id_time = id_time;
    }

    public String getNome_time() {
        return nome_time;
    }

    public void setNome_time(String nome_time) {
        this.nome_time = nome_time;
    }

    public String getLogo_time() {
        return logo_time;
    }

    public void setLogo_time(String logo_time) {
        this.logo_time = logo_time;
    }
}
