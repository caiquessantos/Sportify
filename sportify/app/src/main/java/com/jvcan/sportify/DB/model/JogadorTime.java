package com.jvcan.sportify.DB.model;

public class JogadorTime {
    private Long id;
    private String nome;
    private Long idTime;

    public JogadorTime(Long id, String nome, Long idTime) {
        this.id = id;
        this.nome = nome;
        this.idTime = idTime;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdTime() {
        return idTime;
    }
}
