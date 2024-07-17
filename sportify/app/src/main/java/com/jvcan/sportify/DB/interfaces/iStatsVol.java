package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.*;
import java.util.List;

/**
 * Interface que define operações CRUD e consultas estatísticas relacionadas aos volantes (jogadores de meio-campo defensivo).
 */
public interface iStatsVol {

    /**
     * Cria um novo registro de estatísticas de um volante em uma partida.
     *
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador (volante).
     * @param pontos    Quantidade de pontos acumulados pelo jogador.
     * @param bloqueios Quantidade de bloqueios realizados pelo jogador.
     * @return True se o registro foi criado com sucesso, false caso contrário.
     */
    public Boolean create(Long idPartida, Long idJogador, int pontos, int bloqueios);

    /**
     * Atualiza as estatísticas de um volante em uma partida.
     *
     * @param idStats   ID das estatísticas a serem atualizadas.
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador (volante).
     * @param pontos    Quantidade de pontos acumulados pelo jogador.
     * @param bloqueios Quantidade de bloqueios realizados pelo jogador.
     * @return True se a atualização foi bem-sucedida, false caso contrário.
     */
    public Boolean update(Long idStats, Long idPartida, Long idJogador, int pontos, int bloqueios);

    /**
     * Lê as estatísticas de um volante em uma partida específica.
     *
     * @param idStats   ID das estatísticas.
     * @param idpartida ID da partida.
     * @param idjogador ID do jogador (volante).
     * @return Um objeto VolStats contendo as estatísticas do volante na partida,
     *         ou null se não encontrado.
     */
    public VolStats read(Long idStats, Long idpartida, Long idjogador);

    /**
     * Deleta as estatísticas de um volante em uma partida.
     *
     * @param idStats   ID das estatísticas a serem deletadas.
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador (volante).
     * @return True se as estatísticas foram deletadas com sucesso, false caso contrário.
     */
    public Boolean delete(Long idStats, Long idPartida, Long idJogador);

    /**
     * Obtém todos os registros de estatísticas de volantes.
     *
     * @return Uma lista de objetos VolStats representando todas as estatísticas de volantes registradas.
     */
    public List<VolStats> getData();

    /**
     * Obtém uma lista dos principais artilheiros (volantes com mais pontos acumulados) em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Uma lista de objetos ArtilhariaVol representando os principais artilheiros (volantes).
     */
    public List<ArtilhariaVol> getTopScorers(Long idcampeonato);

    /**
     * Obtém uma lista dos principais bloqueadores (volantes com mais bloqueios realizados) em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Uma lista de objetos Bloqueadores representando os principais bloqueadores (volantes).
     */
    public List<Bloqueadores> getTopBlockers(Long idcampeonato);
}
