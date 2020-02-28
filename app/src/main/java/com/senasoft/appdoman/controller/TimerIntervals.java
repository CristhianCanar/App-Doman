package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.senasoft.appdoman.R;

import java.util.Locale;

public class TimerIntervals extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 4000;

    private TextView txvTimer;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_intervals);

        txvTimer = findViewById(R.id.txvTextNumbers);
        startTimer();
        updateCountDownText();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;

            }
        }.start();
        mTimerRunning=true;
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int seconds = (int)(mTimeLeftInMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%2d",seconds);

        Log.e("Error",""+seconds);
        if (seconds == 3){
            txvTimer.setTextColor(Color.YELLOW);
            txvTimer.setText(timeLeftFormatted);
        }
        if (seconds == 2){
            txvTimer.setTextColor(Color.BLUE);
            txvTimer.setText(timeLeftFormatted);
        }
        if (seconds == 1){
            txvTimer.setTextColor(Color.CYAN);
            txvTimer.setText(timeLeftFormatted);
        }
        if (seconds == 0){
            txvTimer.setTextColor(Color.RED);
            txvTimer.setText("GO");
        }





    }

}
