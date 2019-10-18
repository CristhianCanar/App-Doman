package com.senasoft.appdoman.model;

public class Constantes {

    public static String NAME_DB = "dbDoman.db";
    public static int VERSION_DB = 4;

    public static String NAME_TABLE_USER = "BOY";
    public static String USE_COL_1 = "USE_ID";
    public static String USE_COL_2 = "USE_NAME";
    public static String USE_COL_3 = "USE_GENERO";
    public static String USE_COL_4 = "USE_FECHA_NACIMIENTO";
    public static String USE_COL_5 = "USE_AVATAR";
    public static String USE_COL_6 = "USE_SCORE";

    public static String CREATE_TABLE_USER = "CREATE TABLE " + NAME_TABLE_USER + " ( " + USE_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USE_COL_2 + " TEXT, " + USE_COL_3 + " TEXT, " + USE_COL_4 + " TEXT," + USE_COL_5 + " TEXT, "+USE_COL_6+" INTEGER)";

    public static String NAME_TABLE_PRUEBA = "PRUEBA";
    public static String PRUEBA_COL_1 = "PRUEBA_ID";
    public static String PRUEBA_COL_2 = "PRUEBA_NUM_PALABRAS";
    public static String PRUEBA_COL_3 = "PRUEBA_ID_BOY";


    public static String CREATE_TABLE_PRUEBA = "CREATE TABLE " + NAME_TABLE_PRUEBA + "(" + PRUEBA_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PRUEBA_COL_2  + " INTEGER," + PRUEBA_COL_3 + " INTEGER, " + "FOREIGN KEY (" + PRUEBA_COL_3 + ") REFERENCES " + NAME_TABLE_USER + "(" + USE_COL_1 + "))";

    public static String NAME_TABLE_CATEGORY = "CATEGORY";
    public static String CATE_COL_1 = "CATE_ID";
    public static String CATE_COL_2 = "CATE_NAME";
    public static String CATE_COL_3 = "CATE_URL_IMG";

    public static String CREATE_TABLE_CATEGORY = "CREATE TABLE " + NAME_TABLE_CATEGORY + "(" + CATE_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CATE_COL_2 + " TEXT," + CATE_COL_3 + " TEXT)";

    public static String NAME_TABLE_WORD = "WORD";
    public static String WORD_COL_1 = "WORD_ID";
    public static String WORD_COL_2 = "WORD_NOMBRE";
    public static String WORD_COL_3 = "WORD_ID_CATEGORY";
    public static String WORD_COL_4 = "WORD_URL_AUDIO";


    public static String CREATE_TABLE_WORD = "CREATE TABLE " + NAME_TABLE_WORD + "(" + WORD_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORD_COL_2 + " TEXT," +
            WORD_COL_4 + " TEXT," + WORD_COL_3 + " INTEGER, FOREIGN KEY (" + WORD_COL_3 + ") REFERENCES " + NAME_TABLE_CATEGORY + "(" + CATE_COL_1 + "))";

    public static String NAME_TABLE_GENERATE = "PALABRA_PRUEBA";
    public static String PP_COL_1 = "ID";
    public static String PP_COL_2 = "ID_PALABRA"; // FK
    public static String PP_COL_3 = "ID_PRUEBA"; // FK
    public static String PP_COL_4 = "ES_CORRECTA";

    public static String CREATE_TABLE_GENERATE = "CREATE TABLE " + NAME_TABLE_GENERATE + "(" + PP_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + PP_COL_2 + " INTEGER, " +
            PP_COL_3 + " INTEGER, " + PP_COL_4 + " INTEGER, " + "FOREIGN KEY (" + PP_COL_2 + ") REFERENCES " + NAME_TABLE_WORD + "(" + WORD_COL_1 + "), " +
            "FOREIGN KEY (" + PP_COL_3 + ") REFERENCES " + NAME_TABLE_PRUEBA + "(" + PRUEBA_COL_1 + "))";



}
