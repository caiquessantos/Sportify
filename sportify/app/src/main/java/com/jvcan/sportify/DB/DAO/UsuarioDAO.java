package com.jvcan.sportify.DB.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.jvcan.sportify.DB.database.DBCreation;
import com.jvcan.sportify.DB.interfaces.iUsuarioDAO;
import com.jvcan.sportify.DB.model.Usuario;
import com.jvcan.sportify.DB.utils.PasswordUtil;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements iUsuarioDAO {
    DBCreation db;

    public UsuarioDAO(Context context) {
        db = new DBCreation(context);
    }

    @Override
    public boolean create(Usuario usuario) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        String senhaEncriptada = PasswordUtil.hashPassword(usuario.getSenha());
        String query = "INSERT INTO " + DBCreation.TABLE_USUARIO + " (" +
                DBCreation.COLUMN_NOME_USUARIO + ", " +
                DBCreation.COLUMN_EMAIL + ", " +
                DBCreation.COLUMN_SENHA + ") VALUES (?, ?, ?)";

        try {
            statement = database.compileStatement(query);
            statement.bindString(1, usuario.getNome());
            statement.bindString(2, usuario.getEmail());
            statement.bindString(3, senhaEncriptada);
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


    @SuppressLint("Range")
    @Override
    public Usuario read(String email) {
        SQLiteDatabase database = db.getWritableDatabase();
        Usuario usuario = null;
        Cursor res = null;
        try {
            String query = "SELECT * FROM " + DBCreation.TABLE_USUARIO + " WHERE " + DBCreation.COLUMN_EMAIL + " = ?";
            res = database.rawQuery(query, new String[]{email});
            if (res != null && res.moveToFirst()) {
                String nameDB = res.getString(res.getColumnIndex(DBCreation.COLUMN_NOME_USUARIO));
                String emailDB = res.getString(res.getColumnIndex(DBCreation.COLUMN_EMAIL));
                String senhaDB = res.getString(res.getColumnIndex(DBCreation.COLUMN_SENHA));
                usuario = new Usuario(nameDB, emailDB, senhaDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                res.close();
            }
            database.close();
        }
        return usuario;
    }
    @Override
    public boolean delete(String email) {
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = null;
        String query = "DELETE FROM " + DBCreation.TABLE_USUARIO + " WHERE " + DBCreation.COLUMN_EMAIL + " = ?";
        try {
            statement = database.compileStatement(query);
            statement.bindString(1, email);
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


    @Override
    public boolean update(Usuario usuario) {
        SQLiteDatabase database = db.getWritableDatabase();
        String senhaEncriptada = PasswordUtil.hashPassword(usuario.getSenha());
        try {
            String query = "UPDATE " + DBCreation.TABLE_USUARIO +
                    " SET " + DBCreation.COLUMN_NOME_USUARIO + " = ?, " +
                    DBCreation.COLUMN_EMAIL + " = ?, " +
                    DBCreation.COLUMN_SENHA + " = ? " +
                    "WHERE " + DBCreation.COLUMN_EMAIL + " = ?";
            SQLiteStatement sqLiteStatement = database.compileStatement(query);
            sqLiteStatement.bindString(1, usuario.getNome());
            sqLiteStatement.bindString(2, usuario.getEmail());
            sqLiteStatement.bindString(3, senhaEncriptada);
            sqLiteStatement.bindString(4, usuario.getEmail());
            int rowsUpdated = sqLiteStatement.executeUpdateDelete();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            database.close();
        }
    }


    @SuppressLint("Range")
    @Override
    public List<Usuario> getData() {
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + DBCreation.TABLE_USUARIO, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String nome = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_NOME_USUARIO));
                String email = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_EMAIL));
                String senha = cursor.getString(cursor.getColumnIndex(DBCreation.COLUMN_SENHA));

                Usuario usuario = new Usuario(nome, email, senha);
                usuarios.add(usuario);
            }
            cursor.close();
            database.close();
        }
        return usuarios;
    }


}