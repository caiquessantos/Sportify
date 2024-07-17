package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.Objects;

public class VolStats implements Serializable {
    private Long idStats;
    private Long idPartida;
    private Long idJogador;
    private int pontos;
    private int bloqueios;

    public VolStats(Long idStats, Long idPartida, Long idJogador, int pontos, int bloqueios) {
        this.idStats = idStats;
        this.idPartida = idPartida;
        this.idJogador = idJogador;
        this.pontos = pontos;
        this.bloqueios = bloqueios;
    }

    public Long getIdStats() {
        return idStats;
    }

    public void setIdStats(Long idStats) {
        this.idStats = idStats;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
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
        if (!(o instanceof VolStats)) return false;
        VolStats volStats = (VolStats) o;
        return pontos == volStats.pontos &&
                bloqueios == volStats.bloqueios &&
                idStats.equals(volStats.idStats) &&
                idPartida.equals(volStats.idPartida) &&
                idJogador.equals(volStats.idJogador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStats, idPartida, idJogador, pontos, bloqueios);
    }
}
