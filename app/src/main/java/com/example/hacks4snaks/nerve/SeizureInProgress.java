package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

public class SeizureInProgress extends Header {
    private boolean doubletap = false;
    Handler handlerDoubleClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSensor = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seizure_in_progress);
        handlerDoubleClick = new Handler();
        Button tvBlink = (Button) findViewById(R.id.seizure_in_progress);
        LinearLayout tvBlink2 = (LinearLayout) findViewById(R.id.camera_button);
//        TextView TimerBlink = (TextView) findViewById(R.id.timer);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
//        Animation anim2 = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(10);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
//        anim2.setDuration(500); //You can manage the blinking time with this parameter
//        anim2.setStartOffset(10);
//        anim2.setRepeatMode(Animation.REVERSE);
//        anim2.setRepeatCount(Animation.INFINITE);
        tvBlink.startAnimation(anim);
        tvBlink2.startAnimation(anim);


    }
    @Override
    public void onBackPressed()
    {
        Intent intentMain = new Intent(SeizureInProgress.this ,
                HomeActivity.class);
        SeizureInProgress.this.startActivity(intentMain);
    }
    public  void ProgressonClickToCancel(View v)
    {
        Log.e("jaki stan daouble click",String.valueOf(doubletap));
        handlerDoubleClick = new Handler();
        handlerDoubleClick.postDelayed(new Runnable() {
            @Override
            public void run() {
                doubletap=false;
            }
        }, 2000);
        if (doubletap==true)
        {
            handlerDoubleClick.removeMessages(0);

            Intent intentMain = new Intent(SeizureInProgress.this ,
                    HomeActivity.class);
            SeizureInProgress.this.startActivity(intentMain);
        }
        else
        {
            doubletap=true;
        }

    }
}
