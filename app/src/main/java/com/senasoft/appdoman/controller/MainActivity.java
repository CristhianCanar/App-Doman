package com.senasoft.appdoman.controller;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.AdapterCategory;
import com.senasoft.appdoman.model.Category;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUser;
    private Button btnEmpezar;
    private ImageView btnDesLog, btnGoRanking;
    private RecyclerView rcCategorias;
    private ImageView ivNubeUno, ivNubeDos, ivNubeTres;

    private boolean control = false;
    private Intent intent;
    private int idCategoria;
    private String user;

    public static String MY_PREFRS_USER = "PREFERENTS_USER";
    public static String MY_KEY_USER = "userPref";

    private SharedPreferences preferences;
    private ManagerSQLiteHelper managerSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managerSQLiteHelper = new ManagerSQLiteHelper(this);

        if (getIntent().getStringExtra("user") != null) {

            preferences = getSharedPreferences(MY_PREFRS_USER, MODE_PRIVATE);
            preferences.getString(MY_KEY_USER, getIntent().getStringExtra("user"));

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(MY_KEY_USER, getIntent().getStringExtra("user"));
            editor.apply();

        }

        initViews();
        llenarCategorias();
        startAnimation();

    }

    private void initViews() {

        tvUser = findViewById(R.id.tvNombreUSer);

        preferences = getSharedPreferences(MY_PREFRS_USER, MODE_PRIVATE);
        user = preferences.getString(MY_KEY_USER, "Default");
        tvUser.setText(user);

        rcCategorias = findViewById(R.id.rcCategorias);
        rcCategorias.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(this);

        btnDesLog = findViewById(R.id.btnDesLog);
        btnDesLog.setOnClickListener(this);

        btnGoRanking = findViewById(R.id.btnGoRanking);
        btnGoRanking.setOnClickListener(this::onClick);

        ivNubeUno = findViewById(R.id.nubeUno);
        ivNubeDos = findViewById(R.id.nubeDos);
        ivNubeTres = findViewById(R.id.nubeTres);

    }

    /*Method to start animations
    * by: David Argote */
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
                if (control == false) {
                    Toast.makeText(this, "Selecciona una categoria", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("categoria", idCategoria);
                    intent.putExtra("user", user);
                    intent.putExtra("idUser", getIntent().getIntExtra("idUser", 0));
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.btnDesLog:
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
                break;

            case R.id.btnGoRanking:
                startActivity(new Intent(MainActivity.this, Score.class));
                break;
        }

    }

    /* Method to fill categories
     *  by: David Argote */

    private void llenarCategorias() {

        ArrayList<Category> list = new ArrayList<>(managerSQLiteHelper.readCategory());
        AdapterCategory adapter = new AdapterCategory(list);
        rcCategorias.setAdapter(adapter);

        adapter.setOnClickListener(view -> {
            int id = rcCategorias.getChildAdapterPosition(view);
            idCategoria = list.get((id)).getId();
            control = true;
        });
    }

}
