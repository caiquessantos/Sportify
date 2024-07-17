package com.jvcan.sportify.DB.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iCampeonatoDAO;
import com.jvcan.sportify.DB.model.Campeonato;
import com.jvcan.sportify.DB.model.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampeonatoDAO implements iCampeonatoDAO {
    DBCreation db;

    public CampeonatoDAO(Context context){
        db = new DBCreation(context);
    }

    @Override
    public boolean create(Campeonato campeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        String query = "INSERT INTO " + DBCreation.TABLE_CAMPEONATO + " (" +
                DBCreation.COLUMN_USUARIO + ", " +
                DBCreation.COLUMN_NOME_CAMPEONATO + ", " +
                DBCreation.COLUMN_MODALIDADE + ", " +
                DBCreation.COLUMN_DESCRICAO + ", " +
                DBCreation.COLUMN_FOTO_CAMPEONATO +
                ") VALUES (?, ?, ?, ?, ?)";
        try {
            statement = database.compileStatement(query);
            statement.bindString(1, campeonato.getUserEmail());
            statement.bindString(2, campeonato.getNome_campeonato());
            statement.bindString(3, campeonato.getModalidade());
            statement.bindString(4, campeonato.getDescricao());
            statement.bindString(5, campeonato.getFoto_campeonato());
            statement.executeInsert();
            return true;
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


    @Override
    public boolean update(Campeonato campeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "UPDATE " + DBCreation.TABLE_CAMPEONATO + " SET " +
                DBCreation.COLUMN_NOME_CAMPEONATO + " = ?, " +
                DBCreation.COLUMN_MODALIDADE + " = ?, " +
                DBCreation.COLUMN_DESCRICAO + " = ?, " +
                DBCreation.COLUMN_FOTO_CAMPEONATO + " = ? " +
                "WHERE " + DBCreation.COLUMN_ID_CAMPEONATO + " = ? AND " +
                DBCreation.COLUMN_USUARIO + " = ?";

        SQLiteStatement statement = database.compileStatement(query);
        statement.bindString(1, campeonato.getNome_campeonato());
        statement.bindString(2, campeonato.getModalidade());
        statement.bindString(3, campeonato.getDescricao());
        statement.bindString(4, campeonato.getFoto_campeonato());
        statement.bindLong(5, campeonato.getId_campeonato());
        statement.bindString(6, campeonato.getUserEmail());
        int rows = statement.executeUpdateDelete();
        database.close();
        statement.close();

        return rows>0;
    }

    @SuppressLint("Range")
    @Override
    public Campeonato read(int id_campeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        Campeonato campeonato=null;
        String query = "SELECT * FROM "+DBCreation.TABLE_CAMPEONATO+" WHERE "+ DBCreation.COLUMN_ID_CAMPEONATO + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id_campeonato)});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO));
                String nome_campeonato = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_CAMPEONATO));
                String email_usuario = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_USUARIO));
                String modalidade = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_MODALIDADE));
                String localizacao = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_DESCRICAO));
                String foto_campeonato = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_FOTO_CAMPEONATO));

                campeonato = new Campeonato(email_usuario, nome_campeonato, modalidade, localizacao, foto_campeonato);
                campeonato.setId_campeonato(id);
            }
            database.close();
            cursor.close();
        }
        return campeonato;
    }

    @Override
    public boolean delete(int id_campeonato) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement sqLiteStatement=null;
        boolean result=false;
        try {
            String query = "DELETE FROM " + DBCreation.TABLE_CAMPEONATO + " WHERE "+DBCreation.COLUMN_ID_CAMPEONATO+" = ?";
            sqLiteStatement = database.compileStatement(query);
            sqLiteStatement.bindLong(1, id_campeonato);
            int rowsDeleted = sqLiteStatement.executeUpdateDelete();
            result = rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqLiteStatement!=null){
                sqLiteStatement.close();
            }
            database.close();
        }
        return result;
    }

    @SuppressLint("Range")
    @Override
    public List<Campeonato> getAllByUser(String email_user) {
        List<Campeonato> campeonatos = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        String query = "SELECT * FROM "+DBCreation.TABLE_CAMPEONATO+" WHERE "+DBCreation.COLUMN_USUARIO+" = ?";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(query, new String[]{email_user});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO));
                String nome_campeonato = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_CAMPEONATO));
                String email_usuario = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_USUARIO));
                String modalidade = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_MODALIDADE));
                String descricao = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_DESCRICAO));
                String foto_campeonato = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_FOTO_CAMPEONATO));

                Campeonato campeonato = new Campeonato(email_usuario, nome_campeonato, modalidade, descricao, foto_campeonato);
                campeonato.setId_campeonato(id);
                campeonatos.add(campeonato);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return campeonatos;
    }

    @SuppressLint("Range")
    @Override
    public List<Time> getAllTimes(int id_campeonato) {
        List<Time> times = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        String query = "SELECT "+DBCreation.TABLE_TIME+"."+DBCreation.COLUMN_ID_TIME
                +", "+DBCreation.TABLE_TIME+"."+DBCreation.COLUMN_NOME_TIME
                +", "+DBCreation.TABLE_TIME+"."+DBCreation.COLUMN_LOGO_TIME
                +" FROM "+DBCreation.TABLE_JOGA_CAMPEONATO
                +" INNER JOIN "+DBCreation.TABLE_TIME+" ON "
                + DBCreation.TABLE_JOGA_CAMPEONATO+"."+DBCreation.COLUMN_ID_TIME_JC+" = "
                +DBCreation.TABLE_TIME+"."+DBCreation.COLUMN_ID_TIME
                +" WHERE "+DBCreation.TABLE_JOGA_CAMPEONATO+"."+DBCreation.COLUMN_ID_CAMPEONATO_JC
                +" =? ";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(query, new String[]{String.valueOf(id_campeonato)});
            while (cursor.moveToNext()) {
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME));
                String nome_timeDB = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_TIME));
                String logo_timeDB = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_LOGO_TIME));
                Time time = new Time(nome_timeDB, logo_timeDB);
                time.setId_time(id_timeDB);
                times.add(time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }

        return times;
    }

    @SuppressLint("Range")
    @Override
    public List<Campeonato> getAll() {
        List<Campeonato> campeonatos = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        String query = "SELECT * FROM "+DBCreation.TABLE_CAMPEONATO;
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_CAMPEONATO));
                String nome_campeonato = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_CAMPEONATO));
                String email_usuario = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_USUARIO));
                String modalidade = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_MODALIDADE));
                String descricao = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_DESCRICAO));
                String foto_campeonato = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_FOTO_CAMPEONATO));
                Campeonato campeonato = new Campeonato(email_usuario, nome_campeonato, modalidade, descricao, foto_campeonato);
                campeonato.setId_campeonato(id);
                campeonatos.add(campeonato);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return campeonatos;
    }
}
