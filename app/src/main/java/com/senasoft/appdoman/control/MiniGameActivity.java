package com.senasoft.appdoman.control;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senasoft.appdoman.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

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
    public static int globosEnd = 20;
    private int control = 0;

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

        if (control == globosEnd) {
            AlertDialog.Builder aler = new AlertDialog.Builder(MiniGameActivity.this);
            aler.setTitle("Felicitaciones!");
            aler.setMessage("Haz terminado la fase");
            aler.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MiniGameActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            aler.show();
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
        }, 400);


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
            globoDownY3 = screenHeight + 100.0f;
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
            globoDownY5 = screenHeight + 100.0f;
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
