package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

public class CalendarActivity extends Header {
    private static final String TAG = "Kalendarz";
    private CalendarView calendarActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarActivity =(CalendarView) findViewById(R.id.calendarNerve);
        calendarActivity.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month +1) + " / "+ dayOfMonth + " / " + year;
                Log.e(TAG , date);
                Intent intent = new Intent(CalendarActivity.this,DayLayout.class);
                intent.putExtra("date",date);
                CalendarActivity.this.startActivity(intent);
            }
        });

    }

}
