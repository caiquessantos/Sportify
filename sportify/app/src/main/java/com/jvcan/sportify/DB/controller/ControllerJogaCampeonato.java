package com.jvcan.sportify.DB.controller;

import android.content.Context;

import com.jvcan.sportify.DB.DAO.JogaCampeonatoDAO;
import com.jvcan.sportify.DB.interfaces.iJogaCampeonatoDAO;
import com.jvcan.sportify.DB.model.JogaCampeonato;

import java.util.List;

/**
 * Controlador para gerenciar as operações relacionadas à entidade JogaCampeonato.
 */
public class ControllerJogaCampeonato {
    private iJogaCampeonatoDAO jogaCampeonatoDAO;

    /**
     * Construtor da classe ControllerJogaCampeonato.
     *
     * @param context O contexto da aplicação.
     */
    public ControllerJogaCampeonato(Context context) {
        this.jogaCampeonatoDAO = new JogaCampeonatoDAO(context);
    }

    /**
     * Cria uma nova associação entre um time e um campeonato.
     *
     * @param id_time O ID do time.
     * @param id_campeonato O ID do campeonato.
     * @return true se a associação for criada com sucesso, false caso contrário.
     */
    public boolean create(int id_time, int id_campeonato) {
        return jogaCampeonatoDAO.create(new JogaCampeonato(id_time, id_campeonato));
    }

    /**
     * Lê uma associação específica entre um time e um campeonato.
     *
     * @param id_time O ID do time.
     * @param id_campeonato O ID do campeonato.
     * @return A associação JogaCampeonato correspondente, ou null se não encontrada.
     */
    public JogaCampeonato read(int id_time, int id_campeonato) {
        return jogaCampeonatoDAO.read(id_time, id_campeonato);
    }

    /**
     * Recupera todas as associações para um campeonato específico.
     *
     * @param id_campeonato O ID do campeonato.
     * @return Uma lista contendo todas as associações JogaCampeonato para o campeonato fornecido.
     */
    public List<JogaCampeonato> getAllByCampeonatoId(int id_campeonato) {
        return jogaCampeonatoDAO.getAllByCampeonatoId(id_campeonato);
    }

    /**
     * Recupera todas as associações para um time específico.
     *
     * @param id_time O ID do time.
     * @return Uma lista contendo todas as associações JogaCampeonato para o time fornecido.
     */
    public List<JogaCampeonato> getAllByTimeId(int id_time) {
        return jogaCampeonatoDAO.getAllByTimeId(id_time);
    }

    /**
     * Recupera todas as associações JogaCampeonato.
     *
     * @return Uma lista contendo todas as associações JogaCampeonato.
     */
    public List<JogaCampeonato> getAll() {
        return jogaCampeonatoDAO.getAll();
    }
}

