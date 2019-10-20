package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.senasoft.appdoman.R;

import java.util.Timer;
import java.util.TimerTask;

public class ScoreFinalActivity extends AppCompatActivity {

    private int score = 0;
    private Timer timer;
    private TimerTask timerTask;
    private ImageView imgStart1, imgStart2, imgStart3;
    private TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_final);


        SharedPreferences preferences = getSharedPreferences(GameActivity.SHARED_PREF, MODE_PRIVATE);
        score = preferences.getInt("puntaje", 0);

        initViews();

        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(ScoreFinalActivity.this, MainActivity.class));
                finish();
            }
        };

        timer.schedule(timerTask, 3500);

    }

    private void initViews() {


        imgStart1 = findViewById(R.id.ivStart1);
        imgStart2 = findViewById(R.id.ivStart2);
        imgStart3 = findViewById(R.id.ivStart3);

        tvScore = findViewById(R.id.tvPuntaje);
        tvScore.setText("Puntaje: " + score);

        if (score == 0 || score == 1) {
            imgStart2.setVisibility(View.INVISIBLE);
            imgStart3.setVisibility(View.INVISIBLE);
        } else if (score >= 2 && score <= 3) {
            imgStart3.setVisibility(View.INVISIBLE);
        }

    }


}
