package com.knighenko.sweetvinegar.pushy;

import me.pushy.sdk.Pushy;
import android.content.Intent;
import android.graphics.Color;
import android.content.Context;
import android.app.PendingIntent;
import android.media.RingtoneManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import androidx.core.app.NotificationCompat;

import com.knighenko.sweetvinegar.activities.AdvertisementActivity;
import com.knighenko.sweetvinegar.activities.MainActivity;

public class PushReceiver extends BroadcastReceiver {
    private static int notificationId = 2;
    public static final String CHANNEL_1 = "NotificationChannel";
    @Override
    public void onReceive(Context context, Intent intent) {
        String advTitle = "MyApp";
        String advUrl = "Test notification";

        // Attempt to extract the "message" property from the payload: {"message":"Hello World!"}

        if (intent.getStringExtra("title") != null) {
            advTitle = intent.getStringExtra("title");
            advUrl = intent.getStringExtra("url");
        }


        Intent advIntent = new Intent(context,
                AdvertisementActivity.class);
        advIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        advIntent.putExtra("urlAdv", advUrl);
        advIntent.putExtra("title", advTitle);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                notificationId, advIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Prepare a notification with vibration, sound and lights
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(advTitle)
                .setContentText(advUrl)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setLights(Color.RED, 1000, 1000)
                .setVibrate(new long[]{0, 400, 250, 400})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);

        // Automatically configure a Notification Channel for devices running Android O+
        Pushy.setNotificationChannel(builder, context);

        // Get an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        // Build the notification and display it
        notificationManager.notify(notificationId, builder.build());
        notificationId++;
    }
}