package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.Palabra;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {


    private TextView tvPalabra1, tvPalabra2, tvPalabra3, tvPalabra4, tvPalabra5;
    private ViewFlipper vfLetras;

    private ManagerSQLiteHelper managerSQLiteHelper;
    private ArrayList<Palabra> lista;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();

        managerSQLiteHelper = new ManagerSQLiteHelper(this);

        referent();

        scrollWord();

    }

    private void referent() {

        tvPalabra1 = findViewById(R.id.tvPalabra1);
        tvPalabra2 = findViewById(R.id.tvPalabra2);
        tvPalabra3 = findViewById(R.id.tvPalabra3);
        tvPalabra4 = findViewById(R.id.tvPalabra4);
        tvPalabra5 = findViewById(R.id.tvPalabra5);

        vfLetras = findViewById(R.id.viewFlipperLetras);

    }

    private void scrollWord() {

        lista = new ArrayList<>(managerSQLiteHelper.readDataWord());

        String[] listaLetras = {"Palabra 1","Palabra 2","Palabra 3","Palabra 4","Palabra 5"};

        tvPalabra1.setText(listaLetras[0]);
        tvPalabra2.setText(listaLetras[1]);
        tvPalabra3.setText(listaLetras[2]);
        tvPalabra4.setText(listaLetras[3]);
        tvPalabra5.setText(listaLetras[4]);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        vfLetras.setInAnimation(in);
        vfLetras.setOutAnimation(out);
        vfLetras.setFlipInterval(3500);
        vfLetras.setAutoStart(true);



    }

}
