package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class FoodActivity extends Header {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ImageButton profileButton = (ImageButton) FoodActivity.this.findViewById(R.id.imageButtonFood);
        profileButton.setImageResource(R.drawable.icon_food_blue);
    }

    public void onBackPressed()
    {
        Intent intentMain = new Intent(FoodActivity.this ,
                MainActivity.class);
        FoodActivity.this.startActivity(intentMain);
    }
}
