package com.jvcan.sportify.DB.controller;

import android.content.Context;

import com.jvcan.sportify.DB.DAO.JogadorDAO;
import com.jvcan.sportify.DB.model.Jogador;

import java.util.List;

/**
 * ControllerJogador é responsável por gerenciar as operações CRUD para a entidade Jogador.
 */
public class ControllerJogador {
    private JogadorDAO jogadorDAO;

    /**
     * Construtor para inicializar o ControllerJogador com o contexto fornecido.
     *
     * @param context O contexto da aplicação.
     */
    public ControllerJogador(Context context) {
        jogadorDAO = new JogadorDAO(context);
    }

    /**
     * Cria um novo jogador no banco de dados.
     *
     * @param id_time      O ID do time ao qual o jogador pertence.
     * @param nome_jogador O nome do jogador.
     * @param numero       O número do jogador.
     * @return true se o jogador foi criado com sucesso, false caso contrário.
     */
    public boolean create(int id_time, String nome_jogador, String numero) {
        return jogadorDAO.create(new Jogador(id_time, nome_jogador, numero));
    }

    /**
     * Lê os dados de um jogador específico no banco de dados.
     *
     * @param id_jogador O ID do jogador a ser lido.
     * @return O objeto Jogador se encontrado, ou null caso contrário.
     */
    public Jogador read(int id_jogador) {
        return jogadorDAO.read(id_jogador);
    }

    /**
     * Atualiza o nome de um jogador específico no banco de dados.
     *
     * @param id_jogador      O ID do jogador a ser atualizado.
     * @param novoNomeJogador O novo nome do jogador.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public boolean updateNomeJogador(int id_jogador, String novoNomeJogador) {
        Jogador jogador = jogadorDAO.read(id_jogador);
        jogador.setNome_jogador(novoNomeJogador);
        return jogadorDAO.update(jogador);
    }

    /**
     * Atualiza o número de um jogador específico no banco de dados.
     *
     * @param id_jogador O ID do jogador a ser atualizado.
     * @param novoNumero O novo número do jogador.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public boolean updateNumeroJogador(int id_jogador, String novoNumero) {
        Jogador jogador = jogadorDAO.read(id_jogador);
        jogador.setNumero(novoNumero);
        return jogadorDAO.update(jogador);
    }

    /**
     * Deleta um jogador específico do banco de dados.
     *
     * @param id_jogador O ID do jogador a ser deletado.
     * @return true se a deleção foi bem-sucedida, false caso contrário.
     */
    public boolean delete(int id_jogador) {
        return jogadorDAO.delete(id_jogador);
    }

    /**
     * Recupera todos os jogadores associados a um time específico com base no ID do time.
     *
     * @param id_time O ID do time cujos jogadores devem ser recuperados.
     * @return Uma lista contendo todos os jogadores associados ao time com o ID fornecido. Retorna uma lista vazia se nenhum jogador for encontrado.
     */
    public List<Jogador> getAllByTimeId(int id_time) {
        return jogadorDAO.getAllByTimeID(id_time);
    }

    /**
     * Obtém todos os jogadores do banco de dados.
     *
     * @return Uma lista de todos os jogadores.
     */
    public List<Jogador> getAll() {
        return jogadorDAO.getAll();
    }
}

