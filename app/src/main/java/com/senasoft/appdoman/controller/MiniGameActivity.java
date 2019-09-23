package com.senasoft.appdoman.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.senasoft.appdoman.R;

import java.util.Timer;
import java.util.TimerTask;

public class MiniGameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView globo1, globo2, globo3, globo4, globo5;

    // Tamaño de pantalla
    private int screenWidth;
    private int screenHeight;

    // Posición

    private float globoDownX;
    private float globoDownX2;
    private float globoDownX3;
    private float globoDownX4;
    private float globoDownX5;

    private float globoDownY;
    private float globoDownY2;
    private float globoDownY3;
    private float globoDownY4;
    private float globoDownY5;

    // Inicializacion de clases

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private MediaPlayer mediaPlayer;

    // Control
    public static int globosFin = 15;
    private int control = 0;

    int sizeList = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game);
        getSupportActionBar().hide();

        initViews();

        // Obtener tamaño de pantalla
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePost1();
                        changePost2();
                        changePost3();
                        changePost4();
                        changePost5();
                    }
                });
            }
        }, 0, 20);


    }

    private void initViews() {

        globo1 = findViewById(R.id.globo1);
        globo1.setOnClickListener(this::onClick);

        globo2 = findViewById(R.id.globo2);
        globo2.setOnClickListener(this::onClick);

        globo3 = findViewById(R.id.globo3);
        globo3.setOnClickListener(this::onClick);

        globo4 = findViewById(R.id.globo4);
        globo4.setOnClickListener(this::onClick);

        globo5 = findViewById(R.id.globo5);
        globo5.setOnClickListener(this::onClick);

        mediaPlayer = MediaPlayer.create(MiniGameActivity.this, R.raw.pinchazo_globo);

    }

    @Override
    protected void onResume() {
        super.onResume();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePost1();
                        changePost2();
                        changePost3();
                        changePost4();
                        changePost5();
                    }
                });
            }
        }, 0, 20);

    }

    private void setVisibleGlobo(ImageView imageView) {

        mediaPlayer.start();
        imageView.setVisibility(View.INVISIBLE);
        control++;
        if (control == globosFin) {
            Intent intent = new Intent(MiniGameActivity.this, Score.class);
            //intent.putExtra("size", sizeList);
            startActivity(intent);
            finish();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setVisibility(View.VISIBLE);
                    }
                });
            }
        }, 600);

    }

    private void changePost1() {

        globoDownY -= 10;

        if (globo1.getY() + globo1.getHeight() < 0) {
            globoDownX = (float) Math.floor(Math.random() * (screenWidth + globo1.getWidth()));
            globoDownY = screenHeight + 100.0f;
        }

        globo1.setX(globoDownX);
        globo1.setY(globoDownY);

    }

    private void changePost2() {
        globoDownY2 -= 10;

        if (globo2.getY() + globo2.getHeight() < 0) {
            globoDownX2 = (float) Math.floor(Math.random() * (screenWidth + globo2.getWidth()));
            globoDownY2 = screenHeight + 100.0f;
        }

        globo2.setX(globoDownX2);
        globo2.setY(globoDownY2);
    }

    private void changePost3() {
        globoDownY3 -= 10;

        if (globo3.getY() + globo3.getHeight() < 0) {
            globoDownX3 = (float) Math.floor(Math.random() * (screenWidth - globo3.getWidth()));
            globoDownY3 = screenHeight + 103.0f;
        }

        globo3.setX(globoDownX3);
        globo3.setY(globoDownY3);
    }

    private void changePost4() {
        globoDownY4 -= 10;

        if (globo4.getY() + globo4.getHeight() < 0) {
            globoDownX4 = (float) Math.floor(Math.random() * (screenWidth + globo4.getWidth()));
            globoDownY4 = screenHeight + 100.0f;
        }

        globo4.setX(globoDownX4);
        globo4.setY(globoDownY4);
    }

    private void changePost5() {
        globoDownY5 -= 6;

        if (globo5.getY() + globo5.getHeight() < 0) {
            globoDownX5 = (float) Math.floor(Math.random() * (screenWidth - globo5.getWidth()));
            globoDownY5 = screenHeight + 102.0f;
        }

        globo5.setX(globoDownX5);
        globo5.setY(globoDownY5);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.globo1:
                setVisibleGlobo(globo1);
                break;
            case R.id.globo2:
                setVisibleGlobo(globo2);
                break;
            case R.id.globo3:
                setVisibleGlobo(globo3);
                break;
            case R.id.globo4:
                setVisibleGlobo(globo4);
                break;
            case R.id.globo5:
                setVisibleGlobo(globo5);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
