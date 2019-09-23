package com.senasoft.appdoman.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.Palabra;

import java.text.Normalizer;
import java.util.ArrayList;

import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

public class GameActivity extends AppCompatActivity {

    private ImageButton btnCerrar, btnPlay, btnMicrophone;
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

    //Listener
    private final int RED_COUNT_SPEED_INPUT = 1;
    public static ArrayList<String> resultadoVoz;
    public int bandera = 0;

    //share preferences
    public static final String SHARED_PREF = "puntaje";
    //declarations for Score

    public static int tamanioListaPasar;
    public int puntos = 0;


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


        //save share
        tamanioListaPasar = lista.size();
        Log.e("tamanio", "" + tamanioListaPasar);


    }

    private void referent() {
        tvWord = findViewById(R.id.tvWordGame);

        btnCerrar = findViewById(R.id.btnCerrarGame);
        btnCerrar.setOnClickListener(view -> {

            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        });

        btnMicrophone = findViewById(R.id.btnMicrophoneGame);
        btnMicrophone.setOnClickListener(view -> listenerWord());
        btnPlay = findViewById(R.id.btnAudioGame);
        btnPlay.setOnClickListener(view -> playWord());

    }

    private void initWords() {

        String categoria = getIntent().getStringExtra("categoria");
        lista = new ArrayList<>(managerSQLiteHelper.readDataWord(categoria));
        tamanioListaPasar = lista.size();
        arrayGen = generarNum(lista.size());

        if (lista.isEmpty()) tvWord.setText("Hay pocas palabras :(");
        else tvWord.setText(lista.get(arrayGen[0]).getPalName());

    }

    private void nextWord(int count) {

        mediaPlayer = new MediaPlayer();
        bandera = count;

        if (count < lista.size()) {
            tvWord.setText(lista.get(arrayGen[count]).getPalName());
            try {
                mediaPlayer.setDataSource(lista.get(arrayGen[count]).getUriAudio());
                mediaPlayer.prepare();
                tvWord.startAnimation(animation);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (count == lista.size() && lista.size() != 0) {
            Intent intent = new Intent(GameActivity.this, MiniGameActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void playWord() {
        try {
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listenerWord() {
        try {
            iniciarEntradaVoz();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciarEntradaVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Te escucho...");

        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No se puede hacer el proceso", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String palabra = lista.get(arrayGen[bandera]).getPalName();
        String palabraNormalize = Normalizer.normalize(palabra, Normalizer.Form.NFD);
        String palabraSinAcentos = palabraNormalize.replaceAll("[^\\p{ASCII}]", "");
        Log.e("Palabra", "" + palabraSinAcentos);

        switch (requestCode) {
            case RED_COUNT_SPEED_INPUT:
                if (resultCode == RESULT_OK && null != data) {
                    resultadoVoz = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String cadenaNormalize = Normalizer.normalize(resultadoVoz.get(0), Normalizer.Form.NFD);
                    String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
                    Log.e("Palabra", "" + cadenaSinAcentos);
                    switch (requestCode) {
                        case 1:
                            if (palabraSinAcentos.equals(cadenaSinAcentos)) {
                                View toast = GameActivity.this.getLayoutInflater().inflate(R.layout.toast_correct, null);
                                Toast correctToast = new Toast(getApplicationContext());
                                correctToast.setView(toast);
                                correctToast.setDuration(Toast.LENGTH_LONG);
                                correctToast.show();
                                puntos = puntos + 1;
                                startSensor();
                            } else {
                                View toast = GameActivity.this.getLayoutInflater().inflate(R.layout.toast_incorrect, null);
                                Toast incorrectToast = new Toast(getApplicationContext());
                                incorrectToast.setView(toast);
                                incorrectToast.setDuration(Toast.LENGTH_SHORT);
                                incorrectToast.show();
                                puntos = puntos;
                                startSensor();
                            }
                            saveScore(puntos);

                            break;
                    }
                }
                break;
        }

    }

    public void saveScore(int puntos) {
        String texto = Integer.toString(puntos);
        Log.e("Text", "" + texto);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("puntaje", texto);
        editor.commit();
    }

    @SuppressLint("NewApi")
    public int[] generarNum(int cant) {

        int numMin = 0;
        int numMax = cant;

        int[] array;

        array = IntStream.rangeClosed(numMin, numMax - 1).toArray();

        Random random = new Random();

        for (int i = array.length; i > 0; i--) {

            int pos = random.nextInt(i);
            int tmp = array[i - 1];
            array[i - 1] = array[pos];
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

                if (x > 0) {
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
