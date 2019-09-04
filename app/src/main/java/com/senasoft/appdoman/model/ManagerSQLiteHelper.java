package com.senasoft.appdoman.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.SQLOutput;
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

        openDB();
        ContentValues contenedor = new ContentValues();
        contenedor.put(Constantes.USE_COL_2, usuario.getUsuName());
        contenedor.put(Constantes.USE_COL_4, usuario.getUsuUserName());//mirar luego mejor, se esta guardandp en columna cambiada
        contenedor.put(Constantes.USE_COL_3, usuario.getUsuPassword());
        contenedor.put(Constantes.USE_COL_5, usuario.getUsuGenero());
        long result=  db.insert(Constantes.NAME_TABLE_USER, null, contenedor);
        closeDB();
        return result > 0;

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

    public Usuario readCredentailUser(String strUsu,String strPass){
        Usuario usuario = new Usuario();

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
                usuario.setUsuUserName(cursor.getString(0));
                usuario.setUsuPassword(cursor.getString(1));

            }while (cursor.moveToNext());

        }
        else{//no hay usuario con esas credenciales, retorno objeto
            System.out.println("333333333333333333333333333333");
            usuario.setUsuUserName("-1");
            usuario.setUsuPassword("-1");
        }
        closeDB();

        return usuario;
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
