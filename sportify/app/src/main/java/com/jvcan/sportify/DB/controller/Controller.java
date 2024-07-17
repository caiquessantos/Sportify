package com.jvcan.sportify.DB.controller;

        import android.content.Context;
        import com.jvcan.sportify.DB.interfaces.*;
        import com.jvcan.sportify.DB.model.*;
        import com.jvcan.sportify.DB.DAO.*;
        import java.time.*;
        import java.util.List;

/**
 * Classe de controle que encapsula a lógica de negócio para operações relacionadas a partidas, estatísticas de futebol e voleibol,
 * e jogos de partidas (mandante/visitante).
 */
public class Controller {

    private iMatches imatches;
    private iStatsFut istatsfut;
    private iStatsVol istatsvol;
    private iPlayMatch iplaymatch;

    /**
     * Construtor da classe Control.
     *
     * @param context Contexto da aplicação Android para acesso aos DAOs e ao banco de dados.
     */
    public Controller(Context context) {
        this.imatches = new MatchesDAO(context);
        this.istatsfut = new StatsFutDAO(context);
        this.istatsvol = new StatsVolDAO(context);
        this.iplaymatch = new PlayMatchDAO(context);
    }

    // Métodos destinados a operações com partidas (iMatches)

    /**
     * Cria uma nova partida no sistema.
     *
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @param local       Local onde a partida será realizada.
     * @param data        Data da partida.
     * @param horario     Horário da partida.
     * @param placarTime1 Placar do time 1.
     * @param placarTime2 Placar do time 2.
     * @return True se a operação de criação for bem-sucedida, false caso contrário.
     */
    public long createMatch(Long idcampeonato, String local, String data, String horario, int placarTime1, int placarTime2) {
        return imatches.create(idcampeonato, local, data, horario, placarTime1, placarTime2);
    }

    /**
     * Atualiza os detalhes de uma partida existente.
     *
     * @param idPartida   ID da partida.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @param local       Local onde a partida será realizada.
     * @param data        Data da partida.
     * @param horario     Horário da partida.
     * @param placarTime1 Placar do time 1.
     * @param placarTime2 Placar do time 2.
     * @return True se a atualização for bem-sucedida, false caso contrário.
     */
    public Boolean updateMatch(Long  idPartida, Long idcampeonato, String local, String data, String horario, int placarTime1, int placarTime2){
        return this.imatches.update(idPartida, idcampeonato, local, data, horario, placarTime1, placarTime2);
    }


    /**
     * Retorna os detalhes de uma partida específica.
     *
     * @param idpartida   ID da partida.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @return Objeto Partida contendo os detalhes da partida, ou null se não encontrada.
     */
    public Partida readMatch(Long idpartida, Long idcampeonato){
        return this.imatches.read(idpartida, idcampeonato);
    }

    /**
     * Deleta uma partida específica do sistema.
     *
     * @param idpartida    ID da partida.
     * @param idCampeonato ID do campeonato ao qual a partida pertence.
     * @return True se a exclusão for bem-sucedida, false caso contrário.
     */
    public Boolean deleteMatch(Long idpartida,Long idCampeonato){
        return this.imatches.delete(idpartida, idCampeonato);
    }

    /**
     * Retorna uma lista de todas as partidas cadastradas no sistema.
     *
     * @return Lista de objetos Partida contendo todas as partidas cadastradas.
     */
    public List<Partida> readMatches(Long id){
        return imatches.getData(id);
    }

    /**
     * Retorna as estatísticas das partidas em que um time atua como mandante.
     *
     * @param idcampeonato ID do campeonato.
     * @param idtime       ID do time.
     * @return Objeto PartidaCasaStats contendo as estatísticas das partidas como mandante.
     */
    public PartidaCasaStats getHomeMatches(Long idcampeonato, Long idtime){
        return this.imatches.getHomeMatches(idcampeonato, idtime);
    }

    /**
     * Retorna as estatísticas das partidas em que um time atua como visitante.
     *
     * @param idcampeonato ID do campeonato.
     * @param idtime       ID do time.
     * @return Objeto PartidaForaStats contendo as estatísticas das partidas como visitante.
     */
    public PartidaForaStats getAwayMatches(Long idcampeonato, Long idtime){
        return this.imatches.getAwayMatches(idcampeonato, idtime);
    }

    /**
     * Retorna as estatísticas gerais de um time em um campeonato.
     *
     * @param idCampeonato       ID do Campeonato.
     * @return Objeto PartidaStats contendo as estatísticas gerais do time no campeonato.
     */
    public List<PartidaStats> getPartidaStats(Long idCampeonato){
        return imatches.getPartidaStats(idCampeonato);
    }

    /**
     * Retorna a lista de jogadores de ambas as equipes em uma partida específica.
     *
     * @param idPartida ID da partida.
     * @return Lista contendo duas listas (time A e time B) de objetos JogadorTime.
     */
    public List<List<JogadorTime>> getMatchPlayers(long idPartida){
        return imatches.getMatchPlayers(idPartida);
    }

    // Métodos destinados a operações com estatísticas de futebol (iStatsFut)

    /**
     * Cria um registro de estatísticas de um jogador de futebol em uma partida.
     *
     * @param idPartida         ID da partida.
     * @param idJogador         ID do jogador.
     * @param gols              Número de gols marcados pelo jogador.
     * @param assists           Número de assistências feitas pelo jogador.
     * @param cartoesAmarelos   Número de cartões amarelos recebidos pelo jogador.
     * @param cartoesVermelhos  Número de cartões vermelhos recebidos pelo jogador.
     * @return True se o registro foi criado com sucesso, false caso contrário.
     */
    public boolean createStatsFut(Long idPartida, Long idJogador, int gols, int assists, int cartoesAmarelos, int cartoesVermelhos) {
        return istatsfut.create(idPartida, idJogador, gols, assists, cartoesAmarelos, cartoesVermelhos);
    }

    /**
     * Retorna as estatísticas de um jogador de futebol em uma partida específica.
     *
     * @param idStats   ID das estatísticas.
     * @param idpartida ID da partida.
     * @param idjogador ID do jogador.
     * @return Objeto FutStats contendo as estatísticas do jogador na partida, ou null se não encontrado.
     */
    public FutStats readStatsFut(Long idStats, Long idpartida, Long idjogador){
        return this.istatsfut.read(idStats, idpartida, idjogador);
    }

    /**
     * Atualiza as estatísticas de um jogador de futebol em uma partida.
     *
     * @param idStats           ID das estatísticas a serem atualizadas.
     * @param idPartida         ID da partida.
     * @param idJogador         ID do jogador.
     * @param gols              Novo número de gols marcados pelo jogador.
     * @param assists           Nova quantidade de assistências feitas pelo jogador.
     * @param cartoesAmarelos   Novo número de cartões amarelos recebidos pelo jogador.
     * @param cartoesVermelhos  Novo número de cartões vermelhos recebidos pelo jogador.
     * @return True se a atualização foi bem-sucedida, false caso contrário.
     */
    public boolean updateStatsFut(Long idStats,Long idPartida, Long idJogador, int gols, int assists, int cartoesAmarelos, int cartoesVermelhos){
        return this.istatsfut.update(idStats, idPartida, idJogador, gols, assists, cartoesAmarelos, cartoesVermelhos);
    }

    /**
     * Deleta as estatísticas de um jogador de futebol em uma partida específica.
     *
     * @param idStats   ID das estatísticas a serem deletadas.
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador.
     * @return True se as estatísticas foram deletadas com sucesso, false caso contrário.
     */
    public Boolean deleteStatsFut(Long idStats,Long idPartida, Long idJogador){
        return this.istatsfut.delete(idStats, idPartida, idJogador);
    }

    /**
     * Retorna a lista de todos os registros de estatísticas de futebol.
     *
     * @return Lista de objetos FutStats contendo todas as estatísticas de futebol registradas.
     */
    public List<FutStats> readStatsFut() {
        return istatsfut.getData();
    }

    /**
     * Retorna a lista dos principais artilheiros (jogadores com mais gols marcados) em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Lista de objetos ArtilhariaFut representando os principais artilheiros.
     */
    public List<ArtilhariaFut> getTopScorersFut(Long idcampeonato) {
        return istatsfut.getTopScorers(idcampeonato);
    }

    /**
     * Retorna a lista dos principais assistentes (jogadores com mais assistências) em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Lista de objetos Assistentes representando os principais assistentes.
     */
    public List<Assistentes> getTopAssists(Long idcampeonato){
        return istatsfut.getTopAssists(idcampeonato);
    }

    /**
     * Retorna a lista de cartões amarelos recebidos por jogadores em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Lista de objetos Amarelos representando os cartões amarelos.
     */
    public List<Amarelos> getYellowCards(Long idcampeonato){
        return istatsfut.getYellows(idcampeonato);
    }

    /**
     * Retorna a lista de cartões vermelhos recebidos por jogadores em um campeonato específico.
     *
     * @param idcampeonato ID do campeonato.
     * @return Lista de objetos Vermelhos representando os cartões vermelhos.
     */
    public List<Vermelhos> getRedCards(Long idcampeonato){
        return istatsfut.getRedCards(idcampeonato);
    }


    // Métodos destinados a operações com estatísticas de voleibol (iStatsVol)

    /**
     * Cria um registro de estatísticas de um jogador de voleibol em uma partida.
     *
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador.
     * @param pontos    Número de pontos acumulados pelo jogador.
     * @param bloqueios Número de bloqueios realizados pelo jogador.
     * @return True se o registro foi criado com sucesso, false caso contrário.
     */
    public boolean createStatsVol(Long idPartida, Long idJogador, int pontos, int bloqueios) {
        return istatsvol.create(idPartida, idJogador, pontos, bloqueios);
    }

    /**
     * Retorna as estatísticas de um jogador de voleibol em uma partida específica.
     *
     * @param idStats   ID das estatísticas.
     * @param idpartida ID da partida.
     * @param idjogador ID do jogador.
     * @return Objeto VolStats contendo as estatísticas do jogador na partida, ou null se não encontrado.
     */
    public VolStats readStatsVol(Long idStats, Long idpartida, Long idjogador){
        return this.istatsvol.read(idStats, idpartida, idjogador);
    }

    /**
     * Atualiza as estatísticas de um jogador de voleibol em uma partida.
     *
     * @param idStats   ID das estatísticas a serem atualizadas.
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador.
     * @param pontos    Novo número de pontos acumulados pelo jogador.
     * @param bloqueios Novo número de bloqueios realizados pelo jogador.
     * @return True se a atualização foi bem-sucedida, false caso contrário.
     */
    public boolean updateStatsVol(Long idStats,Long idPartida, Long idJogador, int pontos, int bloqueios){
        return this.istatsvol.update(idStats, idPartida, idJogador, pontos, bloqueios);
    }

    /**
     * Deleta as estatísticas de um jogador de voleibol em uma partida específica.
     *
     * @param idStats   ID das estatísticas a serem deletadas.
     * @param idPartida ID da partida.
     * @param idJogador ID do jogador.
     * @return True se as estatísticas foram deletadas com sucesso, false caso contrário.
     */
    public Boolean deleteStatsVol(Long idStats,Long idPartida, Long idJogador){
        return this.istatsvol.delete(idStats, idPartida, idJogador);
    }

    /**
     * Retorna a lista de todos os registros de estatísticas de voleibol.
     *
     * @return Lista de objetos VolStats contendo todas as estatísticas de voleibol registradas.
     */
    public List<VolStats> readStatsVol() {
        return istatsvol.getData();
    }

    /**
     * Retorna a lista dos principais artilheiros (jogadores com mais pontos acumulados) em um campeonato específico de voleibol.
     *
     * @param idcampeonato ID do campeonato.
     * @return Lista de objetos ArtilhariaVol representando os principais artilheiros.
     */
    public List<ArtilhariaVol> getTopScorersVol(Long idcampeonato){
        return istatsvol.getTopScorers(idcampeonato);
    }

    // Métodos destinados a operações com jogos de partida (iPlayMatch)

    /**
     * Registra a participação de um time em uma partida como mandante ou visitante.
     *
     * @param idtime    ID do time.
     * @param idpartida ID da partida.
     * @param mandante  Boolean indicando se o time é mandante (true) ou visitante (false).
     * @return True se o registro foi criado com sucesso, false caso contrário.
     */
    public boolean createPlayMatch(Long idtime, Long idpartida, Boolean mandante){
        return iplaymatch.create(idtime, idpartida, mandante);
    }

    /**
     * Atualiza a participação de um time em uma partida como mandante ou visitante.
     *
     * @param idtime    ID do time.
     * @param idpartida ID da partida.
     * @param mandante  Boolean indicando se o time é mandante (true) ou visitante (false).
     * @return True se a atualização foi bem-sucedida, false caso contrário.
     */
    public Boolean updatePlayMatch(Long idtime, Long idpartida, Boolean mandante){
        return iplaymatch.update(idtime, idpartida, mandante);
    }

    /**
     * Retorna a participação de um time em uma partida específica.
     *
     * @param idpartida ID da partida.
     * @return Objeto JogaPartida contendo a participação do time na partida, ou null se não encontrado.
     */
    public List<JogaPartida> readPlayMatch(Long idpartida){
        return iplaymatch.read(idpartida);
    }

    /**
     * Deleta a participação de um time em uma partida específica.
     *
     * @param idtime    ID do time.
     * @param idpartida ID da partida.
     * @return True se a participação foi deletada com sucesso, false caso contrário.
     */
    public Boolean deletePlayMatch(Long idtime, Long idpartida){
        return  iplaymatch.delete(idtime, idpartida);
    }
}
