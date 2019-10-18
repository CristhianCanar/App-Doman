package com.senasoft.appdoman.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.Boy;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.Prueba;
import com.senasoft.appdoman.model.Word;
import com.senasoft.appdoman.model.WordPrueba;

import java.sql.Time;
import java.text.Normalizer;
import java.util.ArrayList;

import java.util.Locale;
import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

public class GameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ImageButton btnCerrar;
    private TextView tvWord;

    private ManagerSQLiteHelper managerSQLiteHelper;
    private ArrayList<Word> lista;
    //Puntos
    private int punt = 0;

    private ArrayList<Boy> listBoy;
    private MediaPlayer mediaPlayer;
    private Animation animation;

    // Variables of game

    private int[] arrayGen;
    private final int RED_COUNT_SPEED_INPUT = 1;
    public static ArrayList<String> resultadoVoz;

    // Share preferences
    public static final String SHARED_PREF = "puntaje";

    //declarations for Score

    public static int tamanioListaPasar;
    private int idUser;

    private int numWords = 5;

    private int controlFase = 0;
    public int puntos = 0;
    public int countWord = 0;

    private GestureDetector gestureDetector;
    private Timer timer;
    private TimerTask task;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.gestureDetector = new GestureDetector(this, this);

        managerSQLiteHelper = new ManagerSQLiteHelper(this);

        // Animation
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_word_game);

        // init all

        initViews();
        initWords();


    }

    private void initViews() {

        tvWord = findViewById(R.id.tvWordGame);

        btnCerrar = findViewById(R.id.btnCerrarGame);
        btnCerrar.setOnClickListener(view -> {

            startActivity(new Intent(GameActivity.this, MainActivity.class));
            finish();

        });

    }

    private void initWords() {

        try {

            int categoria = getIntent().getIntExtra("categoria", 0);
            idUser = getIntent().getIntExtra("idUser", 0);
            Prueba prueba = new Prueba();

            lista = new ArrayList<>(managerSQLiteHelper.readDataWord(categoria));

            tamanioListaPasar = numWords;
            arrayGen = generarNum(numWords);

            nextWord(countWord);

            if (lista.size() > numWords) {

                prueba.setId_boy(idUser);
                prueba.setNum_words(numWords);

                managerSQLiteHelper.insertPrueba(prueba);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveWordCalification(int calification, int idWord){

        int idPrueba = managerSQLiteHelper.listRevertPrueba(idUser).getId();

        if (idPrueba > 1) {
            WordPrueba wordPrueba = new WordPrueba();

            wordPrueba.setId_word(idWord);
            wordPrueba.setId_prueba(idPrueba);
            wordPrueba.setEs_correcta(calification);

            managerSQLiteHelper.insertWordPrueba(wordPrueba);

        }

    }

    private void nextWord(int count) {

        mediaPlayer = new MediaPlayer();
        String pathAudio = "";

        if (lista != null) {

            if (count != numWords) {
                try {

                    tvWord.setText(lista.get(arrayGen[count]).getName());
                    tvWord.startAnimation(animation);
                    pathAudio = lista.get(arrayGen[count]).getUrl_auidio();

                    if (controlFase == 1) {
                        if (pathAudio.equals("") || pathAudio == null) {
                            Toast.makeText(GameActivity.this, "No hay audio", Toast.LENGTH_SHORT).show();
                        } else {
                            mediaPlayer.setDataSource(pathAudio);
                            mediaPlayer.prepare();
                            playWord();
                        }
                    } else {

                        timer = new Timer();

                        task = new TimerTask() {
                            @Override
                            public void run() {
                                listenerWord();
                            }
                        };

                        timer.schedule(task, 2500);

                    }

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
            e.printStackTrace();
        }

    }

    public void saveScore(int puntos) {

        String texto = Integer.toString(puntos);
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

    public int traerPuntos(){
        listBoy = new ArrayList<>(managerSQLiteHelper.readDataUser());
        punt = listBoy.get(0).getScore() ;
        return punt;
    }

    public void insertScoreInBD(int puntos){
        int id = 0;
        listBoy = new ArrayList<>(managerSQLiteHelper.readDataUser());
        id = listBoy.get(0).getId();

        managerSQLiteHelper.insertScore(id,puntos);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String palabra = "";

        if (lista != null) {
            palabra = lista.get(arrayGen[countWord]).getName();
        }

        String palabraNormalize = Normalizer.normalize(palabra, Normalizer.Form.NFD);
        String palabraSinAcentos = palabraNormalize.replaceAll("[^\\p{ASCII}]", "");

        switch (requestCode) {
            case RED_COUNT_SPEED_INPUT:

                if (resultCode == RESULT_OK && null != data) {

                    resultadoVoz = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String cadenaNormalize = Normalizer.normalize(resultadoVoz.get(0), Normalizer.Form.NFD);
                    String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

                    switch (requestCode) {
                        case 1:

                            if (palabraSinAcentos.equals(cadenaSinAcentos)) {
                                toastCorrect();
                                puntos = traerPuntos();
                                puntos = puntos + 1;
                                insertScoreInBD(puntos);
                                saveWordCalification(1, lista.get(arrayGen[countWord]).getId());


                            } else {
                                toastIncorrect();
                                saveWordCalification(0, lista.get(arrayGen[countWord]).getId());
                            }

                            saveScore(puntos);

                            break;
                    }
                }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.stop();
    }

    private void playWord() {
        try {
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toastCorrect() {
        View toast = GameActivity.this.getLayoutInflater().inflate(R.layout.toast_correct, null);
        Toast correctToast = new Toast(getApplicationContext());

        correctToast.setView(toast);
        correctToast.setDuration(Toast.LENGTH_SHORT);
        correctToast.show();
    }

    public void toastIncorrect() {
        View toast = GameActivity.this.getLayoutInflater().inflate(R.layout.toast_incorrect, null);
        Toast incorrectToast = new Toast(getApplicationContext());

        incorrectToast.setView(toast);
        incorrectToast.setDuration(Toast.LENGTH_SHORT);
        incorrectToast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        nextWord(countWord += 1);
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}
