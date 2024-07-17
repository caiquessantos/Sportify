package com.jvcan.sportify.DB.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iJogadorDAO;
import com.jvcan.sportify.DB.model.Jogador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JogadorDAO implements iJogadorDAO{
    DBCreation db;
    public JogadorDAO(Context context){
        db = new DBCreation(context);
    }
    @Override
    public boolean create(Jogador jogador) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement sqLiteStatement = null;
        boolean result = false;
        String query = "INSERT INTO " + DBCreation.TABLE_JOGADOR + " (" +
                DBCreation.COLUMN_TIME + ", " +
                DBCreation.COLUMN_NOME_JOGADOR + ", " +
                DBCreation.COLUMN_NUMERO + ")" +
                "VALUES (?,?,?)";
        try {
            sqLiteStatement = database.compileStatement(query);
            sqLiteStatement.bindString(1, String.valueOf(jogador.getId_time()));
            sqLiteStatement.bindString(2, jogador.getNome_jogador());
            sqLiteStatement.bindString(3, jogador.getNumero());
            long id = sqLiteStatement.executeInsert();
            result =  id > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqLiteStatement!=null){
                sqLiteStatement.close();
            }
            database.close();
        }
        return result;
    }
    @SuppressLint("Range")
    @Override
    public Jogador read(int id_jogador) {
        SQLiteDatabase database = db.getReadableDatabase();
        Jogador jogador = null;
        String query = "SELECT * FROM " + DBCreation.TABLE_JOGADOR +
                " WHERE " + DBCreation.COLUMN_ID_JOGADOR + "=? ";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(query, new String[]{String.valueOf(id_jogador)});
            if (cursor != null && cursor.moveToFirst()) {
                int id_jogadorDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR));
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_TIME));
                String nome_jogadorBD = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                String numero = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NUMERO));
                jogador = new Jogador(id_timeDB, nome_jogadorBD, numero);
                jogador.setId_jogador(id_jogadorDB);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }

        return jogador;
    }


    @Override
    public boolean update(Jogador jogador) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "UPDATE " + DBCreation.TABLE_JOGADOR + " SET " +
                DBCreation.COLUMN_NOME_JOGADOR + " = ?, " +
                DBCreation.COLUMN_NUMERO + " = ? " +
                "WHERE " + DBCreation.COLUMN_ID_JOGADOR + " = ? AND " +
                DBCreation.COLUMN_TIME + " = ?";
        SQLiteStatement statement = null;
        boolean result = false;
        try {
            statement = database.compileStatement(query);
            statement.bindString(1, jogador.getNome_jogador());
            statement.bindString(2, jogador.getNumero());
            statement.bindLong(3, jogador.getId_jogador());
            statement.bindLong(4, jogador.getId_time());
            int rows = statement.executeUpdateDelete();
            result = rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            database.close();
        }
        return result;
    }



    @Override
    public boolean delete(int id_jogador) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "DELETE FROM " + DBCreation.TABLE_JOGADOR + " WHERE "
                +DBCreation.COLUMN_ID_JOGADOR+ " = ? ";
        SQLiteStatement statement = null;
        boolean result = false;
        try {
            statement = database.compileStatement(query);
            statement.bindLong(1, id_jogador);
            int rowsDeleted = statement.executeUpdateDelete();
            result = rowsDeleted>0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(statement!=null){
                statement.close();
            }
            database.close();
        }
        return result;
    }

    @SuppressLint("Range")
    @Override
    public List<Jogador> getAllByTimeID(int id_time) {
        List<Jogador> jogadores = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();  // Use getReadableDatabase para leitura
        Cursor cursor = null;
        try {
            String query = "SELECT * FROM " + DBCreation.TABLE_JOGADOR+
                    " WHERE "+ DBCreation.COLUMN_TIME+" = ?";
            cursor = database.rawQuery(query, new String[]{String.valueOf(id_time)});
            while (cursor.moveToNext()) {
                int id_jogadorDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR));
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_TIME));
                String nome_jogadorBD = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                String numero = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NUMERO));
                Jogador jogador = new Jogador(id_timeDB, nome_jogadorBD, numero);
                jogador.setId_jogador(id_jogadorDB);
                jogadores.add(jogador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return jogadores;
    }

    @SuppressLint("Range")
    @Override
    public List<Jogador> getAll() {
        List<Jogador> jogadores = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();  // Use getReadableDatabase para leitura
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + DBCreation.TABLE_JOGADOR;
            cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id_jogadorDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_JOGADOR));
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_TIME));
                String nome_jogadorBD = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_JOGADOR));
                String numero = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NUMERO));
                Jogador jogador = new Jogador(id_timeDB, nome_jogadorBD, numero);
                jogador.setId_jogador(id_jogadorDB);
                jogadores.add(jogador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }

        return jogadores;
    }

}
