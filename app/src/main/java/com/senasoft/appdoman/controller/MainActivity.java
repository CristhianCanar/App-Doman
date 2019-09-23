package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.AdapterCategorias;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUser, tvBienvenida;
    private Button btnEmpezar;

    private ImageButton btnConfig;

    private ImageView btnInfo, btnDesLog;

    private RecyclerView rcCategorias;

    private ImageView ivNubeUno, ivNubeDos, ivNubeTres;

    private boolean catUno = false;
    private boolean catDos = false;
    private boolean catTres = false;
    private boolean catCuatro = false;
    private boolean catCinco = false;

    private Intent intent;
    private String categoria = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        referent();

        llenarCategorias();

        startAnimation();

    }

    private void referent() {

        tvUser = findViewById(R.id.tvNombreUSer);
        String user = getIntent().getStringExtra("user");
        tvUser.setText(user);

        tvBienvenida = findViewById(R.id.tvBienvenida);
        rcCategorias = findViewById(R.id.rcCategorias);

        btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(this);

        btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(this);

        btnDesLog = findViewById(R.id.btnDesLog);
        btnDesLog.setOnClickListener(this);

        btnConfig = findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(this);

        ivNubeUno = findViewById(R.id.nubeUno);
        ivNubeDos = findViewById(R.id.nubeDos);
        ivNubeTres = findViewById(R.id.nubeTres);
    }

    private void startAnimation() {

        Runnable task = () ->{

            // animation 1

            Animation a1 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_n1);
            ivNubeUno.startAnimation(a1);

            // animation 2

            Animation a2 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_n2);
            ivNubeDos.startAnimation(a2);

            // animation 3

            Animation a3 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_n2);
            ivNubeTres.startAnimation(a3);

            // animation 4

            Animation a4 = AnimationUtils.loadAnimation(this, R.anim.anim_word_game);
            btnEmpezar.startAnimation(a4);

        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnEmpezar:


                if (catUno == false && catDos == false && catTres == false && catCuatro == false && catCinco == false) {
                    Toast.makeText(this, "Selecciona una categoria", Toast.LENGTH_SHORT).show();
                } else {

                    intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("categoria", categoria);

                    startActivity(intent);

                    finish();
                }

                break;
            case R.id.btnInfo:
                // intent hacia la pantalla de info
                break;
            case R.id.btnDesLog:
                createDialog();
                break;

            case R.id.btnConfig:

                intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivity(intent);

                break;
        }

    }

    private void llenarCategorias() {

        final AdapterCategorias adapter = new AdapterCategorias();

        rcCategorias.setAdapter(adapter);

        rcCategorias.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        adapter.setOnClickListener(view -> {

            switch (rcCategorias.getChildAdapterPosition(view)) {
                case 0:
                    catUno = true;
                    categoria = "Familia";
                    break;
                case 1:
                    catDos = true;
                    categoria = "Nombres";
                    break;
                case 2:
                    catTres = true;
                    categoria = "Partes del cuerpo";
                    break;
                case 3:
                    catCuatro = true;
                    categoria = "Dias";
                    break;
                case 4:
                    catCinco = true;
                    categoria = "Materias";
                    break;
            }

        });

    }

    private void createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setIcon(R.drawable.logo_exit);

        builder.setTitle("Salir");
        builder.setMessage("¿Deseas salir de la aplicación?");

        builder.setPositiveButton("Aceptar",(dialogInterface, i) ->  System.exit(0));
        builder.setNegativeButton("Cancelar", ((dialogInterface, i) -> builder.setCancelable(true)));

        builder.create();
        builder.show();

    }

}
