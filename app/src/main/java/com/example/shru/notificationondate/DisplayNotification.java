package com.example.shru.notificationondate;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;


public class DisplayNotification extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //---get the notification ID for the notification;
        // passed in by the MainActivity---
        int notifID = getIntent().getExtras().getInt("NotifID");

        //---PendingIntent to launch activity if the user selects
        // the notification---
        Intent i = new Intent("com.example.shru.AlarmDetails");
        i.putExtra("NotifID", notifID);


        CharSequence from = "AlarmManager - Time's up!";
        CharSequence message = "This is your alert, courtesy of the AlarmManager";
       /* notif.setLatestEventInfo(this, from, message, detailsIntent);*/

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.notification);
        builder.setContentTitle(from);
        builder.setContentText(message);
        PendingIntent detailsIntent =
                PendingIntent.getActivity(this, 0, i,  PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Notification notif = new Notification(
                R.drawable.notification,
                "Time's up!",
                System.currentTimeMillis());


        builder.setContentIntent(detailsIntent);

        //---100ms delay, vibrate for 250ms, pause for 100 ms and
        // then vibrate for 500ms---
        notif.vibrate = new long[] { 100, 250, 100, 500};
       /* nm.notify(notifID, notif);*/
        nm.notify(1,builder.build());
        //---destroy the activity---
        finish();
    }

}