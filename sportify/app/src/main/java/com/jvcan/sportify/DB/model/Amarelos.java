package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.Objects;

public class Amarelos implements Serializable {
    private Long idJogador;
    private Long idCampeonato;
    private String nome;
    private int amarelos;

    public Amarelos(Long idJogador, Long idCampeonato, String nome, int amarelos) {
        this.idJogador = idJogador;
        this.idCampeonato = idCampeonato;
        this.nome = nome;
        this.amarelos = amarelos;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public Long getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(Long idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAmarelos() {
        return amarelos;
    }

    public void setAmarelos(int amarelos) {
        this.amarelos = amarelos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amarelos)) return false;
        Amarelos that = (Amarelos) o;
        return amarelos == that.amarelos &&
                idJogador.equals(that.idJogador) &&
                idCampeonato.equals(that.idCampeonato) &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idCampeonato, nome, amarelos);
    }
}
