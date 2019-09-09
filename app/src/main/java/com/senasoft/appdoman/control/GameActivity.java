package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.Palabra;

import java.util.ArrayList;

import java.util.Random;
import java.util.stream.IntStream;

public class GameActivity extends AppCompatActivity {

    private ImageButton btnCerrar, btnPlay;
    private TextView tvWord;

    private ManagerSQLiteHelper managerSQLiteHelper;
    private ArrayList<Palabra> lista;

    private MediaPlayer mediaPlayer;

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener eventListener;

    private int countWord = 0;
    private int[] arrayGen;
    private Animation animation;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();
        managerSQLiteHelper = new ManagerSQLiteHelper(this);

        // init all
        referent();
        initWords();

        // init sensors
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        startSensor();

        // Animation

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_word_game);

    }

    private void referent() {

        tvWord = findViewById(R.id.tvWordGame);

        btnCerrar = findViewById(R.id.btnCerrarGame);
        btnCerrar.setOnClickListener(view -> {

            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        });

        btnPlay = findViewById(R.id.btnAudioGame);
        btnPlay.setOnClickListener(view -> listenerWord());

    }

    private void initWords() {

        String categoria = getIntent().getStringExtra("categoria");
        lista = new ArrayList<>(managerSQLiteHelper.readDataWord(categoria));
        arrayGen = generarNum(lista.size());

        if (lista.isEmpty())tvWord.setText("Hay pocas palabras :(");
        else tvWord.setText(lista.get(arrayGen[0]).getPalName());

    }

    private void nextWord(int count){

        mediaPlayer = new MediaPlayer();

        if (count < lista.size()) {
            tvWord.setText(lista.get(arrayGen[count]).getPalName());
            try {
                mediaPlayer.setDataSource(lista.get(arrayGen[count]).getUriAudio());
                mediaPlayer.prepare();
                tvWord.startAnimation(animation);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (count == lista.size() && lista.size() != 0){
            Intent intent = new Intent(GameActivity.this, MiniGameActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void listenerWord() {
        try {
            mediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    public int[] generarNum(int cant){

        int numMin = 0;
        int numMax = cant;

        int[] array;

        array = IntStream.rangeClosed(numMin, numMax-1).toArray();

        Random random = new Random();

        for (int i = array.length; i > 0; i--){

            int pos =  random.nextInt(i);
            int tmp = array[i-1];
            array[i-1] = array[pos];
            array[pos] = tmp;

        }
        return array;
    }

    private void startSensor() {

        if (sensor == null) Toast.makeText(this, "Sensor null", Toast.LENGTH_SHORT).show();

        eventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                float x = sensorEvent.values[0];

                if (x > 0){
                    nextWord(countWord++);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(eventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) mediaPlayer.stop();
        sensorManager.unregisterListener(eventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.stop();
        sensorManager.unregisterListener(eventListener);
    }
}
