package com.jvcan.sportify.DB.model;

import java.util.List;

public class PartidaForaStats {
    private List<Partida> partidasJogadasVisitante;
    private int totalPartidas;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int totalGolsFeitos;
    private int totalGolsTomados;

    public PartidaForaStats(List<Partida> partidasJogadasVisitante, int totalPartidas, int vitorias, int empates, int derrotas, int totalGolsFeitos, int totalGolsTomados) {
        this.partidasJogadasVisitante = partidasJogadasVisitante;
        this.totalPartidas = totalPartidas;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.totalGolsFeitos = totalGolsFeitos;
        this.totalGolsTomados = totalGolsTomados;
    }
    public List<Partida> getPartidasJogadasVisitante() {
        return partidasJogadasVisitante;
    }

    public int getTotalPartidas() {
        return totalPartidas;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getTotalGolsFeitos() {
        return totalGolsFeitos;
    }

    public int getTotalGolsTomados() {
        return totalGolsTomados;
    }
}
