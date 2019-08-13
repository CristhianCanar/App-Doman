package com.senasoft.appdoman.control;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.control.model.AdapterCategorias;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUser, tvBienvenida;
    private Button btnAnimales, btnFrutas, btnPartes, btnEmpezar;

    private ImageView btnInfo, btnDesLog;

    private ListView lvListCategoria;

    private boolean catAnimales = false;
    private boolean catFrutas = false;
    private boolean catPartes = false;

    private boolean faseUno = false;
    private boolean faseDos = false;
    private boolean faseTres = false;
    private boolean faseCuatro = false;
    private boolean faseCinco = false;

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

        lvListCategoria = findViewById(R.id.lvListaFases);

        btnAnimales = findViewById(R.id.btnAnimales);
        btnAnimales.setOnClickListener(this);

        btnFrutas = findViewById(R.id.btnFrutas);
        btnFrutas.setOnClickListener(this);

        btnPartes = findViewById(R.id.btnPartesCuerpo);
        btnPartes.setOnClickListener(this);

        btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(this);


        btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(this);

        btnDesLog = findViewById(R.id.btnDesLog);
        btnDesLog.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnAnimales:
                catAnimales = true;
                break;
            case R.id.btnFrutas:
                catFrutas = true;
                break;
            case R.id.btnPartesCuerpo:
                catPartes = true;
                break;
            case R.id.btnEmpezar:
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.btnInfo:
                // inteten hacia la pantalla de info
                break;
            case R.id.btnDesLog:
                createDialog();
                break;
        }

    }

    private void llenarCategorias() {

        final AdapterCategorias adapterCategorias = new AdapterCategorias(getApplicationContext());

        lvListCategoria.setAdapter(adapterCategorias);

        lvListCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {

                    case 0:
                        faseUno = true;
                        break;
                    case 1:
                        faseDos = true;
                        break;
                    case 2:
                        faseTres = true;
                        break;
                    case 3:
                        faseCuatro = true;
                        break;
                    case 4:
                        faseCinco = true;
                        break;

                }


            }
        });

    }

    private void createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setIcon(R.drawable.baseline_exit_to_app_white_18dp);

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
