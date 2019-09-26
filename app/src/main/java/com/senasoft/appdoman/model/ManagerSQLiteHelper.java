package com.senasoft.appdoman.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ManagerSQLiteHelper {

    private SQLiteDatabase db;
    private ConexionSQLiteHelper conexionSQLiteHelper;

    private ContentValues values;
    private long insert = 0;

    private ArrayList<Word> listWord;
    private ArrayList<User> listUser;


    //TODO CONEXIÓN A LA BD
    public ManagerSQLiteHelper(Context context) {
        conexionSQLiteHelper = new ConexionSQLiteHelper(context);
    }

    public void openDB() {
        db = conexionSQLiteHelper.getWritableDatabase();
    }

    //TODO CIERRE DE DB

    public void closeDB() {
        if (db != null) {
            db.close();
        }
    }


    //TODO: METODOS PARA INSERCION (INSERTs)

    public long insertDataUser(User user) {
        openDB();

        values = new ContentValues();
        values.put(Constantes.USE_COL_2, user.getUsuName());
        values.put(Constantes.USE_COL_3, user.getUsuGenero());
        values.put(Constantes.USE_COL_4, user.getUrlAvatar());

        try {
            insert = db.insert(Constantes.NAME_TABLE_USER, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeDB();
        return insert;
    }

    public long insertDataWord(Word word) {

        openDB();

        ContentValues values = new ContentValues();

        values.put(Constantes.PAL_COL_2, word.getPalName());
        values.put(Constantes.PAL_COL_3, word.getPalCategory());
        values.put(Constantes.PAL_COL_4, word.getUriAudio());

        long mInsert = db.insert(Constantes.NAME_TABLE_WORD, null, values);

        closeDB();

        return mInsert;

    }

    /* //TODO: SE PUEDE QUITAR YA QUE SE LAMCENA EN PALABRA
    public boolean insertDataCategory(Category categoria) {
        ContentValues contenedor = new ContentValues();
        contenedor.put(Constantes.CAT_COL_2, categoria.getCatName());
        return (db.insert(Constantes.NAME_TABLE_USER, null, contenedor)) > 0;
    }

    */

    //TODO: METODO LEER/LISTAR (READs)
    public ArrayList<User> readDataUser() {

        openDB();

        listUser = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NAME_TABLE_USER, null);

        if (cursor.moveToFirst()){

            do {

                User user = new User();
                user.setUsuName(cursor.getString(1));
                user.setUsuGenero(cursor.getString(2));
                user.setUrlAvatar(cursor.getString(3));
                listUser.add(user);

            }while (cursor.moveToNext());

        }

        closeDB();
        return listUser;
    }


    public ArrayList<User> searchUser(String data){

        openDB();

        ArrayList<User> list = new ArrayList<>();
        String[] arg = {data};
        String[] column = {Constantes.USE_COL_2, Constantes.USE_COL_3, Constantes.USE_COL_4};

        Cursor cursor = db.query(Constantes.NAME_TABLE_USER, column, Constantes.USE_COL_2+"=?", arg, null, null, null);

        if (cursor.moveToFirst()){
            do {

            }while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<Word> readDataWord(String data) {

        openDB();

        listWord = new ArrayList<>();

        String[] arg = {data};
        String[] column = {Constantes.PAL_COL_2, Constantes.PAL_COL_4};

        Cursor cursor = db.query(Constantes.NAME_TABLE_WORD, column, Constantes.PAL_COL_3 + "=?", arg, null, null, null);

        if (cursor.moveToFirst()){

            do {

                Word word = new Word();

                word.setPalName(cursor.getString(0));
                word.setUriAudio(cursor.getString(1));

                listWord.add(word);

            }while (cursor.moveToNext());

        }

        return listWord;
    }

    public long insertFase(Fase fase){

        openDB();

        values = new ContentValues();
        values.put(Constantes.FASE_COL_1, fase.getUserName());
        values.put(Constantes.FASE_COL_2, fase.getScore());
        values.put(Constantes.FASE_COL_3, fase.getLevel());

        try {
            insert = db.insert(Constantes.NAME_TABLE_FASE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeDB();
        return insert;
    }

    public ArrayList<Fase> searchPhase(String param) {

        openDB();

        ArrayList<Fase> list = new ArrayList<>();
        String[] params = {param};
        String[] comluns = {Constantes.FASE_COL_2};

        Cursor cursor = db.query(Constantes.NAME_TABLE_FASE, comluns, Constantes.FASE_COL_2 + "=?", params, null,null, null);

        if (cursor.moveToFirst()){

            do {

                Fase fase = new Fase();
                fase.setScore(cursor.getInt(0));
                list.add(fase);

            }while (cursor.moveToNext());

        }

        closeDB();

        return list;

    }

}
