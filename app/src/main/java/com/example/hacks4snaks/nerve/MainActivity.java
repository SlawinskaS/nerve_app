package com.example.hacks4snaks.nerve;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name = sharedPreferences.getString("name",null);
        String surname = sharedPreferences.getString("surname",null);
        String born = sharedPreferences.getString("born",null);
        String sex = sharedPreferences.getString("sex",null);
        String weight = sharedPreferences.getString("weight",null);
        String height  = sharedPreferences.getString("height",null);
        String fisrtSeizure = sharedPreferences.getString("firstSeizue",null);
        String lastMedicine = sharedPreferences.getString("lastMedicine",null);
        String LastSeizure = sharedPreferences.getString("LastSeizue",null);
        String medicine = sharedPreferences.getString("medicine",null);

        if (name==null && surname==null && born==null && sex==null && weight==null && height==null && fisrtSeizure==null && lastMedicine==null && LastSeizure==null && medicine==null )
        {

            Log.e("test1 ", String.valueOf(name));
            Log.e("test1 ", String.valueOf(surname));
            Log.e("test1 ", String.valueOf(born));
            Log.e("test1 ", String.valueOf(sex));
            Log.e("test1 ", String.valueOf(weight));
            Log.e("test1 ", String.valueOf(height));
            Log.e("test1 ", String.valueOf(fisrtSeizure));
            Log.e("test1 ", String.valueOf(lastMedicine));
            Log.e("test1 ", String.valueOf(LastSeizure));
            Log.e("test1 ", String.valueOf(medicine));


            editor.putString("name",getString(R.string.name_child));
            editor.putString("surname",getString(R.string.surname_child));
            editor.putString("born",getString(R.string.profile_born));
            editor.putString("sex",getString(R.string.profile_sex));
            editor.putString("weight",getString(R.string.profile_weight));
            editor.putString("height",getString(R.string.profile_height));
            editor.putString("firstSeizue",getString(R.string.profile_first_seizure));
            editor.putString("lastMedicine",getString(R.string.profile_last_medicine));
            editor.putString("LastSeizue",getString(R.string.profile_last_seizure));
            editor.putString("medicine",getString(R.string.profile_taken_medicine));
            editor.apply();

        }

        Intent intentMain = new Intent(MainActivity.this ,
                HomeActivity.class);
        MainActivity.this.startActivity(intentMain);
    }

}
