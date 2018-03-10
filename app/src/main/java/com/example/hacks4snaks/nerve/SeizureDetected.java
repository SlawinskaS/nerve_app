package com.example.hacks4snaks.nerve;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hacks4snaks.nerve.utils.Utils;

import java.util.Calendar;

public class SeizureDetected extends AppCompatActivity {
    private ProgressBar spinner;
    //    private TextView chromometr;
    Handler handler;
    Handler handlerDoubleClick;

    private int time = 15000;
    private int second;
    private int milisecond;
    private boolean doubletap = false;
    MediaPlayer alarm;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seizure_detected);
        alarm = MediaPlayer.create(this,R.raw.alarm);
        alarm.start();

        handler = new Handler();
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
        }, 15900);
        if(!doubletap) {
            SeizureDecetedFun();
        }

        final ProgressBar mProgressBarYellow = (ProgressBar) SeizureDetected.this.findViewById(R.id.progressBar_yellow);
        mProgressBarYellow.setAlpha(0);
        final ProgressBar mProgressBarRed = (ProgressBar) SeizureDetected.this.findViewById(R.id.progressBar_red);
        mProgressBarRed.setAlpha(0);

        ValueAnimator animator_yellow = ValueAnimator.ofFloat(0f, 1f);
        animator_yellow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgressBarYellow.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        animator_yellow.setDuration(12000);
        animator_yellow.setRepeatMode(ValueAnimator.REVERSE);
        animator_yellow.setRepeatCount(-1);
        animator_yellow.start();

        ValueAnimator animator_red = ValueAnimator.ofFloat(0f, 1f);
        animator_red.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgressBarRed.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        animator_red.setStartDelay(7500);
        animator_red.setDuration(8000);
        animator_red.setRepeatMode(ValueAnimator.REVERSE);
        animator_red.setRepeatCount(-1);
        animator_red.start();
    }

    private void SeizureDecetedFun() {
        Handler handler2= new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time > 0 && !doubletap) {
                    ProgressBar timer_seizure_in_progress = (ProgressBar) SeizureDetected.this.findViewById(R.id.progressBar);
                    ProgressBar timer_seizure_in_progress_yellow = (ProgressBar) SeizureDetected.this.findViewById(R.id.progressBar_yellow);
                    ProgressBar timer_seizure_in_progress_red = (ProgressBar) SeizureDetected.this.findViewById(R.id.progressBar_red);
                    TextView myAwesomeTextView = (TextView)findViewById(R.id.timer);
                    time-=100;
                    Log.e("Time",String.valueOf(time));
                    second = time/1000;
                    milisecond = time %1000;
                    milisecond = milisecond/10;

                    String seconds = String.valueOf(second);
                    if (second < 10) {
                        seconds = "0" + seconds;
                    }
                    timer_seizure_in_progress.setProgress(15000 - time);
                    timer_seizure_in_progress_yellow.setProgress(15000 - time);
                    timer_seizure_in_progress_red.setProgress(15000 - time);
                    if (milisecond==0) {
                        myAwesomeTextView.setText(String.valueOf(seconds+".0"+milisecond));
                    }
                    else {
                        myAwesomeTextView.setText(String.valueOf(seconds+"."+milisecond));
                    }
                    if(!doubletap) {
                        SeizureDecetedFun();
                    }
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
            if(alarm != null) alarm.stop();
            handlerDoubleClick.removeMessages(0);
            handler.removeMessages(0);

//            long endTime = System.currentTimeMillis();
//            addToCalendarSeizureDetected(endTime);

            Intent intentMain = new Intent(SeizureDetected.this ,
                    HomeActivity.class);
            SeizureDetected.this.startActivity(intentMain);
        } else {
            doubletap=true;
        }

    }

    private void addToCalendarSeizureDetected(long timeOfSeizure) {
        Calendar calendar  = Calendar.getInstance();
        String data  = calendar.get(Calendar.DAY_OF_MONTH)+"."+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.YEAR);
        String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        Utils seizureAdder = new Utils();
//        seizureAdder.addToArray("Alarm_" + data, String.valueOf(time), this);
//        seizureAdder.addToArray("Alarms", data, this);
    }

    @Override
    public void onBackPressed() {
//        if(alarm != null) alarm.stop();
//        handler.removeMessages(0);
//        Intent intentMain = new Intent(SeizureDetected.this ,
//                HomeActivity.class);
//        SeizureDetected.this.startActivity(intentMain);
    }

}
