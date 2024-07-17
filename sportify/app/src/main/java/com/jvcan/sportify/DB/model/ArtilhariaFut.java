package com.jvcan.sportify.DB.model;

import java.util.Objects;

public class ArtilhariaFut {
    private Long idJogador;
    private Long idCampeonato;
    private String nome;
    private int gols;

    public ArtilhariaFut(Long idJogador, Long idCampeonato, String nome, int gols) {
        this.idJogador = idJogador;
        this.idCampeonato = idCampeonato;
        this.nome = nome;
        this.gols = gols;
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

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtilhariaFut)) return false;
        ArtilhariaFut that = (ArtilhariaFut) o;
        return gols == that.gols &&
                idJogador.equals(that.idJogador) &&
                idCampeonato.equals(that.idCampeonato) &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idCampeonato, nome, gols);
    }
}
