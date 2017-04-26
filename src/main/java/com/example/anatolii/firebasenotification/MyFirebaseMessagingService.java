package com.example.anatolii.firebasenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by anatolii on 26.04.17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "My FireBase";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("FireBase")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody());

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(101, notification);
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        Log.e(TAG, "getTitle: " + remoteMessage.getNotification().getTitle());
        Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }
}