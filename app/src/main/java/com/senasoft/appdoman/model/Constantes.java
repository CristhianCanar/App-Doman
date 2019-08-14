package com.senasoft.appdoman.model;

public class Constantes {
    public static String NAME_DB = "dbDoman";
    public static int VERSION_DB = 1;

    public static String NAME_TABLE_USER = "USUARIO";
    public static String USE_COL_1 = "USE_ID";
    public static String USE_COL_2 = "USE_NAME";
    public static String USE_COL_3 = "USE_USER_NAME";
    public static String USE_COL_4 = "USE_PASSWORD";
    public static String USE_COL_5 = "USE_GENERO";
    public static String CREATE_TABLE_USER = ("CREATE TABLE " + NAME_TABLE_USER + " ( " + USE_COL_1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            USE_COL_2 + " TEXT, " + USE_COL_3 + " TEXT, " + USE_COL_4 + " TEXT, " + USE_COL_5 + " TEXT" + " ) ");


    public static String NAME_TABLE_WORD = "PALABRA";
    public static String PAL_COL_1 = "PAL_ID";
    public static String PAL_COL_2 = "PAL_NAME";
    public static String PAL_COL_3 = "PAL_CATEGORY";
    public static String CREATE_TABLE_WORD = ("CREATE TABLE " + NAME_TABLE_WORD + " ( " + PAL_COL_1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            PAL_COL_2 + " TEXT " + PAL_COL_3+" TEXT "+" ) ");


    //TODO: ESTA CONSTANTE ES INSERVIBLE --|
    public static String NAME_TABLE_CATEGORY = "CATEGORIA";
    public static String CAT_COL_1 = "CAT_ID";
    public static String CAT_COL_2 = "CAT_NAME";
    public static String CREATE_TABLE_CATEGORY = ("CREATE TABLE " + NAME_TABLE_CATEGORY + " ( " + CAT_COL_1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            CAT_COL_2 + " TEXT " + " ) ");

}
