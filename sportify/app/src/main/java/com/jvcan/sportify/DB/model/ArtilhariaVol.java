package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.Objects;

public class ArtilhariaVol implements Serializable {
    private Long idJogador;
    private Long idCampeonato;
    private String nome;
    private int pontos;

    public ArtilhariaVol(Long idJogador, Long idCampeonato, String nome, int pontos) {
        this.idJogador = idJogador;
        this.idCampeonato = idCampeonato;
        this.nome = nome;
        this.pontos = pontos;
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

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtilhariaVol)) return false;
        ArtilhariaVol that = (ArtilhariaVol) o;
        return pontos == that.pontos &&
                idJogador.equals(that.idJogador) &&
                idCampeonato.equals(that.idCampeonato) &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idCampeonato, nome, pontos);
    }
}
