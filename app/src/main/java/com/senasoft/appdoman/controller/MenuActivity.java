package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.senasoft.appdoman.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnAddWord, btnAddUser, btnListUser;
    private Button btnIrFase, btnIrEditUser, btnIrEditWord, btnAddCateg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        initViews();

    }

    private void initViews() {

        btnAddUser = findViewById(R.id.btnAddUSer);
        btnAddUser.setOnClickListener(this::onClick);

        btnAddWord = findViewById(R.id.btnAddWord);
        btnAddWord.setOnClickListener(this::onClick);

        btnListUser = findViewById(R.id.btnListUser);
        btnListUser.setOnClickListener(this::onClick);

        btnIrFase = findViewById(R.id.btnIrFase);
        btnIrFase.setOnClickListener(this::onClick);

        btnIrEditUser = findViewById(R.id.btnIrEditarUsuario);
        btnIrEditUser.setOnClickListener(this::onClick);

        btnIrEditWord = findViewById(R.id.btnIrEditarPalabras);
        btnIrEditWord.setOnClickListener(this::onClick);

        btnAddCateg = findViewById(R.id.btnIrAddCateg);
        btnAddCateg.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAddUSer:
                startActivity(new Intent(MenuActivity.this, AddUserActivity.class));
                break;
            case R.id.btnAddWord:
                startActivity(new Intent(MenuActivity.this, AddWordActivity.class));
                break;
            case R.id.btnListUser:
                startActivity(new Intent(MenuActivity.this, ListUserActivity.class));
                break;

            case R.id.btnIrFase:
                break;

            case R.id.btnIrEditarUsuario:
                break;

            case R.id.btnIrEditarPalabras:
                break;

            case R.id.btnIrAddCateg:
                startActivity(new Intent(MenuActivity.this, AddCategoryActivity.class));
                break;

        }
    }
}
