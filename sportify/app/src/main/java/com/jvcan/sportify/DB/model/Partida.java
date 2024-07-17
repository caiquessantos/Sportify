package com.jvcan.sportify.DB.model;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Partida implements Serializable {

        private Long idPartida;
        private Long idcampeonato;
        private String local;
        private String data;
        private String horario;
        private int placarTime1;
        private int placarTime2;

    public Partida() {
        // Construtor padrão
    }

        // Construtor
        public Partida(Long idPartida, Long idcampeonato, String local, String data, String horario, int placarTime1, int placarTime2) {
            this.idPartida = idPartida;
            this.idcampeonato = idcampeonato;
            this.local = local;
            this.data = data;
            this.horario = horario;
            this.placarTime1 = placarTime1;
            this.placarTime2 = placarTime2;
        }

        public Long getIdPartida() {
            return idPartida;
        }

        public void setIdPartida(Long idPartida) {
            this.idPartida = idPartida;
        }

        public Long getIdcampeonato() {
            return idcampeonato;
        }

        public void setIdcampeonato(Long idcampeonato) {
            this.idcampeonato = idcampeonato;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getHorario() {
            return horario;
        }

        public void setHorario(String horario) {
            this.horario = horario;
        }

        public int getPlacarTime1() {
            return placarTime1;
        }

        public void setPlacarTime1(int placarTime1) {
            this.placarTime1 = placarTime1;
        }

        public int getPlacarTime2() {
            return placarTime2;
        }

        public void setPlacarTime2(int placarTime2) {
            this.placarTime2 = placarTime2;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partida)) return false;
        Partida partida = (Partida) o;
        return placarTime1 == partida.placarTime1 &&
                placarTime2 == partida.placarTime2 &&
                idPartida.equals(partida.idPartida) &&
                idcampeonato.equals(partida.idcampeonato) &&
                local.equals(partida.local) &&
                data.equals(partida.data) &&
                horario.equals(partida.horario);
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(idPartida, idcampeonato, local, data, horario, placarTime1, placarTime2);
    }
}
