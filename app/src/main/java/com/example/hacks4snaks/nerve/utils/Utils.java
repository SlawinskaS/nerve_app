package com.example.hacks4snaks.nerve.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hacks4snaks.nerve.R;

/**
 * Created by fglow on 09.03.2018.
 */

public class Utils {
    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("Nerve", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }
    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("Nerve", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }
    public boolean addToArray(String arrayName, String newElement, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("Nerve", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        int size = prefs.getInt(arrayName + "_size", 0);
        editor.putString(arrayName + "_" + size, newElement);
        editor.putInt(arrayName +"_size", size + 1);
        return editor.commit();
    }

    public ImageView getSeizureIcon(int height, int width, Context mContext) {
        Resources r = mContext.getResources();
        ImageView icon = new ImageView(mContext);
        icon.setImageResource(R.drawable.icon_pulse_red);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        icon.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, r.getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, r.getDisplayMetrics())));
        return icon;
    }
    public ImageView getFoodIcon(int height, int width, Context mContext) {
        Resources r = mContext.getResources();
        ImageView icon = new ImageView(mContext);
        icon.setImageResource(R.drawable.icon_food_filled_blue);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        icon.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, r.getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, r.getDisplayMetrics())));
        return icon;
    }
    public ImageView getMedicineIcon(int height, int width, Context mContext) {
        Resources r = mContext.getResources();
        ImageView icon = new ImageView(mContext);
        icon.setImageResource(R.drawable.icon_medicine_blue);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        icon.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, r.getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, r.getDisplayMetrics())));
        return icon;
    }
}
