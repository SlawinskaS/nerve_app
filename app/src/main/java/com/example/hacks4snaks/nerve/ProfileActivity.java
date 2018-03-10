package com.example.hacks4snaks.nerve;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends Header {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSensor = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferencesProfileShow();
        ImageButton profileButton = (ImageButton) ProfileActivity.this.findViewById(R.id.imageButtonHead);
        profileButton.setImageResource(R.drawable.icon_profile_blue);
    }

    public void onBackPressed()
    {
        Intent intentMain = new Intent(ProfileActivity.this ,
                HomeActivity.class);
        ProfileActivity.this.startActivity(intentMain);
    }

    private void SharedPreferencesProfileShow()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);

        TextView sexChild,bornChild,weightChild,heughtChild,firstSeizue,lastSeizue,medicineChild,lastMedicine,childName;
        sexChild = findViewById(R.id.content_sex);
        bornChild = findViewById(R.id.content_born);
        weightChild = findViewById(R.id.content_weight);
        heughtChild = findViewById(R.id.content_height);

        firstSeizue = findViewById(R.id.content_first_seizure);
        lastSeizue = findViewById(R.id.content_last_seizure);
        medicineChild = findViewById(R.id.content_taken_medicine);
        lastMedicine = findViewById(R.id.content_last_medicine);
        childName = findViewById(R.id.child_name);

//        String name = sharedPreferences.getString("name","") + " " + sharedPreferences.getString("surname","");

        sexChild.setText(sharedPreferences.getString("sex",""));
        bornChild.setText(sharedPreferences.getString("born",""));
        weightChild.setText(sharedPreferences.getString("weight",""));
        heughtChild.setText(sharedPreferences.getString("height",""));
        firstSeizue.setText(sharedPreferences.getString("firstSeizue",""));
        lastSeizue.setText(sharedPreferences.getString("LastSeizue",""));
        medicineChild.setText(sharedPreferences.getString("medicine",""));
        lastMedicine.setText(sharedPreferences.getString("lastMedicine",""));

        String boldText = sharedPreferences.getString("surname","").toUpperCase();
        String normalText = sharedPreferences.getString("name","").toUpperCase();
        SpannableString name = new SpannableString(normalText + " " + boldText);
        name.setSpan(new StyleSpan(Typeface.BOLD), normalText.length(), boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        childName.setText(name);
    }
}
