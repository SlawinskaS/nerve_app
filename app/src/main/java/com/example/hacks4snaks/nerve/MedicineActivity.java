package com.example.hacks4snaks.nerve;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.hacks4snaks.nerve.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class MedicineActivity extends Header implements com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    int id = 1;
    TimePicker timePicker;
    String timeOfFood ="";
    Boolean AddingNewPills=false;
    TextView timeTextView;
    private  int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Utils utilities = new Utils();
        Intent intent = getIntent();
//        String date = intent.getStringExtra("date");
        String date  = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(Calendar.getInstance().getTime());


        String[] textArray = utilities.loadArray("Medicine_" + date, this);
        Log.e("this is my array", "arr: " + Arrays.deepToString(textArray));
        int numberOfThis = 1;
        addOldTodayPills("test","test","test");

        for (int i = 0 ;i<textArray.length;i++)
        {
            String elementOfArray = textArray[i];
            String nameOfMedicine="";
            String frequencyOfMedicine="";
            String DoseOfMedicine="";
            int k = 0 ;
            Log.e("this is my array", "arr: " + textArray[i]);
            Log.e("this is my array", "arr: " + elementOfArray);
            for(int j = 0;j<elementOfArray.length() ;j++)
            {
                if (elementOfArray.charAt(j)==';')
                {
                    k++;
                }
                else
                {
                    if(k==1)
                    {
                        nameOfMedicine+=elementOfArray.charAt(j);

                    }
                    else if ( k ==2)
                    {
                        frequencyOfMedicine+=elementOfArray.charAt(j);
                    }
                    else if ( k ==3)
                    {
                        DoseOfMedicine+=elementOfArray.charAt(j);
                    }
                }
            }
            addOldTodayPills(nameOfMedicine,frequencyOfMedicine,DoseOfMedicine);


        }





        addOldTodayPills("test","test","test");


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

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddingNewPills=true;
                Intent intentMain = new Intent(MedicineActivity.this ,
                        AddNewPills.class);
                MedicineActivity.this.startActivity(intentMain);

//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//                Resources r = getResources();
//                layoutParams.setMargins(
//                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics()),
//                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()),
//                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics()),
//                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()));
//
//                LinearLayout contentMedicine = findViewById(R.id.scrollContent);
//                LayoutInflater inflater = (LayoutInflater) MedicineActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
//                View childLayout = inflater.inflate(R.layout.item_medicine,
//                        (RelativeLayout) findViewById(R.id.item_medicine));
//                childLayout.setId(id++);
//                contentMedicine.addView(childLayout, layoutParams);
            }
        });
    }
    @Override
    public void onResume() {


        if (AddingNewPills) {
            Log.e("tu jest pausa ", "udalo sie znaleśćluke ");
            SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
            String nameOfMedicine = sharedPreferences.getString(("nameOfMedicine").toString(),"");
            String doseOfMedicine = sharedPreferences.getString(("doseOfMedicine").toString(),"");
            String frequencyOfMedicine = sharedPreferences.getString(("doseOfMedicine").toString(),"");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(("nameOfMedicine").toString(), "");
            editor.putString(("doseOfMedicine").toString(), "");
            editor.putString(("frequencyOfMedicine").toString(),"");

            editor.apply();
            if (nameOfMedicine!=""&&doseOfMedicine!=""&&frequencyOfMedicine!="")
            {
                Log.e("Name",nameOfMedicine);
                Log.e("dose",doseOfMedicine);
                Log.e("frequency",frequencyOfMedicine);
                addOldTodayPills(nameOfMedicine,doseOfMedicine,frequencyOfMedicine);
            }

            AddingNewPills=false;
        }
        super.onResume();
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
    }
    private void addOldTodayPills(String name,String dose,String frequency){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Resources r = getResources();
        layoutParams.setMargins(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()));

        LinearLayout contentMedicine = findViewById(R.id.scrollContent);
        LayoutInflater inflater = (LayoutInflater) MedicineActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = inflater.inflate(R.layout.item_medicine,
                (RelativeLayout) findViewById(R.id.item_medicine));
        childLayout.setId(id);
//        int idInt =  childLayout.getId();
//        int id2 = getResources().getIdentifier(Integer.toString(idInt), "id", "app.dj");
//        Log.e("id" , String.valueOf(idInt));
//        View homeView = inflater.inflate(id2, null);
        TextView nameMedicine = (TextView) childLayout.findViewById(R.id.medicine_name);
        TextView doseMedicine= (TextView) childLayout.findViewById(R.id.DoseMedicine);
        TextView frequencyMedicine = (TextView) childLayout.findViewById(R.id.FrequencyMedicine);
        nameMedicine.setText(name);
        doseMedicine.setText(dose);
        frequencyMedicine.setText(frequency);
//        TextView textView = (TextView)homeView.findViewById(R.id.medicine_name);
//        textView.setText("test mother Fucker :)");

        contentMedicine.addView(childLayout, layoutParams);
        id++;
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
                MedicineActivity.this,
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
                MedicineActivity.this,
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
