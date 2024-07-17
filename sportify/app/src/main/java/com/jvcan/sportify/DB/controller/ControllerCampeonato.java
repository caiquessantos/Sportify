package com.jvcan.sportify.DB.controller;

import android.content.Context;

import com.jvcan.sportify.DB.DAO.CampeonatoDAO;
import com.jvcan.sportify.DB.model.Campeonato;
import com.jvcan.sportify.DB.model.Time;

import java.util.List;

/**
 * Controller para gerenciar operações relacionadas a Campeonatos.
 */
public class ControllerCampeonato {
    private CampeonatoDAO campeonatoDAO;

    /**
     * Construtor para inicializar o ControllerCampeonato com o contexto.
     *
     * @param context O contexto da aplicação.
     */
    public ControllerCampeonato(Context context) {
        this.campeonatoDAO = new CampeonatoDAO(context);
    }

    /**
     * Cria um novo campeonato.
     *
     * @param usuario         O usuário que cria o campeonato.
     * @param nome_campeonato O nome do campeonato.
     * @param modalidade      A modalidade do campeonato.
     * @param descricao       A descrição do campeonato.
     * @param foto_campeonato A foto do campeonato.
     * @return true se o campeonato for criado com sucesso, caso contrário, false.
     */
    public boolean create(String usuario, String nome_campeonato, String modalidade, String descricao, String foto_campeonato) {
        return campeonatoDAO.create(new Campeonato(usuario, nome_campeonato, modalidade, descricao, foto_campeonato));
    }

    /**
     * Lê um campeonato específico.
     *
     * @param id_campeonato O ID do campeonato.
     * @return O objeto Campeonato se encontrado, caso contrário, null.
     */
    public Campeonato read(int id_campeonato) {
        return campeonatoDAO.read(id_campeonato);
    }

    /**
     * Atualiza o nome de um campeonato.
     *
     * @param id_campeonato      O ID do campeonato.
     * @param novoNomeCampeonato O novo nome do campeonato.
     * @return true se a atualização for bem-sucedida, caso contrário, false.
     */
    public boolean updateNomeCampeonato(int id_campeonato, String novoNomeCampeonato) {
        Campeonato campeonato = campeonatoDAO.read(id_campeonato);
        campeonato.setNome_campeonato(novoNomeCampeonato);
        return campeonatoDAO.update(campeonato);
    }

    /**
     * Atualiza a modalidade de um campeonato.
     *
     * @param id_campeonato  O ID do campeonato.
     * @param novaModalidade A nova modalidade do campeonato.
     * @return true se a atualização for bem-sucedida, caso contrário, false.
     */
    public boolean updateModalidade(int id_campeonato, String novaModalidade) {
        Campeonato campeonato = campeonatoDAO.read(id_campeonato);
        campeonato.setModalidade(novaModalidade);
        return campeonatoDAO.update(campeonato);
    }

    /**
     * Atualiza a descrição de um campeonato.
     * @param id_campeonato O ID do campeonato.
     * @param novaDescricao A nova descrição do campeonato.
     * @return true se a atualização for bem-sucedida, caso contrário, false.
     */
    public boolean updateDescricao(int id_campeonato, String novaDescricao) {
        Campeonato campeonato = campeonatoDAO.read(id_campeonato);
        campeonato.setDescricao(novaDescricao);
        return campeonatoDAO.update(campeonato);
    }

    /**
     * Atualiza a foto de um campeonato.
     *
     * @param id_campeonato      O ID do campeonato.
     * @param novaFotoCampeonato A nova foto do campeonato.
     * @return true se a atualização for bem-sucedida, caso contrário, false.
     */
    public boolean updateFotoCampeonato(int id_campeonato, String novaFotoCampeonato) {
        Campeonato campeonato = campeonatoDAO.read(id_campeonato);
        campeonato.setFoto_campeonato(novaFotoCampeonato);
        return campeonatoDAO.update(campeonato);
    }

    /**
     * Deleta um campeonato específico.
     *
     * @param id_campeonato O ID do campeonato.
     * @return true se a exclusão for bem-sucedida, caso contrário, false.
     */
    public boolean delete(int id_campeonato) {
        return campeonatoDAO.delete(id_campeonato);
    }

    /**
     * Recupera todos os campeonatos associados a um usuário específico com base no email do usuário.
     *
     * @param email O email do usuário cujos campeonatos devem ser recuperados.
     * @return Uma lista contendo todos os campeonatos associados ao usuário com o email fornecido. Retorna uma lista vazia se nenhum campeonato for encontrado.
     */
    public List<Campeonato> getAllByUser(String email) {
        return campeonatoDAO.getAllByUser(email);
    }
    /**
     * Retorna uma lista de todos os times que participam de um campeonato específico.
     *
     * @param id_campeonato O ID do campeonato para o qual os times devem ser retornados.
     * @return Uma lista de objetos Time que participam do campeonato especificado.
     */
    public List<Time> getAllTimes(int id_campeonato){
        return campeonatoDAO.getAllTimes(id_campeonato);
    }



    /**
     * Obtém a lista de todos os campeonatos.
     *
     * @return Uma lista de objetos Campeonato.
     */
    public List<Campeonato> getAll() {
        return campeonatoDAO.getAll();
    }
}
