package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.senasoft.appdoman.Logical.LogicalIntervals;
import com.senasoft.appdoman.R;

import java.util.Locale;

public class TimerIntervals extends AppCompatActivity {


    //Clase logica del temporizador
    LogicalIntervals logicalIntervals;
    //Declaracion
    private TextView txvTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_intervals);

        //Instanciacion del objeto
        logicalIntervals = new LogicalIntervals();
        //Referenciacion
        txvTimer = findViewById(R.id.txvTextNumbers);
        //Ejecucion
        logicalIntervals.startTimer(txvTimer);
        logicalIntervals.updateCountDownText(txvTimer);
    }



}
