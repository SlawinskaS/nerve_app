package com.example.hacks4snaks.nerve;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ManageAccounts extends Header {
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSensor = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accounts);
        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        ImageButton profileButton = (ImageButton) ManageAccounts.this.findViewById(R.id.imageButtonHead);
        profileButton.setImageResource(R.drawable.icon_profile_blue);

        String sex_child = sharedPreferences.getString("sex","");
        Log.e("sad",sex_child);
        if (sex_child.equals("Male")) {
            radioButton= (RadioButton)findViewById(R.id.Male);
            radioButton.setChecked(true);
        }
        else {
            radioButton= (RadioButton)findViewById(R.id.Famale);
            radioButton.setChecked(true);
        }

    }
    public void onClickCancelManage(View w) {
        Intent intentMain = new Intent(ManageAccounts.this ,
                SettingsActivity.class);
        ManageAccounts.this.startActivity(intentMain);
    }
    public void onClickUpdateManage(View w) {

        EditText edit_text_name   = (EditText)findViewById(R.id.NameManage);
        String nameChild = edit_text_name.getText().toString();
        EditText edit_text_surname   = (EditText)findViewById(R.id.SurNameManage);
        String nameSurname = edit_text_surname.getText().toString();
        radioGroup = (RadioGroup)findViewById(R.id.groupRadioSex);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        radioButton= (RadioButton)findViewById(radioButtonId);
        String sexButton = (String) radioButton.getText();
        EditText edit_text_Height   = (EditText)findViewById(R.id.ManageHeight);
        String Height = edit_text_Height.getText().toString();
        EditText edit_text_Weight   = (EditText)findViewById(R.id.ManageWeight);
        String Weight= edit_text_Weight.getText().toString();
        EditText edit_text_Born   = (EditText)findViewById(R.id.BornManage);
        String Born = edit_text_Born.getText().toString();
        Log.e("Name: ",nameChild + " <b>"+nameSurname+"</b>");
        Log.e("Sex: ",sexButton);
        Log.e("Height: ",Height);
        Log.e("Weight: ",Weight);
        Log.e("Born: ",Born);

        SharedPreferences sharedPreferences = getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",nameChild);
        editor.putString("surname",nameSurname);
        editor.putString("born",Born);
        editor.putString("sex",sexButton);
        editor.putString("weight",Weight);
        editor.putString("height",Height);
        editor.apply();

        Intent intentMain = new Intent(ManageAccounts.this ,
                HomeActivity.class);
        ManageAccounts.this.startActivity(intentMain);
    }
    public void onBackPressed() {
        Intent intentMain = new Intent(this ,
                HomeActivity.class);
        this.startActivity(intentMain);
    }
//    public void ManageOnClickToProfile(View view)
//    {
//        Intent intentMain = new Intent(this ,
//                ProfileActivity.class);
//        this.startActivity(intentMain);
//    }
//    public void ManageOnClickToFood(View view)
//    {
//        Intent intentMain = new Intent(this ,
//                FoodActivity.class);
//        this.startActivity(intentMain);
//    }
//    public void ManageOnClickToTimeLine(View view)
//    {
//        Intent intentMain = new Intent(this ,
//                FoodActivity .class);
//        this.startActivity(intentMain);
//    }
//    public void ManageOnClickToSettings(View view)
//    {
//        Intent intentMain = new Intent(this ,
//                SettingsActivity.class);
//        this.startActivity(intentMain);
//    }

}
