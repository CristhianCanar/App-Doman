package com.senasoft.appdoman.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ManagerSQLiteHelper {

    SQLiteDatabase db;
    ConexionSQLiteHelper conexionSQLiteHelper;

    ArrayList<Palabra> listPalabra;
    ArrayList<Usuario> listUsuario;
    //ArrayList<Categoria> listCategoria;
    //Categoria categoria;

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

    public boolean insertDataUser(Usuario usuario) {
        ContentValues contenedor = new ContentValues();
        contenedor.put(Constantes.USE_COL_2, usuario.getUsuName());
        contenedor.put(Constantes.USE_COL_3, usuario.getUsuUserName());
        contenedor.put(Constantes.USE_COL_4, usuario.getUsuGenero());
        contenedor.put(Constantes.USE_COL_5, usuario.getUsuPassword());
        return (db.insert(Constantes.NAME_TABLE_USER, null, contenedor)) > 0;
    }

    public long insertDataWord(Palabra palabra) {

        openDB();

        ContentValues values = new ContentValues();

        values.put(Constantes.PAL_COL_2, palabra.getPalName());
        values.put(Constantes.PAL_COL_3, palabra.getPalCategory());
        values.put(Constantes.PAL_COL_4, palabra.getUriAudio());

        long mInsert = db.insert(Constantes.NAME_TABLE_WORD, null, values);

        closeDB();

        return mInsert;

    }

    /* //TODO: SE PUEDE QUITAR YA QUE SE LAMCENA EN PALABRA
    public boolean insertDataCategory(Categoria categoria) {
        ContentValues contenedor = new ContentValues();
        contenedor.put(Constantes.CAT_COL_2, categoria.getCatName());
        return (db.insert(Constantes.NAME_TABLE_USER, null, contenedor)) > 0;
    }

    */

    //TODO: METODO LEER/LISTAR (READs)
    public ArrayList<Usuario> readDataUser() {
        return listUsuario;
    }



    public ArrayList<Palabra> readDataWord(String data) {

        openDB();

        listPalabra = new ArrayList<>();

        String[] arg = {data};
        String[] column = {Constantes.PAL_COL_2, Constantes.PAL_COL_4};

        Cursor cursor = db.query(Constantes.NAME_TABLE_WORD, column, Constantes.PAL_COL_3 + "=?", arg, null, null, null);

        if (cursor.moveToFirst()){

            do {

                Palabra palabra = new Palabra();

                palabra.setPalName(cursor.getString(0));
                palabra.setUriAudio(cursor.getString(1));

                listPalabra.add(palabra);

            }while (cursor.moveToNext());

        }

        return listPalabra;
    }

}
