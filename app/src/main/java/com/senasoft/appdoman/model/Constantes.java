package com.senasoft.appdoman.model;

public class Constantes {
    public static String NAME_DB = "dbDoman.db";
    public static int VERSION_DB = 2;

    public static String NAME_TABLE_USER = "USUARIO";
    public static String USE_COL_1 = "USE_ID";
    public static String USE_COL_2 = "USE_NAME";
    public static String USE_COL_3 = "USE_GENERO";
    public static String USE_COL_4 = "USE_AVATAR";

    public static String CREATE_TABLE_USER = ("CREATE TABLE " + NAME_TABLE_USER + " ( " + USE_COL_1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            USE_COL_2 + " TEXT, " + USE_COL_3 + " TEXT, " + USE_COL_4 + " TEXT)");

    public static String NAME_TABLE_WORD = "PALABRA";
    public static String PAL_COL_1 = "PAL_ID";
    public static String PAL_COL_2 = "PAL_NAME";
    public static String PAL_COL_3 = "PAL_CATEGORY";
    public static String PAL_COL_4 = "PAL_URIAUDIO";

    public static String CREATE_TABLE_WORD = ("CREATE TABLE " + NAME_TABLE_WORD + " ( " + PAL_COL_1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            PAL_COL_2 + " TEXT," + PAL_COL_3+" TEXT," + PAL_COL_4 + " TEXT)");


    public static String NAME_TABLE_FASE = "FASE";
    public static String FASE_COL_0 = "ID";
    public static String FASE_COL_1 = "USERNAME";
    public static String FASE_COL_2 = "SCORE";
    public static String FASE_COL_3 = "LEVEL";


    public static String CREATE_TABLE_FASE = "CREATE TABLE " + NAME_TABLE_FASE + "(" + FASE_COL_0 + " INTEGER NOT NULL PRIMARY KEY," + FASE_COL_1 + " TEXT," + FASE_COL_2
                                            + " NUMBER," + FASE_COL_3 + " NUMBER)";

}
