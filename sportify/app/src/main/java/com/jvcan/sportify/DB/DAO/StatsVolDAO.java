package com.jvcan.sportify.DB.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.jvcan.sportify.DB.interfaces.iStatsVol;
import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.model.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe DAO para operações CRUD e consultas relacionadas a estatísticas de jogadores de vôlei.
 */
public class StatsVolDAO implements iStatsVol {

    DBCreation db;

    /**
     * Construtor da classe StatsVolDAO.
     *
     * @param context Contexto da aplicação Android.
     */
    public StatsVolDAO(Context context) {
        db = new DBCreation(context);
        db.getReadableDatabase();
    }

    /**
     * Cria novas estatísticas de vôlei para um jogador em uma partida.
     *
     * @param idPartida Identificador da partida.
     * @param idJogador Identificador do jogador.
     * @param pontos    Pontos marcados pelo jogador na partida.
     * @param bloqueios Número de bloqueios realizados pelo jogador na partida.
     * @return true se a operação de inserção foi bem-sucedida, false caso contrário.
     */
    @Override
    public Boolean create(Long idPartida, Long idJogador, int pontos, int bloqueios) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "INSERT INTO " + DBCreation.TABLE_ESTATISTICAS_VOLEI + " (" +
                DBCreation.COLUMN_ID_PARTIDA_VOLEI + ", " +
                DBCreation.COLUMN_ID_JOGADOR_VOLEI + ", " +
                DBCreation.COLUMN_PONTOS + ", " +
                DBCreation.COLUMN_BLOQUEIOS + ") VALUES (?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(query);
        try {
            statement.bindLong(1, idPartida);
            statement.bindLong(2, idJogador);
            statement.bindLong(3, pontos);
            statement.bindLong(4, bloqueios);

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
     * Atualiza as estatísticas de vôlei existentes para um jogador em uma partida.
     *
     * @param idStats   Identificador das estatísticas a serem atualizadas.
     * @param idPartida Identificador da partida.
     * @param idJogador Identificador do jogador.
     * @param pontos    Novo valor de pontos marcados pelo jogador na partida.
     * @param bloqueios Novo valor de bloqueios realizados pelo jogador na partida.
     * @return true se a operação de atualização foi bem-sucedida, false caso contrário.
     */
    @Override
    public Boolean update(Long idStats, Long idPartida, Long idJogador, int pontos, int bloqueios) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "UPDATE " + DBCreation.TABLE_ESTATISTICAS_VOLEI + " SET " +
                DBCreation.COLUMN_ID_PARTIDA_VOLEI + " = ?, " +
                DBCreation.COLUMN_ID_JOGADOR_VOLEI + " = ?, " +
                DBCreation.COLUMN_PONTOS + " = ?, " +
                DBCreation.COLUMN_BLOQUEIOS + " = ? " +
                "WHERE " + DBCreation.COLUMN_ID_ESTATISTICAS_VOLEI + " = ? AND " +
                DBCreation.COLUMN_ID_PARTIDA_VOLEI + " = ? AND " +
                DBCreation.COLUMN_ID_JOGADOR_VOLEI + " = ?";

        SQLiteStatement statement = database.compileStatement(query);
        try {
            statement.bindLong(1, idPartida);
            statement.bindLong(2, idJogador);
            statement.bindLong(3, pontos);
            statement.bindLong(4, bloqueios);
            statement.bindLong(5, idStats);
            statement.bindLong(6, idPartida);
            statement.bindLong(7, idJogador);

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
     * Lê as estatísticas de vôlei de um jogador em uma partida específica.
     *
     * @param idStats   Identificador das estatísticas a serem lidas.
     * @param idPartida Identificador da partida.
     * @param idJogador Identificador do jogador.
     * @return Objeto VolStats contendo as estatísticas lidas, ou null se não encontradas.
     */
    @Override
    public VolStats read(Long idStats, Long idPartida, Long idJogador) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "SELECT * FROM " + DBCreation.TABLE_ESTATISTICAS_VOLEI + " WHERE "
                + DBCreation.COLUMN_ID_ESTATISTICAS_VOLEI + " = ? AND "
                + DBCreation.COLUMN_ID_PARTIDA_VOLEI + " = ? AND "
                + DBCreation.COLUMN_ID_JOGADOR_VOLEI + " = ?";
        Cursor res = database.rawQuery(query, new String[]{String.valueOf(idStats), String.valueOf(idPartida), String.valueOf(idJogador)});
        try {
            VolStats vs = null;
            if (res != null && res.moveToFirst()){
                int pontos = res.getInt(res.getColumnIndex(DBCreation.COLUMN_PONTOS));
                int bloqueios = res.getInt(res.getColumnIndex(DBCreation.COLUMN_BLOQUEIOS));
                vs = new VolStats(idStats, idPartida, idJogador, pontos, bloqueios);
            }
            res.close();
            return vs;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }

    /**
     * Deleta as estatísticas de vôlei específicas associadas a um jogador em uma partida.
     *
     * @param idStats   Identificador das estatísticas a serem deletadas.
     * @param idPartida Identificador da partida das estatísticas.
     * @param idJogador Identificador do jogador das estatísticas.
     * @return true se as estatísticas foram deletadas com sucesso, false caso contrário.
     */
    @Override
    public Boolean delete(Long idStats, Long idPartida, Long idJogador) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        try {
            String sql = "DELETE FROM " + DBCreation.TABLE_ESTATISTICAS_VOLEI +
                    " WHERE " + DBCreation.COLUMN_ID_ESTATISTICAS_VOLEI + " = ?" +
                    " AND " + DBCreation.COLUMN_ID_PARTIDA_VOLEI + " = ?" +
                    " AND " + DBCreation.COLUMN_ID_JOGADOR_VOLEI + " = ?";

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
     * Retorna uma lista contendo todas as estatísticas de vôlei registradas no banco de dados.
     *
     * @return Lista de objetos VolStats representando as estatísticas de vôlei encontradas,
     *         ou null se ocorrer um erro durante a operação.
     */
    @Override
    public List<VolStats> getData() {
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+DBCreation.TABLE_ESTATISTICAS_VOLEI, null);
        try {
            List<VolStats> vsList = new ArrayList<>();
            if (cursor != null){
                while (cursor.moveToNext()){
                    Long idStats = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_ESTATISTICAS_VOLEI));
                    Long idPartida = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_PARTIDA_VOLEI));
                    Long idJogador = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR_VOLEI));
                    int pontos = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_PONTOS));
                    int bloqueios = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_BLOQUEIOS));

                    VolStats vs = new VolStats(idStats, idPartida, idJogador, pontos, bloqueios);
                    vsList.add(vs);
                }
                cursor.close();
            }
            return vsList;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }

    /**
     * Retorna uma lista dos principais artilheiros de um campeonato de vôlei, ordenados pelo total de pontos marcados.
     *
     * @param idcampeonato Identificador do campeonato de vôlei.
     * @return Lista de objetos ArtilhariaVol representando os artilheiros do campeonato,
     *         ordenados pelo total de pontos marcados em ordem decrescente,
     *         ou null se ocorrer um erro durante a operação.
     */
    @Override
    public List<ArtilhariaVol> getTopScorers(Long idcampeonato) {
        SQLiteDatabase database = db.getReadableDatabase();

        try {
            List<ArtilhariaVol> artilhariaList = new ArrayList<>();

            String query = "SELECT j." + DBCreation.COLUMN_ID_JOGADOR_VOLEI + ", j." + DBCreation.COLUMN_NOME_JOGADOR +
                    ", SUM(ev." + DBCreation.COLUMN_PONTOS + ") AS total_pontos " +
                    "FROM " + DBCreation.TABLE_JOGADOR + " j " +
                    "JOIN " + DBCreation.TABLE_ESTATISTICAS_VOLEI + " ev ON j." + DBCreation.COLUMN_ID_JOGADOR + " = ev." + DBCreation.COLUMN_ID_JOGADOR_VOLEI + " " +
                    "JOIN " + DBCreation.TABLE_PARTIDA + " p ON ev." + DBCreation.COLUMN_ID_PARTIDA_VOLEI + " = p." + DBCreation.COLUMN_ID_PARTIDA + " " +
                    "JOIN " + DBCreation.TABLE_JOGA_CAMPEONATO + " jc ON p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " " +
                    "WHERE jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " = ? " +
                    "GROUP BY j." + DBCreation.COLUMN_ID_JOGADOR_VOLEI + ", j." + DBCreation.COLUMN_NOME_JOGADOR + " " +
                    "ORDER BY total_pontos DESC";

            Cursor res = database.rawQuery(query, new String[]{String.valueOf(idcampeonato)});

            if (res != null && res.moveToFirst()) {
                do {
                    Long idJogador = res.getLong(res.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR_VOLEI));
                    String nomeJogador = res.getString(res.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                    int totalPontos = res.getInt(res.getColumnIndex("total_pontos"));
                    artilhariaList.add(new ArtilhariaVol(idJogador, idcampeonato,nomeJogador, totalPontos));
                } while (res.moveToNext());
            }
            res.close();
            return artilhariaList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }

    }

    /**
     * Retorna uma lista dos principais bloqueadores de um campeonato de vôlei, ordenados pelo total de bloqueios realizados.
     *
     * @param idcampeonato Identificador do campeonato de vôlei.
     * @return Lista de objetos Bloqueadores representando os jogadores que mais realizaram bloqueios no campeonato,
     *         ordenados pelo total de bloqueios realizados em ordem decrescente,
     *         ou null se ocorrer um erro durante a operação.
     */
    @Override
    public List<Bloqueadores> getTopBlockers(Long idcampeonato) {
        SQLiteDatabase database = db.getReadableDatabase();

        try {
            List<Bloqueadores> bloqueadoresList = new ArrayList<>();

            String query = "SELECT j." + DBCreation.COLUMN_ID_JOGADOR_VOLEI + ", j." + DBCreation.COLUMN_NOME_JOGADOR +
                    ", SUM(ev." + DBCreation.COLUMN_BLOQUEIOS + ") AS total_bloqueios " +
                    "FROM " + DBCreation.TABLE_JOGADOR + " j " +
                    "JOIN " + DBCreation.TABLE_ESTATISTICAS_VOLEI + " ev ON j." + DBCreation.COLUMN_ID_JOGADOR + " = ev." + DBCreation.COLUMN_ID_JOGADOR_VOLEI + " " +
                    "JOIN " + DBCreation.TABLE_PARTIDA + " p ON ev." + DBCreation.COLUMN_ID_PARTIDA_VOLEI + " = p." + DBCreation.COLUMN_ID_PARTIDA + " " +
                    "JOIN " + DBCreation.TABLE_JOGA_CAMPEONATO + " jc ON p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " " +
                    "WHERE jc." + DBCreation.COLUMN_ID_CAMPEONATO_JC + " = ? " +
                    "GROUP BY j." + DBCreation.COLUMN_ID_JOGADOR_VOLEI + ", j." + DBCreation.COLUMN_NOME_JOGADOR + " " +
                    "ORDER BY total_bloqueios DESC";

            Cursor res = database.rawQuery(query, new String[]{String.valueOf(idcampeonato)});

            if (res != null && res.moveToFirst()) {
                do {
                    Long idJogador = res.getLong(res.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR_VOLEI));
                    String nomeJogador = res.getString(res.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                    int totalBloqueios = res.getInt(res.getColumnIndex("total_bloqueios"));
                    bloqueadoresList.add(new Bloqueadores(idJogador, idcampeonato,nomeJogador, totalBloqueios));
                } while (res.moveToNext());
            }
            res.close();
            return bloqueadoresList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
    }
}
