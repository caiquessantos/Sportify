package com.jvcan.sportify.DB.model;

import java.util.List;

public class PartidaJogadores {
    private List<JogadorTime> jogadoresTimeA;
    private List<JogadorTime> jogadoresTimeB;

    // Construtor, getters e setters
    public PartidaJogadores(List<JogadorTime> jogadoresTimeA, List<JogadorTime> jogadoresTimeB) {
        this.jogadoresTimeA = jogadoresTimeA;
        this.jogadoresTimeB = jogadoresTimeB;
    }

    public List<JogadorTime> getJogadoresTimeA() {
        return jogadoresTimeA;
    }

    public void setJogadoresTimeA(List<JogadorTime> jogadoresTimeA) {
        this.jogadoresTimeA = jogadoresTimeA;
    }

    public List<JogadorTime> getJogadoresTimeB() {
        return jogadoresTimeB;
    }

    public void setJogadoresTimeB(List<JogadorTime> jogadoresTimeB) {
        this.jogadoresTimeB = jogadoresTimeB;
    }
}
