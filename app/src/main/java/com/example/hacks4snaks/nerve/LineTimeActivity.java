package com.example.hacks4snaks.nerve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.vipulasri.timelineview.TimelineView;

public class LineTimeActivity extends AppCompatActivity {

    public TimelineView timelineView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_time);
    }
}
