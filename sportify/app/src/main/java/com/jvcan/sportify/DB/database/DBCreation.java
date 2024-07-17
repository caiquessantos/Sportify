package com.jvcan.sportify.DB.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBCreation extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sportify.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela Usuario
    public static final String TABLE_USUARIO = "Usuario";
    public static final String COLUMN_NOME_USUARIO = "nome_usuario";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SENHA = "senha";

    // Tabela Campeonato
    public static final String TABLE_CAMPEONATO = "Campeonato";
    public static final String COLUMN_ID_CAMPEONATO = "id_campeonato";
    public static final String COLUMN_USUARIO = "usuario";
    public static final String COLUMN_NOME_CAMPEONATO = "nome_campeonato";
    public static final String COLUMN_MODALIDADE = "modalidade";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_FOTO_CAMPEONATO = "foto_campeonato";

    // Tabela Jogador
    public static final String TABLE_JOGADOR = "Jogador";
    public static final String COLUMN_ID_JOGADOR = "id_jogador";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_NOME_JOGADOR = "nome_jogador";
    public static final String COLUMN_NUMERO = "numero";

    // Tabela Time
    public static final String TABLE_TIME = "Time";
    public static final String COLUMN_ID_TIME = "id_time";
    public static final String COLUMN_NOME_TIME = "nome_time";
    public static final String COLUMN_LOGO_TIME = "logo_time";

    // Tabela Joga_Campeonato
    public static final String TABLE_JOGA_CAMPEONATO = "Joga_Campeonato";
    public static final String COLUMN_ID_TIME_JC = "id_time";
    public static final String COLUMN_ID_CAMPEONATO_JC = "id_campeonato";

    // Tabela Partida
    public static final String TABLE_PARTIDA = "Partida";
    public static final String COLUMN_ID_PARTIDA = "id_partida";
    public static final String COLUMN_ID_CAMPEONATO_PARTIDA = "id_campeonato";
    public static final String COLUMN_LOCAL = "local";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HORARIO = "horario";
    public static final String COLUMN_PLACAR_TIME1 = "placar_time1";
    public static final String COLUMN_PLACAR_TIME2 = "placar_time2";

    // Tabela Joga_Partida
    public static final String TABLE_JOGA_PARTIDA = "Joga_Partida";
    public static final String COLUMN_ID_TIME_JP = "id_time";
    public static final String COLUMN_ID_PARTIDA_JP = "id_partida";
    public static final String COLUMN_MANDANTE = "mandante";

    // Tabela Estatisticas_Futebol
    public static final String TABLE_ESTATISTICAS_FUTEBOL = "Estatisticas_Futebol";
    public static final String COLUMN_ID_ESTATISTICAS_FUTEBOL = "id_estatisticas";
    public static final String COLUMN_ID_PARTIDA_FUTEBOL = "id_partida";
    public static final String COLUMN_ID_JOGADOR_FUTEBOL = "id_jogador";
    public static final String COLUMN_GOLS = "gols";
    public static final String COLUMN_ASSISTENCIAS = "assistencias";
    public static final String COLUMN_CARTOES_AMARELOS = "cartoes_amarelos";
    public static final String COLUMN_CARTOES_VERMELHOS = "cartoes_vermelhos";

    // Tabela Estatisticas_Volei
    public static final String TABLE_ESTATISTICAS_VOLEI = "Estatisticas_Volei";
    public static final String COLUMN_ID_ESTATISTICAS_VOLEI = "id_estatisticas";
    public static final String COLUMN_ID_PARTIDA_VOLEI = "id_partida";
    public static final String COLUMN_ID_JOGADOR_VOLEI = "id_jogador";
    public static final String COLUMN_PONTOS = "pontos";
    public static final String COLUMN_BLOQUEIOS = "bloqueios";

    public DBCreation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
        // Criação da tabela Usuario
        db.execSQL("CREATE TABLE " + TABLE_USUARIO + " (" +
                COLUMN_NOME_USUARIO + " VARCHAR(50) NOT NULL, " +
                COLUMN_EMAIL + " VARCHAR(100) PRIMARY KEY NOT NULL, " +
                COLUMN_SENHA + " VARCHAR(64) NOT NULL)");

        // Criação da tabela Campeonato
        db.execSQL("CREATE TABLE " + TABLE_CAMPEONATO + " (" +
                COLUMN_ID_CAMPEONATO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USUARIO + " VARCHAR(100), " +
                COLUMN_NOME_CAMPEONATO + " VARCHAR(50) NOT NULL, " +
                COLUMN_MODALIDADE + " VARCHAR(50), " +
                COLUMN_DESCRICAO + " VARCHAR(100), " +
                COLUMN_FOTO_CAMPEONATO + " VARCHAR(100), " +
                "FOREIGN KEY(" + COLUMN_USUARIO + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_EMAIL + ") ON DELETE CASCADE)");

        // Criação da tabela Jogador
        db.execSQL("CREATE TABLE " + TABLE_JOGADOR + " (" +
                COLUMN_ID_JOGADOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TIME + " INTEGER, " +
                COLUMN_NOME_JOGADOR + " VARCHAR(50) NOT NULL, " +
                COLUMN_NUMERO + " VARCHAR(6), " +
                "FOREIGN KEY(" + COLUMN_TIME + ") REFERENCES " + TABLE_TIME + "(" + COLUMN_ID_TIME + ") ON DELETE CASCADE)");


        // Criação da tabela Time
        db.execSQL("CREATE TABLE " + TABLE_TIME + " (" +
                COLUMN_ID_TIME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME_TIME + " VARCHAR(50) NOT NULL, " +
                COLUMN_LOGO_TIME + " VARCHAR(100))");

        // Criação da tabela Joga_Campeonato
        db.execSQL("CREATE TABLE " + TABLE_JOGA_CAMPEONATO + " (" +
                COLUMN_ID_TIME_JC + " INTEGER, " +
                COLUMN_ID_CAMPEONATO_JC + " INTEGER, " +
                "PRIMARY KEY (" + COLUMN_ID_TIME_JC + ", " + COLUMN_ID_CAMPEONATO_JC + "), " +
                "FOREIGN KEY(" + COLUMN_ID_TIME_JC + ") REFERENCES " + TABLE_TIME + "(" + COLUMN_ID_TIME + ") ON DELETE CASCADE, " +
                "FOREIGN KEY(" + COLUMN_ID_CAMPEONATO_JC + ") REFERENCES " + TABLE_CAMPEONATO + "(" + COLUMN_ID_CAMPEONATO + ") ON DELETE CASCADE)");


        // Criação da tabela Partida
        db.execSQL("CREATE TABLE " + TABLE_PARTIDA + " (" +
                COLUMN_ID_PARTIDA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID_CAMPEONATO_PARTIDA + " INTEGER, " +
                COLUMN_LOCAL + " TEXT, " +
                COLUMN_DATA + " TEXT, " +
                COLUMN_HORARIO + " TEXT, " +
                COLUMN_PLACAR_TIME1 + " INTEGER, " +
                COLUMN_PLACAR_TIME2 + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_ID_CAMPEONATO_PARTIDA + ") REFERENCES " + TABLE_CAMPEONATO + "(" + COLUMN_ID_CAMPEONATO + "))");

        // Criação da tabela Joga_Partida
        db.execSQL("CREATE TABLE " + TABLE_JOGA_PARTIDA + " (" +
                COLUMN_ID_TIME_JP + " INTEGER, " +
                COLUMN_ID_PARTIDA_JP + " INTEGER, " +
                COLUMN_MANDANTE + " INTEGER, " +  // Adiciona a coluna MANDANTE com valor padrão 0
                "PRIMARY KEY (" + COLUMN_ID_TIME_JP + ", " + COLUMN_ID_PARTIDA_JP + "), " +
                "FOREIGN KEY(" + COLUMN_ID_TIME_JP + ") REFERENCES " + TABLE_TIME + "(" + COLUMN_ID_TIME + "), " +
                "FOREIGN KEY(" + COLUMN_ID_PARTIDA_JP + ") REFERENCES " + TABLE_PARTIDA + "(" + COLUMN_ID_PARTIDA + "))");

        // Criação da tabela Estatisticas_Futebol
        db.execSQL("CREATE TABLE " + TABLE_ESTATISTICAS_FUTEBOL + " (" +
                COLUMN_ID_ESTATISTICAS_FUTEBOL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID_PARTIDA_FUTEBOL + " INTEGER, " +
                COLUMN_ID_JOGADOR_FUTEBOL + " INTEGER, " +
                COLUMN_GOLS + " INTEGER DEFAULT 0, " +
                COLUMN_ASSISTENCIAS + " INTEGER DEFAULT 0, " +
                COLUMN_CARTOES_AMARELOS + " INTEGER DEFAULT 0, " +
                COLUMN_CARTOES_VERMELHOS + " INTEGER DEFAULT 0, " +
                "FOREIGN KEY(" + COLUMN_ID_PARTIDA_FUTEBOL + ") REFERENCES " + TABLE_PARTIDA + "(" + COLUMN_ID_PARTIDA + "), " +
                "FOREIGN KEY(" + COLUMN_ID_JOGADOR_FUTEBOL + ") REFERENCES " + TABLE_JOGADOR + "(" + COLUMN_ID_JOGADOR + "))");

        // Criação da tabela Estatisticas_Volei
        db.execSQL("CREATE TABLE " + TABLE_ESTATISTICAS_VOLEI + " (" +
                COLUMN_ID_ESTATISTICAS_VOLEI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID_PARTIDA_VOLEI + " INTEGER, " +
                COLUMN_ID_JOGADOR_VOLEI + " INTEGER, " +
                COLUMN_PONTOS + " INTEGER DEFAULT 0, " +
                COLUMN_BLOQUEIOS + " INTEGER DEFAULT 0, " +
                "FOREIGN KEY(" + COLUMN_ID_PARTIDA_VOLEI + ") REFERENCES " + TABLE_PARTIDA + "(" + COLUMN_ID_PARTIDA + "), " +
                "FOREIGN KEY(" + COLUMN_ID_JOGADOR_VOLEI + ") REFERENCES " + TABLE_JOGADOR + "(" + COLUMN_ID_JOGADOR + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTATISTICAS_VOLEI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTATISTICAS_FUTEBOL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOGA_PARTIDA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIDA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOGA_CAMPEONATO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOGADOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAMPEONATO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        onCreate(db);
    }
}
