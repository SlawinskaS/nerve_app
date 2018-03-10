package com.example.hacks4snaks.nerve;

//import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.hacks4snaks.nerve.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FoodActivity extends Header implements com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{
    private Spinner spinnerMeal;
    private Spinner spinner;
    Button addFoodBurron;
    Button AcceptFood;
    Button HidePicker;
    Button ShowPicker;
    EditText AddFood;
    String[] dania;
    String[] typyJedzenia;
    String meal;
    String newMeal = "";
    TimePicker timePicker;
    String timeOfFood ="";
    private int  mHour, mMinute;

    TextView timeTextView;
    private  int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_food);

        addFoodBurron = (Button)findViewById(R.id.addFoodButton);
        AcceptFood = (Button)findViewById(R.id.AcceptFood);
        AddFood = (EditText)findViewById(R.id.addFood);
//        timePicker = (TimePicker)findViewById(R.id.timePickerFood);
//        timePicker.setIs24HourView(true);
//        HidePicker = (Button)findViewById(R.id.TimePickerHide);
//        ShowPicker = (Button)findViewById(R.id.TimePickerShow);
//        timePicker = (TimePicker) findViewById(R.id.timePickerFood);
        spinner = findViewById(R.id.pillsSpinner);
        timeTextView = (TextView)findViewById(R.id.timerFood);
        Calendar cal = Calendar.getInstance();
        int currentHour = cal.get(Calendar.HOUR_OF_DAY);
        String hours = String.valueOf(currentHour);
        if(currentHour < 10) hours = "0" + hours;
        int currentMinutes = cal.get(Calendar.MINUTE);
        String minutes = String.valueOf(currentMinutes);
        if(currentMinutes < 10) minutes = "0" + minutes;
        timeOfFood = hours+":"+minutes;
        timeTextView.setText(timeOfFood);
        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        meal = sharedPreferences.getString(("meal").toString(),null);
        if (meal!=null){
            for(int i = 0;i<meal.length();i++)
                if(meal.charAt(i)=='\n'){
                    j++;
                }
        }
//        j++;
        j++;

        dania = new String[j];
        dania[0] = "choose your meal";
        j=1;
        String newdania="";
        if (meal!=null){
            for(int i = 0;i<meal.length();i++)
                if(meal.charAt(i)=='\n'){
                    dania[j]=newdania;
                    j++;
                    newdania="";
                }
                else{
                    newdania+=meal.charAt(i);
                }

        }


//        dania = new String[]{"choose the type of food", "Breakfast", "Second breakfast", "dinner", "Afternoon tea", "Dinner", "Snack"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dania);
//set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);

        typyJedzenia = new String[]{"choose the type of food", "Breakfast", "Second breakfast", "dinner", "Afternoon tea", "Dinner", "Snack"};
        ArrayAdapter<String> adapter2= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, typyJedzenia);
        spinnerMeal = findViewById(R.id.TypeOfMeal);

        spinnerMeal.setAdapter(adapter2);
        spinnerMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                Log.e("jhlknknn,jbmbnmbmnsad","asd");
                if(!(spinnerMeal.getSelectedItem().equals("choose the type of food"))){
//                    Log.e("jhlknknn,jbmbnmbmnsad","asd");
                    addFoodBurron.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    AcceptFood.setVisibility(View.VISIBLE);
                    AddFood.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(!(spinner.getSelectedItem().equals("choose your meal"))){
                    if(spinner.isSelected())
                        newMeal = (String) parentView.getItemAtPosition(position);
//                    String selectedItemText = ;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
    public void SendToCalendarDataOfMeal(View view){
        addToCalendarMeal();
        finish();
//        Calendar c = Calendar.getInstance();
//        String data  = c.get(Calendar.DAY_OF_MONTH)+"."+c.get(Calendar.MONTH)+"."+c.get(Calendar.YEAR);
//        Log.e("dzis" , data);
//        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        String meal = sharedPreferences.getString((data+"meal").toString(),null);
//        if(!spinner.getSelectedItem().toString().equals("choose your meal"))
//        {Log.e("opcja" , "1");
//            if (meal==null)
//            {
//                editor.putString((data+"meal").toString(),spinnerMeal.getSelectedItem().toString() + ": " + spinner.getSelectedItem().toString()+" " +timeTextView.getText().toString() +"\n");
//
//
//            }
//            else
//            {
//                editor.putString((data+"meal").toString(),meal+ spinnerMeal.getSelectedItem().toString() + ": " + spinner.getSelectedItem().toString()+" " +timeTextView.getText().toString() + "\n"  );
//            }
//        }
//        else if (!(newMeal.equals("")))
//        {
//            Log.e("opcja" , "2");
//            if (meal==null)
//            {
//                editor.putString((data+"meal").toString(),spinnerMeal.getSelectedItem().toString() + ": "+   newMeal +" " +timeTextView.getText().toString() + "\n"  );
//
//
//            }
//            else
//            {
//                editor.putString((data+"meal").toString(),meal  + spinnerMeal.getSelectedItem().toString() + ": " + newMeal+" " +timeTextView.getText().toString() + "\n"  );
//            }
//        }
//        else
//        {
//            Toast.makeText(FoodActivity.this,
//                    "Choose your meal", Toast.LENGTH_LONG).show();
//            Log.e("opcja" , "3");
//        }
//
//        editor.apply();
//        String mealZapisane = sharedPreferences.getString((data+"meal").toString(),null);

//        Log.e("Wybrane danie" , spinnerMeal.getSelectedItem().toString() + " " + spinner.getSelectedItem().toString());
//        Log.e("Zapinane danie" , mealZapisane.toString());
    }


    private void addToCalendarMeal() {
        Calendar calendar  = Calendar.getInstance();
        String mealDate  = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        Log.e("dzien",mealDate);

        Utils seizureAdder = new Utils();
        seizureAdder.addToArray("Meal_" + mealDate, timeOfFood + ";" +
                spinnerMeal.getSelectedItem() + ";" +
                spinner.getSelectedItem(), this);
        seizureAdder.addToArray("Meals", mealDate, this);
    }


    public void addNewMeal(View view){
        Log.e("test","test4");
        newMeal = AddFood.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String meal = sharedPreferences.getString(("meal").toString(),null);
        Log.e("asd",meal);
        String[] dania2;
        if (meal==null)
        {
            editor.putString(("meal").toString(),newMeal);


        }
        else
        {
            editor.putString(("meal").toString(),meal + "\n" + newMeal);
        }
        editor.apply();
//        meal = sharedPreferences.getString(("meal").toString(),null);
//        if (meal!=null){
//            for(int i = 0;i<meal.length();i++)
//                if(meal.charAt(i)=='\n'){
//                    j++;
//                }
//        }
//        j++;
//        dania2 = new String[j];
//        j=0;
//        String newdania="";
//        if (meal!=null){
//            for(int i = 0;i<meal.length();i++)
//                if(meal.charAt(i)=='\n'){
//                    dania2[j]=newdania;
//                    j++;
//                    newdania="";
//                }
//                else{
//                    newdania+=meal.charAt(i);
//                }
//
//        }
//
//        Log.e("test","test1");
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dania2);
//        Log.e("test","test2");
//        spinner.setAdapter(adapter2);
//        Log.e("test","test3");
    }
    @Override
    public void onBackPressed()
    {

            Intent intentMain = new Intent(FoodActivity.this ,
                    MainActivity.class);
            FoodActivity.this.startActivity(intentMain);

    }
    public void onAceptTime(View view){
        timePicker.setVisibility(View.INVISIBLE);
        int currentHour = timePicker.getCurrentHour();
        String hours = String.valueOf(currentHour);
        if(currentHour < 10) hours = "0" + hours;
        int currentMinutes = timePicker.getCurrentMinute();
        String minutes = String.valueOf(currentMinutes);
        if(currentMinutes < 10) minutes = "0" + minutes;
        timeOfFood = hours + ":" + minutes;
        timeTextView.setText(timeOfFood);
        ShowPicker.setVisibility(View.INVISIBLE);
        HidePicker.setVisibility(View.INVISIBLE);

        addFoodBurron.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
        AcceptFood.setVisibility(View.VISIBLE);
        AddFood.setVisibility(View.VISIBLE);
        spinnerMeal.setVisibility(View.VISIBLE);
    }
    public void onClickPickerShow(View view){
//        final Calendar c = Calendar.getInstance();
//        mHour = c.get(Calendar.HOUR_OF_DAY);
//          mMinute = c.get(Calendar.MINUTE);
//
//        // Launch Time Picker Dialog
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                new TimePickerDialog.OnTimeSetListener() {
//
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay,
//                                          int minute) {
//
//                        timeOfFood=String.valueOf(mHour + ":" + mMinute);
//                        timeTextView.setText(timeOfFood);                    }
//                }, mHour, mMinute, false);
//        timePickerDialog.show();
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                FoodActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.enableSeconds(false);
        tpd.setAccentColor("#4fa3c2");
//        dpd.setAccentColor(accentColor);
        tpd.setOkColor("#057492");
        tpd.setCancelColor("#FFC200");
        tpd.setCancelText("Cancel");
        tpd.setThemeDark(false);
        tpd.vibrate(true);
        tpd.show(getFragmentManager(), "Timepickerdialog");

//        timePicker.setVisibility(View.VISIBLE);
//        HidePicker.setVisibility(View.VISIBLE);
//        ShowPicker.setVisibility(View.VISIBLE);
//
//
//        addFoodBurron.setVisibility(View.INVISIBLE);
//        spinner.setVisibility(View.INVISIBLE);
//        AcceptFood.setVisibility(View.INVISIBLE);
//        AddFood.setVisibility(View.INVISIBLE);
//        spinnerMeal.setVisibility(View.INVISIBLE);

    }
    public void onCancelTime(View view){
        timePicker.setVisibility(View.INVISIBLE);
//        timeOfFood=String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute());
        ShowPicker.setVisibility(View.INVISIBLE);
        HidePicker.setVisibility(View.INVISIBLE);

        addFoodBurron.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
        AcceptFood.setVisibility(View.VISIBLE);
        AddFood.setVisibility(View.VISIBLE);
        spinnerMeal.setVisibility(View.VISIBLE);
    }
    @Override
    public void onTimeSet(com.wdullaer.materialdatetimepicker.time.TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hours = String.valueOf(hourOfDay);
        if(hourOfDay < 10) hours = "0" + hours;
        String minutes = String.valueOf(minute);
        if(minute < 10) minutes = "0" + minutes;
        timeOfFood = hours + ":" + minutes;
        timeTextView.setText(timeOfFood);
    }

    public void onClickDatePickerShow(View view){

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd =  DatePickerDialog.newInstance(
                FoodActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor("#4fa3c2");
        dpd.setOkColor("#057492");
        dpd.setCancelColor("#FFC200");
        dpd.setCancelText("Cancel");
        dpd.setThemeDark(false);
        dpd.vibrate(true);
        dpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}
