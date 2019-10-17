package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.Boy;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

import java.util.ArrayList;

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
    private int idUser;

    public static String[] PERMISSONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};

    public static int REQUEST_CODE = 111;
    private ArrayList<Boy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(PERMISSONS[0]) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(PERMISSONS[1]) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(PERMISSONS[2]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSONS, REQUEST_CODE);
            }
        }

        managerSQLiteHelper = new ManagerSQLiteHelper(this);
        initViews();
        viewUser();

    }

    private void initViews() {

        tvName = findViewById(R.id.tvUserLogin);
        imgAvatar = findViewById(R.id.imgAvatarUser);

        btnIniciaSesionLog = findViewById(R.id.btnIniciaSesionLog);
        btnIniciaSesionLog.setOnClickListener(this::onClick);

        btnBack = findViewById(R.id.btnBackUser);
        btnBack.setVisibility(View.INVISIBLE);
        btnBack.setOnClickListener(this::onClick);

        btnNext = findViewById(R.id.btnNextUser);
        btnNext.setVisibility(View.INVISIBLE);
        btnNext.setOnClickListener(this::onClick);

        btnConfig = findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(this::onClick);

        try {
            list = managerSQLiteHelper.readDataUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list.size() > 1) {
            btnNext.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciaSesionLog:

                if (ban) {
                    sendUser(idUser);
                } else {
                    sendUser(idUser-1);
                }

                break;

            case R.id.btnBackUser:

                count--;

                if (count < 0) {
                    count = 0;
                    btnBack.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }
                viewUser();

                break;

            case R.id.btnNextUser:

                count++;

                btnBack.setVisibility(View.VISIBLE);
                viewUser();

                break;

            case R.id.btnConfig:
                startActivity(new Intent(Login.this, MenuActivity.class));
                finish();
                break;
        }
    }

    private void viewUser() {

        if (managerSQLiteHelper.readDataUser() != null) {

            if (count == 0) {

                try {

                    decodeAvatar(list.get(0).getUrl_avatar());
                    tvName.setText(list.get(0).getName());
                    idUser = list.get(0).getId();
                    ban = true;

                } catch (Exception e) {

                    e.printStackTrace();
                    imgAvatar.setImageResource(R.drawable.avataaars);
                    tvName.setText("No hay registros");

                }

            } else if (count < list.size()) {

                decodeAvatar(list.get(count).getUrl_avatar());
                tvName.setText(list.get(count).getName());
                idUser = list.get(count).getId();
                ban = true;

            } else {
                btnNext.setVisibility(View.INVISIBLE);
                ban = false;
            }

        } else {
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

    public void sendUser(int id){

        intent = new Intent(Login.this, MainActivity.class);
        intent.putExtra("user", tvName.getText().toString());
        intent.putExtra("idUser", id);
        startActivity(intent);
        finish();

    }

}


