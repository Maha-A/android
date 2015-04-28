package com.drtms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.drtms.NotificationService;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
    // When our Alaram time is triggered , this method will be excuted (onReceive)
    // We're invoking a service in this method which shows Notification to the User
     Intent myIntent = new Intent(context, NotificationService.class);
     context.startService(myIntent);
   }

} 