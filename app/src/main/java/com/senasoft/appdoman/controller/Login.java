package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.senasoft.appdoman.R;

public class Login extends AppCompatActivity {

    private Button btnIniciaSesionLog;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        btnIniciaSesionLog = findViewById(R.id.btnIniciaSesionLog);
        btnIniciaSesionLog.setOnClickListener(View -> {
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra("user", "Pablito");
            startActivity(intent);
            finish();
        });
        tvName = findViewById(R.id.tvUserLogin);
    }

}
