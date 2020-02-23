package com.test.table.MusicAlarm;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.test.table.musicalarm.R;

import static com.test.table.MusicAlarm.App.CHANNEL_ID;
import static com.test.table.MusicAlarm.MainActivity.STOP_SERVICE;

//import static com.test.table.foregroundserviceexample.App.CHANNEL_ID;
//import static com.test.table.foregroundserviceexample.MainActivity.STOP_SERVICE;
//import static com.codinginflow.foregroundserviceexample.App.CHANNEL_ID;


public class ExampleService extends Service {
    MediaPlayer myPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        myPlayer = MediaPlayer.create(this, R.raw.sail_awolnation);
        myPlayer.setLooping(true); // Set looping
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // String input = intent.getStringExtra("inputExtra");
        Log.i("ExampleService",intent.getAction());
        if(intent.getAction().equals(STOP_SERVICE)){
            myPlayer.stop();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                stopForeground(true);
                stopSelf();
            }
            else{
                stopSelf();
            }

        }
        else {

            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Example Service")
                    //.setContentText(input)
                    .setSmallIcon(R.drawable.ic_music)
                    .setContentIntent(pendingIntent)
                    .build();

            startForeground(1, notification);
            myPlayer.start();

            //do heavy work on a background thread
            //stopSelf();


        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
