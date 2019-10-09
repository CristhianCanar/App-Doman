package com.senasoft.appdoman.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class ManagerSQLiteHelper {

    private SQLiteDatabase db;
    private ConexionSQLiteHelper conexionSQLiteHelper;

    private ContentValues values;
    private ArrayList<Boy> listBoys;
    private ArrayList<Word> listWords;
    private ArrayList<Category> listCategory;
    private ArrayList<Prueba> listPrueba;
    private long insert;

    public ManagerSQLiteHelper(Context context) {
        this.conexionSQLiteHelper = new ConexionSQLiteHelper(context);
    }


    public void openDB() {
        db = conexionSQLiteHelper.getWritableDatabase();
    }

    public void openDbRead() {
        db = conexionSQLiteHelper.getReadableDatabase();
    }

    public void closeDB() {
        if (db != null) db.close();
    }

    public ArrayList<Boy> readDataUser() {

        openDbRead();

        listBoys = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NAME_TABLE_USER, null);

        if (cursor.moveToFirst()) {

            do {

                Boy boy = new Boy();

                boy.setId(cursor.getInt(0));
                boy.setName(cursor.getString(1));
                boy.setFecha_nacimiento(cursor.getString(2));
                boy.setGenero(cursor.getString(3));
                boy.setUrl_avatar(cursor.getString(4));

                listBoys.add(boy);

            } while (cursor.moveToNext());

        }

        closeDB();

        return listBoys;

    }

    public long insertDataUser(Boy boy) {

        openDB();

        values = new ContentValues();

        values.put(Constantes.USE_COL_2, boy.getName());
        values.put(Constantes.USE_COL_3, boy.getGenero());
        values.put(Constantes.USE_COL_4, String.valueOf(boy.getFecha_nacimiento()));
        values.put(Constantes.USE_COL_5, boy.getUrl_avatar());

        long insert = db.insert(Constantes.NAME_TABLE_USER, null, values);

        closeDB();

        return insert;

    }

    public long insertDataWord(Word word) {

        openDB();

        values = new ContentValues();

        values.put(Constantes.WORD_COL_2, word.getName());
        values.put(Constantes.WORD_COL_3, word.getId_categoriy());
        values.put(Constantes.WORD_COL_4, word.getUrl_auidio());

        insert = db.insert(Constantes.NAME_TABLE_WORD, null, values);

        closeDB();

        return insert;

    }

    public ArrayList<Word> readDataWord(int categoria) {

        openDB();

        listWords = new ArrayList<>();
        String[] param = {String.valueOf(categoria)};
        String[] comulumns = {Constantes.WORD_COL_1, Constantes.WORD_COL_2, Constantes.WORD_COL_3, Constantes.WORD_COL_4};

        Cursor cursor = db.query(Constantes.NAME_TABLE_WORD, comulumns, Constantes.WORD_COL_3 + "=?", param, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                Word word = new Word();
                word.setId(cursor.getInt(0));
                word.setName(cursor.getString(1));
                word.setId_categoriy(cursor.getInt(2));
                word.setUrl_auidio(cursor.getString(3));
                listWords.add(word);

            } while (cursor.moveToNext());
        }

        closeDB();
        return listWords;

    }

    public long insertCategory(Category category) {

        openDB();

        values = new ContentValues();

        values.put(Constantes.CATE_COL_2, category.getName());
        values.put(Constantes.CATE_COL_3, category.getUrl_image());

        insert = db.insert(Constantes.NAME_TABLE_CATEGORY, null, values);

        closeDB();

        return insert;
    }

    public ArrayList<Category> readCategory() {

        openDbRead();

        listCategory = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NAME_TABLE_CATEGORY, null);


        if (cursor.moveToFirst()) {
            do {

                Category category = new Category();

                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));
                category.setUrl_image(cursor.getString(2));

                listCategory.add(category);

            } while (cursor.moveToNext());
        }

        closeDB();

        return listCategory;

    }

    public long insertPrueba(Prueba prueba) {

        openDB();

        values = new ContentValues();

        values.put(Constantes.PRUEBA_COL_2, prueba.getNum_words());
        values.put(Constantes.PRUEBA_COL_3, prueba.getId_boy());

        insert = db.insert(Constantes.NAME_TABLE_PRUEBA, null, values);

        closeDB();

        return insert;

    }

    public ArrayList<Prueba> readPrueba(int id) {

        openDbRead();

        listPrueba = new ArrayList<>();

        String[] param = {String.valueOf(id)};
        String[] columns = {Constantes.PRUEBA_COL_1, Constantes.PRUEBA_COL_2, Constantes.PRUEBA_COL_3};

        Cursor cursor = db.query(Constantes.NAME_TABLE_PRUEBA, columns, Constantes.PRUEBA_COL_3 + "=?", param, null, null, null);


        if (cursor.moveToFirst()) {

            do {

                Prueba prueba = new Prueba();

                prueba.setId(cursor.getInt(0));
                prueba.setNum_words(cursor.getInt(1));
                prueba.setId_boy(cursor.getInt(2));

                listPrueba.add(prueba);

            } while (cursor.moveToNext());

        }

        closeDB();

        return listPrueba;

    }

    public Prueba listRevertPrueba(int id) {

        openDbRead();

        listPrueba = new ArrayList<>();

        String[] param = {String.valueOf(id)};
        String[] columns = {Constantes.PRUEBA_COL_1, Constantes.PRUEBA_COL_2, Constantes.PRUEBA_COL_3};

        Cursor cursor = db.query(Constantes.NAME_TABLE_PRUEBA, columns, Constantes.PRUEBA_COL_3 + "=?", param, null, null, null);


        if (cursor.moveToFirst()) {

            do {

                Prueba prueba = new Prueba();

                prueba.setId(cursor.getInt(0));
                prueba.setNum_words(cursor.getInt(1));
                prueba.setId_boy(cursor.getInt(2));

                listPrueba.add(prueba);

            } while (cursor.moveToNext());

        }

        closeDB();

        Collections.reverse(listPrueba);

        return listPrueba.get(0);

    }


    public long insertWordPrueba(WordPrueba wordPrueba) {

        openDB();

        values = new ContentValues();

        values.put(Constantes.PP_COL_2, wordPrueba.getId_prueba());
        values.put(Constantes.PP_COL_3, wordPrueba.getId_word());
        values.put(Constantes.PP_COL_4, wordPrueba.getEs_correcta());

        insert = db.insert(Constantes.NAME_TABLE_GENERATE, null, values);

        closeDB();

        return insert;

    }


}
