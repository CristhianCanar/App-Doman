package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.senasoft.appdoman.R;

public class SettingsAdmin extends AppCompatActivity implements View.OnClickListener {

    TextView tvNameUserSettings;
    Button btnAgregarPalFasUnoSett,btnCambiarCodigoRegSett, btnListarUsersSett, btnCerrarSesionSett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin);

        tvNameUserSettings = findViewById(R.id.tvNameUserSettings);

        btnAgregarPalFasUnoSett = findViewById(R.id.btnAgregarPalFasUnoSett);
        btnAgregarPalFasUnoSett.setOnClickListener(this);
        btnCambiarCodigoRegSett = findViewById(R.id.btnCambiarCodigoRegSett);
        btnCambiarCodigoRegSett.setOnClickListener(this);
        btnListarUsersSett = findViewById(R.id.btnListarUsersSett);
        btnListarUsersSett.setOnClickListener(this);
        btnCerrarSesionSett = findViewById(R.id.btnCerrarSesionSett);
        btnCerrarSesionSett.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgregarPalFasUnoSett:

                break;

            case  R.id.btnCambiarCodigoRegSett:

                break;

            case R.id.btnListarUsersSett:
                break;

            case R.id.btnCerrarSesionSett:

                break;
        }

    }
}
