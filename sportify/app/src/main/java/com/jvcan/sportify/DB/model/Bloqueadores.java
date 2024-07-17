package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.Objects;

public class Bloqueadores implements Serializable {
    private Long idJogador;
    private Long idCampeonato;
    private String nome;
    private int bloqueios;

    public Bloqueadores(Long idJogador, Long idCampeonato, String nome, int bloqueios) {
        this.idJogador = idJogador;
        this.idCampeonato = idCampeonato;
        this.nome = nome;
        this.bloqueios = bloqueios;
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

    public int getBloqueios() {
        return bloqueios;
    }

    public void setBloqueios(int bloqueios) {
        this.bloqueios = bloqueios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bloqueadores)) return false;
        Bloqueadores that = (Bloqueadores) o;
        return bloqueios == that.bloqueios &&
                idJogador.equals(that.idJogador) &&
                idCampeonato.equals(that.idCampeonato) &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idCampeonato, nome, bloqueios);
    }
}
