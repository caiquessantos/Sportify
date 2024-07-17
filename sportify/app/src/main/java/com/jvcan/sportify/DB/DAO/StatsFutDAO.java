package com.jvcan.sportify.DB.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iStatsFut;
import com.jvcan.sportify.DB.model.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementação da interface iStatsFut que define operações de acesso a dados estatísticos do futebol.
 */
public class StatsFutDAO implements iStatsFut {

    DBCreation db;

    /**
     * Construtor da classe StatsFutDAO.
     *
     * @param context Contexto da aplicação Android para inicializar o banco de dados.
     */
    public StatsFutDAO(Context context) {
        db = new DBCreation(context);
        db.getReadableDatabase();//<- segundo a documentacao o WritableDatabase ja abre o db e permite escrita e leitura
    }

    /**
     * Insere estatísticas de um jogador em uma partida de futebol no banco de dados.
     *
     * @param idPartida         Identificador da partida.
     * @param idJogador         Identificador do jogador.
     * @param gols              Número de gols marcados pelo jogador.
     * @param assists           Número de assistências realizadas pelo jogador.
     * @param cartoesAmarelos   Número de cartões amarelos recebidos pelo jogador.
     * @param cartoesVermelhos  Número de cartões vermelhos recebidos pelo jogador.
     * @return true se a operação de inserção foi bem-sucedida, false caso contrário.
     */
    @Override
    public Boolean create(Long idPartida, Long idJogador, int gols, int assists, int cartoesAmarelos, int cartoesVermelhos) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "INSERT INTO " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL + " (" +
                DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + ", " +
                DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + ", " +
                DBCreation.COLUMN_GOLS + ", " +
                DBCreation.COLUMN_ASSISTENCIAS + ", " +
                DBCreation.COLUMN_CARTOES_AMARELOS + ", " +
                DBCreation.COLUMN_CARTOES_VERMELHOS + ") VALUES (?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(query);
        try {
            statement.bindLong(1, idPartida);
            statement.bindLong(2, idJogador);
            statement.bindLong(3, gols);
            statement.bindLong(4, assists);
            statement.bindLong(5, cartoesAmarelos);
            statement.bindLong(6, cartoesVermelhos);

            statement.executeInsert();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            statement.close();
            database.close();
        }
    }

    /**
     * Atualiza as estatísticas de um jogador em uma partida de futebol no banco de dados.
     *
     * @param idStats           Identificador das estatísticas a serem atualizadas.
     * @param idPartida         Identificador da partida.
     * @param idJogador         Identificador do jogador.
     * @param gols              Número de gols marcados pelo jogador.
     * @param assists           Número de assistências realizadas pelo jogador.
     * @param cartoesAmarelos   Número de cartões amarelos recebidos pelo jogador.
     * @param cartoesVermelhos  Número de cartões vermelhos recebidos pelo jogador.
     * @return true se a operação de atualização foi bem-sucedida, false caso contrário.
     */
    @Override
    public Boolean update(Long idStats, Long idPartida, Long idJogador, int gols, int assists, int cartoesAmarelos, int cartoesVermelhos) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "UPDATE " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL + " SET " +
                DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = ?, " +
                DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " = ?, " +
                DBCreation.COLUMN_GOLS + " = ?, " +
                DBCreation.COLUMN_ASSISTENCIAS + " = ?, " +
                DBCreation.COLUMN_CARTOES_AMARELOS + " = ?, " +
                DBCreation.COLUMN_CARTOES_VERMELHOS + " = ? " +
                "WHERE " + DBCreation.COLUMN_ID_ESTATISTICAS_FUTEBOL + " = ? AND " +
                DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = ? AND " +
                DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " = ?";

        SQLiteStatement statement = database.compileStatement(query);
        try {
            statement.bindLong(1, idPartida);
            statement.bindLong(2, idJogador);
            statement.bindLong(3, gols);
            statement.bindLong(4, assists);
            statement.bindLong(5, cartoesAmarelos);
            statement.bindLong(6, cartoesVermelhos);
            statement.bindLong(7, idStats);
            statement.bindLong(8, idPartida);
            statement.bindLong(9, idJogador);

            int updt = statement.executeUpdateDelete();
            return updt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            statement.close();
            database.close();
        }
    }

    /**
     * Lê as estatísticas de um jogador em uma partida de futebol do banco de dados.
     *
     * @param idStats   Identificador das estatísticas.
     * @param idpartida Identificador da partida.
     * @param idjogador Identificador do jogador.
     * @return Objeto FutStats contendo as estatísticas do jogador na partida especificada,
     *         ou null se não encontradas ou ocorrer um erro.
     */
    @Override
    public FutStats read(Long idStats, Long idpartida, Long idjogador) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "SELECT * FROM " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL + " WHERE "
                + DBCreation.COLUMN_ID_ESTATISTICAS_FUTEBOL + " = ? AND "
                + DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = ? AND "
                + DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " = ?";
        Cursor res = database.rawQuery(query, new String[]{String.valueOf(idStats), String.valueOf(idpartida), String.valueOf(idjogador)});
        try {
            FutStats fs = null;
            if (res != null && res.moveToFirst()){
                int gols = res.getInt(res.getColumnIndex(DBCreation.COLUMN_GOLS));
                int assists = res.getInt(res.getColumnIndex(DBCreation.COLUMN_ASSISTENCIAS));
                int cartoesAmarelos = res.getInt(res.getColumnIndex(DBCreation.COLUMN_CARTOES_AMARELOS));
                int cartoesVermelhos = res.getInt(res.getColumnIndex(DBCreation.COLUMN_CARTOES_VERMELHOS));
                fs = new FutStats(idStats, idpartida, idjogador, gols, assists, cartoesAmarelos, cartoesVermelhos);
            }
            res.close();
            return fs;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }

    /**
     * Deleta as estatísticas de um jogador em uma partida de futebol do banco de dados.
     *
     * @param idStats   Identificador das estatísticas.
     * @param idPartida Identificador da partida.
     * @param idJogador Identificador do jogador.
     * @return true se a operação de deleção foi bem-sucedida, false caso contrário.
     */
    @Override
    public Boolean delete(Long idStats, Long idPartida, Long idJogador) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        try {
            String sql = "DELETE FROM " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL +
                    " WHERE " + DBCreation.COLUMN_ID_ESTATISTICAS_FUTEBOL + " = ?" +
                    " AND " + DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = ?" +
                    " AND " + DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " = ?";

            statement = database.compileStatement(sql);
            statement.bindLong(1, idStats);
            statement.bindLong(2, idPartida);
            statement.bindLong(3, idJogador);

            int rowsDeleted = statement.executeUpdateDelete();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                statement.close();
            }
            database.close();
        }
    }

    /**
     * Obtém todas as estatísticas de jogadores em partidas de futebol do banco de dados.
     *
     * @return Lista de objetos FutStats contendo todas as estatísticas de jogadores em partidas,
     *         ou null se ocorrer um erro.
     */
    @Override
    public List<FutStats> getData() {
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+DBCreation.TABLE_ESTATISTICAS_FUTEBOL, null);
        try {
            List<FutStats> fsList = new ArrayList<>();
            if (cursor != null){
                while (cursor.moveToNext()){
                    Long idStats = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_ESTATISTICAS_FUTEBOL));
                    Long idPartida = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_PARTIDA_FUTEBOL));
                    Long idJogador = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR_FUTEBOL));
                    int gols = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_GOLS));
                    int assistencias = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ASSISTENCIAS));
                    int cartoesA = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_CARTOES_AMARELOS));
                    int cartoesV = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_CARTOES_VERMELHOS));

                    FutStats fs = new FutStats(idStats, idPartida, idJogador, gols, assistencias, cartoesA, cartoesV);
                    fsList.add(fs);
                }
                cursor.close();
            }
            return fsList;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }

    /**
     * Obtém a lista de jogadores que receberam cartões vermelhos em um campeonato específico.
     *
     * @param idcampeonato Identificador do campeonato.
     * @return Lista de objetos Vermelhos contendo informações dos jogadores que receberam cartões vermelhos,
     *         ordenados pelo número total de cartões vermelhos em ordem decrescente,
     *         ou null se ocorrer um erro.
     */
    @Override
    public List<Vermelhos> getRedCards(Long idcampeonato) {
        SQLiteDatabase database = db.getReadableDatabase();

        try {
            List<Vermelhos> jogadorVermelhadoList = new ArrayList<>();

            String query = "SELECT j." + DBCreation.COLUMN_ID_JOGADOR + ", j." + DBCreation.COLUMN_NOME_JOGADOR + ", " +
                    "SUM(ef." + DBCreation.COLUMN_CARTOES_VERMELHOS + ") AS total_vermelhos " +
                    "FROM " + DBCreation.TABLE_JOGADOR + " j " +
                    "JOIN " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL + " ef ON j." + DBCreation.COLUMN_ID_JOGADOR + " = ef." + DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " " +
                    "JOIN " + DBCreation.TABLE_PARTIDA + " p ON ef." + DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = p." + DBCreation.COLUMN_ID_PARTIDA + " " +
                    "JOIN " + DBCreation.TABLE_JOGA_CAMPEONATO + " jc ON p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " " +
                    "WHERE jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " = ? " +
                    "GROUP BY j." + DBCreation.COLUMN_ID_JOGADOR + ", j." + DBCreation.COLUMN_NOME_JOGADOR + " " +
                    "ORDER BY total_vermelhos DESC";

            Cursor res = database.rawQuery(query, new String[]{String.valueOf(idcampeonato)});

            if (res != null && res.moveToFirst()) {
                do {
                    Long idJogador = res.getLong(res.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR));
                    String nomeJogador = res.getString(res.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                    int totalVermelhos = res.getInt(res.getColumnIndex("total_vermelhos"));
                    jogadorVermelhadoList.add(new Vermelhos(idJogador, idcampeonato, nomeJogador, totalVermelhos));
                } while (res.moveToNext());
            }
            res.close();
            return jogadorVermelhadoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }

    /**
     * Obtém a lista de jogadores que foram os principais artilheiros em um campeonato específico.
     *
     * @param idcampeonato Identificador do campeonato.
     * @return Lista de objetos ArtilhariaFut contendo informações dos jogadores artilheiros,
     *         ordenados pelo número total de gols marcados em ordem decrescente,
     *         ou null se ocorrer um erro.
     */
    @Override
    public List<ArtilhariaFut> getTopScorers(Long idcampeonato) {
        SQLiteDatabase database = db.getReadableDatabase();

        try{
            List<ArtilhariaFut> artilhariaFutList = new ArrayList<>();

            String query = "SELECT j." + DBCreation.COLUMN_NOME_JOGADOR + ", SUM(ef." + DBCreation.COLUMN_GOLS + ") AS total_gols " +
                    "FROM " + DBCreation.TABLE_JOGADOR + " j " +
                    "JOIN " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL + " ef ON j." + DBCreation.COLUMN_ID_JOGADOR + " = ef." + DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " " +
                    "JOIN " + DBCreation.TABLE_PARTIDA + " p ON ef." + DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = p." + DBCreation.COLUMN_ID_PARTIDA + " " +
                    "JOIN " + DBCreation.TABLE_JOGA_CAMPEONATO + " jc ON p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " " +
                    "WHERE jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " = ? " +
                    "GROUP BY j." + DBCreation.COLUMN_NOME_JOGADOR + " " +
                    "ORDER BY total_gols DESC";

            Cursor res = database.rawQuery(query, new String[]{String.valueOf(idcampeonato)});

            if (res != null && res.moveToFirst()) {
                do {
                    Long idjogador = res.getLong(res.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR_FUTEBOL));
                    String nomeJogador = res.getString(res.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                    int totalGols = res.getInt(res.getColumnIndex("total_gols"));
                    artilhariaFutList.add(new ArtilhariaFut(idjogador, idcampeonato ,nomeJogador, totalGols));
                } while (res.moveToNext());
            }
            res.close();
            return artilhariaFutList;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }

    /**
     * Obtém a lista de jogadores que foram os principais assistentes em um campeonato específico.
     *
     * @param idcampeonato Identificador do campeonato.
     * @return Lista de objetos Assistentes contendo informações dos jogadores que mais deram assistências,
     *         ordenados pelo número total de assistências realizadas em ordem decrescente,
     *         ou null se ocorrer um erro.
     */
    @Override
    public List<Assistentes> getTopAssists(Long idcampeonato) {
        SQLiteDatabase database = db.getReadableDatabase();

        try {
            List<Assistentes> garconList = new ArrayList<>();

            String query = "SELECT j." + DBCreation.COLUMN_ID_JOGADOR + ", j." + DBCreation.COLUMN_NOME_JOGADOR + ", " +
                    "SUM(ef." + DBCreation.COLUMN_ASSISTENCIAS + ") AS total_assistencias " +
                    "FROM " + DBCreation.TABLE_JOGADOR + " j " +
                    "JOIN " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL + " ef ON j." + DBCreation.COLUMN_ID_JOGADOR + " = ef." + DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " " +
                    "JOIN " + DBCreation.TABLE_PARTIDA + " p ON ef." + DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = p." + DBCreation.COLUMN_ID_PARTIDA + " " +
                    "JOIN " + DBCreation.TABLE_JOGA_CAMPEONATO + " jc ON p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " " +
                    "WHERE jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " = ? " +
                    "GROUP BY j." + DBCreation.COLUMN_ID_JOGADOR + ", j." + DBCreation.COLUMN_NOME_JOGADOR + " " +
                    "ORDER BY total_assistencias DESC";

            Cursor res = database.rawQuery(query, new String[]{String.valueOf(idcampeonato)});

            if (res != null && res.moveToFirst()) {
                do {
                    Long idJogador = res.getLong(res.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR));
                    String nomeJogador = res.getString(res.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                    int totalAssistencias = res.getInt(res.getColumnIndex("total_assistencias"));
                    garconList.add(new Assistentes(idJogador, idcampeonato,nomeJogador, totalAssistencias));
                } while (res.moveToNext());
            }
            res.close();
            return garconList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }

    /**
     * Obtém a lista de jogadores que receberam cartões amarelos em um campeonato específico.
     *
     * @param idcampeonato Identificador do campeonato.
     * @return Lista de objetos Amarelos contendo informações dos jogadores que receberam cartões amarelos,
     *         ordenados pelo número total de cartões amarelos recebidos em ordem decrescente,
     *         ou null se ocorrer um erro.
     */
    @Override
    public List<Amarelos> getYellows(Long idcampeonato){
        SQLiteDatabase database = db.getReadableDatabase();

        try {
            List<Amarelos> jogadoresAmarelados = new ArrayList<>();

            String query = "SELECT j." + DBCreation.COLUMN_NOME_JOGADOR + ", SUM(ef." + DBCreation.COLUMN_CARTOES_AMARELOS + ") AS total_cartoes " +
                    "FROM " + DBCreation.TABLE_JOGADOR + " j " +
                    "JOIN " + DBCreation.TABLE_ESTATISTICAS_FUTEBOL + " ef ON j." + DBCreation.COLUMN_ID_JOGADOR + " = ef." + DBCreation.COLUMN_ID_JOGADOR_FUTEBOL + " " +
                    "JOIN " + DBCreation.TABLE_PARTIDA + " p ON ef." + DBCreation.COLUMN_ID_PARTIDA_FUTEBOL + " = p." + DBCreation.COLUMN_ID_PARTIDA + " " +
                    "JOIN " + DBCreation.TABLE_JOGA_CAMPEONATO + " jc ON p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " " +
                    "WHERE jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " = ? " +
                    "GROUP BY j." + DBCreation.COLUMN_NOME_JOGADOR + " " +
                    "ORDER BY total_cartoes DESC";

            Cursor res = database.rawQuery(query, new String[]{String.valueOf(idcampeonato)});

            if (res != null && res.moveToFirst()) {
                do {
                    Long idjogador = res.getLong(res.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR_FUTEBOL));
                    String nomeJogador = res.getString(res.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                    int totalCartoesAmarelos = res.getInt(res.getColumnIndex("total_cartoes"));
                    jogadoresAmarelados.add(new Amarelos(idjogador, idcampeonato, nomeJogador, totalCartoesAmarelos));
                } while (res.moveToNext());
            }
            res.close();
            return jogadoresAmarelados;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }
}
