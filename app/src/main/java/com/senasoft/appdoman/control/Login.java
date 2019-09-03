package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.senasoft.appdoman.R;

public class Login extends AppCompatActivity {

    EditText etUserLogin,etPasswordLogin;
    Button btnIniciaSesionLog;
    TextView titleRegisterRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        etUserLogin = findViewById(R.id.etUserLogin);
        titleRegisterRegister = findViewById(R.id.titleRegisterRegister);

        btnIniciaSesionLog = findViewById(R.id.btnIniciaSesionLog);

        btnIniciaSesionLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
