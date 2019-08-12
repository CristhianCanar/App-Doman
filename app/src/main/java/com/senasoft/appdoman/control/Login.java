package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.senasoft.appdoman.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
