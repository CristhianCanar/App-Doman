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
import com.senasoft.appdoman.model.Prueba;
import com.senasoft.appdoman.model.Word;
import com.senasoft.appdoman.model.WordPrueba;

import java.text.Normalizer;
import java.util.ArrayList;

import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

public class GameActivity extends AppCompatActivity {

    private ImageButton btnCerrar, btnPlay, btnMicrophone;
    private TextView tvWord;

    private ManagerSQLiteHelper managerSQLiteHelper;
    private ArrayList<Word> lista;

    private MediaPlayer mediaPlayer;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener eventListener;
    private Animation animation;

    // Variables of game

    private int[] arrayGen;
    private int countWord = 0;
    private final int RED_COUNT_SPEED_INPUT = 1;
    public static ArrayList<String> resultadoVoz;
    public int bandera = 0;

    // Share preferences
    public static final String SHARED_PREF = "puntaje";

    //declarations for Score
    public static int tamanioListaPasar;
    public int puntos = 0;
    private int numWords = 5;
    private int idUser;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();
        managerSQLiteHelper = new ManagerSQLiteHelper(this);

        // init all
        initViews();
        initWords();

        // init sensors
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        startSensor();

        // Animation
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_word_game);

        //save share
        if (lista != null) {
            tamanioListaPasar = lista.size();
            Log.e("tamanio", "" + tamanioListaPasar);
        }

    }

    private void initViews() {

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

    /* Method to init words
     *  by: David Argote*/

    private void initWords() {

        try {

            int categoria = getIntent().getIntExtra("categoria", 0);
            idUser = getIntent().getIntExtra("idUser", 0);

            lista = new ArrayList<>(managerSQLiteHelper.readDataWord(categoria));

            if (lista.size() != 0) {

                savePrueba(); // guardar prueba

                int prueba = managerSQLiteHelper.listRevertPrueba(getIntent().getIntExtra("idUser", 0)).getId();

                if (prueba == 1){
                    numWords = 5;
                    btnMicrophone.setVisibility(View.INVISIBLE);
                    btnPlay.setVisibility(View.VISIBLE);
                }else if (prueba >= 2){
                    numWords = 10;
                    btnMicrophone.setVisibility(View.VISIBLE);
                    btnPlay.setVisibility(View.INVISIBLE);
                }

            }

            tamanioListaPasar = numWords;

            arrayGen = generarNum(numWords); // números aleatoreos
            tvWord.setText(lista.get(arrayGen[0]).getName());

        } catch (Exception e) {
            e.printStackTrace();
            tvWord.setText("No hay palabras");
        }

    }

    private void savePrueba() {

        Prueba prueba = new Prueba();

        prueba.setNum_words(numWords);
        prueba.setId_boy(getIntent().getIntExtra("idUser", 0));

        managerSQLiteHelper.insertPrueba(prueba);

    }

    /*Method to change words
     * by: David Argote*/

    private void nextWord(int count) {

        mediaPlayer = new MediaPlayer();
        bandera = count;

        if (lista != null) {
            if (count < numWords) {
                try {

                    tvWord.setText(lista.get(arrayGen[count]).getName());

                    mediaPlayer.setDataSource(lista.get(arrayGen[count]).getUrl_auidio());

                    mediaPlayer.prepare();

                    tvWord.startAnimation(animation);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (count == numWords && lista.size() != 0) {

                Intent intent = new Intent(GameActivity.this, MiniGameActivity.class);
                startActivity(intent);
                finish();

            }
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
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Te escucho");

        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No se puede hacer el proceso", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String palabra = "";

        if (lista != null) {
            palabra = lista.get(arrayGen[bandera]).getName();
        }

        String palabraNormalize = Normalizer.normalize(palabra, Normalizer.Form.NFD);
        String palabraSinAcentos = palabraNormalize.replaceAll("[^\\p{ASCII}]", "");
        Log.e("Word", "" + palabraSinAcentos);

        switch (requestCode) {
            case RED_COUNT_SPEED_INPUT:
                if (resultCode == RESULT_OK && null != data) {

                    resultadoVoz = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String cadenaNormalize = Normalizer.normalize(resultadoVoz.get(0), Normalizer.Form.NFD);
                    String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

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

                                registerWordInFase(idUser, lista.get(arrayGen[bandera]).getId(), 1);

                            } else {

                                View toast = GameActivity.this.getLayoutInflater().inflate(R.layout.toast_incorrect, null);
                                Toast incorrectToast = new Toast(getApplicationContext());

                                incorrectToast.setView(toast);
                                incorrectToast.setDuration(Toast.LENGTH_SHORT);
                                incorrectToast.show();
                                puntos = puntos;
                                startSensor();

                                registerWordInFase(idUser, lista.get(arrayGen[bandera]).getId(), 0);

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


    private void registerWordInFase(int idUser, int idWord, int isCorrect) {

        int idPrueba = managerSQLiteHelper.listRevertPrueba(idUser).getId();

        WordPrueba wordPrueba = new WordPrueba();

        wordPrueba.setId_prueba(idPrueba);
        wordPrueba.setId_word(idWord);
        wordPrueba.setEs_correcta(isCorrect);

        managerSQLiteHelper.insertWordPrueba(wordPrueba);

    }

    /* Method to init array of number randoms
     *  by: David Argote */

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
