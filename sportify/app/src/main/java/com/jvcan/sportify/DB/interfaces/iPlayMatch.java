package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.JogaPartida;

import java.util.List;

/**
 * Interface que define operações CRUD para a tabela JOGA_PARTIDA no banco de dados.
 * As classes que implementam esta interface são responsáveis por manipular registros
 * de participação de times em partidas.
 */
public interface iPlayMatch {

    /**
     * Cria um novo registro de participação de um time em uma partida na tabela JOGA_PARTIDA.
     *
     * @param idtime    Identificador do time que joga a partida.
     * @param idpartida Identificador da partida.
     * @param mandante  Indica se o time é mandante (true) ou visitante (false).
     * @return true se o registro foi criado com sucesso, false caso contrário.
     */
    public boolean create(Long idtime, Long idpartida, Boolean mandante);

    /**
     * Atualiza o registro de participação de um time em uma partida na tabela JOGA_PARTIDA.
     *
     * @param idtime    Identificador do time que joga a partida.
     * @param idpartida Identificador da partida.
     * @param mandante  Indica se o time é mandante (true) ou visitante (false).
     * @return True se a atualização foi bem-sucedida, false caso contrário.
     */
    public Boolean update(Long idtime, Long idpartida, Boolean mandante);

    /**
     * Lê os dados de participação de um time em uma partida na tabela JOGA_PARTIDA.
     *
     * @param idpartida Identificador da partida.
     * @return Um objeto JogaPartida contendo os dados de participação do time na partida,
     *         ou null se não encontrado.
     */
    public List<JogaPartida> read(Long idpartida);

    /**
     * Deleta o registro de participação de um time em uma partida na tabela JOGA_PARTIDA.
     *
     * @param idtime    Identificador do time que joga a partida.
     * @param idpartida Identificador da partida.
     * @return True se o registro foi deletado com sucesso, false caso contrário.
     */
    public Boolean delete(Long idtime, Long idpartida);
}

