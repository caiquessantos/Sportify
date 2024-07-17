package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.*;
import java.util.List;

/**
 * Interface que define operações CRUD e consultas estatísticas relacionadas ao futebol.
 * As implementações desta interface são responsáveis por manipular estatísticas de jogadores
 * e consultar dados estatísticos específicos de um campeonato.
 */
public interface iStatsFut {

    /**
     * Cria um novo registro de estatísticas de um jogador em uma partida.
     *
     * @param idPartida        ID da partida.
     * @param idJogador        ID do jogador.
     * @param gols             Quantidade de gols marcados pelo jogador.
     * @param assists          Quantidade de assistências feitas pelo jogador.
     * @param cartoesAmarelos  Quantidade de cartões amarelos recebidos pelo jogador.
     * @param cartoesVermelhos Quantidade de cartões vermelhos recebidos pelo jogador.
     * @return True se o registro foi criado com sucesso, false caso contrário.
     */
    public Boolean create(Long idPartida, Long idJogador, int gols, int assists, int cartoesAmarelos, int cartoesVermelhos);

    /**
     * Atualiza as estatísticas de um jogador em uma partida.
     *
     * @param idStats          ID das estatísticas a serem atualizadas.
     * @param idPartida        ID da partida.
     * @param idJogador        ID do jogador.
     * @param gols             Quantidade de gols marcados pelo jogador.
     * @param assists          Quantidade de assistências feitas pelo jogador.
     * @param cartoesAmarelos  Quantidade de cartões amarelos recebidos pelo jogador.
     * @param cartoesVermelhos Quantidade de cartões vermelhos recebidos pelo jogador.
     * @return True se a atualização foi bem-sucedida, false caso contrário.
     */
    public Boolean update(Long idStats, Long idPartida, Long idJogador, int gols, int assists, int cartoesAmarelos, int cartoesVermelhos);

    /**
     * Lê as estatísticas de um jogador em uma partida específica.
     *
     * @param idStats   ID das estatísticas.
     * @param idpartida ID da partida.
     * @param idjogador ID do jogador.
     * @return Um objeto FutStats contendo as estatísticas do jogador na partida,
     *         ou null se não encontrado.
     */
    public FutStats read(Long idStats, Long idpartida, Long idjogador);

    /**
     * Deleta as estatísticas de um jogador em uma partida.
     *
     * @param idStats   ID das estatísticas a serem deletadas.
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador.
     * @return True se as estatísticas foram deletadas com sucesso, false caso contrário.
     */
    public Boolean delete(Long idStats, Long idPartida, Long idJogador);

    /**
     * Obtém todos os registros de estatísticas de jogadores.
     *
     * @return Uma lista de objetos FutStats representando todas as estatísticas de jogadores registradas.
     */
    public List<FutStats> getData();

    /**
     * Obtém uma lista de jogadores que receberam cartões vermelhos em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Uma lista de objetos Vermelhos representando os jogadores que receberam cartões vermelhos.
     */
    public List<Vermelhos> getRedCards(Long idcampeonato);

    /**
     * Obtém uma lista dos principais artilheiros (jogadores com mais gols marcados) em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Uma lista de objetos ArtilhariaFut representando os principais artilheiros.
     */
    public List<ArtilhariaFut> getTopScorers(Long idcampeonato);

    /**
     * Obtém uma lista dos principais assistentes (jogadores com mais assistências) em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Uma lista de objetos Assistentes representando os principais assistentes.
     */
    public List<Assistentes> getTopAssists(Long idcampeonato);

    /**
     * Obtém uma lista de jogadores que receberam cartões amarelos em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Uma lista de objetos Amarelos representando os jogadores que receberam cartões amarelos.
     */
    public List<Amarelos> getYellows(Long idcampeonato);
}
