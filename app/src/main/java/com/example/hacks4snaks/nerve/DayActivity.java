package com.example.hacks4snaks.nerve;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hacks4snaks.nerve.utils.Utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DayActivity extends Header {
    TextView mealText;
    TextView seizureText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams wmlp = getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.CENTER;

        setContentView(R.layout.activity_day);

        Utils utilities = new Utils();
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
//        TextView textView = (TextView) findViewById(R.id.NarveDay);
        TextView nerveDay = (TextView) findViewById(R.id.NerveDay);
        TextView nerveWeekDay = (TextView) findViewById(R.id.NerveWeekDay);
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date convertedDateNerve = (Date)format.parse(date, pos);
        String dateNerve = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(convertedDateNerve);
        String dayNerve = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(convertedDateNerve);
        nerveDay.setText(dateNerve);
        nerveWeekDay.setText(dayNerve);

        int currentHour = 0;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Resources r = getResources();
        layoutParams.setMargins(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()));

        LinearLayout scroll = findViewById(R.id.scrollContent);
        boolean addedTextView = false;
        int added = 0;
        for(int i = 0; i < 24; i++) {
            addedTextView = false;
            LayoutInflater inflater = (LayoutInflater)      this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View childLayout = inflater.inflate(R.layout.item_day,
                    (RelativeLayout) findViewById(R.id.item_day));
            childLayout.setId(i);

            TextView hour = childLayout.findViewById(R.id.hour);
            if(i < 10) {
                hour.setText("0" + i + ":00");
            } else {
                hour.setText(i + ":00");
            }
            LinearLayout linearLayout = childLayout.findViewById(R.id.day_events);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            String[] textArray = utilities.loadArray("Seizure_" + date, this);
            for( int j = 0; j < textArray.length; j++ ) {
                if(textArray[j].split(":")[0].equals(String.valueOf(i))) {
                    addedTextView = true;
                    added++;
                    LinearLayout event = new LinearLayout(this);
                    event.setOrientation(LinearLayout.HORIZONTAL);
                    event.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

                    event.addView(utilities.getSeizureIcon(18, 18, this));

                    TextView textView = new TextView(this);
                    textView.setText(textArray[j]);
                    textView.setTextColor(getResources().getColor(R.color.colorGrey));
                    textView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 8, r.getDisplayMetrics()));
                    textView.setPadding(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics())
                    );
                    event.addView(textView);
                    linearLayout.addView(event, layoutParams);
                }
            }
            textArray = utilities.loadArray("Meal_" + date, this);
            for( int j = 0; j < textArray.length; j++ ) {
                if(textArray[j].split(":")[0].equals(String.valueOf(i))) {
                    addedTextView = true;
                    added++;
                    LinearLayout event = new LinearLayout(this);
                    event.setOrientation(LinearLayout.HORIZONTAL);
                    event.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

                    event.addView(utilities.getFoodIcon(18, 18, this));

                    TextView textView = new TextView(this);
                    textView.setText(textArray[j]);
                    textView.setTextColor(getResources().getColor(R.color.colorGrey));
                    textView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 8, r.getDisplayMetrics()));
                    textView.setPadding(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics())
                    );
                    event.addView(textView);
                    linearLayout.addView(event, layoutParams);
                }
            }
            textArray = utilities.loadArray("Medicine_" + date, this);
            for( int j = 0; j < textArray.length; j++ ) {
                if(textArray[j].split(":")[0].equals(String.valueOf(i))) {
                    addedTextView = true;
                    added++;
                    LinearLayout event = new LinearLayout(this);
                    event.setOrientation(LinearLayout.HORIZONTAL);
                    event.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

                    event.addView(utilities.getMedicineIcon(18, 18, this));

                    TextView textView = new TextView(this);
                    textView.setText(textArray[j]);
                    textView.setTextColor(getResources().getColor(R.color.colorGrey));
                    textView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 8, r.getDisplayMetrics()));
                    textView.setPadding(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, r.getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics())
                    );
                    event.addView(textView);
                    linearLayout.addView(event, layoutParams);
                }
            }
            if(addedTextView) scroll.addView(childLayout, layoutParams);
        }
        if(added == 0) {
            TextView textView = new TextView(this);
            textView.setText("No events to report.");
            textView.setTextColor(getResources().getColor(R.color.colorGrey));
            textView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 8, r.getDisplayMetrics()));
            scroll.addView(textView, layoutParams);
        }
    }
    private String getAllMeal(String data)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String meal = sharedPreferences.getString((data+"meal").toString(),null);
        if (meal!=null)
        {
            return meal;
        }
        else {
            return "";
        }

    }
    private String getAllSeizure(String data)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Seizure = sharedPreferences.getString((data+"Seizure").toString(),null);
        if (Seizure!=null)
        {
            return Seizure;
        }
        else {
            return "";
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
