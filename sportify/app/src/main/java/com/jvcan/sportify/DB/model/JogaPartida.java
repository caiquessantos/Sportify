package com.jvcan.sportify.DB.model;

import java.io.Serializable;
import java.util.Objects;

public class JogaPartida implements Serializable {
    private Long idTime;
    private Long idPartida;
    private boolean isMandante; // Indica se o time é mandante (true) ou visitante (false)

    // Construtor, getters e setters
    public JogaPartida(Long idTime, Long idPartida, boolean isMandante) {
        this.idTime = idTime;
        this.idPartida = idPartida;
        this.isMandante = isMandante;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public boolean isMandante() {
        return isMandante;
    }

    public void setMandante(boolean mandante) {
        isMandante = mandante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JogaPartida)) return false;
        JogaPartida that = (JogaPartida) o;
        return isMandante == that.isMandante &&
                idTime.equals(that.idTime) &&
                idPartida.equals(that.idPartida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTime, idPartida, isMandante);
    }
}
