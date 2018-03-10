package com.example.hacks4snaks.nerve;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;

/**
 * Created by fglow on 06.03.2018.
 */

public class NerveBackgroundService extends IntentService implements SensorEventListener {
    int i = 0 ;
    protected int time = 0;
    protected SensorManager sensorManager;
    protected Sensor accelerometer;
    protected boolean checkSensor = true;

    public NerveBackgroundService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            Log.e("Akceleroment","działa :)");
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e("Akceleroment","niedziała :(");
        }
        sensorManager.unregisterListener(this);
        accelerometer=null;
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER && checkSensor) {
//            Log.e("x",String.valueOf(event.values[0]));
//            Log.e("y",String.valueOf(event.values[1]));
//            Log.e("z",String.valueOf(event.values[2]));
            int sensorChange = 15;
            if (event.values[0]>sensorChange||event.values[0]<-sensorChange||event.values[1]>sensorChange||event.values[1]<-sensorChange ||event.values[2]>sensorChange||event.values[2]<-sensorChange) {
                i++;
//                Log.e("ilosc i",String.valueOf(i));
                if(time == 0) {
                    SeizureDecetedFun();
                }
            }
            if (i>5) {
                i=0;
                sensorManager.unregisterListener(this);
                accelerometer=null;
                Intent intentMain = new Intent(NerveBackgroundService.this ,
                        SeizureDetected.class);
                NerveBackgroundService.this.startActivity(intentMain);
            }
        }
    }

    private void SeizureDecetedFun() {
        Handler handler2= new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                time+=100;
                if (time % 5000 == 0) {
                    i = 0;
                    time = 0;
                } else {
                    SeizureDecetedFun();
                }
            }
        }, 100);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
