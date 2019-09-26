package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.senasoft.appdoman.model.AdapterCategory;
import com.senasoft.appdoman.model.Fase;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUser, tvScoreGlobal;
    private Button btnEmpezar;
    private ImageView btnDesLog;
    private RecyclerView rcCategorias;
    private ImageView ivNubeUno, ivNubeDos, ivNubeTres;

    private boolean catUno = false;
    private boolean catDos = false;
    private boolean catTres = false;
    private boolean catCuatro = false;
    private boolean catCinco = false;

    private Intent intent;
    private String categoria = "";
    private String user;

    public static String MY_PREFRS_USER = "PREFERENTS_USET";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getIntent().getStringExtra("user") != null) {
            preferences = getSharedPreferences(MY_PREFRS_USER, MODE_PRIVATE);
            preferences.getString("userPref", getIntent().getStringExtra("user"));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userPref", getIntent().getStringExtra("user"));
            editor.apply();
        }

        getSupportActionBar().hide();
        initViews();
        llenarCategorias();
        startAnimation();

    }

    private void initViews() {

        tvUser = findViewById(R.id.tvNombreUSer);
        tvScoreGlobal = findViewById(R.id.tvScoreGlobal);

        ManagerSQLiteHelper managerSQLiteHelper = new ManagerSQLiteHelper(MainActivity.this);

        preferences = getSharedPreferences(MY_PREFRS_USER, MODE_PRIVATE);
        user = preferences.getString("userPref", "Default");
        tvUser.setText(user);

        ArrayList<Fase> list = managerSQLiteHelper.searchPhase(user);

        int acum = 0;
        for (int i = 0; i < list.size(); i++) {
            acum += list.get(i).getScore();
        }
        tvScoreGlobal.setText(String.valueOf(acum));

        rcCategorias = findViewById(R.id.rcCategorias);

        btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(this);

        btnDesLog = findViewById(R.id.btnDesLog);
        btnDesLog.setOnClickListener(this);


        ivNubeUno = findViewById(R.id.nubeUno);
        ivNubeDos = findViewById(R.id.nubeDos);
        ivNubeTres = findViewById(R.id.nubeTres);
    }

    private void startAnimation() {

        // Runnable for animations
        Runnable task = () -> {

            // Animation 1
            Animation a1 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_n1);
            ivNubeUno.startAnimation(a1);

            // Animation 2
            Animation a2 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_n2);
            ivNubeDos.startAnimation(a2);

            // Animation 3
            Animation a3 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_n2);
            ivNubeTres.startAnimation(a3);

            // Animation 4
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
                // Validation for select category
                if (catUno == false && catDos == false && catTres == false && catCuatro == false && catCinco == false) {
                    Toast.makeText(this, "Selecciona una categoria", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("categoria", categoria);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.btnDesLog:
                createDialog();
                break;
        }

    }

    /* Method to fill categories
     *  by: David Argote */

    private void llenarCategorias() {

        final AdapterCategory adapter = new AdapterCategory();
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


    /* Method to create AlertDialog
     *  by: David Argote */

    private void createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setIcon(R.drawable.logo_exit);
        builder.setTitle("Salir");
        builder.setMessage("¿Deseas salir de la aplicación?");
        builder.setPositiveButton("Aceptar", (dialogInterface, i) -> System.exit(0));
        builder.setNegativeButton("Cancelar", ((dialogInterface, i) -> builder.setCancelable(true)));

        builder.create();
        builder.show();

    }

}
