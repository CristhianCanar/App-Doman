package com.senasoft.appdoman.control;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.AdapterCategorias;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUser, tvBienvenida;
    private Button btnEmpezar;

    private ImageButton btnConfig;

    private ImageView btnInfo, btnDesLog;

    private RecyclerView rcCategorias;

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
    }

    private void referent() {

        tvUser = findViewById(R.id.tvNombreUSer);
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

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            }
        });

    }

    private void createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setIcon(R.drawable.logo_exit);

        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Deseas cerrar sesión?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create();
        builder.show();

    }

}
