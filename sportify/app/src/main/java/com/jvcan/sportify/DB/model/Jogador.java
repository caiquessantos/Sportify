package com.jvcan.sportify.DB.model;

public class Jogador {
    private int id_jogador;
    private int id_time;
    private String nome_jogador;
    private String numero;

    public Jogador(int id_time, String nome_jogador, String numero) {
        this.id_time = id_time;
        this.nome_jogador = nome_jogador;
        this.numero = numero;

    }

    public int getId_jogador() {
        return id_jogador;
    }

    public void setId_jogador(int id_jogador) {
        this.id_jogador = id_jogador;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome_jogador() {
        return nome_jogador;
    }

    public void setNome_jogador(String nome_jogador) {
        this.nome_jogador = nome_jogador;
    }

    public int getId_time() {
        return id_time;
    }

    public void setId_time(int id_time) {
        this.id_time = id_time;
    }
}
