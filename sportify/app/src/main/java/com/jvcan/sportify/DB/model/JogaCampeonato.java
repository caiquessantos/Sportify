package com.jvcan.sportify.DB.model;

public class JogaCampeonato {
    private int id_time;
    private int id_campeonato;

    public JogaCampeonato(int id_time, int id_campeonato) {
        this.id_time = id_time;
        this.id_campeonato = id_campeonato;
    }

    public int getId_time() {
        return id_time;
    }

    public void setId_time(int id_time) {
        this.id_time = id_time;
    }

    public int getId_campeonato() {
        return id_campeonato;
    }

    public void setId_campeonato(int id_campeonato) {
        this.id_campeonato = id_campeonato;
    }
}
