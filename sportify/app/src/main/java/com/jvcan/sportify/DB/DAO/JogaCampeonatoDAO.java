package com.jvcan.sportify.DB.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iJogaCampeonatoDAO;
import com.jvcan.sportify.DB.model.JogaCampeonato;

import java.util.List;

public class JogaCampeonatoDAO implements iJogaCampeonatoDAO {
    DBCreation db;
    public JogaCampeonatoDAO(Context context){
        db = new DBCreation(context);
    }


    @Override
    public boolean create(JogaCampeonato jogaCampeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        boolean result = false;
        String query = "INSERT INTO " + DBCreation.TABLE_JOGA_CAMPEONATO + " (" +
                DBCreation.COLUMN_ID_TIME_JC + ", "+
                DBCreation.COLUMN_ID_CAMPEONATO_JC + " )"+
                "VALUES (?,?)";

        try {
            statement = database.compileStatement(query);
            statement.bindLong(1, jogaCampeonato.getId_time());
            statement.bindLong(2, jogaCampeonato.getId_campeonato());
            long id = statement.executeInsert();
            result = id>0;
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
    public JogaCampeonato read(int id_campeonato, int id_time) {
        SQLiteDatabase database = db.getReadableDatabase();
        JogaCampeonato jogaCampeonato = null;
        Cursor cursor = null;
        String query = "SELECT * FROM "+DBCreation.TABLE_JOGA_CAMPEONATO +
                " WHERE "+DBCreation.COLUMN_ID_TIME_JC+ " = ? AND "+
                DBCreation.COLUMN_ID_CAMPEONATO_JC + " = ?";
        try {
            cursor = database.rawQuery(query, new String[]{String.valueOf(id_time), String.valueOf(id_campeonato)});
            if(cursor!=null && cursor.moveToFirst()){
                int id_timeBD = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME_JC));
                int id_campeonatoDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO_JC));

                jogaCampeonato = new JogaCampeonato(id_timeBD, id_campeonatoDB);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return jogaCampeonato;
    }


    @Override
    public boolean delete(int id_time, int id_campeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "DELETE FROM " + DBCreation.TABLE_JOGA_CAMPEONATO + " WHERE "
                +DBCreation.COLUMN_ID_TIME_JC+ " = ? AND "+
                DBCreation.COLUMN_ID_CAMPEONATO_JC + " =? ";
        SQLiteStatement statement = null;
        boolean result = false;
        try {
            statement = database.compileStatement(query);
            statement.bindLong(1, id_time);
            statement.bindLong(2, id_campeonato);
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
    public List<JogaCampeonato> getAllByTimeId(int id_time) {
        SQLiteDatabase database = db.getReadableDatabase();
        List<JogaCampeonato> jogaCampeonatos = null;
        Cursor cursor = null;
        String query = "SELECT * FROM "+DBCreation.TABLE_JOGA_CAMPEONATO +
                " WHERE "+DBCreation.COLUMN_ID_TIME_JC+ " = ?";

        try {
            cursor = database.rawQuery(query, new String[]{String.valueOf(id_time)});
            while(cursor.moveToNext()){
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME_JC));
                int id_campeonatoDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO_JC));
                JogaCampeonato jogaCampeonato = new JogaCampeonato(id_timeDB, id_campeonatoDB);
                jogaCampeonatos.add(jogaCampeonato);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return jogaCampeonatos;
    }

    @SuppressLint("Range")
    @Override
    public List<JogaCampeonato> getAllByCampeonatoId(int id_campeonato) {
        SQLiteDatabase database = db.getReadableDatabase();
        List<JogaCampeonato> jogaCampeonatos = null;
        Cursor cursor = null;
        String query = "SELECT * FROM "+DBCreation.TABLE_JOGA_CAMPEONATO +
                " WHERE "+DBCreation.COLUMN_ID_CAMPEONATO_JC+ " = ?";

        try {
            cursor = database.rawQuery(query, new String[]{String.valueOf(id_campeonato)});
            while(cursor.moveToNext()){
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME_JC));
                int id_campeonatoDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO_JC));
                JogaCampeonato jogaCampeonato = new JogaCampeonato(id_timeDB, id_campeonatoDB);
                jogaCampeonatos.add(jogaCampeonato);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return jogaCampeonatos;
    }

    @SuppressLint("Range")
    @Override
    public List<JogaCampeonato> getAll() {
        SQLiteDatabase database = db.getReadableDatabase();
        List<JogaCampeonato> jogaCampeonatos = null;
        Cursor cursor = null;
        String query = "SELECT * FROM "+DBCreation.TABLE_JOGA_CAMPEONATO;

        try {
            cursor = database.rawQuery(query,null);
            while(cursor.moveToNext()){
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME_JC));
                int id_campeonatoDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO_JC));
                JogaCampeonato jogaCampeonato = new JogaCampeonato(id_timeDB, id_campeonatoDB);
                jogaCampeonatos.add(jogaCampeonato);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return jogaCampeonatos;
    }
}
