package com.example.hacks4snaks.nerve;

import android.os.Bundle;

public class HomeActivity extends Header{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSensor = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
