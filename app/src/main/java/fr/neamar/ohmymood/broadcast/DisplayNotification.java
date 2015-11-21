package fr.neamar.ohmymood.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import fr.neamar.ohmymood.R;

public class DisplayNotification extends BroadcastReceiver {
    public static final String TAG = "DisplayNotification";
    public static final String BROADCAST = "fr.neamar.ohmymood.display_notification";

    public static final int NOTIFICATION_ID = 1;
    public static final int[] smileysIds = new int[]{
            R.id.smiley1,
            R.id.smiley2,
            R.id.smiley3,
            R.id.smiley4,
            R.id.smiley5
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Displaying notification");

        // Display notification!
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);

        Intent deleteIntent = new Intent(RegisterMood.BROADCAST);
        PendingIntent pendingDeleteIntent = PendingIntent.getBroadcast(context, -1, deleteIntent, 0);

        Notification notification = new NotificationCompat.Builder(context)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setCategory(NotificationCompat.CATEGORY_SOCIAL)
                .setContent(remoteViews)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDeleteIntent(pendingDeleteIntent)
                .build();

        for (int i = 0; i < smileysIds.length; i++) {
            Intent voteIntent = new Intent(RegisterMood.BROADCAST);
            voteIntent.putExtra("mood", i + 1);

            PendingIntent pendingVoteIntent = PendingIntent.getBroadcast(context, i, voteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(smileysIds[i], pendingVoteIntent);
        }

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}