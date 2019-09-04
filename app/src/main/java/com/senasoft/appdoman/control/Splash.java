package com.senasoft.appdoman.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ManagerSQLiteHelper managerSQLiteHelper = new ManagerSQLiteHelper(this);
        managerSQLiteHelper.openDB();
        managerSQLiteHelper.closeDB();

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();

        timer.schedule(tarea, 3000);
    }
}
