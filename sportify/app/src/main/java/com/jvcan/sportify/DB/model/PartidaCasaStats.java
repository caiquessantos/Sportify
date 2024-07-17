package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PartidaCasaStats implements Serializable {
    private List<Partida> partidasJogadasCasa;
    private int totalPartidas;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int totalGolsFeitos;
    private int totalGolsTomados;

    // Construtor, getters e setters
    public PartidaCasaStats(List<Partida> partidasJogadasCasa, int totalPartidas, int vitorias, int empates, int derrotas, int totalGolsFeitos, int totalGolsTomados) {
        this.partidasJogadasCasa = partidasJogadasCasa;
        this.totalPartidas = totalPartidas;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.totalGolsFeitos = totalGolsFeitos;
        this.totalGolsTomados = totalGolsTomados;
    }

    public List<Partida> getPartidasJogadasCasa() {
        return partidasJogadasCasa;
    }

    public void setPartidasJogadasCasa(List<Partida> partidasJogadasCasa) {
        this.partidasJogadasCasa = partidasJogadasCasa;
    }

    public int getTotalPartidas() {
        return totalPartidas;
    }

    public void setTotalPartidas(int totalPartidas) {
        this.totalPartidas = totalPartidas;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getTotalGolsFeitos() {
        return totalGolsFeitos;
    }

    public void setTotalGolsFeitos(int totalGolsFeitos) {
        this.totalGolsFeitos = totalGolsFeitos;
    }

    public int getTotalGolsTomados() {
        return totalGolsTomados;
    }

    public void setTotalGolsTomados(int totalGolsTomados) {
        this.totalGolsTomados = totalGolsTomados;
    }
}
