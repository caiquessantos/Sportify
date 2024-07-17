package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.Objects;

public class Vermelhos implements Serializable {

    private Long idJogador;
    private Long idCampeonato;
    private String nome;
    private int vermelhos;

    public Vermelhos(Long idJogador, Long idCampeonato, String nome, int vermelhos) {
        this.idJogador = idJogador;
        this.idCampeonato = idCampeonato;
        this.nome = nome;
        this.vermelhos = vermelhos;
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

    public int getVermelhos() {
        return vermelhos;
    }

    public void setVermelhos(int vermelhos) {
        this.vermelhos = vermelhos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vermelhos)) return false;
        Vermelhos vermelhos = (Vermelhos) o;
        return this.vermelhos == vermelhos.vermelhos &&
                idJogador.equals(vermelhos.idJogador) &&
                idCampeonato.equals(vermelhos.idCampeonato) &&
                nome.equals(vermelhos.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idCampeonato, nome, vermelhos);
    }
}
