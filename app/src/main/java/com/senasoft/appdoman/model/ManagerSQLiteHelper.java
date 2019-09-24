package com.senasoft.appdoman.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ManagerSQLiteHelper {

    SQLiteDatabase db;
    ConexionSQLiteHelper conexionSQLiteHelper;

    ArrayList<Word> listWord;
    ArrayList<User> listUser;
    //ArrayList<Category> listCategoria;
    //Category categoria;

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

    public boolean insertDataUser(User user) {

        openDB();
        ContentValues contenedor = new ContentValues();
        contenedor.put(Constantes.USE_COL_2, user.getUsuName());
        contenedor.put(Constantes.USE_COL_4, user.getUsuUserName());//mirar luego mejor, se esta guardandp en columna cambiada
        contenedor.put(Constantes.USE_COL_3, user.getUsuPassword());
        contenedor.put(Constantes.USE_COL_5, user.getUsuGenero());
        long result=  db.insert(Constantes.NAME_TABLE_USER, null, contenedor);
        closeDB();
        return result > 0;

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
        return listUser;
    }

    public User readCredentailUser(String strUsu, String strPass){
        User user = new User();

        openDB();

        String[] arg = {strUsu,strPass};
        String[] column = {Constantes.USE_COL_3, Constantes.USE_COL_4};
        System.out.println("aquiiiiiiiiiii");
        Cursor cursor = db.query(Constantes.NAME_TABLE_USER, column, Constantes.USE_COL_3 + "=? and "+Constantes.USE_COL_4 + "=?", arg, null, null, null);
        //System.out.println(cursor.getString(0));
        if (cursor.moveToFirst()){
            System.out.println("11111111111111111111111");

            do {
                System.out.println("2222222222222222");
                user.setUsuUserName(cursor.getString(0));
                user.setUsuPassword(cursor.getString(1));

            }while (cursor.moveToNext());

        }
        else{//no hay user con esas credenciales, retorno objeto
            System.out.println("333333333333333333333333333333");
            user.setUsuUserName("-1");
            user.setUsuPassword("-1");
        }
        closeDB();

        return user;
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

}
