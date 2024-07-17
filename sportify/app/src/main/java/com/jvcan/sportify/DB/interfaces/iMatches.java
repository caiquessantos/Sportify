package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.*;
import java.time.*;
import java.util.List;

/**
 * Interface que define operações relacionadas a partidas de um campeonato.
 */
public interface iMatches {

    /**
     * Cria uma nova partida no sistema.
     *
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @param local        Local onde a partida será realizada.
     * @param data         Data da partida.
     * @param horario      Horário da partida.
     * @param placarTime1  Placar do time 1.
     * @param placarTime2  Placar do time 2.
     * @return true se a partida foi criada com sucesso, false caso contrário.
     */
    public Long create(Long idcampeonato, String local, String data, String horario, int placarTime1, int placarTime2);

    /**
     * Atualiza os detalhes de uma partida existente.
     *
     * @param idPartida    ID da partida a ser atualizada.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @param local        Local onde a partida será realizada.
     * @param data         Data da partida.
     * @param horario      Horário da partida.
     * @param placarTime1  Placar do time 1.
     * @param placarTime2  Placar do time 2.
     * @return true se a partida foi atualizada com sucesso, false caso contrário.
     */
    public Boolean update(Long idPartida, Long idcampeonato, String local, String data, String horario, int placarTime1,
            int placarTime2);

    /**
     * Busca uma partida específica pelo ID da partida e ID do campeonato.
     *
     * @param idpartida    ID da partida.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @return A partida encontrada, ou null se não encontrada.
     */
    public Partida read(Long idpartida, Long idcampeonato);

    /**
     * Deleta uma partida do sistema pelo ID da partida e ID do campeonato.
     *
     * @param idpartida    ID da partida a ser deletada.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @return true se a partida foi deletada com sucesso, false caso contrário.
     */
    public Boolean delete(Long idpartida, Long idcampeonato);

    /**
     * Retorna uma lista de todas as partidas cadastradas no sistema.
     *
     * @return Lista de todas as partidas cadastradas.
     */
    public List<Partida> getData(Long id);

    /**
     * Retorna as estatísticas das partidas jogadas em casa por um time específico
     * em um campeonato.
     *
     * @param idcampeonato ID do campeonato.
     * @param idtime       ID do time.
     * @return Estatísticas das partidas jogadas em casa pelo time.
     */
    public PartidaCasaStats getHomeMatches(Long idcampeonato, Long idtime);

    /**
     * Retorna as estatísticas das partidas jogadas fora de casa por um time
     * específico em um campeonato.
     *
     * @param idcampeonato ID do campeonato.
     * @param idtime       ID do time.
     * @return Estatísticas das partidas jogadas fora de casa pelo time.
     */
    public PartidaForaStats getAwayMatches(Long idcampeonato, Long idtime);

    /**
     * Retorna os jogadores de cada time que participaram de uma partida específica.
     *
     * @param idPartida ID da partida.
     * @return Uma lista de duas listas de JogadorTime, onde o primeiro elemento
     *         contém os jogadores do time A (mandante) e o segundo os jogadores do
     *         time B (visitante).
     */
    public List<List<JogadorTime>> getMatchPlayers(long idPartida);
}
