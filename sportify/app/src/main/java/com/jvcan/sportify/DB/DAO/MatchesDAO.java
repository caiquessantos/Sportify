package com.jvcan.sportify.DB.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iMatches;
import com.jvcan.sportify.DB.model.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MatchesDAO implements iMatches {

    DBCreation db;

    /**
     * Construtor para inicializar o acesso ao banco de dados.
     *
     * @param context Contexto da aplicação.
     */
    public MatchesDAO(Context context) {
        db = new DBCreation(context);
        db.getReadableDatabase();// <- segundo a documentacao o WritableDatabase ja abre o db e permite escrita e
                                 // leitura
    }

    /**
     * Cria uma nova partida no banco de dados.
     *
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @param local        Local onde a partida será realizada.
     * @param data         Data da partida.
     * @param horario      Horário da partida.
     * @param placarTime1  Placar do time 1.
     * @param placarTime2  Placar do time 2.
     * @return True se a operação de criação for bem-sucedida, false caso contrário.
     */
    @Override
    public Long create(Long idcampeonato, String local, String data, String horario, int placarTime1, int placarTime2) {
        Long id = null;
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "INSERT INTO " + DBCreation.TABLE_PARTIDA + " (" +
                DBCreation.COLUMN_ID_CAMPEONATO + ", " +
                DBCreation.COLUMN_LOCAL + ", " +
                DBCreation.COLUMN_DATA + ", " +
                DBCreation.COLUMN_HORARIO + ", " +
                DBCreation.COLUMN_PLACAR_TIME1 + ", " +
                DBCreation.COLUMN_PLACAR_TIME2 + ") VALUES (?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(query);
        try {
            statement.bindLong(1, idcampeonato);
            statement.bindString(2, local);
            statement.bindString(3, data);
            statement.bindString(4, horario);
            statement.bindLong(5, placarTime1);
            statement.bindLong(6, placarTime2);

            id = statement.executeInsert();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
            database.close();
        }
        return id;
    }

    /**
     * Atualiza uma partida existente no banco de dados.
     *
     * @param idPartida    ID da partida a ser atualizada.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @param local        Local onde a partida será realizada.
     * @param data         Data da partida.
     * @param horario      Horário da partida.
     * @param placarTime1  Placar do time 1.
     * @param placarTime2  Placar do time 2.
     * @return True se a operação de atualização for bem-sucedida, false caso
     *         contrário.
     */
    @Override
    public Boolean update(Long idPartida, Long idcampeonato, String local, String data, String horario, int placarTime1,
            int placarTime2) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "UPDATE " + DBCreation.TABLE_PARTIDA + " SET " +
                DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = ?, " +
                DBCreation.COLUMN_LOCAL + " = ?, " +
                DBCreation.COLUMN_DATA + " = ?, " +
                DBCreation.COLUMN_HORARIO + " = ?, " +
                DBCreation.COLUMN_PLACAR_TIME1 + " = ?, " +
                DBCreation.COLUMN_PLACAR_TIME2 + " = ? " +
                "WHERE " + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = ? AND " +
                DBCreation.COLUMN_ID_PARTIDA + " = ?";

        SQLiteStatement statement = database.compileStatement(query);
        try {
            statement.bindLong(1, idcampeonato);
            statement.bindString(2, local);
            statement.bindString(3, data);
            statement.bindString(4, horario);
            statement.bindLong(5, placarTime1);
            statement.bindLong(6, placarTime2);
            statement.bindLong(7, idcampeonato);
            statement.bindLong(8, idPartida);

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
     * Consulta uma partida específica no banco de dados com base nos IDs da partida
     * e do campeonato.
     *
     * @param idpartida    ID da partida.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @return Objeto Partida se encontrada, null caso contrário.
     */
    @SuppressLint("Range")
    @Override
    public Partida read(Long idpartida, Long idcampeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor res = null;
        Partida partida = null;

        try {
            String query = "SELECT * FROM " + DBCreation.TABLE_PARTIDA + " WHERE "
                    + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA +
                    " = " + idcampeonato + " AND " + DBCreation.COLUMN_ID_PARTIDA + " = " + idpartida;
            res = database.rawQuery(query, null);

            if (res != null && res.moveToFirst()) {
                String local = res.getString(res.getColumnIndex(DBCreation.COLUMN_LOCAL));
                String dataStr = res.getString(res.getColumnIndex(DBCreation.COLUMN_DATA));
                String horarioStr = res.getString(res.getColumnIndex(DBCreation.COLUMN_HORARIO));
                int placarTime1 = res.getInt(res.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME1));
                int placarTime2 = res.getInt(res.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME2));
                partida = new Partida(idpartida, idcampeonato, local, dataStr, horarioStr, placarTime1, placarTime2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                res.close();
            }
            database.close();
        }

        return partida;
    }

    /**
     * Exclui uma partida do banco de dados com base nos IDs da partida e do
     * campeonato.
     *
     * @param idpartida    ID da partida a ser excluída.
     * @param idcampeonato ID do campeonato ao qual a partida pertence.
     * @return True se a operação de exclusão for bem-sucedida, false caso
     *         contrário.
     */
    @Override
    public Boolean delete(Long idpartida, Long idcampeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        try {
            String sql = "DELETE FROM " + DBCreation.TABLE_PARTIDA +
                    " WHERE " + DBCreation.COLUMN_ID_PARTIDA + " = ?" +
                    " AND " + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = ?";

            statement = database.compileStatement(sql);
            statement.bindLong(1, idpartida);
            statement.bindLong(2, idcampeonato);

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
     * Obtém uma lista de todas as partidas armazenadas no banco de dados.
     *
     * @return Lista de objetos Partida.
     */
    @SuppressLint("Range")
    @Override
    public List<Partida> getData(Long idCampeonato) {
        List<Partida> partidas = new ArrayList<>();
        SQLiteDatabase database = db.getWritableDatabase();

        // Query para selecionar todas as partidas de um campeonato específico
        String query = "SELECT * FROM " + DBCreation.TABLE_PARTIDA + " WHERE " + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA
                + " = ?";
        Cursor cursor = database.rawQuery(query, new String[] { String.valueOf(idCampeonato) });

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Long idpartida = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_PARTIDA));
                Long idcampeonato = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA));
                String local = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_LOCAL));
                String dataStr = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_DATA));
                String horarioStr = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_HORARIO));
                int placarTime1 = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME1));
                int placarTime2 = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME2));

                Partida p = new Partida(idpartida, idcampeonato, local, dataStr, horarioStr, placarTime1, placarTime2);
                partidas.add(p);
            }
            cursor.close();
        }
        database.close();
        return partidas;
    }

    /**
     * Obtém estatísticas das partidas realizadas em casa com base no ID do
     * campeonato e do time.
     *
     * @param idcampeonato ID do campeonato.
     * @param idtime       ID do time.
     * @return Objeto PartidaCasaStats se encontrado, null caso contrário.
     */
    @SuppressLint("Range")
    @Override
    public PartidaCasaStats getHomeMatches(Long idcampeonato, Long idtime) {
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = db.getReadableDatabase();

            String query = "SELECT p.*, jp." + DBCreation.COLUMN_MANDANTE + " " +
                    "FROM " + DBCreation.TABLE_PARTIDA + " p " +
                    "JOIN " + DBCreation.TABLE_JOGA_PARTIDA + " jp " +
                    "ON p." + DBCreation.COLUMN_ID_PARTIDA + " = jp." + DBCreation.COLUMN_ID_PARTIDA_JP + " " +
                    "WHERE p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = ? " +
                    "AND jp." + DBCreation.COLUMN_ID_TIME_JP + " = ? " +
                    "AND jp." + DBCreation.COLUMN_MANDANTE + " = 1";

            cursor = database.rawQuery(query, new String[] { idcampeonato.toString(), idtime.toString() });

            List<Partida> partidasJogadasCasa = new ArrayList<>();
            int totalPartidas = 0;
            int vitorias = 0;
            int empates = 0;
            int derrotas = 0;
            int totalGolsFeitos = 0;
            int totalGolsTomados = 0;

            if (cursor.moveToFirst()) {
                do {
                    Partida partida = new Partida();
                    partida.setIdPartida(cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_PARTIDA)));
                    partida.setIdcampeonato(
                            cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA)));
                    partida.setLocal(cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_LOCAL)));
                    partida.setData(cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_DATA)));
                    partida.setHorario(cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_HORARIO)));
                    partida.setPlacarTime1(cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME1)));
                    partida.setPlacarTime2(cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME2)));

                    partidasJogadasCasa.add(partida);
                    totalPartidas++;

                    if (partida.getPlacarTime1() > partida.getPlacarTime2()) {
                        vitorias++;
                    } else if (partida.getPlacarTime1() == partida.getPlacarTime2()) {
                        empates++;
                    } else {
                        derrotas++;
                    }

                    totalGolsFeitos += partida.getPlacarTime1();
                    totalGolsTomados += partida.getPlacarTime2();

                } while (cursor.moveToNext());
            }

            return new PartidaCasaStats(partidasJogadasCasa, totalPartidas, vitorias, empates, derrotas,
                    totalGolsFeitos, totalGolsTomados);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
    }

    /**
     * Obtém estatísticas das partidas realizadas fora com base no ID do campeonato
     * e do time.
     *
     * @param idcampeonato ID do campeonato.
     * @param idtime       ID do time.
     * @return Objeto PartidaForaStats se encontrado, null caso contrário.
     */
    @SuppressLint("Range")
    @Override
    public PartidaForaStats getAwayMatches(Long idcampeonato, Long idtime) {
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = db.getReadableDatabase();

            String query = "SELECT p.*, jp." + DBCreation.COLUMN_MANDANTE + " " +
                    "FROM " + DBCreation.TABLE_PARTIDA + " p " +
                    "JOIN " + DBCreation.TABLE_JOGA_PARTIDA + " jp " +
                    "ON p." + DBCreation.COLUMN_ID_PARTIDA + " = jp." + DBCreation.COLUMN_ID_PARTIDA_JP + " " +
                    "WHERE p." + DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA + " = ? " +
                    "AND jp." + DBCreation.COLUMN_ID_TIME_JP + " = ? " +
                    "AND jp." + DBCreation.COLUMN_MANDANTE + " = 0";

            cursor = database.rawQuery(query, new String[] { idcampeonato.toString(), idtime.toString() });

            List<Partida> partidasJogadasVisitante = new ArrayList<>();
            int totalPartidas = 0;
            int vitorias = 0;
            int empates = 0;
            int derrotas = 0;
            int totalGolsFeitos = 0;
            int totalGolsTomados = 0;

            if (cursor.moveToFirst()) {
                do {
                    Partida partida = new Partida();
                    partida.setIdPartida(cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_PARTIDA)));
                    partida.setIdcampeonato(
                            cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO_PARTIDA)));
                    partida.setLocal(cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_LOCAL)));
                    partida.setData(cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_DATA)));
                    partida.setHorario(cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_HORARIO)));
                    partida.setPlacarTime1(cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME1)));
                    partida.setPlacarTime2(cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_PLACAR_TIME2)));

                    partidasJogadasVisitante.add(partida);
                    totalPartidas++;

                    if (partida.getPlacarTime2() > partida.getPlacarTime1()) {
                        vitorias++;
                    } else if (partida.getPlacarTime2() == partida.getPlacarTime1()) {
                        empates++;
                    } else {
                        derrotas++;
                    }

                    totalGolsFeitos += partida.getPlacarTime2();
                    totalGolsTomados += partida.getPlacarTime1();

                } while (cursor.moveToNext());
            }

            return new PartidaForaStats(partidasJogadasVisitante, totalPartidas, vitorias, empates, derrotas,
                    totalGolsFeitos, totalGolsTomados);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
    }

    /**
     * Obtém os jogadores de cada time que participaram de uma partida específica.
     *
     * @param idPartida ID da partida.
     * @return Uma lista de duas listas de JogadorTime, onde o primeiro elemento
     *         contém os jogadores do time A (mandante) e o segundo os jogadores do
     *         time B (visitante).
     */
    @SuppressLint("Range")
    @Override
    public List<List<JogadorTime>> getMatchPlayers(long idPartida) {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        List<JogadorTime> jogadoresTimeA = new ArrayList<>();
        List<JogadorTime> jogadoresTimeB = new ArrayList<>();
        List<List<JogadorTime>> times = new ArrayList<>();

        try {
            database = db.getReadableDatabase();

            String query = "SELECT j." + DBCreation.COLUMN_ID_JOGADOR + ", j." + DBCreation.COLUMN_NOME_JOGADOR
                    + ", jp." + DBCreation.COLUMN_ID_TIME_JP + ", jp." + DBCreation.COLUMN_MANDANTE + " " +
                    "FROM " + DBCreation.TABLE_JOGADOR + " j " +
                    "JOIN " + DBCreation.TABLE_JOGA_PARTIDA + " jp " +
                    "ON j." + DBCreation.COLUMN_TIME + " = jp." + DBCreation.COLUMN_ID_TIME_JP + " " +
                    "WHERE jp." + DBCreation.COLUMN_ID_PARTIDA_JP + " = ?";

            cursor = database.rawQuery(query, new String[] { String.valueOf(idPartida) });

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Long id = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR));
                    String nome = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                    Long idTime = cursor.getLong(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME_JP));
                    int mandante = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_MANDANTE));

                    JogadorTime jogador = new JogadorTime(id, nome, idTime);

                    if (mandante == 1) {
                        jogadoresTimeA.add(jogador);
                    } else {
                        jogadoresTimeB.add(jogador);
                    }

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception, log it or show a message
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }

        times.add(jogadoresTimeA);
        times.add(jogadoresTimeB);

        return times;
    }
}
