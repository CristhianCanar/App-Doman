package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnIniciaSesionLog;
    private TextView tvName;
    private ImageView btnBack, btnNext, btnConfig;
    private CircleImageView imgAvatar;
    private ManagerSQLiteHelper managerSQLiteHelper;

    private Intent intent;

    private int count = 0;
    private boolean ban = false;

    public static String[] PERMISSONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};

    public static int REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(PERMISSONS[0]) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(PERMISSONS[1]) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(PERMISSONS[2]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSONS, REQUEST_CODE);
            }
        }

        initViews();

        btnBack.setVisibility(View.INVISIBLE);
        managerSQLiteHelper = new ManagerSQLiteHelper(this);
        viewUser();

    }

    private void initViews() {

        tvName = findViewById(R.id.tvUserLogin);
        imgAvatar = findViewById(R.id.imgAvatarUser);

        btnIniciaSesionLog = findViewById(R.id.btnIniciaSesionLog);
        btnIniciaSesionLog.setOnClickListener(this::onClick);

        btnBack = findViewById(R.id.btnBackUser);
        btnBack.setOnClickListener(this::onClick);

        btnNext = findViewById(R.id.btnNextUser);
        btnNext.setOnClickListener(this::onClick);

        btnConfig = findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciaSesionLog:
                if (ban) {
                    intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("user", tvName.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "No sabemos quien quiere jugar", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBackUser:
                count--;
                if (count < 0) count = 0;
                viewUser();
                if (count == 0) btnNext.setVisibility(View.VISIBLE);
                break;

            case R.id.btnNextUser:
                count++;
                btnBack.setVisibility(View.VISIBLE);
                viewUser();
                break;

            case R.id.btnConfig:
                startActivity(new Intent(Login.this, SettingsAdmin.class));
                break;
        }
    }

    private void viewUser() {

        if (managerSQLiteHelper.readDataUser() != null) {
            ArrayList<User> list = managerSQLiteHelper.readDataUser();

            if (count == 0){

                try {
                    decodeAvatar(list.get(0).getUrlAvatar());
                    tvName.setText(list.get(0).getUsuName());
                    ban = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    imgAvatar.setImageResource(R.drawable.avataaars);
                    tvName.setText("No hay registros");
                    btnNext.setVisibility(View.INVISIBLE);
                }

            }else if (count < list.size()){

                decodeAvatar(list.get(count).getUrlAvatar());
                tvName.setText(list.get(count).getUsuName());
                ban = true;

            }else {
                Toast.makeText(this, "No hay mas registros", Toast.LENGTH_SHORT).show();
                btnNext.setVisibility(View.INVISIBLE);
                ban = false;
            }

        } else {
            Toast.makeText(this, "No hay registros de usuarios en la base de datos", Toast.LENGTH_SHORT).show();
            imgAvatar.setImageResource(R.drawable.avataaars);
            tvName.setText("Registra un usuario");
        }
    }

    public void decodeAvatar(String path) {
        if (path != null) {
            imgAvatar.setImageBitmap(BitmapFactory.decodeFile(path));
        } else {
            imgAvatar.setImageResource(R.drawable.avataaars);
        }
    }
}
