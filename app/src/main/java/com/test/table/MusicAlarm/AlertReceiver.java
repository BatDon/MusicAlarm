package com.test.table.MusicAlarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import static com.test.table.MusicAlarm.MainActivity.START_SERVICE;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());

        Intent serviceIntent = new Intent(context, ExampleService.class);
        //serviceIntent.putExtra("inputExtra", input);
        serviceIntent.setAction(START_SERVICE);

        ContextCompat.startForegroundService(context, serviceIntent);
    }
}
