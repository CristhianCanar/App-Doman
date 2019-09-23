package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.senasoft.appdoman.R;

public class Register extends AppCompatActivity {


    EditText etUserReg,etUserNameReg,etPasswordRegConfir;
    Button btnRegistrarReg;
    RadioButton rButNinioReg,rButNiniaReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserReg = findViewById(R.id.etUserReg);
        etUserNameReg = findViewById(R.id.etUserNameReg);
        etPasswordRegConfir = findViewById(R.id.etPasswordRegConfir);

        rButNinioReg = findViewById(R.id.rButNinioReg);
        rButNiniaReg = findViewById(R.id.rButNiniaReg);

        btnRegistrarReg = findViewById(R.id.btnRegistrarReg);

    }
}
