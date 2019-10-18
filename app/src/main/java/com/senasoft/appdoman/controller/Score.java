package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.Boy;
import com.senasoft.appdoman.model.ConexionSQLiteHelper;
import com.senasoft.appdoman.model.Constantes;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Score extends AppCompatActivity {

    //declarations
    private TextView tvNameOne;
    private TextView tvNameTwo;
    private TextView tvNameThree;
    private ImageView ivPhotoOne, ivPhotoTwo, ivPhotoThree;



    //SCORE RANKING
    private ManagerSQLiteHelper managerSQLiteHelper;
    private SQLiteDatabase db;
    private ArrayList<Boy> listBoy;

    Boy boy1;
    Boy boy2;
    Boy boy3;

    private int sizeList = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        references();
        try {
            sizeList = GameActivity.tamanioListaPasar;
        }catch (Exception e){
            e.printStackTrace();
        }

        loadScore();

        /*TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Score.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(tarea, 4000);

        */
    }

    public void references() {
        ivPhotoOne = findViewById(R.id.ivStarOne);
        ivPhotoTwo = findViewById(R.id.ivStarTwo);
        ivPhotoThree = findViewById(R.id.ivStarThree);

        tvNameOne = findViewById(R.id.tvNameOne);
        tvNameTwo = findViewById(R.id.tvNameTwo);
        tvNameThree = findViewById(R.id.tvNameThree);


    }


    public void loadScore() {
        /*SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        text = sharedPreferences.getString("puntaje", "No existe puntaje");
        textScore.setText(text);
        */
        //managerSQLiteHelper.openDB();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM "+ Constantes.NAME_TABLE_USER+" ORDER BY "+Constantes.USE_COL_6+" DESC; ",null);
            if (cursor.moveToFirst()) {

                do {

                    Boy boy = new Boy();
                /*
                boy.setId(cursor.getInt(0));
                boy.setName(cursor.getString(1));
                boy.setFecha_nacimiento(cursor.getString(2));
                boy.setGenero(cursor.getString(3));
                boy.setUrl_avatar(cursor.getString(4));
                */
                    boy.setScore(cursor.getInt(5));

                    listBoy.add(boy);

                } while (cursor.moveToNext());

            }
            boy1 = listBoy.get(0);
            boy2 = listBoy.get(1);
            boy3 = listBoy.get(2);
            updateViews();

        }catch (Exception e){
            Toast.makeText(this, "No hay suficicientes registros", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateViews() {
        /*
        int puntos = 0;
        try {
            puntos = Integer.parseInt(text);
            Log.e("puntos", "" + puntos);
        }catch (Exception e){
            e.printStackTrace();
        }


        if (puntos < sizeList / 2) {
            starOne.setImageResource(R.drawable.starllenita);
            starTwo.setImageResource(R.drawable.emptystar);
            starThree.setImageResource(R.drawable.emptystar);
        } else if (puntos >= sizeList / 2 && puntos < sizeList) {
            starOne.setImageResource(R.drawable.starllenita);
            starTwo.setImageResource(R.drawable.starllenita);
            starThree.setImageResource(R.drawable.emptystar);
        } else if (puntos >= sizeList) {
            starOne.setImageResource(R.drawable.starllenita);
            starTwo.setImageResource(R.drawable.starllenita);
            starThree.setImageResource(R.drawable.starllenita);
        }

        textScore.setText("Tú calificación fue " + text + " de " + sizeList);
    */

        try {
            decodeImage(boy1.getUrl_avatar(),ivPhotoOne);
            tvNameOne.setText(boy1.getName());

            decodeImage(boy2.getUrl_avatar(),ivPhotoTwo);
            tvNameTwo.setText(boy2.getName());

            decodeImage(boy2.getUrl_avatar(),ivPhotoThree);
            tvNameThree.setText(boy3.getName());

        }catch (Exception e){
            Toast.makeText(this, "Aun no hay suficientes integrantes", Toast.LENGTH_SHORT).show();
        }


    }

    public void decodeImage(String path, ImageView imageView) {
        if (path != null) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(path));
        } else {
            imageView.setImageResource(R.drawable.avataaars);
        }
    }




}
