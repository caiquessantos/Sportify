package com.jvcan.sportify.DB.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iPlayMatch;
import com.jvcan.sportify.DB.model.JogaPartida;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para operações relacionadas à tabela JOGA_PARTIDA no banco de dados.
 * Esta classe oferece métodos para criar, ler, atualizar e excluir registros da tabela JOGA_PARTIDA.
 */
public class PlayMatchDAO implements iPlayMatch{
    DBCreation db;

    /**
     * Construtor da classe PlayMatchDAO.
     *
     * @param context Contexto da aplicação Android para acessar o banco de dados.
     */
    public PlayMatchDAO(Context context) {
        db = new DBCreation(context);
        db.getReadableDatabase();
    }

    /**
     * Cria um novo registro na tabela JOGA_PARTIDA.
     *
     * @param idtime   Identificador do time que joga a partida.
     * @param idpartida Identificador da partida.
     * @param mandante Indica se o time é mandante (true) ou visitante (false).
     * @return true se o registro foi criado com sucesso, false caso contrário.
     */
    @Override
    public boolean create(Long idtime, Long idpartida, Boolean mandante) {
        SQLiteDatabase database = db.getWritableDatabase();

        String query = "INSERT INTO " + DBCreation.TABLE_JOGA_PARTIDA + " (" +
                DBCreation.COLUMN_ID_TIME_JP + ", " +
                DBCreation.COLUMN_ID_PARTIDA_JP + ", " +
                DBCreation.COLUMN_MANDANTE + ") VALUES (?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(query);

        try {
            // Converter boolean para inteiro (0 ou 1)
            int mandanteInt = mandante ? 1 : 0;

            statement.bindLong(1, idtime);
            statement.bindLong(2, idpartida);
            statement.bindLong(3, mandanteInt);

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
     * Atualiza um registro na tabela JOGA_PARTIDA.
     *
     * @param idtime   Identificador do time que joga a partida.
     * @param idpartida Identificador da partida.
     * @param mandante Novo valor para indicar se o time é mandante (true) ou visitante (false).
     * @return true se o registro foi atualizado com sucesso, false caso contrário.
     */
    @Override
    public Boolean update(Long idtime, Long idpartida, Boolean mandante) {
        SQLiteDatabase database = db.getWritableDatabase();

        String query = "UPDATE " + DBCreation.TABLE_JOGA_PARTIDA + " SET " +
                DBCreation.COLUMN_MANDANTE + " = ? " +
                "WHERE " + DBCreation.COLUMN_ID_TIME_JP + " = ? AND " +
                DBCreation.COLUMN_ID_PARTIDA_JP + " = ?";

        SQLiteStatement statement = database.compileStatement(query);

        try {
            int mandanteValue = mandante ? 1 : 0;  // Converte Boolean para INTEGER (1 para true, 0 para false)

            statement.bindLong(1, mandanteValue);
            statement.bindLong(2, idtime);
            statement.bindLong(3, idpartida);

            int rowsAffected = statement.executeUpdateDelete();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            statement.close();
            database.close();
        }
    }

    /**
     * Lê um registro da tabela JOGA_PARTIDA com base no identificador do time e da partida.
     *
     * @param idpartida Identificador da partida.
     * @return Objeto JogaPartida se encontrado, ou null caso contrário.
     */
    @Override
    public List<JogaPartida> read(Long idpartida) {
        List<JogaPartida> timesNaPartida = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();

        String query = "SELECT " + DBCreation.COLUMN_ID_TIME_JP + ", " +
                DBCreation.COLUMN_ID_PARTIDA_JP + ", " +
                DBCreation.COLUMN_MANDANTE + " FROM " + DBCreation.TABLE_JOGA_PARTIDA +
                " WHERE " + DBCreation.COLUMN_ID_PARTIDA_JP + " = ?";

        Cursor cursor = null;

        try {
            cursor = database.rawQuery(query, new String[]{String.valueOf(idpartida)});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Long timeId = cursor.getLong(cursor.getColumnIndexOrThrow(DBCreation.COLUMN_ID_TIME_JP));
                    Long partidaId = cursor.getLong(cursor.getColumnIndexOrThrow(DBCreation.COLUMN_ID_PARTIDA_JP));
                    int mandanteInt = cursor.getInt(cursor.getColumnIndexOrThrow(DBCreation.COLUMN_MANDANTE));
                    boolean mandante = mandanteInt == 1;

                    timesNaPartida.add(new JogaPartida(timeId, partidaId, mandante));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }

        return timesNaPartida;
    }

    /**
     * Deleta um registro da tabela JOGA_PARTIDA com base no identificador do time e da partida.
     *
     * @param idtime   Identificador do time.
     * @param idpartida Identificador da partida.
     * @return true se o registro foi deletado com sucesso, false caso contrário.
     */
    @Override
    public Boolean delete(Long idtime, Long idpartida) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "DELETE FROM " + DBCreation.TABLE_JOGA_PARTIDA + " WHERE " +
                DBCreation.COLUMN_ID_TIME_JP + " = ? AND " + DBCreation.COLUMN_ID_PARTIDA_JP + " = ?";
        SQLiteStatement statement = database.compileStatement(query);

        try {
            statement.bindLong(1, idtime);
            statement.bindLong(2, idpartida);
            statement.executeUpdateDelete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            statement.close();
            database.close();
        }
    }
}
