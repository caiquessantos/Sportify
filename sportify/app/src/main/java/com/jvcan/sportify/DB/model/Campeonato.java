package com.jvcan.sportify.DB.model;

public class Campeonato {
    private int id_campeonato;
    private String userEmail;
    private String nome_campeonato;
    private String modalidade;
    private String descricao;
    private String foto_campeonato;

    public Campeonato(String userEmail, String nome_campeonato, String modalidade, String descricao, String foto_campeonato) {
        this.userEmail = userEmail;
        this.nome_campeonato = nome_campeonato;
        this.modalidade = modalidade;
        this.descricao = descricao;
        this.foto_campeonato = foto_campeonato;
    }

    public String getNome_campeonato() {
        return nome_campeonato;
    }

    public void setNome_campeonato(String nome_campeonato) {
        this.nome_campeonato = nome_campeonato;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto_campeonato() {
        return foto_campeonato;
    }

    public void setFoto_campeonato(String foto_campeonato) {
        this.foto_campeonato = foto_campeonato;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getId_campeonato() {
        return id_campeonato;
    }

    public void setId_campeonato(int id_campeonato) {
        this.id_campeonato = id_campeonato;
    }
}
