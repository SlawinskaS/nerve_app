package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends Header {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSensor = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageButton settingsButton = (ImageButton) SettingsActivity.this.findViewById(R.id.imageButtonSettings);
        settingsButton.setImageResource(R.drawable.icon_settings_blue);
    }
    public void onBackPressed()
    {
        Intent intentMain = new Intent(SettingsActivity.this ,
                HomeActivity.class);
        SettingsActivity.this.startActivity(intentMain);
    }

    public void settingsOnClickToManage(View view)
    {
        Intent intentMain = new Intent(this ,
                SettingsActivity.class);
        this.startActivity(intentMain);
    }

}
