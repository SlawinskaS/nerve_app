package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.hacks4snaks.nerve.utils.Utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        List<EventDay> events = new ArrayList<>();

        final TextView nerveDay = (TextView) findViewById(R.id.NerveDay);
        final TextView nerveWeekDay = (TextView) findViewById(R.id.NerveWeekDay);
        String dateNerve = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        String dayNerve = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        nerveDay.setText(dateNerve);
        nerveWeekDay.setText(dayNerve);

        Calendar calendar = Calendar.getInstance();


        Utils utilities = new Utils();
        String[] seizures = utilities.loadArray("Seizures", this);
        String[] meals = utilities.loadArray("Meals", this);

        ParsePosition pos;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date seizure_date;
        for( int j = 0; j < seizures.length; j++ ) {
            pos = new ParsePosition(0);
            seizure_date = (Date)format.parse(seizures[j], pos);
            if(seizure_date != null) {
                Log.e("napad " + j ,seizures[j]);
                calendar.setTime(seizure_date);
                events.add(new EventDay(calendar, R.drawable.event_red));
            }
        }
        Date meal_date;
        for( int j = 0; j < meals.length; j++ ) {
            pos = new ParsePosition(0);
            meal_date = (Date)format.parse(meals[j], pos);
            if(meal_date != null) {
                Log.e("posiłek " + j ,meals[j]);
                calendar.setTime(meal_date);
                events.add(new EventDay(calendar, R.drawable.event_green));
            }
        }
//        Date alarm_date;
//        for( int j = 0; j < alarms.length; j++ ) {
//            alarm_date = (Date)format.parse(alarms[j], pos);
//            if(alarm_date != null) {
//                calendar.setTime(alarm_date);
//                events.add(new EventDay(calendar, R.drawable.event_yellow));
//            }
//        }

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -1);
        calendarView.setMinimumDate(min);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.YEAR, 1);
        calendarView.setMaximumDate(max);

        calendarView.setEvents(events);

        try {
            calendarView.setDate(Calendar.getInstance().getTime());
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

//        calendarView.setDisabledDays(getDisabledDays());

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                String dateNerve = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(eventDay.getCalendar().getTime());
                String dayNerve = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(eventDay.getCalendar().getTime());
                nerveDay.setText(dateNerve);
                nerveWeekDay.setText(dayNerve);

                Calendar clickedDayCalendar = eventDay.getCalendar();
                Intent intent = new Intent(CalendarActivity.this, DayActivity.class);
                String date = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(eventDay.getCalendar().getTime());
                intent.putExtra("date", date);
                CalendarActivity.this.startActivity(intent);
            }
        });
    }

}
