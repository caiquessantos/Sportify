package com.jvcan.sportify.DB.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iTimeDAO;
import com.jvcan.sportify.DB.model.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeDAO implements iTimeDAO {
    DBCreation db;

    public TimeDAO(Context context){
        db = new DBCreation(context);
    }


    @Override
    public Long create(Time time) {
        Long id = null;
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        //boolean result = false;
        try {
            String query = "INSERT INTO "+DBCreation.TABLE_TIME + " ("+
                    DBCreation.COLUMN_NOME_TIME + ", " +
                    DBCreation.COLUMN_LOGO_TIME + ") " +
                    "VALUES(?,?)";
            statement = database.compileStatement(query);
            statement.bindString(1, time.getNome_time());
            statement.bindString(2, time.getLogo_time());
            id = statement.executeInsert();
            //result = id>0;
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            if(statement!=null){
                statement.close();
            }
            database.close();
        }
        return id;
    }

    @SuppressLint("Range")
    @Override
    public Time read(int id_time) {
        SQLiteDatabase database = db.getReadableDatabase();
        Time time = null;
        String query = "SELECT * FROM " + DBCreation.TABLE_TIME +
                " WHERE " + DBCreation.COLUMN_ID_TIME + "=?";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(query, new String[]{String.valueOf(id_time)});
            if(cursor!=null && cursor.moveToFirst()){
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME));
                String nome_timeDB = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_TIME));
                String logo_timeDB = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_LOGO_TIME));
                time = new Time(nome_timeDB, logo_timeDB);
                time.setId_time(id_timeDB);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return time;
    }

    @Override
    public boolean update(Time time) {
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "UPDATE "+ DBCreation.TABLE_TIME + " SET "+
                DBCreation.COLUMN_NOME_TIME + " = ?, " +
                DBCreation.COLUMN_LOGO_TIME + " = ? " +
                "WHERE "+DBCreation.COLUMN_ID_TIME + " = ?";
        SQLiteStatement statement = null;
        boolean result = false;
        try {
            statement = database.compileStatement(query);
            statement.bindString(1, time.getNome_time());
            statement.bindString(2, time.getLogo_time());
            int rows = statement.executeUpdateDelete();
            result = rows > 0;
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



    @Override
    public boolean delete(int id_time) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        boolean result = false;
        String query = "DELETE FROM "+DBCreation.TABLE_TIME + " WHERE "+
                DBCreation.COLUMN_ID_TIME +" = ?";
        try {
            statement = database.compileStatement(query);
            statement.bindLong(1, id_time);
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
    public List<Time> getAll() {
        List<Time> times = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        String query = "SELECT * FROM " + DBCreation.TABLE_TIME;
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(query, null);

            while (cursor.moveToNext()) {
                int id_timeDB = cursor.getInt(cursor.getColumnIndex(DBCreation.COLUMN_ID_TIME));
                String nome_timeDB = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_TIME));
                String logo_timeDB = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_LOGO_TIME));
                Time time = new Time(nome_timeDB, logo_timeDB);
                time.setId_time(id_timeDB);
                times.add(time);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return times;
    }
}
