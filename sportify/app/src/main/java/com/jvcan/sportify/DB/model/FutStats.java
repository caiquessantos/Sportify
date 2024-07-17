package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.Objects;

public class FutStats implements Serializable {
    private Long idStats;
    private Long idPartida;
    private Long idJogador;
    private int gols;
    private int assists;
    private int cartoesAmarelos;
    private int cartoesVermelhos;

    public FutStats(Long idStats, Long idPartida, Long idJogador, int gols, int assists, int cartoesAmarelos, int cartoesVermelhos) {
        this.idStats = idStats;
        this.idPartida = idPartida;
        this.idJogador = idJogador;
        this.gols = gols;
        this.assists = assists;
        this.cartoesAmarelos = cartoesAmarelos;
        this.cartoesVermelhos = cartoesVermelhos;
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

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getCartoesAmarelos() {
        return cartoesAmarelos;
    }

    public void setCartoesAmarelos(int cartoesAmarelos) {
        this.cartoesAmarelos = cartoesAmarelos;
    }

    public int getCartoesVermelhos() {
        return cartoesVermelhos;
    }

    public void setCartoesVermelhos(int cartoesVermelhos) {
        this.cartoesVermelhos = cartoesVermelhos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FutStats)) return false;
        FutStats futStats = (FutStats) o;
        return gols == futStats.gols &&
                assists == futStats.assists &&
                cartoesAmarelos == futStats.cartoesAmarelos &&
                cartoesVermelhos == futStats.cartoesVermelhos &&
                idStats.equals(futStats.idStats) &&
                idPartida.equals(futStats.idPartida) &&
                idJogador.equals(futStats.idJogador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStats, idPartida, idJogador, gols, assists, cartoesAmarelos, cartoesVermelhos);
    }
}
