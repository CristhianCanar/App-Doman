package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.Palabra;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Score extends AppCompatActivity {

    //declarations
    TextView textScore;
    ImageView starOne, starTwo, starThree;

    //share preferences
    public static final String SHARED_PREF = "puntaje";

    public  String text;

    private int sizeList = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_score);

        referent();
        sizeList = GameActivity.tamanioListaPasar;

        loadScore();
        updateViews();

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Score.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(tarea,5000);


    }

    public void referent (){
        textScore = findViewById(R.id.tvScore);
        starOne = findViewById(R.id.ivStarOne);
        starTwo = findViewById(R.id.ivStarTwo);
        starThree = findViewById(R.id.ivStarThree);


    }



    public void loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        text = sharedPreferences.getString("puntaje","No existe puntaje");
        textScore.setText(text);

    }

    public void updateViews(){
        int puntos = Integer.parseInt(text);
        Log.e("puntos",""+puntos);

        if (puntos< sizeList/2){
            starOne.setImageResource(R.drawable.starllenita);
            starTwo.setImageResource(R.drawable.emptystar);
            starThree.setImageResource(R.drawable.emptystar);
        }else if (puntos >= sizeList/2 && puntos < sizeList){
            starOne.setImageResource(R.drawable.starllenita);
            starTwo.setImageResource(R.drawable.starllenita);
            starThree.setImageResource(R.drawable.emptystar);
        }else if (puntos>=sizeList){
            starOne.setImageResource(R.drawable.starllenita);
            starTwo.setImageResource(R.drawable.starllenita);
            starThree.setImageResource(R.drawable.starllenita);
        }


        textScore.setText("Tú califiacación fue "+text+" de "+sizeList);
    }
}
