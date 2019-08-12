package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.senasoft.appdoman.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUser;
    private Button btnAnimales, btnFrutas, btnPartes, btnEmpezar;

    private ImageView btnInfo, btnDesLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        referent();

    }

    private void referent() {

        tvUser = findViewById(R.id.tvNombreUSer);

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

        switch (view.getId()){
            case R.id.btnAnimales:
                break;
            case R.id.btnFrutas:
                break;
            case R.id.btnPartesCuerpo:
                break;
            case R.id.btnEmpezar:
                break;
            case R.id.btnInfo:
                break;
            case R.id.btnDesLog:
                break;
        }

    }
}
