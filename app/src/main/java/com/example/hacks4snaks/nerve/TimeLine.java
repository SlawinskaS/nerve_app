package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

//import java.awt.event.ActionListener;

public class TimeLine extends AppCompatActivity {
    private int minDay = -100;
    private int maxDay = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        Log.e("TimeLine","dziala");


    }

    public void   addDayToTimeLine(int numbersOfDays,int numberOfSeizure,String NameOfPils){


        String dzien;
        // get a date to represent "today"
        //  Date today = calendar.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, numbersOfDays);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        String monthString = Miesiace(month);
        int year = calendar.get(Calendar.YEAR);
        LinearLayout layoutTest ;

        layoutTest=(LinearLayout)findViewById(R.id.activity_linear_layout);

        if(day==1) {

                dzien = "" +monthString + " " +year+ "\n";
                TextView textView = new TextView(getApplicationContext());

                textView.setText(dzien);
                layoutTest.addView(textView);


        }
        dzien = day + " Pils: " + NameOfPils+"\n";

        Log.i("data", dzien);
//     setContentView(R.layout.layout_dynamic);

        TextView textView = new TextView(getApplicationContext());
        ProgressBar progressbar;
        progressbar = new ProgressBar(TimeLine.this, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setMax(100);
        progressbar.setProgress(numberOfSeizure);
        //progressbar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        //layoutparams = new LayoutParams(450,LayoutParams.WRAP_CONTENT);

     //   layoutparams.setMargins(0,100,0,0);

//


        layoutTest.addView(progressbar);
        textView.setText(dzien);
        layoutTest.addView(textView);
//            findWeather();



    }
    private String Miesiace(int miesiac){
        switch(miesiac+1){
            case 1:
                return "Jan";

            case 2:
                return "Feb";

            case 3:
                return "Mar";

            case 4:
                return "Apr";

            case 5:
                return "May";

            case 6:
                return "Jun";

            case 7:
                return "Jul";

            case 8:
                return "Aug";

            case 9:
                return "Sep";

            case 10:
                return "Oct";

            case 11:
                return "Nov";

            case 12:
                return "Dec";
        }
        return null;
    }
    public void findWeather()
    {

        JSONObject jsonObject = new JSONObject();

        String url = "http://api.openweathermap.org/data/2.5/forecast?id=756135&APPID=74fccc3c499e3089015c17e8c578372a";
        Log.i("pogoda","test4");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("test",String.valueOf(response));
                        try{
                            LinearLayout layoutTest ;

                            layoutTest=(LinearLayout)findViewById(R.id.activity_linear_layout);
                            TextView textView = new TextView(getApplicationContext());

                            JSONObject main_object = response.getJSONObject("main");
                            JSONArray array = response.getJSONArray("weather");
                            JSONObject object = array.getJSONObject(0);
                            String temp = String.valueOf(main_object.getDouble("temp"));
                            textView.setText(temp);
                            layoutTest.addView(textView);
                            Log.i("pogoda",temp);


                        }catch (JSONException e){
                            e.printStackTrace();
                            Log.i("pogoda","test3");

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("test",String.valueOf(error));                    }
                });
//

    }
    public void onBackPressed()
    {
        Intent intentMain = new Intent(TimeLine.this ,
                MainActivity.class);
        TimeLine.this.startActivity(intentMain);
    }
    public void OnClickToProfilTimeLine(View view)
    {

        Intent intentMain = new Intent(TimeLine.this ,
                ProfileActivity.class);
        TimeLine.this.startActivity(intentMain);
    }
    public void OnClickToFoodTimeLine(View view)
    {

        Intent intentMain = new Intent(this ,
                FoodActivity.class);
        this.startActivity(intentMain);
    }
    public void OnClickToTimeLineTimeLine(View view)
    {

        Intent intentMain = new Intent(this ,
                FoodActivity .class);
        this.startActivity(intentMain);
    }
    public void OnClickToSettingsTimeLine(View view)
    {

        Intent intentMain = new Intent(this ,
                SettingsActivity.class);
        this.startActivity(intentMain);
    }
}
