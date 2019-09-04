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

    EditText etUserLogin,etPasswordLogin;
    Button btnIniciaSesionLog;
    TextView titleRegisterRegister,tvErrorCredenciales;
    ManagerSQLiteHelper manSQLiteHelper = new ManagerSQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        etUserLogin = findViewById(R.id.etUserLogin);
        titleRegisterRegister = findViewById(R.id.titleRegisterRegister);
        tvErrorCredenciales = findViewById(R.id.tvErrorCredenciales);


        btnIniciaSesionLog = findViewById(R.id.btnIniciaSesionLog);

        //Usuario u = new Usuario();
        //u.setUsuPassword("andy");
        //u.setUsuUserName("111");
        manSQLiteHelper = new ManagerSQLiteHelper(this);
        //manSQLiteHelper.insertDataUser(u);

        btnIniciaSesionLog.setOnClickListener(view -> {

            //tomar datos de un srvicio o array de credenciales
            Usuario usuario = manSQLiteHelper.readCredentailUser(etUserLogin.getText().toString(),etPasswordLogin.getText().toString());

            //if(etUserLogin.getText().toString().equals(usuario.getUsuUserName().toString()) && etPasswordLogin.getText().toString().equals(usuario.getUsuPassword().toString())){//credenciales correctas
            if(etUserLogin.getText().toString().isEmpty() || etUserLogin.getText().toString().isEmpty()){

                //arreglar cuando los campos esten vacios
            }
            if(!usuario.getUsuUserName().equalsIgnoreCase("-1") && !usuario.getUsuPassword().equalsIgnoreCase("-1")){//credenciales correctas
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{//credenciales incorrectas mensaje de error
                tvErrorCredenciales.setVisibility(View.VISIBLE);
                etPasswordLogin.setText("");
                etUserLogin.setText("");
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        tvErrorCredenciales.setVisibility(View.INVISIBLE);
                    }
                };
                timer.schedule(timerTask,3000);
            }

        });


    }

}
