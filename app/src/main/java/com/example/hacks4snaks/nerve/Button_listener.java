package com.example.hacks4snaks.nerve;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by filipp on 7/28/2016.
 */
public class Button_listener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(intent.getExtras().getInt("id"));
        Toast.makeText(context, "Conect to NERVE!!", Toast.LENGTH_LONG).show();
        Log.e("sadasd","działa");
    }
}
