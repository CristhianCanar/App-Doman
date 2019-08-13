package com.senasoft.appdoman.control.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context) {
        super(context, Constantes.NAME_DB, null, Constantes.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constantes.CREATE_TABLE_USER);
        db.execSQL(Constantes.CREATE_TABLE_CATEGORY);
        db.execSQL(Constantes.CREATE_TABLE_WORD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int updateVersion) {

    }
}
