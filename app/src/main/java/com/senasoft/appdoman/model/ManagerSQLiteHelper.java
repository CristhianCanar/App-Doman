package com.senasoft.appdoman.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ManagerSQLiteHelper {
    SQLiteDatabase db;
    ConexionSQLiteHelper conexionSQLiteHelper;

    ArrayList<Palabra> listPalabra;
    ArrayList<Usuario> listUsuario;
    //ArrayList<Categoria> listCategoria;

    Usuario usuario;
    Palabra palabra;
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

    public boolean insertDataWord(Palabra palabra) {
        ContentValues contenedor = new ContentValues();
        contenedor.put(Constantes.PAL_COL_2, palabra.getPalName());
        contenedor.put(Constantes.PAL_COL_3, palabra.getPalCategory());
        return (db.insert(Constantes.NAME_TABLE_WORD, null, contenedor)) > 0;
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


    public ArrayList<Palabra> readDataWord() {
        return listPalabra;
    }


}
