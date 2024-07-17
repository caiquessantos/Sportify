package com.jvcan.sportify.DB.model;

import java.util.Objects;

public class PartidaStats {
    private int totalPartidas;
    private int totalVitorias;
    private int totalEmpates;
    private int totalDerrotas;
    private int totalGolsFeitos;
    private int totalGolsSofridos;
    private int saldoGols;
    private int totalPontos;
    private long idTime;

    public PartidaStats(long idTime, int totalPartidas, int totalVitorias, int totalEmpates, int totalDerrotas, int totalGolsFeitos, int totalGolsSofridos, int saldoGols, int totalPontos) {
        this.idTime = idTime;
        this.totalPartidas = totalPartidas;
        this.totalVitorias = totalVitorias;
        this.totalEmpates = totalEmpates;
        this.totalDerrotas = totalDerrotas;
        this.totalGolsFeitos = totalGolsFeitos;
        this.totalGolsSofridos = totalGolsSofridos;
        this.saldoGols = saldoGols;
        this.totalPontos = totalPontos;
    }

    public long getIdTime() {
        return idTime;
    }

    public void setIdTime(long idTime) {
        this.idTime = idTime;
    }

    public int getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(int totalPontos) {
        this.totalPontos = totalPontos;
    }

    public int getTotalPartidas() {
        return totalPartidas;
    }

    public void setTotalPartidas(int totalPartidas) {
        this.totalPartidas = totalPartidas;
    }

    public int getTotalVitorias() {
        return totalVitorias;
    }

    public void setTotalVitorias(int totalVitorias) {
        this.totalVitorias = totalVitorias;
    }

    public int getTotalEmpates() {
        return totalEmpates;
    }

    public void setTotalEmpates(int totalEmpates) {
        this.totalEmpates = totalEmpates;
    }

    public int getTotalDerrotas() {
        return totalDerrotas;
    }

    public void setTotalDerrotas(int totalDerrotas) {
        this.totalDerrotas = totalDerrotas;
    }

    public int getTotalGolsFeitos() {
        return totalGolsFeitos;
    }

    public void setTotalGolsFeitos(int totalGolsFeitos) {
        this.totalGolsFeitos = totalGolsFeitos;
    }

    public int getTotalGolsSofridos() {
        return totalGolsSofridos;
    }

    public void setTotalGolsSofridos(int totalGolsSofridos) {
        this.totalGolsSofridos = totalGolsSofridos;
    }

    public int getSaldoGols() {
        return saldoGols;
    }

    public void setSaldoGols(int saldoGols) {
        this.saldoGols = saldoGols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartidaStats that = (PartidaStats) o;
        return totalPartidas == that.totalPartidas &&
                totalVitorias == that.totalVitorias &&
                totalEmpates == that.totalEmpates &&
                totalDerrotas == that.totalDerrotas &&
                totalGolsFeitos == that.totalGolsFeitos &&
                totalGolsSofridos == that.totalGolsSofridos &&
                saldoGols == that.saldoGols;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPartidas, totalVitorias, totalEmpates, totalDerrotas, totalGolsFeitos, totalGolsSofridos, saldoGols);
    }

    @Override
    public String toString() {
        return "PartidaStats{" +
                "totalPartidas=" + totalPartidas +
                ", totalVitorias=" + totalVitorias +
                ", totalEmpates=" + totalEmpates +
                ", totalDerrotas=" + totalDerrotas +
                ", totalGolsFeitos=" + totalGolsFeitos +
                ", totalGolsSofridos=" + totalGolsSofridos +
                ", saldoGols=" + saldoGols +
                '}';
    }
}
