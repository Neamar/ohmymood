package fr.neamar.ohmymood.broadcast;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import fr.neamar.ohmymood.db.DBHelper;

public class RegisterMood extends BroadcastReceiver {
    public static final String TAG = "RegisterMood";
    public static final String BROADCAST = "fr.neamar.ohmymood.register_mood";

    @Override
    public void onReceive(Context context, Intent intent) {
        int moodId = intent.getIntExtra("mood", -1);

        if (moodId != -1) {

            final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(DisplayNotification.NOTIFICATION_ID);

            Log.i(TAG, "Current mood: " + moodId);

            DBHelper.insertMood(context, moodId);
        }


        // Display notification once again later.
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent displayIntent = new Intent(context, DisplayNotification.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, displayIntent, 0);

        int delay = 1000 * 60 * 60 * 4;
        Log.i(TAG, "Registering new notification in " + (delay / 1000 / 60) + " minutes.");
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + delay, alarmIntent);
    }
}