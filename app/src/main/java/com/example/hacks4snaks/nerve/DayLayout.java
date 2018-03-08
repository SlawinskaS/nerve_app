package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DayLayout  extends Header {
    private TextView theDate;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_layout);
        theDate = (TextView)findViewById(R.id.date);
        button = (Button) findViewById(R.id.backToCalendar);
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        theDate.setText(date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayLayout.this,CalendarActivity.class);
                DayLayout.this.startActivity(intent);
            }
        });

    }

}
