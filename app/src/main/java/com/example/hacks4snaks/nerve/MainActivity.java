package com.example.hacks4snaks.nerve;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File folder = new File(Environment.getExternalStorageDirectory() + "/com.hacks4snacks.nerve");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            Log.e("appdir success ", Environment.getExternalStorageDirectory() + "/com.hacks4snacks.nerve");
        } else {
            Log.e("appdir failure ", Environment.getExternalStorageDirectory() + "/com.hacks4snacks.nerve");
        }


        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);

        remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);
        remoteViews.setImageViewResource(R.id.notif_icon,R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.notif_title,"TEXT");
        remoteViews.setProgressBar(R.id.progressBar,100,40,true);

        notification_id = (int) System.currentTimeMillis();

        Intent button_intent = new Intent("button_click");
        button_intent.putExtra("id",notification_id);
        PendingIntent button_pending_event = PendingIntent.getBroadcast(context,notification_id,
                button_intent,0);

        remoteViews.setOnClickPendingIntent(R.id.button,button_pending_event);

        Intent notification_intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notification_intent,0);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setCustomBigContentView(remoteViews)
                .setContentIntent(pendingIntent);

        notificationManager.notify(notification_id,builder.build());

         SharedPref();
    }
    private void SharedPref()
    {
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


    public void onClickExit(View view) {
        finish();
        System.exit(0);
    }
}
