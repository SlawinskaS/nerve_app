package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SeizureDetected extends Header {
    private ProgressBar spinner;
    //    private TextView chromometr;
    Handler handler;
    Handler handlerDoubleClick;

    private int time = 14700;
    private int second;
    private int milisecond;
    private boolean doubletap = false;
    MediaPlayer alarm;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSensor = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seizure_detected);
        alarm = MediaPlayer.create(this,R.raw.alarm);
        alarm.start();

        handler= new Handler();
        handlerDoubleClick = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                alarm.stop();
                if(!doubletap) {
                    Intent intentMain = new Intent(SeizureDetected.this, SeizureInProgress.class);
                    SeizureDetected.this.startActivity(intentMain);
                }
            }
        }, 15000);
        SeizureDecetedFun();
    }

    private void SeizureDecetedFun() {
        Handler handler2= new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time > 0 && !doubletap) {
                    ProgressBar timer_seizure_in_progress = (ProgressBar) SeizureDetected.this.findViewById(R.id.progressBar);
                    TextView myAwesomeTextView = (TextView)findViewById(R.id.timer);
                    time-=100;
                    Log.e("Time",String.valueOf(time));
                    second = time/1000;
                    milisecond = time %1000;
                    milisecond = milisecond/10;

                    timer_seizure_in_progress.setProgress(15000 - time);
                    if (milisecond==0)
                    {
                        myAwesomeTextView.setText(String.valueOf(second+".0"+milisecond));
                    }
                    else {
                        myAwesomeTextView.setText(String.valueOf(second+"."+milisecond));
                    }
                    SeizureDecetedFun();
                }
            }
        }, 100);
    }

    public void OnClickCancelDetecting(View view) {
        Log.e("jaki stan daouble click",String.valueOf(doubletap));
        handlerDoubleClick = new Handler();
        handlerDoubleClick.postDelayed(new Runnable() {
            @Override
            public void run() {
                doubletap=false;
            }
        }, 2000);
        if (doubletap==true) {
            alarm.stop();
            handlerDoubleClick.removeMessages(0);
            handler.removeMessages(0);
            Intent intentMain = new Intent(SeizureDetected.this ,
                    HomeActivity.class);
            SeizureDetected.this.startActivity(intentMain);
        } else {
            doubletap=true;
        }

    }

    public void onBackPressed() {
        alarm.stop();
        handler.removeMessages(0);
        Intent intentMain = new Intent(SeizureDetected.this ,
                HomeActivity.class);
        SeizureDetected.this.startActivity(intentMain);
    }

}
