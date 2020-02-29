package com.senasoft.appdoman.Logical;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class LogicalIntervals {
    //Variables de tiempo
    private static final long START_TIME_IN_MILLIS = 4000;
    //Clase del temporizador
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;


    public void startTimer(TextView txvTimer){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText(txvTimer);
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;

            }
        }.start();
        mTimerRunning=true;
    }

    public void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    public void resetTimer(TextView txvTimer){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText(txvTimer);
    }

    public void updateCountDownText(TextView txvTimer) {
        int seconds = (int)(mTimeLeftInMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%2d",seconds);
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
