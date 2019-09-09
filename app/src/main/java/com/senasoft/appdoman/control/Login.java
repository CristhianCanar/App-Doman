package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.Usuario;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {

    private Button btnIniciaSesionLog;

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

    }

}
